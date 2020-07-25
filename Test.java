/* Author : Mahmudul Hossain (19303235)
 * Purpose : Intiate affine cipher program by taking 
 * 			 valid user inputs with two options to 
 * 			 either perform encryption by reading the 
 * 			 input file or from user input
 * Last modified : 13/04/2020
 */
import java.util.*;
import java.io.*;

public class Test
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int a, b;			
		
		//Provide options to user to either apply affine cipher on a file or from their input
		System.out.println("Enter choice : (f/F) to read test file or (t/T) to enter text : ");
		char c = sc.nextLine().charAt(0);
		c = Character.toUpperCase(c);
		
		//Input validation
		while((c != 'F') && (c != 'T'))
		{
			System.out.println("(Wrong input)Enter choice : (f/F) to read test file or (t/T) to enter text : ");
			c = sc.nextLine().charAt(0);
			c = Character.toUpperCase(c);
		}

		//Input values for a and b
		System.out.println("Enter value of a : ");
		while (!sc.hasNextInt())
		{
			sc.next();
			System.out.println("Enter value of a : ");	
		}
		a = sc.nextInt();

		System.out.println("Enter value of b : ");
		while (!sc.hasNextInt())
		{
			sc.next();
			System.out.println("Enter value of b : ");	
		}

		b = sc.nextInt();

		//Check if a and b is valid within range and they are co-prime
		if(!EuclidInv.isValid(a,b))
		{
			System.err.println("Error keys (" + a + "," + b + ") are invalid try again by running the program");
		}
		else
		{
			//Initiate affine cipher
			process(c,a,b);
		}
	}
	
	//Determine if user opted for file I/O or user input
	public static void process(char c, int a, int b)	
	{
		switch(c)
		{
			case 'F' :		
				fileOp(a,b);
				break;
			case 'T' :
				userInput(a,b);
				break;
			default : 
				break;		
		}
	}
		
	//Take input from user which will be the plain text
	public static void userInput(int a, int b)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter text to encrypt ");
		String plain = sc.nextLine();
		outputOperation(plain,a,b);
	}

	//The user inputted plain text will go into Affine encryption and
	//decryption and the whole operation will be saved onto Output.txt 
	//file
	public static void outputOperation(String plain, int a, int b)
	{
		try
		{
			PrintWriter pw;
			String encryptTxt = Affine.encrypt(plain,a,b);
		
			String decryptTxt = Affine.decrypt(encryptTxt,a,b);
			
			//Ensure that the decrypted text is same as the plain text
			if(decryptTxt.compareTo(plain) == 0)
			{
				System.out.println("Encryption and decryption done successfully ");
			}
			else
			{
			 	System.out.println("Encryption and decryption failed ");
			}
		
			System.out.println("All operations written to Output.txt");
					
			pw = new PrintWriter("Output.txt");
			
			pw.println("Plain text     : " + plain);
			pw.println("Encrypted text : " + encryptTxt);
			pw.println("Decrypted text : " + decryptTxt);


			pw.close();

		}	
		catch(IOException ex)
		{
			System.err.println(ex.getMessage());
		}
	}

	//Read from the test file and perform encryption and
	//decryption line by line 
	public static void fileOp(int a, int b)
	{
		Scanner sc;
		File file;

		try
		{
			String plain = new String();
			String encrypted = new String();
			String decrypted = new String();
			file = new File("testfile-Affine.txt");
			sc = new Scanner(file);
			PrintWriter pwDec = new PrintWriter("decrypted.txt");
			PrintWriter pwEnc = new PrintWriter("encrypted.txt");

			while(sc.hasNextLine())
			{
				plain = sc.nextLine();
				
				//Perform encryption
				encrypted = Affine.encrypt(plain,a,b);

				//Perform decryption
				decrypted = Affine.decrypt(encrypted,a,b);
				if(decrypted.compareTo(plain) != 0)
				{
					System.out.println("Error : decrypted does not match plain text ");
				}
				
				//Save the encrypted lines to encrypted.txt
				pwEnc.println(encrypted);
				//Save the decrypted lines to decrypted.txt
				pwDec.println(decrypted);
			}	
			
			System.out.println("Encrypted file stored in encrypted.txt");
			System.out.println("Decrypted file stored in decrypted.txt");

			sc.close();
			pwDec.close();
			pwEnc.close();
		}
		catch(IOException ex)
		{
			System.err.println(ex.getMessage());
		}

	}

}
