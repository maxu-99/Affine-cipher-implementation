/* Author : Mahmudul Hossain
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
		
		System.out.println("Enter choice : (f/F) to read test file or (t/T) to enter text : ");
		char c = sc.nextLine().charAt(0);
		c = Character.toUpperCase(c);
		
		while((c != 'F') && (c != 'T'))
		{
			System.out.println("(Wrong input)Enter choice : (f/F) to read test file or (t/T) to enter text : ");
			c = sc.nextLine().charAt(0);
			c = Character.toUpperCase(c);
		}

			
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

		if(!EuclidInv.isValid(a,b))
		{
			System.err.println("Error keys (" + a + "," + b + ") are invalid try again by running the program");
		}
		else
		{
			process(c,a,b);
		}
	}
	
	public static void process(char c, int a, int b)	
	{
		switch(c)
		{
			case 'F' :		
				//readFile(a,b);
				fileOp(a,b);
				break;
			case 'T' :
				userInput(a,b);
				break;
			default : 
				break;		
		}
	}
		
	public static void readFile(int a, int b)
	{
		try
		{
			File file = new File("testfile-Affine.txt");
//		PrintWriter pw;
			Scanner sc = new Scanner(file);
			String plain = new String();

			while(sc.hasNextLine())
			{
				plain = plain + sc.nextLine();
			}	
			
			sc.close();
		
			outputOperation(plain, a, b);
		}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
		}

	}

	public static void userInput(int a, int b)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter text to encrypt ");
		String plain = sc.nextLine();
		outputOperation(plain,a,b);
	}

	public static void outputOperation(String plain, int a, int b)
	{
		try
		{
			PrintWriter pw;
			String encryptTxt = Affine.encrypt(plain,a,b);
		
			String decryptTxt = Affine.decrypt(encryptTxt,a,b);

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

	public static void fileOp(int a, int b)
	{
		Scanner sc;
		File file;

		try
		{
			String plain = new String();
		//	String line = new String();
			String encrypted = new String();
			String decrypted = new String();
			file = new File("testfile-Affine.txt");
			sc = new Scanner(file);
			PrintWriter pwDec = new PrintWriter("decrypted.txt");
			PrintWriter pwEnc = new PrintWriter("encrypted.txt");

			while(sc.hasNextLine())
			{
				plain = sc.nextLine();
			//	line = line + plain;

				encrypted = Affine.encrypt(plain,a,b);
				decrypted = Affine.decrypt(encrypted,a,b);
				if(decrypted.compareTo(plain) != 0)
				{
					System.out.println("Error : decrypted does not match plain text ");
				}

				pwEnc.println(encrypted);
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
