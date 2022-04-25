package br.com.ivanfsilva.ecommerce.detalhesimportantes;

import br.com.ivanfsilva.ecommerce.EntityManagerTest;
import br.com.ivanfsilva.ecommerce.model.Cliente;
import org.junit.Test;

public class ValidacaoTest extends EntityManagerTest {

    @Test
    public void validarCliente() {
        entityManager.getTransaction().begin();

        Cliente cliente = new Cliente();
        entityManager.merge(cliente);

        entityManager.getTransaction().commit();
    }
}
