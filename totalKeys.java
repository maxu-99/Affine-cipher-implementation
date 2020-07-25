/* Author : Mahmudul Hossain (19303235)
 * Purpose : Generate all valid keys for Affine cipher to
 * 			 work and save it into keys.txt
 * Last modified : 13/04/2020
 */
import java.io.*;

public class totalKeys
{

	public static void main(String[] args)
	{
		generateKeys();
	}

	public static void generateKeys()
	{
		PrintWriter pw;
		 
		try
		{
			pw = new PrintWriter("keys.txt");

			for(int a = 0; a <= 27; a++)
			{
				for(int b = 0; b <= 27; b++)
				{
					//Only print out keys where a and b are between 1 and 27 inclusive
					//a and 27 are co-prime
					if(EuclidInv.isValid(a,b))
					{
						pw.println("(" + a + "," + b + ")");
					}
				}
			}

			System.out.println("Generated keys saved in keys.txt");

			pw.close();
		}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
		}
	}
}
