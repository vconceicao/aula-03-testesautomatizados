package com.example.aula03;

import com.example.aula03.entity.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.DisplayName.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProdutoIntegracaoTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Deve adicionar um produto - Teste Integracao")
    public void salvarProduto() throws Exception {
        Produto produto = new Produto(1l, "Vinicius", "teste", 1l);
        String produtoJson = new ObjectMapper().writeValueAsString(produto);
        mockMvc.perform(post("/api/v1/produtos")
                .content(produtoJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


        mockMvc.perform(get("/api/v1/produtos")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[0]").isNotEmpty())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Deve remover um produto - Teste Integracao")
    public void removerProduto() throws Exception {
        Produto produto = new Produto(1l, "Vinicius", "teste", 1l);
        String produtoJson = new ObjectMapper().writeValueAsString(produto);

        mockMvc.perform(post("/api/v1/produtos")
                        .content(produtoJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());



        mockMvc.perform(delete("/api/v1/produtos/{id}",1 ))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(get("/api/v1/produtos")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$").isEmpty())
                .andExpect(status().isOk());

    }






}
