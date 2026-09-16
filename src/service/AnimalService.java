package service;

import consulta.Animais;
import java.util.List;
import repository.AnimalRepository;

public class AnimalService {

    private final AnimalRepository repository;

    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }

    public int cadastrar(String nome, String raca, String abrigo) {

        validar(nome, "Nome");
        validar(raca, "Raça");
        validar(abrigo, "Abrigo");

        Animais animal = new Animais();

        animal.setNome(nome.trim());
        animal.setRaca(raca.trim());
        animal.setAbrigo(abrigo.trim());
        animal.setAdotado("Não");
        animal.setAdotante_id(0);

        return repository.salvar(animal);
    }

    public List<Animais> listar() {
        return repository.listar();
    }

    public void excluir(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("ID do animal inválido.");
        }

        repository.excluir(id);
    }

    private void validar(String valor, String campo) {

        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " é obrigatório.");
        }
    }
}
