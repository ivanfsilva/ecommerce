package br.com.ivanfsilva.ecommerce.iniciandocomjpa;

import br.com.ivanfsilva.ecommerce.EntityManagerTest;
import br.com.ivanfsilva.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    public void atualizarObjetoGerenciado() {
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
        produto.setNome("Kindle Paperwhite 2ª Geração");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertEquals("Kindle Paperwhite 2ª Geração", produtoVerificacao.getNome());
    }

    @Test
    public void atualizarObjeto() {
        Produto produto = new Produto();

        produto.setId(1);
        produto.setNome("Kindle Paperwhite");
        produto.setDescricao("Conheça o novo Kindle.");
        produto.setPreco(new BigDecimal(599));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);
        Assert.assertEquals("Kindle Paperwhite", produtoVerificacao.getNome());
    }

    @Test
    public void removerObjeto() {
        Produto produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

//        entityManager.clear(); Não é necessário na asserção para operação de remoção.

        Produto produtoVerificacao = entityManager.find(Produto.class, 3);
        Assert.assertNull(produtoVerificacao);
    }

    @Test
    public void inserirOPrimeiroObjeto() {
        Produto produto = new Produto();

        produto.setId(2);
        produto.setNome("Câmera Canon");
        produto.setDescricao("A melhor definição para suas fotos.");
        produto.setPreco(new BigDecimal(5000));

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);
    }

    @Test
    public void abrirEFecharATransacao() {
//        Produto produto = new Produto(); // Somente para o método não mostrar erros.

        entityManager.getTransaction().begin();

//        entityManager.persist(produto);
//        entityManager.merge(produto);
//        entityManager.remove(produto);

        entityManager.getTransaction().commit();
    }
}