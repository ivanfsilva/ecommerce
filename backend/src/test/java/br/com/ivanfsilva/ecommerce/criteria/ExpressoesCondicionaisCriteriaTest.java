package br.com.ivanfsilva.ecommerce.criteria;

import br.com.ivanfsilva.ecommerce.EntityManagerTest;
import br.com.ivanfsilva.ecommerce.model.Cliente;
import br.com.ivanfsilva.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ExpressoesCondicionaisCriteriaTest extends EntityManagerTest {

    @Test
    public void usarMaiorMenor() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        criteriaQuery.where(
                criteriaBuilder.greaterThanOrEqualTo(
                        root.get("preco"), "799"),
                criteriaBuilder.lessThanOrEqualTo(
                        root.get("preco"), "3500"));

        TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(p -> System.out.println(
                "ID: " + p.getId() + ", Nome: " + p.getNome() + ", Preço: " + p.getPreco()));
    }

    @Test
    public void usarIsEmpty() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.isEmpty(root.get("categorias")));

        TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Produto> lista = typedQuery.getResultList();
        Assert.assertTrue(lista.isEmpty());
    }

    @Test
    public void usarIsNull() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.isNull(root.get("foto")));
        // criteriaQuery.where(root.get(Produto_.foto).isNull());

        TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarExpressaoCondicionalLike() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        Root<Cliente> root = criteriaQuery.from(Cliente.class);

        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.like(root.get("nome"), "%a%"));

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Cliente> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }
}
