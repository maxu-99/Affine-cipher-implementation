
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
		int total = 0;
		 
		try
		{
			pw = new PrintWriter("keys.txt");

			for(int a = 0; a <= 27; a++)
			{
				for(int b = 0; b <= 27; b++)
				{
					if(EuclidInv.isValid(a,b))
					{
						total++;
						pw.println("(" + a + "," + b + ")   key number : " + total );
					}
				}
			}

			pw.close();
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
