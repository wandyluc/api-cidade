package br.com.cidade.wscidade.jpa.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.cidade.wscidade.entities.Cidade;
import br.com.cidade.wscidade.enums.Coluna;
import br.com.cidade.wscidade.util.Utils;

public class CidadeSpecification {

	public static Specification<Cidade> filtragem(Coluna coluna, String filtro){
		switch(coluna) {
		case ALTERNATIVE_NAMES:
			return(root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(coluna.getCampo()), filtro);
		case CAPITAL:
			return(root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(coluna.getCampo()), Utils.convertToBoolean(filtro));
		case IBGE_ID:
			return(root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(coluna.getCampo()), Utils.convertToLong(filtro));
		case LAT:
			return(root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(coluna.getCampo()), filtro);
		case LON:
			return(root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(coluna.getCampo()), filtro);
		case MESOREGION:
			return(root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(coluna.getCampo()), filtro);
		case MICROREGION:
			return(root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(coluna.getCampo()), filtro);
		case NAME:
			return(root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(coluna.getCampo()), filtro);
		case NO_ACCENTS:
			return(root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(coluna.getCampo()), filtro);
		case UF:
			return(root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(coluna.getCampo()), filtro);
		default:
			throw new RuntimeException("Não exite aa coluna para filtro");
		}
				
	}
}
