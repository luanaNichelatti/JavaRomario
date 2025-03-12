package primeiroSpring.aula1.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
public class PFisica extends Cliente{

    private String sobrenome;
    private Long rg;

}
