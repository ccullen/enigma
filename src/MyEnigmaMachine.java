

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

import java.util.Scanner;

public class MyEnigmaMachine {

	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		boolean correctlyEntered = false;
		
		String[][] machineConfiguration = new String[5][2];
		String[][] ETWLIBRARY = EntryWheel.getETWLIBRARY();
		String[][] ROTORLIBRARY = Rotor.getROTORLIBRARY();
		String[][] REFLECTORLIBRARY = Reflector.getREFLECTORLIBRARY();
		
		//Machine.displayMachineOptions();
		
		// The following while-loop blocks should be replaced with a reusable method
		// Entry Wheel
		while (correctlyEntered == false);
		{
			System.out.println("Please enter the Machine Type for the Entry Wheel: ");
			machineConfiguration[0][0] = scan.nextLine();
			scan.nextLine();
			System.out.println("\nPlease enter the Entry Wheel Type: ");
			machineConfiguration[0][1] = scan.nextLine();
			System.out.println("TEST: " + machineConfiguration[0][1]);
			scan.nextLine();
			
			for (int i = 0; i < ETWLIBRARY.length; i++)
			{
				if (		ETWLIBRARY[i][0].equalsIgnoreCase(machineConfiguration[0][0]) && 
						ETWLIBRARY[i][1].equalsIgnoreCase(machineConfiguration[0][1])) 
				{
					correctlyEntered = true;
					break;
				}
				else
				{
					System.out.println("Improper Input. Please try again.");
				}
			}
		} 
		/*correctlyEntered = false;
		
		// Right Rotor
		while (correctlyEntered == false);
		{
			System.out.println("\nPlease enter the Machine Type for the Right Rotor: ");
			machineConfiguration[1][0] = scan.nextLine();
			scan.nextLine();
			System.out.println("\nPlease enter the Right Rotor Type: ");
			machineConfiguration[1][1] = scan.nextLine();
			System.out.println("TEST: " + machineConfiguration[1][1]);
			scan.nextLine();
			
			for (int i = 0; i < ROTORLIBRARY.length; i++)
			{
				if (		ROTORLIBRARY[i][0].equalsIgnoreCase(machineConfiguration[1][0]) && 
						ROTORLIBRARY[i][1].equalsIgnoreCase(machineConfiguration[1][1])) 
				{
					correctlyEntered = true;
					break;
				}
				else
				{
					System.out.println("Improper Input. Please try again.");
				}
			}
		} 
		correctlyEntered = false;
		
		// Middle Rotor
		while (correctlyEntered == false);
		{
			System.out.println("\nPlease enter the Machine Type for the Middle Rotor: ");
			machineConfiguration[2][0] = scan.nextLine();
			scan.nextLine();
			System.out.println("\nPlease enter the Middle Rotor Type: ");
			machineConfiguration[2][1] = scan.nextLine();
			System.out.println("TEST: " + machineConfiguration[2][1]);
			scan.nextLine();
			
			for (int i = 0; i < ROTORLIBRARY.length; i++)
			{
				if (		ROTORLIBRARY[i][0].equalsIgnoreCase(machineConfiguration[2][0]) && 
						ROTORLIBRARY[i][1].equalsIgnoreCase(machineConfiguration[2][1])) 
				{
					correctlyEntered = true;
					break;
				}
				else
				{
					System.out.println("Improper Input. Please try again.");
				}
			}
		} 
		correctlyEntered = false;
		
		// Left Rotor
		while (correctlyEntered == false);
		{
			System.out.println("\nPlease enter the Machine Type for the Left Rotor: ");
			machineConfiguration[3][0] = scan.nextLine();
			scan.nextLine();
			System.out.println("\nPlease enter the Left Rotor Type: ");
			machineConfiguration[3][1] = scan.nextLine();
			System.out.println("TEST: " + machineConfiguration[3][1]);
			scan.nextLine();
			
			for (int i = 0; i < ROTORLIBRARY.length; i++)
			{
				if (		ROTORLIBRARY[i][0].equalsIgnoreCase(machineConfiguration[3][0]) && 
						ROTORLIBRARY[i][1].equalsIgnoreCase(machineConfiguration[3][1])) 
				{
					correctlyEntered = true;
					break;
				}
				else
				{
					System.out.println("Improper Input. Please try again.");
				}
			}
		} 
		correctlyEntered = false;
		
		//Reflector
		while (correctlyEntered == false);
		{
			System.out.println("\nPlease enter the Machine Type for the Reflector: ");
			machineConfiguration[4][0] = scan.nextLine();
			scan.nextLine();
			System.out.println("\nPlease enter the Reflector Type: ");
			machineConfiguration[4][1] = scan.nextLine();
			System.out.println("TEST: " + machineConfiguration[4][1]);
			scan.nextLine();
			
			for (int i = 0; i < ETWLIBRARY.length; i++)
			{
				if (		REFLECTORLIBRARY[i][0].equalsIgnoreCase(machineConfiguration[4][0]) && 
						REFLECTORLIBRARY[i][1].equalsIgnoreCase(machineConfiguration[4][1])) 
				{
					correctlyEntered = true;
					break;
				}
			}
		} 
		correctlyEntered = false;
		
		Machine myMachine = new Machine(machineConfiguration);
		
		System.out.printf("%nYour Enigma Simulator is configured as follows:%n");
		System.out.printf("%-15s%-20s%-20s%n", "", "Machine Type", "Disc Type");
		System.out.printf(	"%-15s%-20s%-20s%n", "Entry Wheel:",
							machineConfiguration[0][0], machineConfiguration[0][1]);
		System.out.printf(	"%-15s%-20s%-20s%n", "Right Rotor:",
							machineConfiguration[1][0], machineConfiguration[1][1]);
		System.out.printf(	"%-15s%-20s%-20s%n", "Middle Rotor:",
							machineConfiguration[2][0], machineConfiguration[2][1]);
		System.out.printf(	"%-15s%-20s%-20s%n", "Left Rotor:",
							machineConfiguration[3][0], machineConfiguration[3][1]);
		System.out.printf(	"%-15s%-20s%-20s%n", "Reflector:",
							machineConfiguration[4][0], machineConfiguration[4][1]);
		System.out.printf("%-15s%-20s", "Rotor Setting:", "AAA");
		System.out.printf("%-15s%-20s", "Ring Setting:", "AAA");
		
		String theSetting, messageIn, cipherOut;
		
		do
		{
			System.out.println("Enter the Rotor Setting (3 letters): ");
			theSetting = scan.nextLine();
			scan.nextLine();
			myMachine.setRotorSetting(theSetting);
			
			System.out.println("Enter the Ring Setting (3 letters): ");
			theSetting = scan.nextLine();
			scan.nextLine();
			myMachine.setRingSetting(theSetting);
			
			System.out.printf("%-15s%-20s", "Rotor Setting:", "AAA");
			System.out.printf("%-15s%-20s", "Ring Setting:", "AAA");
			System.out.println();
			
			System.out.println("Enter the phrase to encode:\n");
			messageIn = scan.nextLine();
			scan.nextLine();
			System.out.println();
			
			cipherOut = myMachine.encryptPhrase(messageIn);
			System.out.println(messageIn);
			System.out.println(cipherOut);
			System.out.println();
			
			System.out.println("Enter Y if you want to create another cipher: ");
			theSetting = scan.nextLine();
			scan.nextLine();
			System.out.println();
		} while (theSetting.equalsIgnoreCase("Y"));
		
		System.out.println("All done now.");*/
		
		
/*		String phrase = "";
		
		for (int i = 0; i < 10; i++)
		{
			phrase = phrase + "ABCDE";
		}
	
		String cipher = myMachine.encryptPhrase(phrase,"ASA","ACA");
		System.out.println(cipher);
		System.out.println();*/

	}
	// To-Do List:
	// -Add comments to make the code easier to follow/update.
	// -Make a text interface that lists the options for selection and enables setting Rotor & Ring.
	// -Implement the Double-Notching on Rotors VI, VII, and VIII.
	// -Clean up the Rotor, Reflector, and EntryWheel classes. The default code is too messy.
	// -Add a Plugboard.
	// -Add GUI elements and/or build an App version.
	

}
