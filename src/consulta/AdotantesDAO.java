package consulta;

import app.Dependencias;
import java.util.Optional;
import repository.AdotanteRepository;
import service.AdotanteService;


public class AdotantesDAO {

    private final AdotanteService service = Dependencias.adotanteService();
    private final AdotanteRepository repository = Dependencias.adotanteRepository();

    public int salvar(String nome, String senha, String telefone, String email) {
        return service.cadastrar(nome, senha, telefone, email);
    }

    public Adotantes buscarPorId(int id) {
        Optional<Adotantes> adotante = repository.buscarPorId(id);
        return adotante.orElse(null);
    }
}
