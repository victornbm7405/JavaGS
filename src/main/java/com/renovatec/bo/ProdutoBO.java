package com.renovatec.bo;

import com.renovatec.model.Produto;

public class ProdutoBO {

    public boolean validarProduto(Produto produto) {
        return produto.getNome() != null && !produto.getNome().isEmpty() &&
                produto.getTipo() != null && !produto.getTipo().isEmpty() &&
                produto.getCustoMensal() > 0 &&
                produto.getConsumoEnergetico() > 0;
    }
}
