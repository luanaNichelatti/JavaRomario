package primeiroSpring.aula1.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

// só para testar herança no spring
@Entity
@Data
public class PJuridica extends Cliente{

    private long cnpj;
    private String razaoSocial;
}
