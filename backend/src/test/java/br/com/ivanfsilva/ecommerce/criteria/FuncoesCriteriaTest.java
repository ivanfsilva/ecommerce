package br.com.ivanfsilva.ecommerce.criteria;

import br.com.ivanfsilva.ecommerce.EntityManagerTest;
import br.com.ivanfsilva.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class FuncoesCriteriaTest extends EntityManagerTest {

    @Test
    public void aplicarFuncaoData() {
        // current_date, current_time, current_timestamp

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        Join<Pedido, Pagamento> joinPagamento = root.join("pagamento");
        Join<Pedido, PagamentoBoleto> joinPagamentoBoleto = criteriaBuilder
                .treat(joinPagamento, PagamentoBoleto.class);

        criteriaQuery.multiselect(
                root.get("id"),
                criteriaBuilder.currentDate(),
                criteriaBuilder.currentTime(),
                criteriaBuilder.currentTimestamp()
        );

        criteriaQuery.where(
                criteriaBuilder.between(criteriaBuilder.currentDate(),
                        root.get("dataCriacao").as(java.sql.Date.class),
                        joinPagamentoBoleto.get("dataVencimento").as(java.sql.Date.class)),
                criteriaBuilder.equal(root.get("status"), StatusPedido.AGUARDANDO)
        );

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(
                arr[0]
                        + ", current_date: " + arr[1]
                        + ", current_time: " + arr[2]
                        + ", current_timestamp: " + arr[3]));
    }

    @Test
    public void aplicarFuncaoString() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Cliente> root = criteriaQuery.from(Cliente.class);

        criteriaQuery.multiselect(
                root.get("nome"),
                criteriaBuilder.concat("Nome do cliente: ", root.get("nome")),
                criteriaBuilder.length(root.get("nome")),
                criteriaBuilder.locate(root.get("nome"), "a"),
                criteriaBuilder.substring(root.get("nome"), 1, 2),
                criteriaBuilder.lower(root.get("nome")),
                criteriaBuilder.upper(root.get("nome")),
                criteriaBuilder.trim(root.get("nome"))
        );

        criteriaQuery.where(criteriaBuilder.equal(
                criteriaBuilder.substring(root.get("nome"), 1, 1), "M"));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(
                arr[0]
                        + ", concat: " + arr[1]
                        + ", length: " + arr[2]
                        + ", locate: " + arr[3]
                        + ", substring: " + arr[4]
                        + ", lower: " + arr[5]
                        + ", upper: " + arr[6]
                        + ", trim: |" + arr[7] + "|"));
    }
}