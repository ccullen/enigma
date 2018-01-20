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

public class Reflector 
{
	// Static Class Variable
	private static final String[][] REFLECTORLIBRARY = 
		{
			{"German Railway","UKW","QYHOGNECVPUZTFDJAXWMKISRBL",null},
			{"Swiss K","UKW-K","IMETCGFRAYSQBZXWLHKDVUPOJN",null},
			{"Enigma I","Reflector A","EJMZALYXVBWFCRQUONTSPIKHGD",null},
			{"Enigma I","Reflector B","YRUHQSLDPXNGOKMIEBFZCWVJAT",null},
			{"Enigma I","Reflector C","FVPJIAOYEDRZXWGCTKUQSBNMHL",null},
			{"M4 R1","Reflector B Thin","ENKQAUYWJICOPBLMDXZVFTHRGS",null},
			{"M4 R1","Reflector C Thin","RDOBJNTKVEHMLFCWZAXGYIPSUQ",null}
			//Beta and Gamma Rotors must use B Thin or C Thin Reflector
		};
	
	// Non-Static Class Variables
	private String reflectorEncoding;
	private boolean defaultFlag;
	
	// Default Constructor - selects Reflector B of Enigma I by default.
	public Reflector()
	{
		reflectorEncoding = REFLECTORLIBRARY[3][2];
		defaultFlag = true;
		
		System.out.printf(	"%nNo reflector selected. Reflector is set as %s %s by default.%n",
							REFLECTORLIBRARY[3][0], REFLECTORLIBRARY[3][1]);
	}	
	
	// Alternate Constructor - requires that Machine Type and Reflector Type are specified in argument.
	public Reflector(String machineType, String reflectorDesignation)
	{
		reflectorEncoding = REFLECTORLIBRARY[3][2];
		defaultFlag = true;
		
		for (int i = 0; i < REFLECTORLIBRARY.length; i++)
		{
			if (		REFLECTORLIBRARY[i][0].equalsIgnoreCase(machineType) && 
					REFLECTORLIBRARY[i][1].equalsIgnoreCase(reflectorDesignation)) 
			{
				reflectorEncoding = REFLECTORLIBRARY[i][2];
				defaultFlag = false;
				break;
			}
		}
		
		// Defaults to Reflector B of Enigma I if specified Machine Type and Reflector Type do not match any in REFLECTORLIBRARY.
		if (defaultFlag == true)
		{
			System.out.printf(	"%nInvalid reflector selection. Reflector is set as %s %s by default.%n",
								REFLECTORLIBRARY[3][0], REFLECTORLIBRARY[3][1]);
		}	
	}
	
	// Accessors
	public String getReflectorEncoding()
	{
		return reflectorEncoding;
	}
	
	// Static Methods
	public static String[][] getREFLECTORLIBRARY()
	{
		return REFLECTORLIBRARY;
	}

}
