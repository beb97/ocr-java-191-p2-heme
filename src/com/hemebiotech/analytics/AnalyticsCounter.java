package com.hemebiotech.analytics;

import java.io.*;
import java.util.ArrayList;
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
		List<String> symptoms = new ArrayList<String>();

		ISymptomReader reader = new ReadSymptomDataFromFile(inputFile);
		symptoms = reader.getSymptoms();

		return symptoms;
	}

	public Map<String, Integer> countSymptoms(List<String> symptomsList) {
		Map<String, Integer> symptoms = new TreeMap<String, Integer>();

		for(String line : symptomsList) {
			if ( symptoms.containsKey(line) ) {
				// si la ligne contient déjà la ligne, on incrémente le compteur qui est associé
				symptoms.put(line, symptoms.get(line)+1);
			}
			else {
				// sinon on ajoute l'association en initialisant le compteur à 1
				symptoms.put(line, 1);
			}
		}

		return symptoms;
	}

	public void sortSymptoms() {
		//Deja fait par le countSymptoms (usage d'un TREEMAP)
	}

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
