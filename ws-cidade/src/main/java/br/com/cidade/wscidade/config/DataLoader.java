package br.com.cidade.wscidade.config;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.cidade.wscidade.entities.Cidade;
import br.com.cidade.wscidade.repositories.CidadeRepository;
import br.com.cidade.wscidade.util.Utils;

@Component
public class DataLoader implements ApplicationRunner {

	private CidadeRepository repository;

	@Autowired
	public DataLoader(CidadeRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.repository.saveAll(processarInputFile());
	}

	private List<Cidade> processarInputFile() throws IOException {

		List<Cidade> cidades = new ArrayList<Cidade>();

		Reader reader = Files.newBufferedReader(Paths.get("src","fileRepository","Desafio Cidades - Back End.csv"));

		Iterable<CSVRecord> linhas = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().parse(reader);

		for (CSVRecord l : linhas) {
			Cidade item = new Cidade();
			item.setIbgeId(Utils.convertToLong(l.get(0)));
			item.setUf(l.get(1));
			item.setName(l.get(2));
			item.setCapital(Utils.convertToBoolean(l.get(3)));
			item.setLon(l.get(4));
			item.setLat(l.get(5));
			item.setNoAccents(l.get(6));
			item.setAlternativeNames(l.get(7));
			item.setMicroregion(l.get(8));
			item.setMesoregion(l.get(9));
			cidades.add(item);
		}

		return cidades;
	}


}
