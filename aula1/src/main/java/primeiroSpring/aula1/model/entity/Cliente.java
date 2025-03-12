package primeiroSpring.aula1.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import primeiroSpring.aula1.model.dto.cliente.ClienteContaGetResponseDTO;
import primeiroSpring.aula1.model.dto.cliente.ClienteResponseDTO;
import primeiroSpring.aula1.model.dto.conta.ContaClienteResponseDTO;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "tb_cliente")
//@Inheritance(strategy = InheritanceType.JOINED)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Long cpf;
    @OneToMany(mappedBy = "titular")
    private List<Conta> contas;


    public void addConta(@NotNull Conta conta) {
        if (!contas.contains(conta)) {
            contas.add(conta);
            conta.setTitular(this);
        } else {
            throw new RuntimeException();
        }

    }

    public void removerConta(@NotNull Conta conta) {

        if (!contas.contains(conta)) {
            throw new RuntimeException();

        }
        this.contas.remove(conta);
        contas.remove(conta);
    }

    public List<Conta> getContas() {
        if (this.contas != null) {
            return this.contas;
        }
        return new ArrayList<>();
    }

    public ClienteContaGetResponseDTO convert() {
        return new ClienteContaGetResponseDTO(
                this.id, this.nome, this.cpf
        );
    }

    public ClienteResponseDTO convertoToClienteResponseDTO() {

        if (this.contas != null) {
            List<ContaClienteResponseDTO> contasDTO = this.contas.stream().map(Conta::convertToContaClienteResponseDTO).toList();
            return new ClienteResponseDTO(this.id, this.nome, this.cpf, contasDTO);
        }
        return new ClienteResponseDTO(this.id, this.nome, this.cpf, null);

    }
}