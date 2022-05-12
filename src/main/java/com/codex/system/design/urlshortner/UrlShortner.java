package com.codex.system.design.urlshortner;

public class UrlShortner {


	private ShortURL idToShortURL(int n)
	{
	    //Map to store 62 possible characters
	    char[] map= new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

	  
	    ShortURL  shorturl = null;
	  
	    // Convert given integer id to a base 62 number
	    while (n>0)
	    {
	        shorturl.push_back(map[n%62]);
	        n = n/62;
	    }
	  
	    // Reverse shortURL to complete base conversion
	    reverse(begin(shorturl), end(shorturl));
	  
	    return shorturl;
	}
	private void reverse(Object begin, Object end) {
		// TODO Auto-generated method stub
		
	}
	
	private static String begin(ShortURL shorturl) {
		return "";
	}
	private static String end(ShortURL shorturl) {
		return "";
	}
	  
	// Function to get integer ID back from a short url
	private  long shortURLtoID(String shortURLString)
	{
		char[] shortURL = shortURLString.toCharArray();
	    long id = 0; // initialize result
	  
	    // A simple base conversion logic
	    for (int i=0; i < shortURL.length; i++)
	    {
	        if ('a' <= shortURL[i] && shortURL[i] <= 'z')
	          id = id*62 + shortURL[i] - 'a';
	        if ('A' <= shortURL[i] && shortURL[i] <= 'Z')
	          id = id*62 + shortURL[i] - 'A' + 26;
	        if ('0' <= shortURL[i] && shortURL[i] <= '9')
	          id = id*62 + shortURL[i] - '0' + 52;
	    }
	    return id;
	}
}
