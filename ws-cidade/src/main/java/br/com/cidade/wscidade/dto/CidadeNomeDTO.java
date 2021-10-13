package br.com.cidade.wscidade.dto;

import br.com.cidade.wscidade.entities.Cidade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CidadeNomeDTO {
	
	private String name;	

	public CidadeNomeDTO(Cidade entity) {	
		this.name = entity.getName();	
	}
	
}
