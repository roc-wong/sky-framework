package org.sky.framework.test.encoding;

import java.io.UnsupportedEncodingException;

public class CharacterSet {
	public static String ascii2native(String ascii) {
			int n = ascii.length() / 6;
			StringBuilder sb = new StringBuilder(n);
			for (int i = 0, j = 2; i < n; i++, j += 6) {
				String code = ascii.substring(j, j + 4);
				char ch = (char) Integer.parseInt(code, 16);
				sb.append(ch);
			}
			return sb.toString();
		}
		
		public static String toGBK(String unicodeStr) {  
		    try {  
		        String gbkStr = new String(unicodeStr.getBytes("ISO8859-1"), "GBK");  
		        return gbkStr;  
		    } catch (UnsupportedEncodingException e) {  
		        return unicodeStr;  
		    }  
		}  
		
		
		public static String convert(String utfString){  
		    StringBuilder sb = new StringBuilder();  
		    int i = -1;  
		    int pos = 0;  
		      
		    while((i=utfString.indexOf("\\u", pos)) != -1){  
		        sb.append(utfString.substring(pos, i));  
		        if(i+5 < utfString.length()){  
		            pos = i+6;  
		            sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));  
		        }  
		    }  
		      
		    return sb.toString();  
		}  
}

