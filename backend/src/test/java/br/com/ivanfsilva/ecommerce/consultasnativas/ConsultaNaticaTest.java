package br.com.ivanfsilva.ecommerce.consultasnativas;

import br.com.ivanfsilva.ecommerce.EntityManagerTest;
import org.junit.Test;

import javax.persistence.Query;
import java.util.List;

public class ConsultaNaticaTest extends EntityManagerTest {

    @Test
    public void executarSQL() {
        String sql = "select id, nome from produto";
        Query query = entityManager.createNativeQuery(sql);

        List<Object[]> lista = query.getResultList();

        lista.stream().forEach(arr -> System.out.println(
                String.format("Produto => ID: %s, Nome: %s", arr[0], arr[1])));
    }
}
