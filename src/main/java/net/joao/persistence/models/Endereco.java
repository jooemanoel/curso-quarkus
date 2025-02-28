package net.joao.persistence.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "endereco", schema = "curso_quarkus")
@NamedNativeQueries({
        @NamedNativeQuery(name = Endereco.LISTAR, query = "SELECT * from curso_quarkus.ENDERECOS", resultClass = Endereco.class),
        @NamedNativeQuery(name = Endereco.CONSULTAR, query = "SELECT * from curso_quarkus.ENDERECOS WHERE id = :id", resultClass = Endereco.class),
        @NamedNativeQuery(name = Endereco.INSERIR, query = "INSERT INTO curso_quarkus.ENDERECOS (rua, logradouro, complemento, numero) VALUES (:rua, :logradouro, :complemento, :numero)"),
        @NamedNativeQuery(name = Endereco.ATUALIZAR, query = "UPDATE curso_quarkus.ENDERECOS SET rua = :rua, logradouro = :logradouro, complemento = :complemento, numero = :numero WHERE id = :id"),
        @NamedNativeQuery(name = Endereco.EXCLUIR, query = "DELETE FROM curso_quarkus.ENDERECOS WHERE id = :id")
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Endereco {

    public static final String LISTAR = "LISTAR_ENDERECO";
    public static final String CONSULTAR = "CONSULTAR_ENDERECO_ID";
    public static final String INSERIR = "INSERIR_ENDERECO";
    public static final String ATUALIZAR = "ATUALIZAR_ENDERECO";
    public static final String EXCLUIR = "EXCLUIR_ENDERECO";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String rua;

    private String logradouro;

    private String complemento;

    private Integer numero;
}
