package com.codex.system.design.filesystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Implements an in-memory file system supporting ls, mkdir, addContentToFile, and readContentFromFile.
 * Reference: https://medium.com/root-node/design-in-memory-file-system-96ee6c484616
 */
public class InMemoryFileSystem {
    /**
     * Represents a file or directory in the file system.
     */
    private static class File {
        boolean isFile = false;
        HashMap<String, File> files = new HashMap<>();
        String content = "";
    }

    private final File root;

    /**
     * Initializes the in-memory file system.
     */
    public InMemoryFileSystem() {
        root = new File();
    }

    /**
     * Lists files and directories at the given path.
     * @param path the path to list
     * @return list of file or directory names
     */
    public List<String> ls(String path) {
        File t = root;
        List<String> files = new ArrayList<>();
        if (!path.equals("/")) {
            String[] d = path.split("/");
            for (int i = 1; i < d.length; i++) {
                t = t.files.get(d[i]);
            }
            if (t.isFile) {
                files.add(d[d.length - 1]);
                return files;
            }
        }
        List<String> resFiles = new ArrayList<>(t.files.keySet());
        Collections.sort(resFiles);
        return resFiles;
    }

    /**
     * Creates a directory at the given path.
     * @param path the directory path
     */
    public void mkdir(String path) {
        File t = root;
        String[] d = path.split("/");
        for (int i = 1; i < d.length; i++) {
            if (!t.files.containsKey(d[i]))
                t.files.put(d[i], new File());
            t = t.files.get(d[i]);
        }
    }

    /**
     * Adds content to a file at the given path.
     * @param filePath the file path
     * @param content the content to add
     */
    public void addContentToFile(String filePath, String content) {
        File t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            t = t.files.get(d[i]);
        }
        if (!t.files.containsKey(d[d.length - 1]))
            t.files.put(d[d.length - 1], new File());
        t = t.files.get(d[d.length - 1]);
        t.isFile = true;
        t.content = t.content + content;
    }

    /**
     * Reads content from a file at the given path.
     * @param filePath the file path
     * @return the file content
     */
    public String readContentFromFile(String filePath) {
        File t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            t = t.files.get(d[i]);
        }
        return t.files.get(d[d.length - 1]).content;
    }
}
