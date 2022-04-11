package br.com.ivanfsilva.ecommerce.mapeamentobasico;

import br.com.ivanfsilva.ecommerce.EntityManagerTest;
import br.com.ivanfsilva.ecommerce.model.Categoria;
import org.junit.Assert;
import org.junit.Test;

public class EstrategiaChavePrimariaTest extends EntityManagerTest {

    @Test
    public void testarEstrategiaAuto() {
        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônicos");

        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
        Assert.assertNotNull(categoriaVerificacao);
    }
}
