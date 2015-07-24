/*
Copyright (C) 2015  Arnould GUIDAT

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; version 2 of the License.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package fr.beaftech.walrus;

import java.util.HashMap;
import java.util.Map;

public class Translator {
	
	private static final String DOT = ".";
	private static final String DASH ="_";
	
	private static Translator instance;
	
	private Map<String,Character> morse2alpha;
	private Map<Character,String> alpha2morse;
	
	
	private Translator(){
		morse2alpha = new HashMap<String, Character>(27);
		alpha2morse = new HashMap<Character, String>(27);
		
		addAlpha2Morse('a', DOT+DASH );
		addAlpha2Morse('b', DASH+DOT+DOT+DOT );
		addAlpha2Morse('c', DASH+DOT+DASH+DOT );
		addAlpha2Morse('d', DASH+DOT+DOT );
		addAlpha2Morse('e', DOT );
		addAlpha2Morse('f', DOT+DOT+DASH+DOT );
		addAlpha2Morse('g', DASH+DASH+DOT );
		addAlpha2Morse('h', DOT+DOT+DOT+DOT);
		addAlpha2Morse('i', DOT+DOT );
		addAlpha2Morse('j', DOT+DASH+DASH+DASH );
		addAlpha2Morse('k', DASH+DOT+DASH );
		addAlpha2Morse('l', DOT+DASH+DOT+DOT );
		addAlpha2Morse('m', DASH+DASH );
		addAlpha2Morse('n', DASH+DOT );
		addAlpha2Morse('o', DASH+DASH+DASH );
		addAlpha2Morse('p', DOT+DASH+DASH+DOT );
		addAlpha2Morse('q', DASH+DASH+DOT+DASH );
		addAlpha2Morse('r', DOT+DASH+DOT );
		addAlpha2Morse('s', DOT+DOT+DOT );
		addAlpha2Morse('t', DASH );
		addAlpha2Morse('u', DOT+DOT+DASH );
		addAlpha2Morse('v', DOT+DOT+DOT+DASH );
		addAlpha2Morse('w', DOT+DASH+DASH );
		addAlpha2Morse('x', DASH+DOT+DOT+DASH );
		addAlpha2Morse('y', DASH+DOT+DASH+DASH );
		addAlpha2Morse('z', DASH+DASH+DOT+DOT );
		
		addAlpha2Morse(' ', "|" );
	}
	
	public static Translator getInstance(){
		if(instance == null){
			instance = new Translator();
		}
		return instance;
	}
	
	private void addAlpha2Morse(Character alpha, String morse){
		alpha2morse.put(alpha, morse);
		morse2alpha.put(morse, alpha);
	}
	

	public String translateToMorse(String message){
		StringBuilder result = new StringBuilder();
		String s;
		
		message = message.toLowerCase();
		
		for(char c : message.toCharArray()){
			s = alpha2morse.get(c);
			if(s != null){
				result.append(s).append("'");
			}
		}

		
		return result.toString();
	}
	


}
