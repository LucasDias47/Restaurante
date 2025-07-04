package com.example.demo.Services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.dtos.ClienteRecordDto;
import com.example.demo.dtos.PedidoRecordDto;
import com.example.demo.dtos.PratoRecordDto;
import com.example.demo.models.CartaoFidelidadeModel;
import com.example.demo.models.ClienteModel;
import com.example.demo.models.PedidoModel;
import com.example.demo.models.PratoModel;
import com.example.demo.repository.CartaoFidelidadeRepository;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.PratoRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;
	private final PedidoRepository pedidoRepository;
	private final PratoRepository pratoRepository;
	private final CartaoFidelidadeRepository cartaoFidelidadeRepository;

	public ClienteService(ClienteRepository clienteRepository, PedidoRepository pedidoRepository,
			PratoRepository pratoRepository, CartaoFidelidadeRepository cartaoFidelidadeRepository) {
		this.clienteRepository = clienteRepository;
		this.pedidoRepository = pedidoRepository;
		this.pratoRepository = pratoRepository;
		this.cartaoFidelidadeRepository = cartaoFidelidadeRepository;
	}
	
	@Transactional
	public ClienteModel saveCliente(ClienteRecordDto dto) {
	    // Cria e preenche cliente
	    ClienteModel cliente = new ClienteModel();
	    cliente.setNome(dto.nome());

	    // Salva o cliente primeiro para garantir que ele já exista no banco antes de usar como FK
	    clienteRepository.save(cliente);

	    // Cria e salva o cartão fidelidade
	    CartaoFidelidadeModel cartao = new CartaoFidelidadeModel();
	    cartao.setCodigo(dto.cartaoFidelidade().getCodigo());
	    cartao.setCliente(cliente);
	    cartaoFidelidadeRepository.save(cartao);

	    // Associa o cartão ao cliente
	    cliente.setCartaoFidelidade(cartao);

	    List<PedidoModel> pedidos = new ArrayList<>();
	    for (PedidoRecordDto pedidoDto : dto.pedidos()) {
	        PedidoModel pedido = new PedidoModel();
	        pedido.setData(pedidoDto.data());
	        pedido.setCliente(cliente);

	        List<PratoModel> pratos = new ArrayList<>();
	        for (PratoRecordDto pratoDto : pedidoDto.pratos()) {
	            PratoModel prato = new PratoModel();
	            prato.setNome(pratoDto.nome());
	            prato.setPreco(pratoDto.preco());
	            pratoRepository.save(prato);
	            pratos.add(prato);
	        }

	        pedido.setPratos(pratos);
	        pedidoRepository.save(pedido);
	        pedidos.add(pedido);
	    }

	    cliente.setPedidos(pedidos);

	    // Atualiza o cliente com os pedidos e cartão associados
	    return clienteRepository.save(cliente);
	    
	}
	
	public List<ClienteModel> getAllClientes() {
	    return clienteRepository.findAll();
	}

	public void deleteCliente(UUID id) {
	    clienteRepository.deleteById(id);
	}
}
