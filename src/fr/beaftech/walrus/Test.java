/*
Copyright (C) 2015  Arnould GUIDAT

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation; version 3 of the License.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package fr.beaftech.walrus;



public class Test {
	
	public static void main(String args[]){
		
		Translator t = Translator.getInstance();
		
		String message = "alpha";
		
		
		Player p = new Player();
		p.setDir("D:/PERSO/morse/");
		
		p.generateSound(t.translateToMorse(message));
		p.playSound();

		
	}

}
