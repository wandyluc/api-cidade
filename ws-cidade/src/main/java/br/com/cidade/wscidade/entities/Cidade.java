package br.com.cidade.wscidade.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbCidade")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cidade {
	
	@Id
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
}
