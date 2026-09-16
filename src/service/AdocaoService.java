package service;

import consulta.Animais;
import java.util.List;
import repository.AnimalRepository;

public class AdocaoService {

    private final AnimalRepository repository;

    public AdocaoService(AnimalRepository repository) {
        this.repository = repository;
    }

    public void adotar(int animalId, int adotanteId) {

        if (animalId <= 0 || adotanteId <= 0) {
            throw new IllegalArgumentException(
                    "Animal e adotante devem ser válidos.");
        }

        List<Animais> animais = repository.listar();
        Animais animalEncontrado = null;

        for (Animais animal : animais) {
            if (animal.getId() == animalId) {
                animalEncontrado = animal;
                break;
            }
        }

        if (animalEncontrado == null) {
            throw new IllegalArgumentException("Animal não encontrado.");
        }

        if ("Adotado".equals(animalEncontrado.getAdotado())) {
            throw new IllegalStateException("Este animal já foi adotado.");
        }

        animalEncontrado.setAdotado("Adotado");
        animalEncontrado.setAdotante_id(adotanteId);

        repository.atualizarAdocao(animalEncontrado);
    }
}
