/* Author : Mahmudul Hossain (19303235)
 * Purpose : Record the number of occurences for each letter in
 * 			 the given input file and display it as a histogram
 * 			 of letter distribution
 * Last modified : 13/04/2020
 */

import java.io.*;
import java.util.*;
public class Occurence
{
	public static void main(String[] args)
	{
		//Name of the file to be analysed 
		plot("testfile-Affine.txt");
	}

	//Record the number of occurences of each alphabet by reading 
	//the imported filename
	public static void plot(String filename)
	{
		//Stores the frequency of occurence for each 
		//26 English alphabets
		int[] frequency = new int[26];
		File file;
		String line;
		Scanner sc;

		//Index for each character eg : A = 0, B = 1,.., Z = 25
		int index;

		//Intialize all indexes of frequency to be 0
		for(int i = 0; i < frequency.length; i++)
		{
			frequency[i] = 0;
		}

		try
		{
			file = new File(filename);
			sc = new Scanner(file);

			while(sc.hasNextLine())
			{
				//I just assumed to display all letters to be upper case
				line = sc.nextLine().toUpperCase();

				for(int i = 0; i < line.length(); i++)
				{
					//Only accept English letters 
					if((line.charAt(i) >= 'A') && (line.charAt(i) <= 'Z'))
					{
						//Map each letter to correct index
						index = (int)line.charAt(i) - 'A';

						//Increment the frequency index with respect to the index
						//obtained after mapping 
						(frequency[index])++;
					}
				}
			}	

			sc.close();

		}

		catch(IOException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		//Display the number of occurences in histogram format 
		printOccur(frequency);
	}

	//Express every occurence for each letter in the form of '*'
	public static void printOccur(int[] frequency)
	{
		char c;
		//Loop through every index in frequency array 
		for(int ii = 0; ii < frequency.length; ii++)
		{
			//Represent each index to its corresponding character
			c = (char)(ii + 'A');

			System.out.print(c + " : ");
			
			//For every occurence for a specific letter print out '*'
			for(int jj = 0; jj < frequency[ii]; jj++)
			{
				System.out.print("*");
			}
			//After the *'s has been printed out also show the occurence number
			System.out.print("(" + frequency[ii] + ")");
			System.out.println();
		}
	}

}
