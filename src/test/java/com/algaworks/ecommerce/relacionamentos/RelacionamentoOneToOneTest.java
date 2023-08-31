package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.mapeamentoavancado.SalvandoArquivosTest;
import com.algaworks.ecommerce.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;

public class RelacionamentoOneToOneTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        PagamentoCartao pagamentoCartao = new PagamentoCartao();
        pagamentoCartao.setNumeroCartao("1234");
        pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
        pagamentoCartao.setPedido(pedido);

        entityManager.getTransaction().begin();
        entityManager.persist(pagamentoCartao);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, 1);
        Assertions.assertNotNull(pedidoVerificacao.getPagamento());
    }

    @Test
    public void verificarRelacionamentoPedidoNotaFiscal() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        NotaFiscal notafiscal = new NotaFiscal();
        notafiscal.setXml(carregarNotaFiscal());
        notafiscal.setDataEmissao(new Date());
        notafiscal.setPedido(pedido);

        entityManager.getTransaction().begin();
        entityManager.persist(notafiscal);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, 1);
        Assertions.assertNotNull(pedidoVerificacao.getNotaFiscal());
    }

    private static byte[] carregarNotaFiscal() {
        try {
            return SalvandoArquivosTest.class.getResourceAsStream("/nota-fiscal.xml").readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
