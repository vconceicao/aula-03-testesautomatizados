package com.example.aula03.service;

import com.example.aula03.entity.Produto;
import com.example.aula03.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto adicionarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }
    public Produto buscarProdutoPorId(Long id){
       Optional<Produto> optionalProduto = produtoRepository.findById(id);
        return optionalProduto.orElseThrow(() ->
                new IllegalArgumentException("Pro"));
    }


    public Produto removerProduto(Long id){
        Produto produto = buscarProdutoPorId(id);
        produtoRepository.delete(produto);
        return produto;
    }


}
