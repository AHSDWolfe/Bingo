/**
 ******************************************************************************
 *                        BINGO
 ******************************************************************************
 *
 * The standard bingo game.
 *
 *
 *
 *
 * Name:    Denver Wolfe
 *
 * Date:    5/22/19 - Due 6/4/19
 *
 *
 ***************************************************************************** */

import java.util.*;
import java.io.*;

public class Bingo {

    private Random rand = new Random();
    private int[][] card;       //Bingo card configuration
    private int[] stream;       //list of 75 integers
    private boolean[][] marks;  //simulates placing chips on a Bingo card

    public Bingo() {
        
        card = new int[5][5];
        stream = new int[75];
        marks = new boolean[5][5];
    }

    /**
     * This method writes a random Bingo card configuration and a stream of
     * random number between 1 and 75 to the output file.
     *
     * The first column in the table contains only integers between 1 and 15,
     * the second column numbers are all between 16 and 30, the third are 31 to
     * 45, the fourth 46-60, and the fifth 61-75.
     *
     * There are no duplicate numbers on a Bingo card.
     */
    public void write(String outputFile) throws IOException {
        
        //Randomly recieve values for card
        for (int c = 0; c < 5; c++) {
            for (int r = 0; r < 5; r++) {
                int random;
                if (c == 0) {
                    random = (int) (Math.random() * 16);
                    card[r][c] = random;
                }
                if (c == 1) {
                    random = (int) (Math.random() * ((30-16) + 1)) + 16;
                    card[r][c] = random;
                }
                if (c == 2) {
                    random = (int) (Math.random() * ((45 - 31) + 1)) + 31;
                    card[r][c] = random;
                    card[2][2] = 0;
                }
                if (c == 3) {
                    random = (int) (Math.random() * ((60 - 46) + 1)) + 46;
                    card[r][c] = random;
                }
                if (c == 4) {
                    random = (int) (Math.random() * ((75 - 61) + 1)) + 61;
                    card[r][c] = random;
                }

                //Call shuffle method
                ArrayList<Integer> numberStream = new ArrayList<>();
                for (int x = 0; x < stream.length; x++) {
                    numberStream.add(x + 1);
                }

                shuffle(numberStream);

                for (int x = 0; x < stream.length; x++) {
                    stream[x] = numberStream.get(x);
                }
            }
        }
    }

    /**
     * Shuffles the list of numbers
     * @param list
     */
    public void shuffle(ArrayList<Integer> list) {
        
        //swaps k-th index with a random index
        for (int x = 0; x < 75; x++) {
            int random =(int) (Math.random() * (75));
            int holder = list.get(x);
            list.set(x, list.get(random));
            list.set(random, holder);
        }
    }

    /**
     * This method reads a given inputFile that contains a Bingo card
     * configuration and a stream of numbers between 1 and 75. . A Bingo card
     * configuration is stored in the card array. A list of 75 integers is
     * stored in the stream array.
     */
    public void read(String inputFile) throws IOException {
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
        for (int r = 0; r < card[0].length; r++) {
            for (int c = 0; c < card.length; c++) {
                writer.write(card[r][c] + " ");
                if (c == card.length - 1) {
                    writer.newLine();
                }
            }
        }
        writer.newLine();
        for(int x = 0; x < stream.length; x++){
            writer.write(stream[x] + " ");
        }
        writer.close();
    }
    

    /**
     * This method returns the first integer from the stream array that gives
     * you the earliest winning condition.
     *
     * - all the spots in a column are marked - all the spots in a row are
     * marked - all the spots in either of the two diagonals are marked - all
     * four corner squares are marked
     */
    public int playGame() {
        marks[2][2] = true;
        for (int x = 0; x < stream.length; x++) {
            for (int c = 0; c < card[0].length; c++) {
                for (int r = 0; r < card.length; r++) {
                    if (card[r][c] == stream[x]) {
                        marks[r][c] = true;
                    }

                    //Diagonals
                    if (marks[0][0] == true && marks[1][1] == true
                            && marks[2][2] == true && marks[3][3] == true
                            && marks[4][4] == true) {
                        return stream[x];
                    }
                    if (marks[4][0] == true && marks[3][1] == true
                            && marks[2][2] == true && marks[1][3] == true
                            && marks[0][4] == true) {
                        return stream[x];
                    }

                    //Rows
                    if (marks[0][0] == true && marks[0][1] == true
                            && marks[0][2] == true && marks[0][3] == true
                            && marks[0][4] == true) {
                        return stream[x];
                    }
                    if (marks[1][0] == true && marks[1][1] == true
                            && marks[1][2] == true && marks[1][3] == true
                            && marks[1][4] == true) {
                        return stream[x];
                    }
                    if (marks[2][0] == true && marks[2][1] == true
                            && marks[2][2] == true && marks[2][3] == true
                            && marks[2][4] == true) {
                        return stream[x];
                    }
                    if (marks[3][0] == true && marks[3][1] == true
                            && marks[3][2] == true && marks[3][3] == true
                            && marks[3][4] == true) {
                        return stream[x];
                    }
                    if (marks[4][0] == true && marks[4][1] == true
                            && marks[4][2] == true && marks[4][3] == true
                            && marks[4][4] == true) {
                        return stream[x];
                    }

                    //Columns
                    if (marks[0][0] == true && marks[1][0] == true
                            && marks[2][0] == true && marks[3][0] == true
                            && marks[4][0] == true) {
                        return stream[x];
                    }
                    if (marks[0][1] == true && marks[1][1] == true 
                            && marks[2][1] == true && marks[3][1] == true
                            && marks[4][1] == true) {
                        return stream[x];
                    }
                    if (marks[0][2] == true && marks[1][2] == true
                            && marks[2][2] == true && marks[3][2] == true
                            && marks[4][2] == true) {
                        return stream[x];
                    }
                    if (marks[0][3] == true && marks[1][3] == true
                            && marks[2][3] == true && marks[3][3] == true
                            && marks[4][3] == true) {
                        return stream[x];
                    }
                    if (marks[0][4] == true && marks[1][4] == true
                            && marks[2][4] == true && marks[3][4] == true
                            && marks[4][4] == true) {
                        return stream[x];
                    }
                    
                    //Corners
                    if (marks[0][0] == true && marks[0][4] == true
                            && marks[4][0] == true && marks[4][4] == true) {
                        return stream[x];
                    }
                }
            }
        }
        throw new RuntimeException("You need to implement this method");
    }
}
