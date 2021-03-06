package com.aqa.kb;

import com.aqa.kb.Document;
import com.aqa.kb.Triple;
import com.aqa.extraction.ExtractorCoordinator;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TripleStore {


    /**
     * Flag to print extra information and exactly what is happening.
     */
    private boolean explicit;

    /**
     * Flag to print statistics about what is being stored in the documents.
     */
    private boolean stats;

    /**
     * The ExtractorCoordinator to extract triples.
     */
    private ExtractorCoordinator extractors;

    /**
     * The store of all the triples we are storing.
     *   The primary key is the for all information regarding the key.  
     */
    private Map<String, Collection<Triple>> tripleMap;

    public TripleStore(boolean explicit, boolean stats) {
        this.explicit = explicit;
        this.stats = stats;

        this.tripleMap = new HashMap<String, Collection<Triple>>();
        this.extractors = new ExtractorCoordinator();
    }

    public void createTriples(int sentenceNumber, String sentence, Document currentDoc) {

        ArrayList<Triple> triples = extractors.extractRelations(sentenceNumber, sentence, currentDoc);

        for(Triple t : triples){

            Object[] o = t.getTriple();
            Collection<Triple> coll = tripleMap.get((String) o[0]);
            // If we don't have a Colelction, create one
            if(coll == null)
                coll = new ArrayList<Triple>();
            coll.add(t);
            tripleMap.put((String) o[0], coll);
        }
 
    }

    public Collection<Triple> getTriplesOfSubject(String subject) {
        return tripleMap.get(subject);
    }

    public Map<String, Collection<Triple>> getStore() {
        return tripleMap;
    }

    public void printStore() {
        for(String key : tripleMap.keySet()) {
            System.out.println("\nKey: " + key + "\n[");
            for(Triple t : tripleMap.get(key))
                System.out.println(" " + t);
            System.out.println("]");
        }
    }
    
}
