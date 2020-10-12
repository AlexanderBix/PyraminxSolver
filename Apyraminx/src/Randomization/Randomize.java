package Randomization;

import AStar.Star;
import Layout.Side;
import Moving.Movements;

import java.util.Scanner;


public class Randomize {
    // Starting the process of randomization
    public void start(Side index, Movements moving) {
        // Creating Sides - Accessing them
        //                                      side[0][0]
        //                          side[1][0]  side[1][1]  side[1][2]
        //              side[2][0]  side[2][1]  side[2][2]  side[2][3]  side[2][4]
        // side[3][0]   side[3][1]  side[3][2]  side[3][3]  side[3][4]  side[3][5]  side[3][6]
        String[][] red = index.createSide("red");
        String[][] green = index.createSide("green");
        String[][] blue = index.createSide("blue");
        String[][] yellow = index.createSide("yellow");

        randomLoop(index, moving, yellow, red, blue, green);

    }

    // Displaying sides of Pyraminx in Terminal
    public void displayOutput(String[][] yellow, String[][] red, String[][] blue, String[][] green) {
        //Outputting Everything At the Start
        System.out.println("   " + yellow[0][0] + "              " + red[0][0] + "              " + blue[0][0]);
        System.out.println("  " + yellow[1][0] + yellow[1][1] + yellow[1][2] + "            " + red[1][0] + red[1][1] + red[1][2] + "            " + blue[1][0] + blue[1][1] + blue[1][2]);
        System.out.println(" " + yellow[2][0] + yellow[2][1] + yellow[2][2] + yellow[2][3] + yellow[2][4] + "          " + red[2][0] + red[2][1] + red[2][2] + red[2][3] + red[2][4] + "          " + blue[2][0]+ blue[2][1] + blue[2][2] + blue[2][3] + blue[2][4]);
        System.out.println(yellow[3][0] + yellow[3][1] + yellow[3][2] + yellow[3][3] + yellow[3][4] + yellow[3][5] + yellow[3][6] + "        " + red[3][0] + red[3][1] + red[3][2] + red[3][3] + red[3][4] + red[3][5] + red[3][6] + "        " + blue[3][0] + blue[3][1] + blue[3][2] + blue[3][3] + blue[3][4] + blue[3][5] + blue[3][6]);
        System.out.println("                  " + green[0][0]);
        System.out.println("                 " + green[1][0] + green[1][1] + green[1][2]);
        System.out.println("                " + green[2][0] + green[2][1] + green[2][2] + green[2][3] + green[2][4]);
        System.out.println("               " + green[3][0] + green[3][1] + green[3][2] + green[3][3] + green[3][4] + green[3][5] + green[3][6]);
        System.out.println("");
    }

    // Gets user input for random movement
    public void randomLoop(Side index, Movements moving, String[][] yellow, String[][] red, String[][] blue, String[][] green) {
            Star a = new Star();
            int previousMovement = -1;
            int previousFace = -1;
            int previousLog[] = {};
                for(int k = 3; k <= 20; k++) { // 3 to 20 moves

                    // Perform each k value 5 times
                    for(int j = 0; j < 5; j++) {

                        // Perform Random Moves
                        for (int i = 0; i < k; i++) {
                            previousLog = randomizeMovement(moving, yellow, red, blue, green, previousFace, previousMovement);
                            previousFace = previousLog[0];
                            previousMovement = previousLog[1];
                        }

                        // Perform A* Search Algorithm
                        a.searchStart(index, moving, yellow, red, blue, green, k);

                        // Reset Afterwards
                        red = index.createSide("red");
                        green = index.createSide("green");
                        blue = index.createSide("blue");
                        yellow = index.createSide("yellow");
                    }

                }

    }

    // Performs random movement on Pyraminx
    public int[] randomizeMovement(Movements moving, String[][] yellow, String[][] red, String[][] blue, String[][] green, int previousFace, int previousMovement) {
        // System.out.println("MOVE:");
        String[][] center = {};
        String[][] left = {};
        String[][] right = {};
        String[][] down = {};
        String currentDirection = "right";

        // Randomizing Face Index
        int faceIndex = (int)(1.0 * Math.random());
        switch(faceIndex) {
            case 0:
                center = red;
                left = yellow;
                right = blue;
                down = green;
                break;
            case 1:
                center = green;
                left = red;
                right = blue;
                down = yellow;
                break;
            case 2:
                center = blue;
                left = red;
                right = yellow;
                down = green;
                break;
            case 3:
                center = yellow;
                left = blue;
                right = red;
                down = green;
                break;
            default:
                break;
        }

        // Randomizing which part of the face is going to be moved
        int movingFunction = (int)(12.0 * Math.random()); // Changed this as some functions may be redundant
       /*while(movingFunction == previousMovement) { // Preventing the same movement function from appearing twice
            movingFunction = (int)(12.0 * Math.random()); // Changed this as some functions may be redundant
        }*/
        switch (movingFunction) {
            case 0:
                moving.rowFirst(currentDirection, center, left, right, down);
                break;
            case 1:
                moving.rowSecond(currentDirection, center, left, right, down);
                break;
            case 2:
                moving.rowThird(currentDirection, center, left, right, down);
                break;
            case 3:
                moving.rowFourth(currentDirection, center, left, right, down);
                break;
            case 4:
               moving.leftDiagonalFirst(currentDirection, center, left, right, down);
                break;
            case 5:
                moving.leftDiagonalSecond(currentDirection, center, left, right, down);
                break;
            case 6:
                moving.leftDiagonalThird(currentDirection, center, left, right, down);
                break;
            case 7:
                moving.leftDiagonalFourth(currentDirection, center, left, right, down);
                break;
            case 8:
                moving.rightDiagonalFirst(currentDirection, center, left, right, down);
                break;
            case 9:
                moving.rightDiagonalSecond(currentDirection, center, left, right, down);
                break;
            case 10:
                moving.rightDiagonalThird(currentDirection, center, left, right, down);
                break;
            case 11:
                moving.rightDiagonalFourth(currentDirection, center, left, right, down);
                break;
            default:
                break;
        }

        int[] previous = {faceIndex,movingFunction};
        return previous;
    }

}
