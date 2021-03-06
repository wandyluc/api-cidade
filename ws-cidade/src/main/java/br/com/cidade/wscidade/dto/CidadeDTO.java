package br.com.cidade.wscidade.dto;

import br.com.cidade.wscidade.entities.Cidade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CidadeDTO {

	private Long ibgeId;	
	private String uf;	
	private String name;	
	private Boolean capital;
	private String lon;
	private String lat;	
	private String noAccents;	
	private String alternativeNames;	
	private String microregion;	
	private String mesoregion;
	
	public CidadeDTO(Cidade entity) {
		this.ibgeId = entity.getIbgeId();	
		this.uf = entity.getUf();	
		this.name = entity.getName();	
		this.capital = entity.getCapital();
		this.lon = entity.getLon();
		this.lat = entity.getLat();	
		this.noAccents = entity.getNoAccents();	
		this.alternativeNames = entity.getAlternativeNames();	
		this.microregion = entity.getMicroregion();	
		this.mesoregion = entity.getMesoregion();
	}
	
}
