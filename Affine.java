/* Author : Mahmudul Hossain (19303235)
 * Purpose : This class contains all the static functions for Affine cipher
 * 			 to encrypt and decrypt.
 * Last modified : 17/04/2020
 */

public class Affine
{
	//This function takes in a plain text and perform affine cipher applying the 
	//equation f(x) = ((a*x) + b) mod 27 for each character in x thus generating
	//a cipher text which gets returned
	//Assuming 27th character to be '!' for upper case and '#' for lower case 
	public static String encrypt(String plaintext, int a, int b)
	{
		//Obtain each character from plain text
		char c;

		//Index of the cipher character after applying the function
		int index;

		//The cipher character in ascii representation
		char cipherCh;
		

		StringBuilder ciphertext = new StringBuilder(plaintext);

		//Loop through every character and encrypt them
		for(int i = 0; i < plaintext.length(); i++)
		{
			c = plaintext.charAt(i);
			
			//Encrypt upper-case letters
			if(Character.isUpperCase(c))
			{
				//Apply the affine function
				index = (int)(a*(c - 'A') + b) % 27;
	
				//If the function produces the 27th index, replace the index with '!'
				if(index == 26)
				{
					cipherCh = '!';
				
					//Set the character at the given index to the encrypted char
					ciphertext.setCharAt(i,cipherCh); 				
				}
				//If the function produces the index to be mapped on characters between 'A-Z'
				//store the equivalent ascii value
				else
				{
					//Generate the equivalent ascii value
					cipherCh = (char)(index + 'A');
					//Set the character at the given index to the encrypted char
					ciphertext.setCharAt(i,cipherCh);
				} 
			}
			//Encrypt lower-case letters
			else if(Character.isLowerCase(c))
			{
				//Apply the affine function
				index = (int)(a*(c - 'a') + b) % 27;

				//If the function produces the 27th index, replace the index with '#'
				if(index == 26)
				{
					cipherCh = '#';

					//Set the character at the given index to the encrypted char
					ciphertext.setCharAt(i,cipherCh); 				
				}
				//If the function produces the index to be mapped on character between 'a-z'
				//store the equivalent ascii value
				else
				{
					//Generate the equivalent ascii value
					cipherCh = (char)(index + 'a');

					//Set the character at the given index to the encrypted char
					ciphertext.setCharAt(i,cipherCh);
				}

			}
			//Special cases if plain text contains '!' or '#' it should avoid the encryption function
			//I have assumed here to replace '!' and '#' with non-printable ascii characters
			//to ensure that if by any chance the encrypted text contains '!' or '#' it will go through
			//the decryption function where as the non-printable ascii values will be directly mapped to 
			//'#' or '!' thus retaining the original plain text
			else if(c == '!')
			{
				//Assuming '!' to be mapped on an ascii value of 28 which is non-printable
				int num = 28;

				//cipherCh is now a non-printable character FILE SEPARATOR(FS) RIGHT ARROW
				cipherCh = (char)num;
				//Set the character at the given index to be the replaced char
				ciphertext.setCharAt(i,cipherCh);
			}
			else if(c == '#')
			{
				//Assuming '#' to be mapped on an ascii value of 29 which is non-printable 
				int num = 29;

				//cipherCh is now a non-printable character GROUP SEPARATOR(GS) LEFT ARROW
				cipherCh = (char)num;
				//Set the character at the given index to be the replaced char
				ciphertext.setCharAt(i,cipherCh);
				
			}

			//Ignore all other non-letter characters
		}

		//Return the generated cipher-text
		return ciphertext.toString();
	}
	
	//This function accepts the cipher-text and generates the plain-text using the 
	//function x = ((multiplicative inverse of a)*(y-b)) mod 27 where x is the plain 
	//char and y is the cipher char
	//Assuming 27th character to be '!' for upper case and '#' for lower case 
	public static String decrypt(String ciphertext, int a, int b)
	{
		StringBuffer decryptedtext = new StringBuffer(ciphertext);

		char  c;
		char plainCh;

		//Obtain the actual mapping without ascii
		int charIdx;

		//Store negative values of (charIdx - b)
		int absIdx;

		//Determine the multiplicative inverse of 'a' with m = 27
		int inverseMod = EuclidInv.inverseModulo(a, 27);

		//Loop through each character and decrypt them
		for(int i = 0; i < ciphertext.length(); i++)
		{
			c = ciphertext.charAt(i);

			//Special case if the encrypted char is a non-printable character
			//then it is either '!'or '#' with their respective values that
			//were assigned previously during encryption
			if((int)c == 28)
			{
				plainCh = '!';
				decryptedtext.setCharAt(i,plainCh);
			}
			else if((int)c == 29)
			{
				plainCh = '#';
				decryptedtext.setCharAt(i,plainCh);
			}
			//Decrypt upper case letters
			else if(Character.isUpperCase(c))
			{
				//Change the ASCII values to 0-26 mappings
				charIdx = (char)(c - 'A');

				//The decryption formula does not work if the calculated value (charIdx - b)
				//is less than 0 hence with the help of absIdx, if absIdx is 
				//less than 0 then we add 27 accordingly to retain a positive value
				absIdx = ((int)charIdx - b);
				if(absIdx < 0)
				{
					absIdx = absIdx + 27;
				}
				
				//Apply the decryption formula 
				plainCh = (char)((inverseMod * absIdx) % 27);

				//Change the plainCh index back to ASCII value of character
				plainCh = (char)(plainCh + 'A');

				//Set the character at the given index to be the decrypted char
				decryptedtext.setCharAt(i,plainCh);
			}
			//Decrypt lower case letters
			else if(Character.isLowerCase(c))
			{
				//Change the ASCII values to 0-26 mappings
				charIdx = (char)(c - 'a');

				//The decryption formula does not work if the calculated value (charIdx - b)
				//is less than 0 hence with the help of absIdx, if absIdx is 
				//less than 0 then we add 27 accordingly to retain a positive value			
				absIdx = ((int)charIdx - b);
				if(absIdx < 0)
				{
					absIdx = absIdx + 27;
				}
				
				//Apply the decryption formula
				plainCh = (char)((inverseMod * absIdx) % 27);

				//Change the plainCh  index back to ASCII value of character
				plainCh = (char)(plainCh + 'a');

				//Set the character at the given index to be the decrypted char
				decryptedtext.setCharAt(i,plainCh);
			}

			//Special case for '!' as the cipher char which is the 27th index for uppercase 
			//letters
			else if(c == '!')
			{		
				//Since we know the 27th mapping of character space is '!'
				absIdx = 26 - b;
				//The decryption formula does not work if the calculated value (charIdx - b)
				//is less than 0 hence with the help of absIdx, if absIdx is 
				//less than 0 then we add 27 accordingly to retain a positive value			
				if(absIdx < 0)
				{
					absIdx = absIdx + 27;
				}

				//Apply the decryption formula
				plainCh = (char)((inverseMod * absIdx) % 27);

				//Change the plainCh index back to ASCII value of character
				plainCh = (char)(plainCh + 'A');
				
				//Set the character at the given index to be the decrypted char
				decryptedtext.setCharAt(i,plainCh);
			}

			//Special case for '#' as the cipher char which is the 27th index for lowercase
			//letters
			else if(c == '#')
			{
				//Since we know the 27th mapping of character space is '#'
				absIdx = 26 - b;
				//The decryption formula does not work if the calculated value (charIdx - b)
				//is less than 0 hence with the help of absIdx, if absIdx is 
				//less than 0 then we add 27 accordingly to retain a positive value
				if(absIdx < 0)
				{
					absIdx = absIdx + 27;
				}

				//Apply the decryption formula
				plainCh = (char)((inverseMod * absIdx) % 27);

				//Change the plainCh index back to ASCII value of character
				plainCh = (char)(plainCh + 'a');
				
				//Set the character at the given index to be the decrypted char
				decryptedtext.setCharAt(i,plainCh);
			}

		   	//Ignore all other non-letter characters 	
		}

		//Return the generated plain text
		return decryptedtext.toString();	
	}	
}	
