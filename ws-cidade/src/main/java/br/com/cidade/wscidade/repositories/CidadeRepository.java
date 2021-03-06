package br.com.cidade.wscidade.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.cidade.wscidade.dto.CidadeCapitalOrdenadaDTO;
import br.com.cidade.wscidade.dto.CidadeDTO;
import br.com.cidade.wscidade.dto.CidadeNomeDTO;
import br.com.cidade.wscidade.dto.EstadoMaisMenosCidadeDTO;
import br.com.cidade.wscidade.entities.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

	@Query("SELECT new br.com.cidade.wscidade.dto.CidadeCapitalOrdenadaDTO(c.name) FROM Cidade as c WHERE c.capital=true ORDER BY c.name")
	List<CidadeCapitalOrdenadaDTO> buscarCapitaisEmOrdemAlfabetica();
	
	@Query("SELECT new br.com.cidade.wscidade.dto.EstadoMaisMenosCidadeDTO(c.uf, COUNT(c.name)) FROM Cidade as c GROUP BY c.uf")
	List<EstadoMaisMenosCidadeDTO> quantidadeCidadePorEstado();
	
	@Query("SELECT new br.com.cidade.wscidade.dto.CidadeDTO(c) FROM Cidade as c WHERE c.ibgeId=?1")
	Optional<CidadeDTO> buscarCidadePorIbge(Long ibge);
	
	@Query("SELECT new br.com.cidade.wscidade.dto.CidadeNomeDTO(c) FROM Cidade as c WHERE c.uf=?1")
	List<CidadeNomeDTO> buscarCidaderPorUF(String uf);
	
}
