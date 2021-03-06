package br.com.cidade.wscidade.enums;

import java.util.Arrays;

import br.com.cidade.wscidade.util.Utils;

public enum Coluna {
	
	IBGE_ID("ibge_id","ibgeId"),
	UF("uf","uf"),
	NAME("name","name"),
	CAPITAL("capital","capital"),
	LON("lon","lon"),
	LAT("lat","lat"),
	NO_ACCENTS("no_accents","noAccents"),
	ALTERNATIVE_NAMES("alternative_names","alternativeNames"),
	MICROREGION("microregion","microregion"),
	MESOREGION("mesoregion","mesoregion");
	
	
	private String coluna;
	private String campo;

	private Coluna(String coluna,String campo) {
		this.campo = campo;
		this.coluna = coluna;
	}
	
	public static Coluna getColuna(String nome) {
		
		if(Utils.isEmptyOrNull(nome)) {
			return null;
		}		
		return Arrays.stream(Coluna.values()).filter(f -> f.getColuna().equals(nome.toLowerCase())).reduce((S1, S2)->S1).orElseThrow(() -> {throw new RuntimeException("Coluna não suportada");});
	}

	public String getColuna() {
		return coluna;
	}

	public String getCampo() {
		return campo;
	}

}
