package br.com.ivanfsilva.ecommerce.iniciandocomjpa;

import br.com.ivanfsilva.ecommerce.model.Produto;
import org.junit.Test;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    public void abrirEfecharTransacao() {
//        Produto produto = new Produto(); // Somente para o método não mostrar erros.

        entityManager.getTransaction().begin();

//        entityManager.persist(produto);
//        entityManager.merge(produto);
//        entityManager.remove(produto);

        entityManager.getTransaction().commit();
    }
    }
}
