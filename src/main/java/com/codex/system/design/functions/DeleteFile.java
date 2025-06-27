package com.codex.system.design.functions;

/**
 * Utility for deleting files or folders (simulated logic).
 */
public class DeleteFile {
    public static void main(String[] args) {
        System.out.println("Delete the folder from path : ");
        File.delete("/home/antiwari/temp");
    }

    /**
     * Simulates file/folder deletion logic.
     */
    static class File {
        private static void delete(String path) {
            String[] folders = path.split("/");
            for (String folder : folders) {
                if (isFile(folder)) {
                    // Simulate file deletion
                    folder = null;
                    return;
                }
                // Simulate folder deletion
                folder = null;
                return;
            }
        }

        private static boolean isFile(String folder) {
            return !hasChild(folder);
        }

        private static boolean hasChild(String folder) {
            String[] folders = folder.split("/");
            return folders.length > 0;
        }
    }
}
