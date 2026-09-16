package app;

import repository.AdotanteRepository;
import repository.AnimalRepository;
import repository.jdbc.JdbcAdotanteRepository;
import repository.jdbc.JdbcAnimalRepository;
import service.AdocaoService;
import service.AdotanteService;
import service.AnimalService;

public final class Dependencias {

    private Dependencias() {
    }

    public static AdotanteRepository adotanteRepository() {
        return new JdbcAdotanteRepository();
    }

    public static AnimalRepository animalRepository() {
        return new JdbcAnimalRepository();
    }

    public static AdotanteService adotanteService() {
        return new AdotanteService(adotanteRepository());
    }

    public static AnimalService animalService() {
        return new AnimalService(animalRepository());
    }

    public static AdocaoService adocaoService() {
        return new AdocaoService(animalRepository());
    }
}
