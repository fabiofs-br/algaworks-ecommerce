package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BasicoJPQLTest extends EntityManagerTest {

    @Test
    public void buscarPorIdentificador() {
        // entityManager.find(Pedido.class, 1)

        TypedQuery<Pedido> typedQuery = entityManager
                .createQuery("select p from Pedido p where p.id = 1", Pedido.class);

        // select p.* from pedido p where p.id = 1

        Pedido pedido = typedQuery.getSingleResult();
        Assertions.assertNotNull(pedido);

//        List<Pedido> lista = typedQuery.getResultList();
//        Assertions.assertFalse(lista.isEmpty());

    }

}
