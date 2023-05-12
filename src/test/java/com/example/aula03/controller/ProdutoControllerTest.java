package com.example.aula03.controller;

import com.example.aula03.entity.Produto;
import com.example.aula03.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
class ProdutoControllerTest {

    @InjectMocks
    private ProdutoController produtoController;

    @Mock
    private ProdutoService produtoService;

    @Autowired
    private MockMvc mockMvc;
    private Produto produto;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
        produto = new Produto(1l, "Caderno", "teste", 1l);

    }

    @Test
    @DisplayName("Deve adicionar produto utilizando MockMvc")
    void deveAdicionarProdutoMockMvc() throws Exception {

        String produtoJson = new ObjectMapper().writeValueAsString(produto);
        doReturn(produto).when(produtoService).adicionarProduto(any());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(produtoJson))
                        .andDo(print())
                        .andExpect(MockMvcResultMatchers.content().json(produtoJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(produtoService).adicionarProduto(produto);
        assertEquals(200, mvcResult.getResponse().getStatus());

        System.out.println(mvcResult.getResponse().getContentAsString());
        assertEquals(produtoJson, mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Deve remover produto utilizando MockMvc")
    void deveRemoverProdutoMockMvc() throws Exception {

      doReturn(produto).when(produtoService).removerProduto(1l);

        mockMvc.perform(delete("/api/v1/produtos/{id}",1 ))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Caderno"));

    }

    @Test
    @DisplayName("Deve listar produtos, utilizando MockMvc")
    void deveListarTodosOsProdutosMockMVC() throws Exception {
        List<Produto> produtos1 = List.of(new Produto(1l, "teste", "teste", 1l));

        doReturn(produtos1).when(produtoService).listarProdutos();

        mockMvc.perform(get("/api/v1/produtos"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1));

        verify(produtoService).listarProdutos();
    }
}