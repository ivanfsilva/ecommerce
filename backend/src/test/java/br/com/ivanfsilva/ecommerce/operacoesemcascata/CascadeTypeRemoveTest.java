package br.com.ivanfsilva.ecommerce.operacoesemcascata;

import br.com.ivanfsilva.ecommerce.EntityManagerTest;
import br.com.ivanfsilva.ecommerce.model.ItemPedido;
import br.com.ivanfsilva.ecommerce.model.ItemPedidoId;
import br.com.ivanfsilva.ecommerce.model.Pedido;
import br.com.ivanfsilva.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

public class CascadeTypeRemoveTest extends EntityManagerTest {

    @Test
    public void removerRelacaoProdutoCategoria() {
        Produto produto = entityManager.find(Produto.class, 1);

        Assert.assertFalse(produto.getCategorias().isEmpty());

        entityManager.getTransaction().begin();
        produto.getCategorias().clear();
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertTrue(produtoVerificacao.getCategorias().isEmpty());
    }

    // @Test
    public void removerPedidoEItens() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        entityManager.getTransaction().begin();
        entityManager.remove(pedido); // Necessário CascadeType.REMOVE no atributo "itens".
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNull(pedidoVerificacao);
    }

    // @Test
    public void removerItemPedidoEPedido() {
        ItemPedido itemPedido = entityManager.find(
                ItemPedido.class, new ItemPedidoId(1, 1));

        entityManager.getTransaction().begin();
        entityManager.remove(itemPedido); // Necessário CascadeType.REMOVE no atributo "pedido".
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, itemPedido.getPedido().getId());
        Assert.assertNull(pedidoVerificacao);
    }
}
