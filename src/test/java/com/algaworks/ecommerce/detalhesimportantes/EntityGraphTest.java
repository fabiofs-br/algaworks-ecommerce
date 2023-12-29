package com.algaworks.ecommerce.detalhesimportantes;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.Subgraph;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityGraphTest extends EntityManagerTest {

    @Test
    public void buscarAtributosEssenciaisDePedido02() {
        EntityGraph<Pedido> entityGraph = entityManager.createEntityGraph(Pedido.class);
        entityGraph.addAttributeNodes("dataCriacao", "status", "total");

        Subgraph<Cliente> subgraphCliente = entityGraph.addSubgraph("cliente", Cliente.class);
        subgraphCliente.addAttributeNodes("nome", "cpf");

        TypedQuery<Pedido> typedQuery = entityManager
                .createQuery("select p from Pedido p", Pedido.class);
        typedQuery.setHint("jakarta.persistence.fetchgraph", entityGraph);
        List<Pedido> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());
    }

    @Test
    public void buscarAtributosEssenciaisDePedido() {
        EntityGraph<Pedido> entityGraph = entityManager.createEntityGraph(Pedido.class);
        entityGraph.addAttributeNodes("dataCriacao", "status", "total", "notaFiscal");

//        Map<String, Object> properties = new HashMap<>();
//        properties.put("jakarta.persistence.fetchgraph", entityGraph);
////        properties.put("jakarta.persistence.loadgraph", entityGraph);
//
//        Pedido pedido = entityManager.find(Pedido.class, 1, properties);
//        Assertions.assertNotNull(pedido);

        TypedQuery<Pedido> typedQuery = entityManager
                .createQuery("select p from Pedido p", Pedido.class);
        typedQuery.setHint("jakarta.persistence.fetchgraph", entityGraph);
        List<Pedido> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());
    }

}
