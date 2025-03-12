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
import primeiroSpring.aula1.model.dto.cliente.ClientePostRequestDTO;
import primeiroSpring.aula1.model.dto.cliente.ClientePutRequestDTO;
import primeiroSpring.aula1.model.dto.cliente.ClienteResponseDTO;
import primeiroSpring.aula1.model.entity.Cliente;
import primeiroSpring.aula1.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@AllArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @GetMapping("/nome")
    public ClienteResponseDTO buscarPorNome(@RequestParam String nome) {
        return service.buscarPorNome(nome).convertoToClienteResponseDTO();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO cadastro(@RequestBody @Valid ClientePostRequestDTO clienteDTO){
        Cliente cliente = service.cadastrar(clienteDTO);
        return cliente.convertoToClienteResponseDTO();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO editar(@RequestBody @Valid ClientePutRequestDTO clienteDTO,
                          @PathVariable Integer id){
        Cliente cliente = service.editar(clienteDTO, id);
        return cliente.convertoToClienteResponseDTO();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO alterarContas( @PathVariable Integer id, @RequestParam Integer idConta){
        Cliente cliente = service.alterarConta(id, idConta);
        return cliente.convertoToClienteResponseDTO();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO buscarCliente(@PathVariable Integer id){
        Cliente cliente = service.buscar(id);
        return cliente.convertoToClienteResponseDTO();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ClienteResponseDTO> buscarClientes(@PageableDefault(
            size = 20,
            sort = "nome",
            direction = Sort.Direction.DESC,
            page = 0
    ) Pageable pageable){

        Page<Cliente> clientePage = service.buscar(pageable);
        List<ClienteResponseDTO> contentList = clientePage.getContent().stream().map(
                Cliente::convertoToClienteResponseDTO).toList();

        return new PageImpl<>(contentList, clientePage.getPageable(), clientePage.getTotalElements());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerCliente(@PathVariable Integer id){
        service.remover(id);
    }
}
