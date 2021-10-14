package br.com.cidade.wscidade.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.cidade.wscidade.dto.CidadeCapitalOrdenadaDTO;
import br.com.cidade.wscidade.dto.CidadeDTO;
import br.com.cidade.wscidade.dto.CidadeMaiorDistanciaOutra;
import br.com.cidade.wscidade.dto.CidadeNomeDTO;
import br.com.cidade.wscidade.dto.EstadoMaisMenosCidadeDTO;
import br.com.cidade.wscidade.entities.Cidade;
import br.com.cidade.wscidade.enums.Coluna;
import br.com.cidade.wscidade.jpa.specification.CidadeSpecification;
import br.com.cidade.wscidade.repositories.CidadeRepository;
import br.com.cidade.wscidade.util.Utils;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	public List<CidadeCapitalOrdenadaDTO> buscarCapitaisEmOrdemAlfabetica() {
		return repository.buscarCapitaisEmOrdemAlfabetica();
	}

	public List<EstadoMaisMenosCidadeDTO> buscarEstadoMaisMenosCidade() {

		List<EstadoMaisMenosCidadeDTO> estados = repository.quantidadeCidadePorEstado();
		List<EstadoMaisMenosCidadeDTO> retorno = new ArrayList<EstadoMaisMenosCidadeDTO>();

		EstadoMaisMenosCidadeDTO estadoMenosCidades = estados.stream()
				.min(Comparator.comparing(EstadoMaisMenosCidadeDTO::getQuantidade))
				.orElseThrow(NoSuchElementException::new);
		retorno.add(estadoMenosCidades);

		EstadoMaisMenosCidadeDTO estadoMaisCidades = estados.stream()
				.max(Comparator.comparing(EstadoMaisMenosCidadeDTO::getQuantidade))
				.orElseThrow(NoSuchElementException::new);
		retorno.add(estadoMaisCidades);

		return retorno;
	}

	public Optional<EstadoMaisMenosCidadeDTO> buscarQuantidadeCidadePorEstado(String uf) {

		List<EstadoMaisMenosCidadeDTO> estados = repository.quantidadeCidadePorEstado();

		if (Utils.isEmptyOrNull(uf)) {
			throw new RuntimeException("Estado nao encontrado");
		}

		return estados.stream().filter(l -> l.getUf().equals(uf.toUpperCase())).findFirst();
	}

	public Optional<CidadeDTO> buscaCidadePorIbge(Long ibge) {
		return repository.buscarCidadePorIbge(ibge);
	}

	public List<CidadeNomeDTO> buscarCidadesPorEstado(String uf) {

		if (Utils.isEmptyOrNull(uf)) {
			throw new RuntimeException("Estado nao encontrado");
		}

		return repository.buscarCidaderPorUF(uf.toUpperCase());
	}

	public Optional<CidadeDTO> adicionaCidade(CidadeDTO dto) {
		if (!Utils.isEmptyOrNull(dto)) {
			Cidade cidade = new Cidade(dto.getIbgeId(), dto.getUf(), dto.getName(), dto.getCapital(), dto.getLon(),
					dto.getLat(), dto.getNoAccents(), dto.getAlternativeNames(), dto.getMicroregion(),
					dto.getMesoregion());
			return Optional.ofNullable(new CidadeDTO(repository.saveAndFlush(cidade)));
		} else {
			return Optional.empty();
		}

	}

	public void deletarCidade(CidadeDTO dto) {
		if (!Utils.isEmptyOrNull(dto)) {
			Cidade cidade = new Cidade(dto.getIbgeId(), dto.getUf(), dto.getName(), dto.getCapital(), dto.getLon(),
					dto.getLat(), dto.getNoAccents(), dto.getAlternativeNames(), dto.getMicroregion(),
					dto.getMesoregion());
			repository.delete(cidade);
		} else {
			throw new RuntimeException("Não foi possivel apagar a cidade");
		}
	}

	public List<CidadeDTO> filtroPorColuna(Coluna coluna, String filtro) {
		List<Cidade> lista = repository.findAll(Specification.where(CidadeSpecification.filtragem(coluna, filtro)));
		List<CidadeDTO> retorno = lista.stream().map(x -> new CidadeDTO(x)).collect(Collectors.toList());
		return retorno;
	}

	public Map<String, Integer> qtdeRegistrosPorColuna(Coluna coluna) {
		List<Cidade> lista = repository.findAll();

		Map<String, Integer> retorno = new HashMap<String, Integer>();
		try {
			switch (coluna) {
			case ALTERNATIVE_NAMES:
				Set<String> alternativeNames = lista.stream().map(Cidade::getAlternativeNames)
						.collect(Collectors.toSet());
				retorno.put(coluna.getColuna(), alternativeNames.size());
				break;
			case CAPITAL:
				Set<Boolean> capital = lista.stream().map(Cidade::getCapital).collect(Collectors.toSet());
				retorno.put(coluna.getColuna(), capital.size());
				break;
			case IBGE_ID:
				Set<Long> ibgeId = lista.stream().map(Cidade::getIbgeId).collect(Collectors.toSet());
				retorno.put(coluna.getColuna(), ibgeId.size());
				break;
			case LAT:
				Set<String> lat = lista.stream().map(Cidade::getLat).collect(Collectors.toSet());
				retorno.put(coluna.getColuna(), lat.size());
				break;
			case LON:
				Set<String> lon = lista.stream().map(Cidade::getLon).collect(Collectors.toSet());
				retorno.put(coluna.getColuna(), lon.size());
				break;
			case MESOREGION:
				Set<String> mesoregion = lista.stream().map(Cidade::getMesoregion).collect(Collectors.toSet());
				retorno.put(coluna.getColuna(), mesoregion.size());
				break;
			case MICROREGION:
				Set<String> microregion = lista.stream().map(Cidade::getMicroregion).collect(Collectors.toSet());
				retorno.put(coluna.getColuna(), microregion.size());
				break;
			case NAME:
				Set<String> name = lista.stream().map(Cidade::getName).collect(Collectors.toSet());
				retorno.put(coluna.getColuna(), name.size());
				break;
			case NO_ACCENTS:
				Set<String> noAccents = lista.stream().map(Cidade::getNoAccents).collect(Collectors.toSet());
				retorno.put(coluna.getColuna(), noAccents.size());
				break;
			case UF:
				Set<String> uf = lista.stream().map(Cidade::getUf).collect(Collectors.toSet());
				retorno.put(coluna.getColuna(), uf.size());
				break;
			}

			return retorno;

		} catch (Exception e) {
			throw new RuntimeException("Processo quantidade de registro com falha");
		}
	}

	public Map<String, Integer> qtdeRegistrosTotal() {
		Map<String, Integer> retorno = new HashMap<String, Integer>();
		List<Cidade> lista = repository.findAll();
		retorno.put("totalRegistros", lista.size());
		return retorno;
	}
	
	public List<CidadeMaiorDistanciaOutra> maiorDistanciaEntreCidade() {
		List<Cidade> lista = repository.findAll();
		List<CidadeMaiorDistanciaOutra> retorno = new ArrayList<CidadeMaiorDistanciaOutra>();
		CidadeMaiorDistanciaOutra maiorDistancia = null;
		double maior = 0;
		for(Cidade c1 : lista) {
			for(Cidade c2 : lista) {
				if(!c1.getName().contains(c2.getName())) {
					CidadeMaiorDistanciaOutra cidade = new CidadeMaiorDistanciaOutra(c1, c2);
					if (cidade.getDistancia() > maior) {
						maior = cidade.getDistancia();
						maiorDistancia = cidade;
						
					}
				}
			}
		}
		retorno.add(maiorDistancia);
		return retorno;
	}

}
