package repository;

import consulta.Animais;
import java.util.ArrayList;
import java.util.List;

public class FakeAnimalRepository implements AnimalRepository {

    private final List<Animais> animais = new ArrayList<>();

    @Override
    public int salvar(Animais animal) {
        int id = animais.size() + 1;
        animal.setId(id);   
        animais.add(animal);
        return id;
    }

    @Override
    public List<Animais> listar() {
        return new ArrayList<>(animais);
    }

    @Override
    public void excluir(int id) {
        animais.removeIf(animal -> animal.getId() == id);
    }

    @Override
    public void atualizarAdocao(Animais animal) {
        
    }
}
