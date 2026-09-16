package service;

import consulta.Adotantes;
import java.util.Optional;
import repository.AdotanteRepository;

public class AdotanteService {

    private final AdotanteRepository repository;

    public AdotanteService(AdotanteRepository repository) {
        this.repository = repository;
    }

    public int cadastrar(String nome, String senha, String telefone, String email) {

        validar(nome, "Nome");
        validar(senha, "Senha");
        validar(telefone, "Telefone");
        validar(email, "E-mail");

        Adotantes adotante = new Adotantes();

        adotante.setNome(nome.trim());
        adotante.setSenha(senha.trim());
        adotante.setTelefone(telefone.trim());
        adotante.setEmail(email.trim());

        return repository.salvar(adotante);
    }

    public Optional<Adotantes> buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    private void validar(String valor, String campo) {

        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " é obrigatório.");
        }
    }
}
