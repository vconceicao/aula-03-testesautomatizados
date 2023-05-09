package com.example.aula03.controller;

import com.example.aula03.entity.Produto;
import com.example.aula03.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProdutoControllerTest {

    @InjectMocks
    private ProdutoController produtoController;

    @Mock
    private ProdutoService produtoService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
    }

    @Test
    void deveAdicionarProdutoMockMvc() throws Exception {

        Produto produto = new Produto(1l, "Caderno", "teste", 1l);
        String produtoJson = new ObjectMapper().writeValueAsString(produto);
        Mockito.doReturn(produto).when(produtoService).adicionarProduto(any());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(produtoJson)).andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.content().json(produtoJson))
                .andReturn();

        Mockito.verify(produtoService).adicionarProduto(produto);
        assertEquals(200, mvcResult.getResponse().getStatus());

        System.out.println(mvcResult.getResponse().getContentAsString());
        assertEquals(produtoJson, mvcResult.getResponse().getContentAsString());

    }
}