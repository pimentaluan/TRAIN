package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dicionario {
	private String idiomaCorrente;
	private ArrayList<String[]> traducoes;

	public Dicionario(String idioma) throws Exception {
		this.traducoes = new ArrayList<>();
		setIdioma(idioma);
	}

	public void setIdioma(String idioma) throws Exception {
	    String caminhoArquivo = "/dados/" + idioma.toLowerCase() + ".csv";

	    try (Scanner scanner = new Scanner(getClass().getResourceAsStream(caminhoArquivo))) {
	        this.idiomaCorrente = idioma;
	        carregarTraducoes(scanner);
	    } catch (Exception e) {
	        throw new Exception("Idioma não encontrado ou erro ao carregar: " + idioma,
	        		e);
	    }
	}


	public String getIdiomaCorrente() {
		return idiomaCorrente;
	}

	public ArrayList<String> getIdiomas() {
		ArrayList<String> idiomas = new ArrayList<>();
		idiomas.add("Ingles");
		idiomas.add("Espanhol");
		idiomas.add("Alemao");
		return idiomas;
	}

	private void carregarTraducoes(Scanner scanner) {
	    traducoes.clear();
	    while (scanner.hasNextLine()) {
	        String linha = scanner.nextLine();
	        String[] partes = linha.split(";");
	        if (partes.length == 2) {
	            traducoes.add(partes);
	        }
	    }
	}

	public ArrayList<String> traduzirParaPortugues(String termo) {
	    ArrayList<String> resultados = new ArrayList<>();
	    for (String[] traducao : traducoes) {
	        if (traducao[0].equalsIgnoreCase(termo)) {
	            resultados.add(traducao[1]);
	        }
	    }
	    if (resultados.isEmpty()) {
	        throw new IllegalArgumentException("Nenhuma tradução encontrada para o termo: " + termo);
	    }
	    return resultados;
	}

	public ArrayList<String> traduzirParaIdioma(String termo) {
		ArrayList<String> resultados = new ArrayList<>();
		for (String[] traducao : traducoes) {
			if (traducao[1].equalsIgnoreCase(termo)) {
				resultados.add(traducao[0]);
			}
		}
		return resultados;
	}

	public ArrayList<String> localizarPalavraIdioma(String termo) {
		ArrayList<String> resultados = new ArrayList<>();
		for (String[] traducao : traducoes) {
			if (traducao[0].toLowerCase().contains(termo.toLowerCase())) {
				resultados.add(traducao[0]);
			}
		}
		return resultados;
	}

	public ArrayList<String> localizarPalavraPortugues(String termo) {
		ArrayList<String> resultados = new ArrayList<>();
		for (String[] traducao : traducoes) {
			if (traducao[1].toLowerCase().contains(termo.toLowerCase())) {
				resultados.add(traducao[1]);
			}
		}
		return resultados;
	}
}
