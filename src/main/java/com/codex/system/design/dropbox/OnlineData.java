package com.codex.system.design.dropbox;

/**
 * Provides online document tree data for Dropbox design.
 */
public class OnlineData {
    /**
     * Returns the root node of the online document tree.
     */
    public static Node getOnlineDocs() {
        Node root = new Node("Documents", 3);
        root.children[0] = new Node("Documents\\PDF", 2);
        root.children[1] = new Node("Documents\\JPG", 2);
        root.children[2] = new Node("Documents\\XLS", 2);
        root.children[0].children[0] = new Node("Documents\\PDF\\A.pdf", 1);
        root.children[0].children[1] = new Node("Documents\\PDF\\B.pdf", 1);
        root.children[1].children[0] = new Node("Documents\\JPG\\D.jpg", 1);
        root.children[1].children[1] = new Node("Documents\\JPG\\C.jpg", 1);
        root.children[2].children[0] = new Node("Documents\\XLS\\E.xls", 1);
        root.children[2].children[1] = new Node("Documents\\XLS\\F.xls", 1);
        return root;
    }
}
