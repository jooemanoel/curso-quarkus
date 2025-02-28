package net.joao.persistence.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;

@Entity
@Table(name = "agencia")
@NamedNativeQueries({
        @NamedNativeQuery(name = Agencia.LISTAR, query = "SELECT id, nome, razao_social, cnpj, endereco_id FROM agencia", resultClass = Agencia.class),
        @NamedNativeQuery(name = Agencia.CONSULTAR, query = "SELECT id, nome, razao_social, cnpj, endereco_id FROM agencia WHERE id = :id", resultClass = Agencia.class),
        @NamedNativeQuery(name = Agencia.CONSULTAR_POR_CNPJ, query = "SELECT id, nome, razao_social, cnpj, endereco_id FROM agencia WHERE cnpj = :cnpj", resultClass = Agencia.class),
        @NamedNativeQuery(name = Agencia.INSERIR, query = "INSERT INTO agencia (nome, razao_social, cnpj) VALUES (:nome, :razao_social, :cnpj);"),
        @NamedNativeQuery(name = Agencia.ATUALIZAR, query = "UPDATE agencia SET nome = :nome, cnpj = :cnpj WHERE id = :id "),
        @NamedNativeQuery(name = Agencia.EXCLUIR, query = "DELETE FROM agencia WHERE  id = :id")
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Agencia {

    public static final String LISTAR = "LISTAR_AGENCIA";
    public static final String CONSULTAR = "CONSULTAR_AGENCIA";
    public static final String CONSULTAR_POR_CNPJ = "CONSULTAR_AGENCIA_POR_CNPJ";
    public static final String INSERIR = "INSERIR_AGENCIA";
    public static final String ATUALIZAR = "ATUALIZAR_AGENCIA";
    public static final String EXCLUIR = "EXCLUIR_AGENCIA";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(name = "razao_social")
    private String razaoSocial;

    private String cnpj;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

}
