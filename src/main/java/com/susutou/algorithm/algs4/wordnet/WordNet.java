package com.susutou.algorithm.algs4.wordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * @author susen
 */
public class WordNet {
    private Digraph g;
    private Map<Integer, Set<String>> synsetMap;
    private Set<String> nounSet;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new NullPointerException();
        }

        synsetMap = new HashMap<>();
        nounSet = new HashSet<>();
        In in = new In(synsets);
        int size = 0;  // graph size

        // read synsets file
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] segments = line.split(",");
            int synsetId = Integer.parseInt(segments[0]);
            Set<String> synset = new HashSet<>(Arrays.asList(segments[1].split(" ")));
            synsetMap.put(synsetId, synset);
            nounSet.addAll(synset);
            size++;
        }

        g = new Digraph(size);
        in = new In(hypernyms);

        // read hypernyms file
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] segments = line.split(",");
            int synsetId = Integer.parseInt(segments[0]);
            for (int i = 1; i < segments.length; i++) {
                int hypernymId = Integer.parseInt(segments[i]);
                g.addEdge(synsetId, hypernymId);
            }
        }

        // TODO throw exception if g is not a rooted DAG
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nounSet;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) {
            throw new NullPointerException();
        }
        return nounSet.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) {
            throw new NullPointerException();
        }
        return 0;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        return null;
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}
