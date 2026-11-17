package dev.anurag.handbook.lld.urlshortener;

/**
 * Implements a URL shortener with base62 encoding/decoding.
 */
public class UrlShortner {
    /**
     * Converts an integer ID to a ShortURL using base62 encoding.
     */
    private ShortURL idToShortURL(int n) {
        // Map to store 26 possible characters (should be 62 for full base62)
        char[] map = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        ShortURL shorturl = new ShortURL();
        // Convert given integer id to a base 62 number
        while (n > 0) {
            shorturl.push_back(map[n % 26]); // Only 26 chars used for now
            n = n / 26;
        }
        // Reverse shortURL to complete base conversion (placeholder)
        reverse(begin(shorturl), end(shorturl));
        return shorturl;
    }
    private void reverse(Object begin, Object end) {
        // Placeholder for reverse logic
    }
    private static String begin(ShortURL shorturl) { return ""; }
    private static String end(ShortURL shorturl) { return ""; }
    /**
     * Converts a short URL string back to an integer ID using base62 decoding.
     */
    private long shortURLtoID(String shortURLString) {
        char[] shortURL = shortURLString.toCharArray();
        long id = 0;
        // A simple base conversion logic
        for (int i = 0; i < shortURL.length; i++) {
            if ('a' <= shortURL[i] && shortURL[i] <= 'z')
                id = id * 62 + shortURL[i] - 'a';
            if ('A' <= shortURL[i] && shortURL[i] <= 'Z')
                id = id * 62 + shortURL[i] - 'A' + 26;
            if ('0' <= shortURL[i] && shortURL[i] <= '9')
                id = id * 62 + shortURL[i] - '0' + 52;
        }
        return id;
    }
}
