package consulta;

import app.Dependencias;
import java.util.List;
import repository.AnimalRepository;
import service.AnimalService;

public class ListaConsulta {

    private static final AnimalRepository REPOSITORY =
            Dependencias.animalRepository();

    private static final AnimalService SERVICE =
            Dependencias.animalService();

    public static List<Animais> Listar() {
        return REPOSITORY.listar();
    }

    public static void Adicionar(Animais animal) {
        SERVICE.cadastrar(
                animal.getNome(),
                animal.getRaca(),
                animal.getAbrigo());
    }

    public static void excluir(int id) {
        SERVICE.excluir(id);
    }

    public static void adotar(int idAnimal) {

        Animais animal = null;

        for (Animais item : REPOSITORY.listar()) {
            if (item.getId() == idAnimal) {
                animal = item;
                break;
            }
        }

        if (animal == null) {
            throw new IllegalArgumentException("Animal não encontrado.");
        }

        animal.setAdotado("Adotado");
        REPOSITORY.atualizarAdocao(animal);
    }
}
