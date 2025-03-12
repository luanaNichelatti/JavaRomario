//package primeiroSpring.aula1.model.mapper;
//
//
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//import primeiroSpring.aula1.model.dto.conta.ContaClienteResponseDTO;
//import primeiroSpring.aula1.model.dto.conta.ContaGetResponseDTO;
//import primeiroSpring.aula1.model.dto.conta.ContaPostRequestDTO;
//import primeiroSpring.aula1.model.entity.Conta;
//
//@Mapper
//public interface ContaMapper {
//
//    ContaMapper INSTANCE = Mappers.getMapper(ContaMapper.class);
//
//    @Mapping(expression = "java(conta.getId())")
//    ContaGetResponseDTO contaToContaGetResponseDTO(Conta conta);
//
//    ContaClienteResponseDTO contaToContaClienteResponseDTO(Conta conta);
//
//    ContaPostRequestDTO contaToContaPostRequestDTO(Conta conta);
//}