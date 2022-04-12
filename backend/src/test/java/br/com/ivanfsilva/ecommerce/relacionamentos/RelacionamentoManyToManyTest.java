package br.com.ivanfsilva.ecommerce.relacionamentos;

import br.com.ivanfsilva.ecommerce.EntityManagerTest;
import br.com.ivanfsilva.ecommerce.model.Categoria;
import br.com.ivanfsilva.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RelacionamentoManyToManyTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento() {
        Produto produto = entityManager.find(Produto.class, 1);
        Categoria categoria = entityManager.find(Categoria.class, 1);

        entityManager.getTransaction().begin();
//        categoria.setProdutos(Arrays.asList(produto)); // não é o owner da relação, toma uma exception
        produto.setCategorias(Arrays.asList(categoria)); // aqui é o owner da relação, então não dá erro
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
        Assert.assertFalse(categoriaVerificacao.getProdutos().isEmpty());
    }
}
