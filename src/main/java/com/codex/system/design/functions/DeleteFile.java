package com.codex.system.design.functions;

public class DeleteFile {
	
	public static void main(String [] args) {
		System.out.println("Delete the folder from path : ");
		File.delete("/home/antiwari/temp");
	}

	static class File {
		private static void delete(String path) {
			String[] folders = path.split("/");
			for (String folder : folders) {
				if (isFile(folder)) {
					folder = null;
					return;
				}
				folder = null;
				return;
			}
		}

		private static boolean isFile(String folder) {
			if (hasChild(folder)) {
				return false;
			}
			return true;
		}

		private static boolean hasChild(String folder) {
			String[] folders = folder.split("/");
			return folders.length > 0;
		}
	}

}
