package dev.anurag.handbook.lld.dropbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Calculates the differences (chunks) between online and offline document trees.
 */
public class ChunksCalculator {
    public static void main(String[] args) {
        Node T1 = OnlineData.getOnlineDocs();
        Node T2 = OfflineData.getOfflineDocs();
        if (T1.children.length == 0 || T2.children.length == 0) {
            System.out.println("Document Tree is missing for either T1 or T2. Please check");
            return;
        }
        List<String> res = new ArrayList<>();
        List<String> T1_List = new ArrayList<>();
        inorder(T1, T1_List);
        List<String> T2_List = new ArrayList<>();
        inorder(T2, T2_List);
        Collections.sort(T1_List);
        Collections.sort(T2_List);
        int i = 0;
        while (i < T1_List.size()) {
            if (!T1_List.get(i).equals(T2_List.get(i))) {
                System.out.println(T1_List.get(i) + " :: " + T2_List.get(i));
                res.add("DELETE " + T1_List.get(i));
                res.add("ADD " + T2_List.get(i));
            }
            i++;
        }
        System.out.println("\n\nModification Require for Online Tree T1 are ");
        System.out.println(res);
    }

    /**
     * Inorder traversal of the document tree.
     */
    private static void inorder(Node node, List<String> list) {
        if (node == null) return;
        int total = node.children.length;
        for (int i = 0; i < total - 1; i++)
            inorder(node.children[i], list);
        list.add(node.document);
        inorder(node.children[total - 1], list);
    }
}
