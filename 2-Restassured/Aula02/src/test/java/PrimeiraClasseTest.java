import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrimeiraClasseTest {

    @Test
    public void testPrimeiroMetodo() {
        System.out.println("Meu primeiro método de teste");
    }

    @Test
    @DisplayName("US12341 - TC12 - Meu segundo método de teste")
    public void testSegundoMetodo() {
        System.out.println("Meu segundo método de teste");
    }
}
