package com.example.demo.dtos;

import java.util.Set;

import com.example.demo.models.CartaoFidelidadeModel;
import com.example.demo.models.PedidoModel;

public record ClienteRecordDto(String nome,
		Set<PedidoRecordDto> 
		pedidos,CartaoFidelidadeModel cartaoFidelidade) {

}
