package com.example.aula03.controller;

import com.example.aula03.entity.Produto;
import com.example.aula03.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/produtos")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> adicionarProduto(@RequestBody Produto product) {
        Produto produtoSalva = produtoService.adicionarProduto(product);
        return ResponseEntity.ok().body(produtoSalva);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Produto> removerProduto(@PathVariable("id") Long id) {
        Produto produto = produtoService.removerProduto(id);
        return new ResponseEntity<>(produto, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
    }

}
