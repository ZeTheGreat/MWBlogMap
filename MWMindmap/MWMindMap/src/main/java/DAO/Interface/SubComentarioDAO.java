package DAO.Interface;

import Model.Comentario;
import Model.SubComentario;
import Model.Usuario;

import java.util.ArrayList;

public interface SubComentarioDAO {
    void apagarComentariosFilhos(Comentario comentario);
    ArrayList<SubComentario> buscarComentariosFilhosPorComentarioPai(Comentario comentario);
    void alterarSubComentario(SubComentario velho, SubComentario novo);
    void excluirSubComentario(SubComentario subcomentario);
    void adicionarSubComentario(SubComentario subcomentario);
    Usuario buscarUsuDoSubComentario(SubComentario subcomentario);
}
