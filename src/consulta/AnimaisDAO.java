package consulta;

import app.Dependencias;
import java.util.List;
import repository.AnimalRepository;

public class AnimaisDAO {

    private final AnimalRepository repository = Dependencias.animalRepository();

    public List<Animais> listar() {
        return repository.listar();
    }

    public void excluir(int id) {
        repository.excluir(id);
    }

    public void adotar(int idAnimal) {

        Animais animal = null;

        for (Animais item : repository.listar()) {
            if (item.getId() == idAnimal) {
                animal = item;
                break;
            }
        }

        if (animal == null) {
            throw new IllegalArgumentException("Animal não encontrado.");
        }

        animal.setAdotado("Adotado");
        repository.atualizarAdocao(animal);
    }

    public void atualizarAdocao(Animais animal) {
        repository.atualizarAdocao(animal);
    }
}
