package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import com.algaworks.ecommerce.model.Categoria_;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Cliente_;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.ItemPedido_;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Pedido_;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.Produto_;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class GroupByCriteriaTest extends EntityManagerTest {

    @Test
    public void condicionarAgrumpamentoComHaving() {
//        Total de vendas dentre as categorias que mais vendem.
//        String jpql = "select cat.nome, sum(ip.precoProduto * ip.quantidade)" +
//                " from ItemPedido ip join ip.produto pro join pro.categorias cat " +
//                " group by cat.id " +
//                " having avg(ip.precoProduto * ip.quantidade) > 800";

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ItemPedido> root = criteriaQuery.from(ItemPedido.class);
        Join<ItemPedido, Produto> joinProduto = root.join(ItemPedido_.produto);
        Join<Produto, Categoria> joinProdutoCategoria = joinProduto.join(Produto_.categorias);

        criteriaQuery.multiselect(
                joinProdutoCategoria.get(Categoria_.nome),
                criteriaBuilder.sum(
                        criteriaBuilder.prod(
                                root.get(ItemPedido_.precoProduto),
                                root.get(ItemPedido_.quantidade)
                        )

                ),
                criteriaBuilder.avg(
                        criteriaBuilder.prod(
                                root.get(ItemPedido_.precoProduto),
                                root.get(ItemPedido_.quantidade)
                        )
                )
        );

        criteriaQuery.groupBy(joinProdutoCategoria.get(Categoria_.id));

        criteriaQuery.having(
                criteriaBuilder.greaterThan(
                        criteriaBuilder.avg(
                                criteriaBuilder.prod(
                                        root.get(ItemPedido_.precoProduto),
                                        root.get(ItemPedido_.quantidade)
                                )
                        ).as(BigDecimal.class),
                        new BigDecimal(800)
                )
        );

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Object[]> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(
                "Nome categoria: " + arr[0]
                        + ", SUM: " + arr[1]
                        + ", AVG: " + arr[2]
        ));
    }

    @Test
    public void agruparResultadoComFuncoes() {
//        Total de vendas por mês.
//        String jpql = "select concat(year(p.dataCriacao), '/', function('monthname', p.dataCriacao)), sum(p.total) " +
//                " from Pedido p " +
//                " group by year(p.dataCriacao), month(p.dataCriacao)";

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        Expression<Integer> anoCriacaoPedido = criteriaBuilder
                .function("year", Integer.class, root.get(Pedido_.dataCriacao));
        Expression<Integer> mesCriacaoPedido = criteriaBuilder
                .function("month", Integer.class, root.get(Pedido_.dataCriacao));
        Expression<String> nomeMesCriacaoPedido = criteriaBuilder
                .function("monthname", String.class, root.get(Pedido_.dataCriacao));

        Expression<String> anoMesConcat = criteriaBuilder.concat(
                criteriaBuilder.concat(anoCriacaoPedido.as(String.class), "/"),
                nomeMesCriacaoPedido
        );

        criteriaQuery.multiselect(
                anoMesConcat,
                criteriaBuilder.sum(root.get(Pedido_.total))
        );

        criteriaQuery.groupBy(anoMesConcat, anoCriacaoPedido, mesCriacaoPedido);

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> lista = typedQuery.getResultList();

        lista.forEach(arr -> System.out.println(
                "Ano/Mês: " + arr[0] + ", Sum: " + arr[1]
        ));
    }

    @Test
    public void agruparResultado03Exercicio() {
//        Total de vendas por cliente
//        String jpql = "select c.nome, sum(ip.precoProduto * ip.quantidade) " +
//                " from ItemPedido ip join ip.pedido p join p.cliente c " +
//                " group by c.id";

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ItemPedido> root = criteriaQuery.from(ItemPedido.class);
        Join<ItemPedido, Pedido> joinPedido = root.join(ItemPedido_.pedido);
        Join<Pedido, Cliente> joinPedidoCliente = joinPedido.join(Pedido_.cliente);

        criteriaQuery.multiselect(
                joinPedidoCliente.get(Cliente_.nome),
                criteriaBuilder.sum(
                        criteriaBuilder.prod(
                                root.get(ItemPedido_.precoProduto),
                                root.get(ItemPedido_.quantidade)
                        )
                )
        );

        criteriaQuery.groupBy(joinPedidoCliente.get(Cliente_.id));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> lista = typedQuery.getResultList();

        lista.forEach(arr -> System.out.println(
                "Nome cliente: " + arr[0] + ", Sum: " + arr[1]
        ));
    }

    @Test
    public void agruparResultado02() {
//        Total de vendas dentre as categorias que mais vendem.
//        String jpql = "select cat.nome, sum(ip.precoProduto * ip.quantidade)" +
//                " from ItemPedido ip join ip.produto pro join pro.categorias cat " +
//                " group by cat.id ";

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ItemPedido> root = criteriaQuery.from(ItemPedido.class);
        Join<ItemPedido, Produto> joinProduto = root.join(ItemPedido_.produto);
        Join<Produto, Categoria> joinProdutoCategoria = joinProduto.join(Produto_.categorias);

        criteriaQuery.multiselect(
                joinProdutoCategoria.get(Categoria_.nome),
                criteriaBuilder.sum(
                        criteriaBuilder.prod(root.get(ItemPedido_.precoProduto), root.get(ItemPedido_.quantidade))
                )
        );

        criteriaQuery.groupBy(joinProdutoCategoria.get(Categoria_.id));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Object[]> lista = typedQuery.getResultList();

        lista.forEach(arr -> System.out.println("Nome categoria: " + arr[0] + ", Sum: " + arr[1]));
    }

    @Test
    public void agruparResultado01() {
//        Quantidade de produtos por categoria.
//        String jpql = "select c.nome, count(p.id) from Categoria c join c.produtos p group by c.id";

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Categoria> root = criteriaQuery.from(Categoria.class);
        Join<Categoria, Produto> joinProduto = root.join(Categoria_.produtos, JoinType.LEFT);

        criteriaQuery.multiselect(
              root.get(Categoria_.nome),
              criteriaBuilder.count(joinProduto.get(Produto_.id))
        );

        criteriaQuery.groupBy(root.get(Categoria_.id));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Object[]> lista = typedQuery.getResultList();

        lista.forEach(arr -> System.out.println("Nome: " + arr[0] + ", Count: " + arr[1]));
    }

}
