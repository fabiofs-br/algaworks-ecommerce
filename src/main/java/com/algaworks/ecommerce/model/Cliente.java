package com.algaworks.ecommerce.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NamedStoredProcedureQuery(name = "compraram_acima_media",
        procedureName = "compraram_acima_media",
        parameters = {
                @StoredProcedureParameter(name = "ano", type = Integer.class, mode = ParameterMode.IN)
        },
        resultClasses = Cliente.class
)
@SecondaryTable(name = "cliente_detalhe",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id"),
        foreignKey = @ForeignKey(name = "fk_cliente_detalhe_cliente"))
@Entity
@Table(name = "cliente",
        uniqueConstraints = @UniqueConstraint(name = "unq_cpf", columnNames = {"cpf"}),
        indexes = {@Index(name = "idx_nome", columnList = "nome")}
)
public class Cliente extends EntidadeBaseInteger {

    @NotBlank
    @Column(length = 100, nullable = false)
    private String nome;

    @NotBlank
    @CPF
    @Column(length = 14, nullable = false)
    private String cpf;

    @ElementCollection
    @CollectionTable(name = "cliente_contato",
            joinColumns = @JoinColumn(name = "cliente_id", nullable = false,
                    foreignKey = @ForeignKey(name = "fk_cliente_contato_cliente")))
    @MapKeyColumn(name = "tipo")
    @Column(name = "descricao")
    private Map<String, String> contatos;

    @Transient
    private String primeiroNome;

    @NotNull
    @Column(table = "cliente_detalhe", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private SexoCliente sexo;

    @Past
    @Column(name = "data_nascimento" ,table = "cliente_detalhe")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @PostLoad
    public void configurarPrimeiroNome() {
        if (nome != null && !nome.isBlank()) {
            int index = nome.indexOf(" ");
            if (index > -1) {
                primeiroNome = nome.substring(0, index);
            }
        }
    }

}


