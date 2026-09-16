package repository;

import consulta.Adotantes;
import java.util.Optional;

public interface AdotanteRepository {

    int salvar(Adotantes adotante);

    Optional<Adotantes> buscarPorId(int id);
}
