/* Project: 	Enigma Simulator
 * Author: 	Chad Cullen
 * Date:		January 1, 2018
 * 
 * Description: This code simulates an electro-mechanical rotor cipher
 * machine commonly referred to as an Enigma machine.
 * 
 * Copyright (c) 2018 Chad Cullen. All rights reserved.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

public class Rotor 
{	
	// Static Class Variables
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String[][] ROTORLIBRARY = 
		{
			{"Commercial Enigma","IC","DMTWSILRUYQNKFEJCAZBPGXOHV","Q"},
			{"Commercial Enigma","IIC","HQZGPJTMOBLNCIFDYAWVEUSRKX","E"},
			{"Commercial Enigma","IIIC","UQNTLSZFMREHDPXKIBVYGJCWOA","V"},
			{"German Railway","I","JGDQOXUSCAMIFRVTPNEWKBLZYH","Q"},
			{"German Railway","II","NTZPSFBOKMWRCJDIVLAEYUXHGQ","E"},
			{"German Railway","III","JVIUBHTCDYAKEQZPOSGXNRMWFL","V"},
			{"Swiss K","I-K","PEZUOHXSCVFMTBGLRINQJWAYDK","Q"},
			{"Swiss K","II-K","ZOUESYDKFWPCIQXHMVBLGNJRAT","E"},
			{"Swiss K","III-K","EHRVXGAOBQUSIMZFLYNWKTPDJC","V"},
			{"Enigma I","I","EKMFLGDQVZNTOWYHXUSPAIBRCJ","Q"},
			{"Enigma I","II","AJDKSIRUXBLHWTMCQGZNPYFVOE","E"},
			{"Enigma I","III","BDFHJLCPRTXVZNYEIWGAKMUSQO","V"},
			{"M3 Army","IV","ESOVPZJAYQUIRHXLNFTGKDCMWB","J"},
			{"M3 Army","V","VZBRGITYUPSDNHLXAWMJQOFECK","Z"},
			{"M3 & M4 Naval","VI","JPGVOUMFYQBENHZRDKASXLICTW","ZM"},
			{"M3 & M4 Naval","VII","NZJHGRCXMYSWBOUFAIVLPEKQDT","ZM"},
			{"M3 & M4 Naval","VIII","FKQHTLXOCBJSPDZRAMEWNIUYGV","ZM"},
			{"M4 R2","Beta","LEYJVCNIXWPBQMDRTAKZGFUHOS",null},
			{"M4 R2","Gamma","FSOKANUERHMBTIYCWLQPZXVGJD",null}
		};
	
	// Non-Static Class Variables
	private String rotorEncoding;
	private int notchPosition;
	private boolean defaultFlag;
	
	// Default Constructor - selects Rotor I of Enigma I by default.
	public Rotor()
	{
		rotorEncoding = ROTORLIBRARY[9][2];
		notchPosition = ROTORLIBRARY[9][2].indexOf(ROTORLIBRARY[9][3]);
		defaultFlag = true;
		
		System.out.printf(	"%nNo rotor selected. Rotor is set as %s rotor %s by default.%n",
							ROTORLIBRARY[9][0], ROTORLIBRARY[9][1]);
	}	
	
	// Alternate Constructor - requires that Machine Type and Rotor Type are specified in argument.
	public Rotor(String machineType, String rotorDesignation) 
	{
		rotorEncoding = ROTORLIBRARY[9][2];
		notchPosition = ROTORLIBRARY[9][2].indexOf(ROTORLIBRARY[9][3]);
		defaultFlag = true;
		
		for (int i = 0; i < ROTORLIBRARY.length; i++)
		{
			if (		ROTORLIBRARY[i][0].equalsIgnoreCase(machineType) && 
					ROTORLIBRARY[i][1].equalsIgnoreCase(rotorDesignation)) 
			{
				rotorEncoding = ROTORLIBRARY[i][2];
				defaultFlag = false;
				
				if (ROTORLIBRARY[i][3].length() == 1) // Can be simplified later. Sets notch position.
				{
					notchPosition = ALPHABET.indexOf(ROTORLIBRARY[i][3]);
				} 
				else if (ROTORLIBRARY[i][3].length() == 2)
				{
					notchPosition = ALPHABET.indexOf(ROTORLIBRARY[i][3].charAt(0)); // Does not handle multiple notches.
					System.out.printf(	"%nThe %s rotor %s has 2 notches. This code does " +
										"not implement the second notch at %c yet.%n",
										ROTORLIBRARY[i][0], ROTORLIBRARY[i][1], ROTORLIBRARY[i][3].charAt(1));
				}
				
				break;
			}
		}
		
		// Defaults to Rotor I of Enigma I if specified Machine Type and Rotor Type do not match any in ROTORLIBRARY.
		if (defaultFlag == true)
		{
			System.out.printf(	"%nInvalid rotor selection for Rotor. Rotor is set as %s rotor %s by default.%n",
								ROTORLIBRARY[9][0], ROTORLIBRARY[9][1]);
		}	
	}
	
	// Accessors
	public String getRotorEncoding()
	{
		return rotorEncoding;
	}
	
	public int getNotchPosition()
	{
		return notchPosition;
	}
	
	public String getAlphabet()
	{
		return ALPHABET;
	}
	
	// Static Methods
	public static String[][] getROTORLIBRARY()
	{
		return ROTORLIBRARY;
	}
}
