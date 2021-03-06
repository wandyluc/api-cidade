package br.com.cidade.wscidade.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cidade.wscidade.dto.CidadeCapitalOrdenadaDTO;
import br.com.cidade.wscidade.dto.CidadeDTO;
import br.com.cidade.wscidade.dto.CidadeMaiorDistanciaOutra;
import br.com.cidade.wscidade.dto.CidadeNomeDTO;
import br.com.cidade.wscidade.dto.EstadoMaisMenosCidadeDTO;
import br.com.cidade.wscidade.entities.Cidade;
import br.com.cidade.wscidade.enums.Coluna;
import br.com.cidade.wscidade.services.CidadeService;

@RestController
@RequestMapping(value = "/cidade")
public class CidadeController {

	@Autowired
	private CidadeService service;
	
	@GetMapping(value = "/capitais-ordem-alfabetica")
	public ResponseEntity<List<CidadeCapitalOrdenadaDTO>> buscarCapitaisEmOrdemAlfabetica(){
		List<CidadeCapitalOrdenadaDTO> result = service.buscarCapitaisEmOrdemAlfabetica();		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "/estado-mais-menos-cidades")
	public ResponseEntity<List<EstadoMaisMenosCidadeDTO>> buscarEstadoMaisMenosCidade(){
		List<EstadoMaisMenosCidadeDTO> result = service.buscarEstadoMaisMenosCidade();		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "/qtde-cidades-por-estado/{uf}")
	public ResponseEntity<Optional<EstadoMaisMenosCidadeDTO>> buscarQuantidadeCidadePorEstado(@PathVariable String uf){
		Optional<EstadoMaisMenosCidadeDTO> result = service.buscarQuantidadeCidadePorEstado(uf);		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "/cidades-por-ibgeId/{id}")
	public ResponseEntity<Optional<CidadeDTO>> buscaCidadePorIbge(@PathVariable Long id){
		Optional<CidadeDTO> result = service.buscaCidadePorIbge(id);		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "/cidades-por-estado/{uf}")
	public ResponseEntity<List<CidadeNomeDTO>> buscarCidadesPorEstado(@PathVariable String uf){
		List<CidadeNomeDTO> result = service.buscarCidadesPorEstado(uf);		
		return ResponseEntity.ok(result);
	}
	
	@PostMapping(value = "/adicionarCidade")
	public Optional<CidadeDTO> adicionaCidade(@RequestBody CidadeDTO cidade) {
		return service.adicionaCidade(cidade);
	}
	
	@DeleteMapping(value = "/deletarCidade")
	public void deletarCidade(@RequestBody CidadeDTO cidade) {
		service.deletarCidade(cidade);
	}
	
	@GetMapping(value = "/filtro-por-coluna/{coluna}/{filtro}")
	public ResponseEntity<List<CidadeDTO>> filtroPorColuna(@PathVariable String coluna,@PathVariable String filtro){
		List<CidadeDTO> result = service.filtroPorColuna(Coluna.getColuna(coluna) ,filtro);		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "/qte-registro-por-coluna/{coluna}")
	public ResponseEntity<Map<String, Integer>> filtroPorColuna(@PathVariable String coluna){
		Map<String, Integer> result = service.qtdeRegistrosPorColuna(Coluna.getColuna(coluna));		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "/qte-registro-total")
	public ResponseEntity<Map<String, Integer>> quantidadeREgistroTotal(){
		Map<String, Integer> result = service.qtdeRegistrosTotal();		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "/maior-distancia-entre-cidades")
	public ResponseEntity<List<CidadeMaiorDistanciaOutra>> maiorDistanciaEntreCidade(){
		List<CidadeMaiorDistanciaOutra> result = service.maiorDistanciaEntreCidade();
		return ResponseEntity.ok(result);
	}
}
