/**
 * Denver Wolfe
 * Bingo - Final
 * Programming III - AP CS
 * 5/22/19 - Due 6/4/19
 */
import java.io.*;

public class MainDriver
{
   public static void main (String[] args) throws IOException
   {
		Bingo game = new Bingo();
		game.write("input.txt");
		game.read("input.txt");
		int x = game.playGame();
		System.out.println("the winning number is " + x);
	}
}
