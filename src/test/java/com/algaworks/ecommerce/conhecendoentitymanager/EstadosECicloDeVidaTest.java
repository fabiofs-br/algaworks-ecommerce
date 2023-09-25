package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import org.junit.jupiter.api.Test;

public class EstadosECicloDeVidaTest extends EntityManagerTest {

    @Test
    public void analisarEstados() {
        // Estado Transiente ou Novo
        Categoria categoriaNovo = new Categoria();
        categoriaNovo.setNome("Eletrônicos");

        // Tornar estado Gerenciado
        Categoria categoriaGerenciadaMerge = entityManager.merge(categoriaNovo);

        // Estado Gerenciado
        Categoria categoriaGerenciada = entityManager.find(Categoria.class, 1);

        // Estado Removido
        entityManager.remove(categoriaGerenciada);

        // Voltar ao estado Gerenciado
        entityManager.persist(categoriaGerenciada);

        // Tornar estado desanexado
        entityManager.detach(categoriaGerenciada);
    }

}
