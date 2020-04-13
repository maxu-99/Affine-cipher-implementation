/* Author : Mahmudul Hossain (19303235)
 * Purpose : This class provides the helper functions to 
 * 			 carry out the affine cipher correctly by 
 * 			 determining valid inputs of alpha and beta 
 * 			 and generating multiplicative inverse from 
 * 			 inputs
 * Last modified : 13/04/2020 
 */
	
public class EuclidInv
{
	//Finds the greatest common divisor among num1 and num2 
	//Code adapted from the pseudocode found in lecture 6 slide 19
	//Used to determine if num1 is coprime with respect to num2
	public static int gcd(int num1, int num2)
	{
		int temp;

		while(num2 != 0)
		{
			temp = num2;
			num2 = num1 % num2;
			num1 = temp;
		}

		return num1;
	}

	//Finds the multiplicative inverse of a such that
	//(a*x) mod m = 1 where m is 27 
	public static int inverseModulo(int a, int m)
	{
		//a and m are relatively co-prime which is validated earlier
		//Test.java with the help of isValid
		a = a % m;
	
		//The default value is set to 1 
		int value = 1;
		
		//Try all numbers from 1 to m
		//For every number x, check if (a * x) mod m is 1 
		//The value returned is within the range {0,..,m - 1}
		for(int x = 1; x < m; x++)
		{
			if((a * x) % m == 1)
			{
				//Modify value only if a multiplicative inverse is found 
				value = x;
			}
		}
		
		return value;
	}

	//Ensure that a and b are between 1 and 27 inclusive
	//Ensure that a and 27 are co-prime
	public static boolean isValid(int a, int b)
	{
		boolean valid = true;

		if((a < 1 || a > 27) || ( b < 1 || b > 27))
		{
			valid = false;
		}

		if(gcd(a,27) != 1)
		{
			valid = false;
		}

		return valid;
	}

}
