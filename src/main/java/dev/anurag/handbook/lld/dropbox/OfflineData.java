package dev.anurag.handbook.lld.dropbox;

/**
 * Provides offline document tree data for Dropbox design.
 */
public class OfflineData {
    /**
     * Returns the root node of the offline document tree.
     */
    public static Node getOfflineDocs() {
        Node root = new Node("Documents", 3);
        root.children[0] = new Node("Documents\\PDF", 4);
        root.children[1] = new Node("Documents\\JPG", 2);
        root.children[2] = new Node("Documents\\XLS", 2);
        root.children[0].children[0] = new Node("Documents\\PDF\\A.pdf", 1);
        root.children[0].children[1] = new Node("Documents\\PDF\\B.pdf", 2);
        root.children[0].children[2] = new Node("Documents\\PDF\\X.pdf", 3);
        root.children[1].children[0] = new Node("Documents\\JPG\\C.jpg", 4);
        root.children[1].children[1] = new Node("Documents\\JPG\\D.jpg", 5);
        root.children[2].children[0] = new Node("Documents\\XLS\\E.xls", 6);
        root.children[2].children[1] = new Node("Documents\\XLS\\F.xls", 7);
        return root;
    }
}
