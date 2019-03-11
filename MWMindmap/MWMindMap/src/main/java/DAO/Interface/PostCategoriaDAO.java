package DAO.Interface;

import Model.Categoria;
import Model.Post;

import java.util.ArrayList;

public interface PostCategoriaDAO {
    void inserirCategoriadoPost(Post p, ArrayList<Categoria> cats);
    void excluirRelacaoPostCat(Post p);
    void excluirRelacaoCatPost(Categoria cat);
    void alterarCategoriasdoPost(Post p, ArrayList<Categoria> cats);
    ArrayList<Post> bucasPostsPorCategoria(Categoria cat);
    ArrayList<Categoria> buscarCategoriaPorPost(Post post);
}
