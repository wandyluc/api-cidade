package br.com.cidade.wscidade.dto;

import java.math.BigDecimal;

import br.com.cidade.wscidade.entities.Cidade;
import br.com.cidade.wscidade.util.Utils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CidadeMaiorDistanciaOutra {
	
	private String cidades;
	private Double distancia;
	
	public CidadeMaiorDistanciaOutra(Cidade ent1, Cidade ent2) {
		this.cidades = ent1.getName() +" X "+ ent2.getName(); 
		this.distancia = getDistancia(ent1.getLat(), ent1.getLon(), ent2.getLat(), ent2.getLon());
	}
	
	private Double getDistancia(String lat1, String lon1, String lat2, String lon2) {
		
		BigDecimal lt1 = new BigDecimal(lat1);
		BigDecimal ln1 = new BigDecimal(lon1);
		BigDecimal lt2 = new BigDecimal(lat2);
		BigDecimal ln2 = new BigDecimal(lon2);
		
		return Utils.distance(lt1.doubleValue(), ln1.doubleValue(), lt2.doubleValue(), ln2.doubleValue(),"K");
	}
}
