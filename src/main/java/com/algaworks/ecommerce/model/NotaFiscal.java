package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal  extends EntidadeBaseInteger {

    @NotNull
    @MapsId
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false, foreignKey = @ForeignKey(name = "fk_nota_fiscal_pedido"))
//    @JoinTable(name = "pedido_nota_fiscal",
//            joinColumns = @JoinColumn(name = "nota_fiscal_id", unique = true),
//            inverseJoinColumns = @JoinColumn(name = "pedido_id", unique = true))
    private Pedido pedido;

    @NotEmpty
    @Lob
    @Column(length = 1000, nullable = false)
    @JdbcTypeCode(Types.VARBINARY)
    private byte[] xml;

    @PastOrPresent
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_emissao", nullable = false)
    private Date dataEmissao;

}
