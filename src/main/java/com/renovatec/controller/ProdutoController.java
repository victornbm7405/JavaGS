package com.renovatec.controller;

import com.renovatec.dao.ProdutoDAO;
import com.renovatec.model.Produto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/produtos")
public class ProdutoController {
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    // GET - Listar Produtos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProdutos() {
        System.out.println("Recebida requisição GET para listar produtos");
        try {
            List<Produto> produtos = produtoDAO.listarProdutos();
            System.out.println("Produtos encontrados: " + produtos);
            return Response.ok(produtos).build();
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

    // PUT - Atualizar Produto
    @PUT
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

    // DELETE - Excluir Produto
    @DELETE
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

    // OPTIONS - Pré-flight para CORS
    @OPTIONS
    @Path("{path:.*}")
    public Response handleOptions() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "http://localhost:3000")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .build();
}
}
