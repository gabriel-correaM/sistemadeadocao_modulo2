package repository;

import consulta.Animais;
import java.util.List;

public interface AnimalRepository {

    int salvar(Animais animal);

    List<Animais> listar();

    void excluir(int id);

    void atualizarAdocao(Animais animal);
}
