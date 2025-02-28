package net.joao.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgenciaNomeRuaDTO {

    private String nome;

    private String razaoSocial;

    private String rua;

    private String logradouro;

    private String complemento;

    private Integer numero;
}
