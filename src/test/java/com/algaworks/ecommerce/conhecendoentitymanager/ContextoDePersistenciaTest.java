package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ContextoDePersistenciaTest extends EntityManagerTest {

    @Test
    public void usarContextoPersistencia() {
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class, 1); // Vai para o contexto de persistência
        produto.setPreco(new BigDecimal(100.0)); // Dirty checking

        Produto produto2 = new Produto();
        produto2.setNome("Caneca para café");
        produto2.setPreco(new BigDecimal(10.0));
        produto2.setDescricao("Boa caneca para café");
        entityManager.persist(produto2); // Vai para o contexto de persistência

        Produto produto3 = new Produto();
        produto2.setNome("Caneca para chá");
        produto3.setPreco(new BigDecimal(10.0));
        produto3.setDescricao("Boa caneca para chá");
        produto3 = entityManager.merge(produto3); // Vai para o contexto de persistência

        entityManager.flush(); // Persiste no banco

        produto3.setDescricao("Alterar descrição"); // Dirty checking

        entityManager.getTransaction().commit(); // Persiste no banco
    }

}
