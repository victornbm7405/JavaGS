package com.renovatec.dao;

import com.renovatec.config.DatabaseConfig;
import com.renovatec.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void excluirProduto(int id) {
        String query = "DELETE FROM Produto WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao excluir produto no banco de dados.");
            e.printStackTrace();
        }
    }


    public void atualizarProduto(Produto produto) {
        String query = "UPDATE Produto SET nome = ?, tipo = ?, consumoEnergetico = ?, custoMensal = ? WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getTipo());
            stmt.setDouble(3, produto.getConsumoEnergetico());
            stmt.setDouble(4, produto.getCustoMensal());
            stmt.setInt(5, produto.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar produto no banco de dados.");
            e.printStackTrace();
        }
    }

    // Método para listar produtos
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String query = "SELECT * FROM Produto";

        try (Connection connection = DatabaseConfig.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setTipo(rs.getString("tipo"));
                produto.setConsumoEnergetico(rs.getDouble("consumoEnergetico"));
                produto.setCustoMensal(rs.getDouble("custoMensal"));

                produtos.add(produto);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos do banco de dados.");
            e.printStackTrace();
        }

        return produtos;
    }

    // Método para cadastrar um produto
    public void cadastrarProduto(Produto produto) throws Exception {
        String query = "INSERT INTO Produto (nome, tipo, consumoEnergetico, custoMensal) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getTipo());
            stmt.setDouble(3, produto.getConsumoEnergetico());
            stmt.setDouble(4, produto.getCustoMensal());

            stmt.executeUpdate();
        }
    }

    // Método main para testar o DAO diretamente
    public static void main(String[] args) {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        System.out.println("=== Testando ProdutoDAO ===");

        // Testar cadastro de um novo produto
        try {
            Produto novoProduto = new Produto();
            novoProduto.setNome("Ventilador");
            novoProduto.setTipo("Eletrodoméstico");
            novoProduto.setConsumoEnergetico(50.5);
            novoProduto.setCustoMensal(30.0);

            produtoDAO.cadastrarProduto(novoProduto);
            System.out.println("Produto cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto.");
            e.printStackTrace();
        }

        // Listar produtos
        List<Produto> produtos = produtoDAO.listarProdutos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado no banco de dados.");
        } else {
            produtos.forEach(produto -> {
                System.out.println(
                        "ID: " + produto.getId() +
                                ", Nome: " + produto.getNome() +
                                ", Tipo: " + produto.getTipo() +
                                ", Consumo: " + produto.getConsumoEnergetico() +
                                ", Custo: " + produto.getCustoMensal()
                );
            });
        }
    }
}
