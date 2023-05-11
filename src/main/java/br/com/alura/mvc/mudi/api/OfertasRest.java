package br.com.alura.mvc.mudi.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.dto.RequisicaoDeNovaOferta;
import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@PostMapping
	public Oferta criaOferta(@Valid @RequestBody RequisicaoDeNovaOferta requisicao) {
		
		Pedido pedido = pedidoRepository.findById(requisicao.getPedidoId()); 
		
		if(pedido == null) {
			return null;
		}
		
		Oferta nova = requisicao.toOferta();
		nova.setPedido(pedido);
		pedido.getOfertas().add(nova);
		
		pedidoRepository.save(pedido);
		
		return nova;
	}
	
}
