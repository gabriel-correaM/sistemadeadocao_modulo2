package service;

import consulta.Animais;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import repository.FakeAnimalRepository;

import static org.junit.Assert.*;

public class AnimalServiceTest {

    private FakeAnimalRepository repository;
    private AnimalService service;

    @Before
    public void setUp() {
        repository = new FakeAnimalRepository();
        service = new AnimalService(repository);
    }

    @Test
    public void deveCadastrarAnimalComDadosValidos() {
        int id = service.cadastrar("  NomeCachorro  ", "  Vira-lata  ", "  Abrigo Teste  ");

        assertEquals(1, id);

        List<Animais> animais = repository.listar();

        assertEquals(1, animais.size());
        assertEquals("NomeCachorro", animais.get(0).getNome());
        assertEquals("Vira-lata", animais.get(0).getRaca());
        assertEquals("Abrigo Teste", animais.get(0).getAbrigo());
        assertEquals("Não", animais.get(0).getAdotado());
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveCadastrarAnimalSemNome() {
        service.cadastrar("   ", "Vira-lata", "Abrigo Teste");
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveCadastrarAnimalSemRaca() {
        service.cadastrar("NomeCachorro", "   ", "Abrigo Teste");
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveCadastrarAnimalSemAbrigo() {
        service.cadastrar("NomeCachorro", " Vira-lata ", "   ");
    }
}
