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

public class EntryWheel 
{
	// Static Class Variable
	private static final String[][] ETWLIBRARY = 
		{
			{"German Railway","ETW","QWERTZUIOASDFGHJKPYXCVBNML",null},
			{"Swiss K","ETW-K","QWERTZUIOASDFGHJKPYXCVBNML",null},
			{"Enigma I","ETW","ABCDEFGHIJKLMNOPQRSTUVWXYZ",null},
		};
	
	// Non-Static Class Variables
	private String etwEncoding;
	private boolean defaultFlag;
	
	// Default Constructor - selects ETW of Enigma I by default.
	public EntryWheel()
	{
		etwEncoding = ETWLIBRARY[2][2];
		defaultFlag = true;
		
		System.out.printf(	"%nNo entry wheel selected. Entry wheel is set as %s %s by default.%n",
							ETWLIBRARY[2][0], ETWLIBRARY[2][1]);
	}	
	
	// Alternate Constructor - requires that Machine Type and Entry Wheel Type are specified in argument.
	public EntryWheel(String machineType, String etwDesignation)
	{
		etwEncoding = ETWLIBRARY[2][2];
		defaultFlag = true;
		
		for (int i = 0; i < ETWLIBRARY.length; i++)
		{
			if (		ETWLIBRARY[i][0].equalsIgnoreCase(machineType) && 
					ETWLIBRARY[i][1].equalsIgnoreCase(etwDesignation)) 
			{
				etwEncoding = ETWLIBRARY[i][2];
				defaultFlag = false;
				break;
			}
		}
		
		// Defaults to ETW of Enigma I if specified Machine Type and Entry Wheel Type do not match any in ETWLIBRARY.
		if (defaultFlag == true)
		{
			System.out.printf(	"%nInvalid entry wheel selection. Entry wheel is set as %s %s by default.%n",
								ETWLIBRARY[2][0], ETWLIBRARY[2][1]);
		}	
	}
	
	// Accessors
	public String getETWEncoding()
	{
		return etwEncoding;
	}
	
	// Static Methods
	public static String[][] getETWLIBRARY()
	{
		return ETWLIBRARY;
	}

}
