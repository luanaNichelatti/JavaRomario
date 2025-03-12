package primeiroSpring.aula1.model.entity;

import jakarta.persistence.*;
import lombok.*;
import primeiroSpring.aula1.model.dto.conta.ContaClienteResponseDTO;
import primeiroSpring.aula1.model.dto.conta.ContaResponseDTO;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "numero_da_conta", nullable = false, unique = true)
    private Integer numero;
    @Builder.Default
    private Double saldo = 0.0;
    @NonNull
    private Double limite;
    @ManyToOne
    private Cliente titular;

    public ContaResponseDTO convert() {
        return new ContaResponseDTO(
                this.id, this.numero, this.saldo,this.limite,this.titular.convert()
        );
    }

    public ContaClienteResponseDTO convertToContaClienteResponseDTO() {
        return new ContaClienteResponseDTO(this.id,this.saldo,this.limite,this.numero);
    }

    public ContaResponseDTO convertToContaResponseDTO() {
        return new ContaResponseDTO(this.id,this.numero,this.saldo,this.limite,this.titular.convert());
    }

//    public static ContaBuilder builder(){
//        return new ContaBuilder();
//    }
//
//    public static class ContaBuilder{
//        private Integer id;
//        private Integer numero;
//        private Double saldo;
//        private Double limite;
//        private String titular;
//
//        public ContaBuilder titular(String titular){
//            this.titular = titular;
//            return this;
//        }
//
//        public ContaBuilder numero(Integer titular){
//            this.limite = limite;
//            return this;
//        }
//
//        public ContaBuilder limite(double limite){
//            this.limite = limite;
//            return this;
//        }
//
//        public Conta build(){
//            return new Conta(id,numero,saldo,limite,titular);
//        }
//    }
}