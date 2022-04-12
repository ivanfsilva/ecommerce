package br.com.ivanfsilva.ecommerce.conhecendoentitymanager;

import br.com.ivanfsilva.ecommerce.EntityManagerTest;
import br.com.ivanfsilva.ecommerce.model.Cliente;
import br.com.ivanfsilva.ecommerce.model.Pedido;
import br.com.ivanfsilva.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

public class CallbacksTest extends EntityManagerTest {

    @Test
    public void acionarCallbacks() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO);

        entityManager.getTransaction().begin();

        entityManager.persist(pedido);
        entityManager.flush();

        pedido.setStatus(StatusPedido.PAGO);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao.getDataCriacao());
        Assert.assertNotNull(pedidoVerificacao.getDataUltimaAtualizacao());
    }
}
