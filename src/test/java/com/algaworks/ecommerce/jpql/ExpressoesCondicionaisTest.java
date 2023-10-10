package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPagamento;
import jakarta.persistence.TemporalType;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class ExpressoesCondicionaisTest extends EntityManagerTest {

    @Test
    public void usarIsNull() {
        // cast as string para evitar erro
        String jpql = "select p from Produto p where cast(p.foto as string) is null";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarIsEmpty() {
        String jpql = "select p from Produto p where p.categorias is empty";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());
    }

}
