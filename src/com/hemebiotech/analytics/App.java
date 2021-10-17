package com.hemebiotech.analytics;

import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        String inputFile = "symptoms.txt";
        String outputFile = "output.txt";

        AnalyticsCounter ac = new AnalyticsCounter(inputFile, outputFile);

        //* récupérer les strings d’un fichier,
        List symptoms = ac.getListSymptoms();

        //* compter les occurrences d’une string,
        Map symptomsMap = ac.countSymptoms(symptoms);

        //* mettre les strings dans l’ordre alphabétique,
        ac.sortSymptoms();

        //* ressortir dans un fichier les strings dans l’ordre
        ac.writeResult(symptomsMap);
    }
}
