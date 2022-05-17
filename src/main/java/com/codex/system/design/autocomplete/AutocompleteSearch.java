package com.codex.system.design.autocomplete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// https://aaronice.gitbook.io/lintcode/data_structure/design-search-autocomplete-system

public class AutocompleteSearch {

	class Trie {
		Map<Character, Trie> children;
		Map<String, Integer> counts;
		boolean isWord;

		public Trie() {
			children = new HashMap<>();
			counts = new HashMap<>();
			isWord = false;
		}
	}

	Trie root;
	String prefix;

	public AutocompleteSearch(String[] sentences, int[] times) {
		root = new Trie();
		prefix = "";

		for (int i = 0; i < sentences.length; i++) {
			add(sentences[i], times[i]);
		}

	}

	private void add(String str, int count) {

		char[] ch = str.toCharArray();
		Trie node = root;

		for (char c : ch) {
			Trie nextNode = node.children.get(c);
			if (nextNode == null) {
				nextNode = new Trie();
				node.children.put(c, nextNode);
			}
			node = nextNode;
			node.counts.put(str, node.counts.getOrDefault(str, 0) + count);
		}
		node.isWord = true;
	}
	
	public List<String> search(char c){
		if (c == '#') {
            add(prefix, 1);
            prefix = "";
            return new ArrayList<>();
        }

        prefix = prefix + c;
        Trie node = root;
        for (char cc: prefix.toCharArray()) {
            node = node.children.get(cc);
            if (node == null) 
            	return new ArrayList<>();
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> (o1.count == o2.count ? o1.str.compareTo(o2.str) : o2.count - o1.count));

        for (String str: node.counts.keySet()) {
            pq.add(new Pair(str, node.counts.get(str)));
        }
		List<String> res = new ArrayList<String>();
		for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
            Pair pair = pq.poll();
            res.add(pair.str);
        }

        return res;
		
	}
	class Pair {
        String str;
        int count;
        public Pair(String str, int count) {
            this.str = str;
            this.count = count;
        }
    }
}
