package com.codex.system.design.dropbox;

/**
 * Represents a node in the document tree for Dropbox design.
 */
public class Node {
    public String document;
    public Node[] children;

    public Node(String document, int level) {
        this.document = document;
        children = new Node[level];
    }
}
