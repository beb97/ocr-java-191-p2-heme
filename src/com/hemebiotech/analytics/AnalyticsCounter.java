package com.hemebiotech.analytics;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AnalyticsCounter {

	private String inputFile;
	private String outputFile;

	public AnalyticsCounter(String inputFile, String outputFile) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}

	//* récupérer les strings d’un fichier,
	public List<String> getListSymptoms() {
		ISymptomReader reader = new ReadSymptomDataFromFile(inputFile);
		return reader.getSymptoms();
	}

	//* compter les occurrences d’une liste de symptomes
	public Map<String, Integer> countSymptoms(List<String> symptomsList) {
		// Choix d'un treemap car les clés sont triées
		Map<String, Integer> symptoms = new TreeMap<String, Integer>();

		for(String line : symptomsList) {
			// si la ligne contient déjà la ligne, on incrémente le compteur qui est associé
			if ( symptoms.containsKey(line) ) {
				symptoms.put(line, symptoms.get(line)+1);
			}
			// sinon on ajoute l'association en initialisant le compteur à 1
			else {
				symptoms.put(line, 1);
			}
		}

		return symptoms;
	}

	public void sortSymptoms() {
		//Deja fait par le countSymptoms (usage d'un TREEMAP)
	}

	//* ressortir dans un fichier txt les symptomes et leur quantité
	public void writeResult(Map<String, Integer> symptoms) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(outputFile);

			for (Map.Entry<String, Integer> symptom : symptoms.entrySet() ) {
				writer.write(symptom.getKey() +" : "+symptom.getValue()+"\n");
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			close(writer);
		}
	}

	public void close(Closeable c) {
		if (c == null) return;
		try {
			c.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
