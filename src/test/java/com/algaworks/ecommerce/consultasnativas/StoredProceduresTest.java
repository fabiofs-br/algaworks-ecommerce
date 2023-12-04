package com.algaworks.ecommerce.consultasnativas;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class StoredProceduresTest extends EntityManagerTest {

    @Test
    public void executeView() {
        Query query = entityManager.createNativeQuery(
                "select cli.id, cli.nome, sum(ped.total) " +
                        " from pedido ped " +
                        " join view_clientes_acima_media cli on cli.id = ped.cliente_id " +
                        " group by ped.cliente_id");

        List<Object[]> lista = query.getResultList();

        lista.stream().forEach(arr -> System.out.println(String.format("Cliente => ID: %s, Nome: %s, Total: %s", arr)));
    }

    @Test
    public void executeViewRetornandoCliente() {
        Query query = entityManager.createNativeQuery(
                "select * from view_clientes_acima_media", Cliente.class);

        List<Cliente> lista = query.getResultList();

        lista.stream().forEach(obj -> System.out.println(
                String.format("Cliente => ID: %s, Nome: %s", obj.getId(), obj.getNome())));
    }

    @Test
    public void chamarNamedStoreProcedure() {
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createNamedStoredProcedureQuery("compraram_acima_media");

        storedProcedureQuery.setParameter("ano", LocalDateTime.now().getYear());

        List<Cliente> lista = storedProcedureQuery.getResultList();

        Assertions.assertFalse(lista.isEmpty());
    }

    @Test
    public void atualizarPrecoProdutoExercicio() {
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("ajustar_preco_produto");

        storedProcedureQuery
                .registerStoredProcedureParameter("produto_id", Integer.class, ParameterMode.IN);
        storedProcedureQuery
                .registerStoredProcedureParameter("percentual_ajuste", BigDecimal.class, ParameterMode.IN);
        storedProcedureQuery
                .registerStoredProcedureParameter("preco_ajustado", BigDecimal.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("produto_id", 1);
        storedProcedureQuery.setParameter("percentual_ajuste", new BigDecimal("0.1"));

        BigDecimal precoAjustado = (BigDecimal) storedProcedureQuery
                .getOutputParameterValue("preco_ajustado");

        Assertions.assertEquals(new BigDecimal("878.9"), precoAjustado);
    }


    @Test
    public void receberListaDaProcedure() {
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("compraram_acima_media", Cliente.class);

        storedProcedureQuery
                .registerStoredProcedureParameter("ano", Integer.class, ParameterMode.IN);

        storedProcedureQuery.setParameter("ano", LocalDateTime.now().getYear());

        List<Cliente> lista = storedProcedureQuery.getResultList();

        Assertions.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarParamentrosInEOut() {
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("buscar_nome_produto");

        storedProcedureQuery
                .registerStoredProcedureParameter("produto_id", Integer.class, ParameterMode.IN);

        storedProcedureQuery
                .registerStoredProcedureParameter("produto_nome", String.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("produto_id", 1);

        String nome = (String) storedProcedureQuery.getOutputParameterValue("produto_nome");

        Assertions.assertEquals("Kindle", nome);
    }

}
