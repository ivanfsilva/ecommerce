package br.com.ivanfsilva.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class NotaFiscal {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    private Integer pedidoId;
    private String xml;
    private Date dataEmissao;

}
