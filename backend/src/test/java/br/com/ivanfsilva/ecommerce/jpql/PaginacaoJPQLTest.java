package br.com.ivanfsilva.ecommerce.jpql;

import br.com.ivanfsilva.ecommerce.EntityManagerTest;
import br.com.ivanfsilva.ecommerce.model.Categoria;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class PaginacaoJPQLTest extends EntityManagerTest {

    @Test
    public void paginarResultados() {
        String jpql = "select c from Categoria c order by c.nome";

        TypedQuery<Categoria> typedQuery = entityManager.createQuery(jpql, Categoria.class);

        // FIRST_RESULT = MAX_RESULTS * (pagina - 1)
        typedQuery.setFirstResult(2); // número da página; primeira página é zero
        typedQuery.setMaxResults(2); // dois registros por página

        List<Categoria> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(c -> System.out.println(c.getId() + ", " + c.getNome()));
    }
}
