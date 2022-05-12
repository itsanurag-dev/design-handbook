package com.codex.system.design.dropbox;

public class Node {
	String document;
	Node children[];

	public Node(String document, int level) {
		this.document = document;
		children = new Node[level];
	}

}
