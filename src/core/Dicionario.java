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
		File arquivo = new File("src/dados/" + idioma.toLowerCase() + ".csv");
		if (!arquivo.exists()) {
			throw new Exception("Idioma não encontrado: " + idioma);
		}

		this.idiomaCorrente = idioma;
		carregarTraducoes(arquivo);
	}

	public String getIdiomaCorrente() {
		return idiomaCorrente;
	}

	public ArrayList<String> getIdiomas() {
		ArrayList<String> idiomas = new ArrayList<>();
		idiomas.add("Inglês");
		idiomas.add("Espanhol");
		idiomas.add("Alemão");
		return idiomas;
	}

	private void carregarTraducoes(File arquivo) throws FileNotFoundException {
		traducoes.clear();
		Scanner scanner = new Scanner(arquivo);
		while (scanner.hasNextLine()) {
			String linha = scanner.nextLine();
			String[] partes = linha.split(";");
			if (partes.length == 2) {
				traducoes.add(partes);
			}
		}
		scanner.close();
	}

	public ArrayList<String> traduzirParaPortugues(String termo) {
		ArrayList<String> resultados = new ArrayList<>();
		for (String[] traducao : traducoes) {
			if (traducao[0].equalsIgnoreCase(termo)) {
				resultados.add(traducao[1]);
			}
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
