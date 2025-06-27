package com.codex.system.design.autocomplete;

import java.util.*;

/**
 * Autocomplete system using Trie data structure.
 * Reference: https://aaronice.gitbook.io/lintcode/data_structure/design-search-autocomplete-system
 */
public class AutocompleteSearch {
    /**
     * Trie node for storing children and sentence counts.
     */
    private static class Trie {
        Map<Character, Trie> children = new HashMap<>();
        Map<String, Integer> counts = new HashMap<>();
        boolean isWord = false;
    }

    /**
     * Helper class for storing sentence and its count.
     */
    private static class Pair {
        String str;
        int count;
        Pair(String str, int count) {
            this.str = str;
            this.count = count;
        }
    }

    private final Trie root;
    private String prefix;

    /**
     * Initializes the autocomplete system with given sentences and their times.
     * @param sentences array of sentences
     * @param times array of times corresponding to each sentence
     */
    public AutocompleteSearch(String[] sentences, int[] times) {
        root = new Trie();
        prefix = "";
        for (int i = 0; i < sentences.length; i++) {
            add(sentences[i], times[i]);
        }
    }

    /**
     * Adds a sentence to the Trie with its count.
     * @param str the sentence
     * @param count the count
     */
    private void add(String str, int count) {
        Trie node = root;
        for (char c : str.toCharArray()) {
            node.children.putIfAbsent(c, new Trie());
            node = node.children.get(c);
            node.counts.put(str, node.counts.getOrDefault(str, 0) + count);
        }
        node.isWord = true;
    }

    /**
     * Searches for top 3 autocomplete suggestions for the current prefix.
     * @param c next character input
     * @return list of top 3 suggestions
     */
    public List<String> search(char c) {
        if (c == '#') {
            add(prefix, 1);
            prefix = "";
            return new ArrayList<>();
        }
        prefix += c;
        Trie node = root;
        for (char cc : prefix.toCharArray()) {
            node = node.children.get(cc);
            if (node == null) return new ArrayList<>();
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.count == b.count ? a.str.compareTo(b.str) : b.count - a.count);
        for (String str : node.counts.keySet()) {
            pq.offer(new Pair(str, node.counts.get(str)));
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
            res.add(pq.poll().str);
        }
        return res;
    }
}
