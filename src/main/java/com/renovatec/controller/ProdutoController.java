package com.renovatec.controller;

import com.renovatec.dao.ProdutoDAO;
import com.renovatec.model.Produto;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/produtos") // Endpoint base
public class ProdutoController {

    private ProdutoDAO produtoDAO = new ProdutoDAO();

    @GET // Método para listar todos os produtos
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProdutos() {
        System.out.println("Recebida requisição GET para listar produtos");
        try {
            List<Produto> produtos = produtoDAO.listarProdutos();
            return Response.ok(produtos).build(); // Retorna lista de produtos
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar produtos.")
                    .build();
        }
    }

    @POST // Método para cadastrar um novo produto
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarProduto(Produto produto) {
        System.out.println("Recebida requisição POST para cadastrar produto: " + produto);
        try {
            produtoDAO.cadastrarProduto(produto);
            return Response.status(Response.Status.CREATED)
                    .entity("Produto cadastrado com sucesso!")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar produto.")
                    .build();
        }
    }

    @PUT // Método para atualizar um produto existente
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarProduto(@PathParam("id") int id, Produto produto) {
        System.out.println("Recebida requisição PUT para atualizar produto com ID: " + id);
        try {
            produto.setId(id);
            produtoDAO.atualizarProduto(produto);
            return Response.ok("Produto atualizado com sucesso!").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar produto.")
                    .build();
        }
    }

    @DELETE // Método para excluir um produto existente
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirProduto(@PathParam("id") int id) {
        System.out.println("Recebida requisição DELETE para excluir produto com ID: " + id);
        try {
            produtoDAO.excluirProduto(id);
            return Response.ok("Produto excluído com sucesso!").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir produto.")
                    .build();
        }
    }
}
