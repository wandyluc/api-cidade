package br.com.cidade.wscidade.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EstadoMaisMenosCidadeDTO {
	private String uf;
	private Long quantidade;
	
	public EstadoMaisMenosCidadeDTO(String uf, Long count) {
		
		this.uf = uf;
		this.quantidade = count;
	}
	
	
}
