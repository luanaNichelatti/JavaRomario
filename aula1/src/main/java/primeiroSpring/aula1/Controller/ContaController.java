package primeiroSpring.aula1.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import primeiroSpring.aula1.model.dto.conta.ContaPutRequestDTO;
import primeiroSpring.aula1.model.dto.conta.ContaResponseDTO;
import primeiroSpring.aula1.model.dto.conta.ContaPostRequestDTO;
import primeiroSpring.aula1.model.entity.Conta;
import primeiroSpring.aula1.service.ContaService;

import java.util.List;

//@Component é uma das anotações que identificam uma classe como a @Service e a @RestController mas generico
@RestController
@RequestMapping("/conta")
@AllArgsConstructor
public class ContaController {

    private ContaService contaService;

    //  @RequestMapping(method = RequestMethod.GET, value = "/ola")
    @GetMapping("/ola") // mesma coisa que o de cima só que de uma forma mais simplificada
    public String metodoGet(){
        return "Hello World";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Conta cadastrarConta(@RequestBody @Valid ContaPostRequestDTO contaDTO){
        return contaService.criarConta(contaDTO);
    }

    @GetMapping
    public List<ContaResponseDTO> listarContas(){
        List<Conta> contasList = contaService.buscarContas();
        return contasList.stream().map(Conta::convertToContaResponseDTO).toList();
    }

    @GetMapping("/page")
    public Page<ContaResponseDTO> listarContasPage(
            @PageableDefault(
                    size = 20,
                    sort = "saldo",
                    direction = Sort.Direction.DESC,
                    page = 0
            ) Pageable pageable){
        Page<Conta> contasPage = contaService.buscarContas(pageable);

        List<ContaResponseDTO> contasResponseList = contasPage.stream().map(Conta::convertToContaResponseDTO).toList();
        return new PageImpl<>(contasResponseList, pageable, contasPage.getTotalElements());
    }

    @GetMapping("/{id}")
    public ContaResponseDTO buscarContaPorId(@PathVariable Integer id){
        Conta conta = contaService.buscarConta(id);
        return conta.convert();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void removerConta(@PathVariable Integer id){
        contaService.deletarConta(id);
    }

    @PutMapping("/{id}")
    public ContaResponseDTO atualizarConta(@PathVariable Integer id, @RequestBody ContaPutRequestDTO contaDTO){
       Conta conta = contaService.atualizarConta(id, contaDTO);
       return conta.convert();
    }

    @PatchMapping("/{id}")
    public ContaResponseDTO alterarLimite(@RequestParam Double limite, @PathVariable Integer id){
        Conta conta = contaService.alterarLimite(id, limite);
        return conta.convert();
    }

    @GetMapping("/filtro")
    public List<ContaResponseDTO> buscarPorFiltro(@RequestParam String titular, @RequestParam Integer numero){
        List<Conta> contaList = contaService.buscarContasFiltro(titular, numero);
        return contaList.stream()
                .map(Conta::convertToContaResponseDTO)
                .toList();
    }

    @GetMapping("/saldo")
    public List<ContaResponseDTO> buscarSaldo(@RequestParam Double saldo) {
        return contaService.buscarContasPorSaldo(saldo).stream().
                map(Conta::convertToContaResponseDTO).toList();
    }

}