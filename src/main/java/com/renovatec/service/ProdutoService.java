package com.renovatec.service;

import com.renovatec.dao.ProdutoDAO;
import com.renovatec.model.Produto;

import java.util.List;

public class ProdutoService {
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public List<Produto> obterTodosProdutos() throws Exception {
        return produtoDAO.listarProdutos();
    }
}
