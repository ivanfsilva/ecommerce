package br.com.ivanfsilva.ecommerce.jpql;

import br.com.ivanfsilva.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class FuncoesStringsTest extends EntityManagerTest {

    @Test
    public void aplicarFuncao() {
        // concat, length, locate, substring, lower, upper, trim

        String jpql = "select c.nome, length(trim(c.nome)) from Categoria c " +
                " where substring(c.nome, 1, 1) = 'N'";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + " - " + arr[1]));
    }
}
