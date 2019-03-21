package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        this.rand = new Random(seed);

        //TODO: Initialize random number generator
    }

    public String generateRandomString(int n) {
        String rans = "";
        for(int i = 0; i < n; i++) {
            rans += CHARACTERS[rand.nextInt(25)];
        }
        //TODO: Generate random string of letters of length n
        return rans;
    }

    public void drawFrame(String s) {
        StdDraw.clear();
        Font font = new Font("Arial", Font.BOLD, 32);
        StdDraw.setFont(font);
        StdDraw.text(20,20,s);
        StdDraw.setPenColor(1,2,3);
        StdDraw.show();
        StdDraw.pause(1000);
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for(int i = 0; i < letters.length(); i++) {
        drawFrame(letters.substring(i,i+1));
        StdDraw.clear();
        StdDraw.show();
        StdDraw.pause(2000);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        String str = "";
        while(str.length() != n) {
            if (StdDraw.hasNextKeyTyped()) {
                String key = String.valueOf(StdDraw.nextKeyTyped());
                this.drawFrame(key);
                str += key;
            }
        }
        return str;
    }

    public void startGame() {
        int i = 1;
        while(true) {
            drawFrame("Round" + String.valueOf(i));

            String str = generateRandomString(i);
            flashSequence(str);
            String ans = solicitNCharsInput(i);
            if(!str.equals(ans)) {
                break;
            }
            i++;
        }
        drawFrame("End at round" + String.valueOf(i - 1));
        //TODO: Set any relevant variables before the game starts

        //TODO: Establish Game loop
    }

}
