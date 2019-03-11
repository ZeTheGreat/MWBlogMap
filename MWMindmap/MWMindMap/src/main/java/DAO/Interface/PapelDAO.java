package DAO.Interface;

import Model.Papel;
import Model.PapelEnum;
import Model.Usuario;

import java.util.ArrayList;

public interface PapelDAO {
    Papel BuscarporId(Long id);
    ArrayList<Papel> Buscartodos();
    void altrarPapel(Papel p, Papel n);
}
