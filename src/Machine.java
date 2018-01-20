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

public class Machine {

	// Non-Static Class Variables
	private EntryWheel etw;
	private Rotor[] rotor;
	private Reflector reflector;
	private int[] rotorSetting, ringSetting;
	
	// Static Class Variable
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	// Default Constructor - selects Enigma I with ETW, Rotors III, II, & I, and Reflector B by default.
	public Machine()
	{
		// Entry Wheel
		etw = new EntryWheel("Enigma I","ETW");
		
		// Rotors
		rotor = new Rotor[3];
		rotor[0] = new Rotor("Enigma I","III");
		rotor[1] = new Rotor("Enigma I","II");
		rotor[2] = new Rotor("Enigma I","I");
		
		// Reflector
		reflector = new Reflector("Enigma I","Reflector B");		
		
		// Sets Rotor Setting to AAA (i.e., 000) by default.
		rotorSetting = new int[3];
		rotorSetting[0] = 0;
		rotorSetting[1] = 0;
		rotorSetting[2] = 0;
		
		// Sets Ring Setting to AAA (i.e., 000) by default.
		ringSetting = new int[3];
		ringSetting[0] = 0;
		ringSetting[1] = 0;
		ringSetting[2] = 0;
	}
	
	// Alternate Constructor - Requires a String Array as the argument
	public Machine(String[][] machineConfiguration)
	{
		// First row of String Array must be the Entry Wheel.
		// Subsequent rows of String Array must be Rotors. Any number of Rotors may be entered.
		// Last row of String Array must be the Reflector.
		// Columns of String Array must be Machine Type and Type of Entry Wheel, Rotor, or Reflector.
		
		// Sets the Entry Wheel type.
		etw = new EntryWheel(machineConfiguration[0][0], machineConfiguration[0][1]);
		
		// Establishes size of Arrays for the Rotor, Rotor Setting, and Ring Setting.
		rotor = new Rotor[machineConfiguration.length - 2];
		rotorSetting = new int[machineConfiguration.length - 2];
		ringSetting = new int[machineConfiguration.length - 2];
		
		// Sets each Rotor and Ring Settings for all rotors to A (i.e., 0) by default.
		for (int i = 1; i < (machineConfiguration.length - 1); i++)
		{
			rotor[i] = new Rotor(machineConfiguration[i][0], machineConfiguration[i][1]);
			rotorSetting[i] = 0;
			ringSetting[i] = 0;
		}
		
		// Sets the Reflector type.
		reflector = new Reflector(	machineConfiguration[machineConfiguration.length - 1][0], 
									machineConfiguration[machineConfiguration.length - 1][1]);		
	}
	
	// Class Methods
	
	// Encrypt Phrase with default Rotor Settings and Ring Settings
	public String encryptPhrase(String phraseIn)
	{
		String phraseOut = "";
		for (int i = 0; i < phraseIn.length(); i++)
		{
			stepRotors();
			phraseOut = phraseOut + encryptLetter(phraseIn.charAt(i));
		}
		
		return phraseOut;
	}
	
	// Encrypt Phrase with specified Rotor Setting and Ring Setting
		public String encryptPhrase(String phraseIn, String theRotorSetting, String theRingSetting)
		{
			// Rotor Setting and Ring Setting should be provided as a string of letters A-Z
			// with a length equal to the number of Rotors. Code for left-most rotor (Rotor[end])
			// should be entered first and code for right-most rotor should be entered last (Rotor[0]).
			
			setRotorSetting(theRotorSetting);
			setRingSetting(theRingSetting);
			
			String phraseOut = "";
			for (int i = 0; i < phraseIn.length(); i++)
			{
				stepRotors();
				phraseOut = phraseOut + encryptLetter(phraseIn.charAt(i));
			}
			
			return phraseOut;
		}
	
	// Rotor Stepping (Only 3 Right-Most Rotors Step)
	public void stepRotors()
	{
		// Implements Infamous Double-Step for Middle Rotor
		if (rotorSetting[1] == rotor[1].getNotchPosition())
		{
			rotorSetting[1] = (rotorSetting[1] + 1) % 26;
			rotorSetting[2] = (rotorSetting[2] + 1) % 26;
		}
		
		// Steps Right Rotor and, if at Notch, Steps Middle Rotor
		if (rotorSetting[0] == rotor[0].getNotchPosition())
		{
			rotorSetting[0] = (rotorSetting[0] + 1) % 26;
			rotorSetting[1] = (rotorSetting[1] + 1) % 26;
		}
		else
		{
			rotorSetting[0] = (rotorSetting[0] + 1) % 26;
		}
	}
	
	// Main Encryption Algorithm
	public char encryptLetter(char letterIn)
	{
		// Method encrypts a single character based on the selected Entry Wheel, Rotors, and Reflector. It takes
		// into account the rotation position of the rotors. (Assumes Entry Wheel and Reflector do not step. This
		// will be updated if I learn that these do step.)
		
		int positionInAlphabet, positionInETW;
		char letterOut = letterIn;
		
		// Forward pass through Entry Wheel
		positionInETW = etw.getETWEncoding().indexOf(letterOut);
		letterOut = ALPHABET.charAt(positionInETW);
		
		// Forward pass through Rotors
		for (int i = 0; i < rotor.length; i++)
		{
			positionInAlphabet = (ALPHABET.indexOf(letterOut) + rotorSetting[i] + 26 - ringSetting[i]) % 26;
			letterOut = rotor[i].getRotorEncoding().charAt(positionInAlphabet);
			positionInAlphabet = (ALPHABET.indexOf(letterOut) + ringSetting[i] + 26 - rotorSetting[i]) % 26;
			letterOut = ALPHABET.charAt(positionInAlphabet);
		}
		
		// Pass through Reflector
		positionInAlphabet = ALPHABET.indexOf(letterOut);
		letterOut = reflector.getReflectorEncoding().charAt(positionInAlphabet);
		
		// Reverse pass through Rotors
		for (int i = rotor.length - 1; i >= 0 ; i--)
		{
			positionInAlphabet = (ALPHABET.indexOf(letterOut) + rotorSetting[i] + 26 - ringSetting[i]) % 26;
			letterOut = ALPHABET.charAt(positionInAlphabet);
			positionInAlphabet = (rotor[i].getRotorEncoding().indexOf(letterOut) + ringSetting[i] + 26 - rotorSetting[i]) % 26;
			letterOut = ALPHABET.charAt(positionInAlphabet);
		}
		
		// Reverse pass through Entry Wheel
		positionInETW = ALPHABET.indexOf(letterOut);
		letterOut = etw.getETWEncoding().charAt(positionInETW);
		
		// Return encoded letter
		return letterOut;
	}
	
	// Accessors
	public String getRotorSetting()
	{
		// Assumes Rotor[0] is the right-most rotor and Rotor[end] is left-most rotor.
		// First element of String represents Rotor[end] and last element of array sets Rotor[0].
		// This matches the convention when using an actual Enigma Machine.
		
		String theRotorSetting = "";
		
		for (int i = rotorSetting.length - 1; i >= 0; i--)
		{
			theRotorSetting = theRotorSetting + ALPHABET.charAt(rotorSetting[i]);
		}
		
		return theRotorSetting;
	}
	
	public String getRingSetting()
	{
		// Assumes Rotor[0] is the right-most rotor and Rotor[end] is left-most rotor.
		// First element of String represents Rotor[end] and last element of array sets Rotor[0].
		// This matches the convention when using an actual Enigma Machine.
		
		String theRingSetting = "";
		
		for (int i = ringSetting.length - 1; i >= 0; i--)
		{
			theRingSetting = theRingSetting + ALPHABET.charAt(ringSetting[i]);
		}
		
		return theRingSetting;
	}
	
	// Mutators
	public void setRotorSetting(String theRotorSetting)
	{
		// Assumes Rotor[0] is the right-most rotor and Rotor[end] is left-most rotor.
		// First element of String sets Rotor[end] and last element of array sets Rotor[0].
		// This matches the convention when using an actual Enigma Machine.
		
		for (int i = 0; i < theRotorSetting.length(); i++)
		{
			rotorSetting[i] = ALPHABET.indexOf(theRotorSetting.charAt(theRotorSetting.length() - (1 + i)));
		}
	}
	
	public void setRingSetting(String theRingSetting)
	{
		// Assumes Rotor[0] is the right-most rotor and Rotor[end] is left-most rotor.
		// First element of String sets Rotor[end] and last element of array sets Rotor[0].
		// This matches the convention when using an actual enigma machine.
		
		for (int i = 0; i < theRingSetting.length(); i++)
		{
			ringSetting[i] = ALPHABET.indexOf(theRingSetting.charAt(theRingSetting.length() - (1 + i)));
		}
	}
	
	// Static Methods
	public static void displayMachineOptions()
	{
		String[][] rotorOptions = Rotor.getROTORLIBRARY();
		String[][] etwOptions = EntryWheel.getETWLIBRARY();
		String[][] reflectorOptions = Reflector.getREFLECTORLIBRARY();
		
		System.out.printf("%nEntry Wheels:%n%-20s%-20s%n", "Machine Type","Entry Wheel Type");
		for (int i = 0; i < etwOptions.length; i++)
		{
			System.out.printf("%-20s%-20s%n", etwOptions[i][0],etwOptions[i][1]);
		}
		
		System.out.printf("%nRotors:%n%-20s%-20s%n", "Machine Type","Rotor Type");
		for (int i = 0; i < rotorOptions.length; i++)
		{
			System.out.printf("%-20s%-20s%n", rotorOptions[i][0],rotorOptions[i][1]);
		}
		
		System.out.printf("%nReflectors:%n%-20s%-20s%n", "Machine Type","Reflector Type");
		for (int i = 0; i < reflectorOptions.length; i++)
		{
			System.out.printf("%-20s%-20s%n", reflectorOptions[i][0],reflectorOptions[i][1]);
		}
		
		System.out.println();
	}
}