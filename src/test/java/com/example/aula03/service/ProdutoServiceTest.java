package com.example.aula03.service;

import com.example.aula03.entity.Produto;
import com.example.aula03.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @InjectMocks
    ProdutoService produtoService;

    @Mock
    ProdutoRepository produtoRepository;



    @Test
    void deveAdicionarProduto() {

        Produto produto =new Produto(1l, "Mesa", "Mesa de Madeira", 1);
        //when(produtoRepository.save(any())).thenReturn(produto);
        doReturn(produto).when(produtoRepository).save(any());
        produto = produtoService.adicionarProduto(produto);

        assertEquals(1l, produto.getId());

    }

    @Test
    void deveExcluirUmProduto() {

        Produto produtoRetornado =new Produto(1l, "Mesa", "Mesa de Madeira", 1);
        //var produto = produtoRetornado;
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produtoRetornado));
        doNothing().when(produtoRepository).delete(any());
        Produto produto = produtoService.removerProduto(1l);

        assertEquals(1l, produto.getId());

    }
}