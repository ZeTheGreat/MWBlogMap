package DAO.Interface;

import Model.Categoria;

import java.util.ArrayList;

public interface CategoriaDAO {
    Categoria buscarPorNome(String nomecat);
    Categoria buscarPorID(long id);
    ArrayList<Categoria> bucarTodas();
    void inserirCategoria(Categoria cat);
    void alterarCategoria(Categoria catv, Categoria catn);
    void excluirCategoria(Categoria cat);
}
