package com.example.demo.dtos;

import java.time.LocalDate;
import java.util.Set;

import com.example.demo.models.ClienteModel;
import com.example.demo.models.PratoModel;

public record PedidoRecordDto(LocalDate data, 
		Set<PratoRecordDto>pratos
	) {

}
