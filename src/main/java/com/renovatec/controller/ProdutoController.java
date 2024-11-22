package com.renovatec.controller;

import com.renovatec.dao.ProdutoDAO;
import com.renovatec.model.Produto;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/produtos")
public class ProdutoController {

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirProduto(@PathParam("id") int id) {
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


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarProduto(@PathParam("id") int id, Produto produto) {
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


    private ProdutoDAO produtoDAO = new ProdutoDAO();

    // GET - Listar Produtos (já existente)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProdutos() {
        try {
            return Response.ok(produtoDAO.listarProdutos()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar produtos.")
                    .build();
        }
    }

    // POST - Cadastrar Produto
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarProduto(Produto produto) {
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
}
