package com.codex.system.design.dropbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChunksCalculator {

	public static void main(String[] args) {
		Node T1 = OnlineData.getOnlineDocs();
		Node T2 = OfflineData.getOfflineDocs();
		
		if(T1.children.length == 0 || T2.children.length == 0) {
			System.out.println("Document Tree is missing for either T1 or T2. Please check");
			return;
		}
		List<String> res = new ArrayList<>();
		
		List<String> T1_List = new ArrayList<String>();
		inorder(T1, T1_List);		
		List<String> T2_List = new ArrayList<String>();
		inorder(T2, T2_List);

		Collections.sort(T1_List);
		Collections.sort(T2_List);
		int i = 0;
		while(i < T1_List.size()) {
			if(!T1_List.get(i).equals(T2_List.get(i))) {
				System.out.println(T1_List.get(i)+" :: "+T2_List.get(i));
				res.add("DELETE "+T1_List.get(i));
				res.add("ADD "+T2_List.get(i));
			}
			
			i++;
		}
		
//		for(String ele:T2_List) {
//			if (!T1_List.contains(ele)) {
//				res.add("ADD "+ele);
//			}
//		}
//
//		for(String ele:T1_List) {
//			if (!T2_List.contains(ele)) {
//				res.add("DELETE "+ele);
//			}
//		}
		System.out.println("\n\nModification Require for Online Tree T1 are ");
		System.out.println(res);
	}

	private static void inorder(Node node, List<String> list) {
		if (node == null)
			return;

		// Total children count
		int total = node.children.length;
		// All the children except the last
		for (int i = 0; i < total - 1; i++)
			inorder(node.children[i], list);

		// Print the current node's data
		list.add(node.document);

		// Last child
		inorder(node.children[total - 1],list);
	}
}
