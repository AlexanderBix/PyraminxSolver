package Moving;

import java.util.Arrays;

public class Movements {

    // Creates duplicate array of arrays
    public String[][] arrayCopy(String[][] index) {
        String[][] copy = new String[index.length][];

        for(int i = 0; i < index.length; i++) {
            String[] row = index[i];
            copy[i] = new String[row.length];
            System.arraycopy(row,0,copy[i],0,row.length);
        }

        return copy;
    }

    //Modifies side[0][0]
    public void rowFirst(String direction, String[][] center, String[][] left, String[][] right, String[][] down) {
        String[][] copyCenter = arrayCopy(center);
        String[][] copyLeft = arrayCopy(left);
        String[][] copyRight = arrayCopy(right);
        // String[][] copyDown = arrayCopy(down); // redundant in this case
        switch (direction){
            case "left":
                center[0][0] = copyRight[0][0];
                left[0][0] = copyCenter[0][0];
                right[0][0] = copyLeft[0][0];
                break;
            case "right":
                center[0][0] = copyLeft[0][0];
                left[0][0] = copyRight[0][0];
                right[0][0] = copyCenter[0][0];
                break;
            default:
                break;
        }
    }

    //Modifies side[1][0] side[1][1] side[1][2]
    public void rowSecond(String direction, String[][] center, String[][] left, String[][] right, String[][] down) {
        String[][] copyCenter = arrayCopy(center);
        String[][] copyLeft = arrayCopy(left);
        String[][] copyRight = arrayCopy(right);
        // String[][] copyDown = arrayCopy(down); //not needed in this case
        switch (direction){
            case "left":
                center[0][0] = copyRight[0][0];
                left[0][0] = copyCenter[0][0];
                right[0][0] = copyLeft[0][0];
                center[1] = copyRight[1];
                left[1] = copyCenter[1];
                right[1] = copyLeft[1];
                break;
            case "right":
                center[0][0] = copyLeft[0][0];
                left[0][0] = copyRight[0][0];
                right[0][0] = copyCenter[0][0];
                center[1] = copyLeft[1];
                left[1] = copyRight[1];
                right[1] = copyCenter[1];
                break;
            default:
                break;
        }
    }

    //Modifies side[2][0] side[2][1] side[2][2] side[2][3] side[2][4]
    public void rowThird(String direction, String[][] center, String[][] left, String[][] right, String[][] down) {
        String[][] copyCenter = arrayCopy(center);
        String[][] copyLeft = arrayCopy(left);
        String[][] copyRight = arrayCopy(right);
        // String[][] copyDown = arrayCopy(down); //not needed in this case
        switch (direction){
            case "left":
                center[2] = copyRight[2];
                left[2] = copyCenter[2];
                right[2] = copyLeft[2];
                break;
            case "right":
                center[2] = copyLeft[2];
                left[2] = copyRight[2];
                right[2] = copyCenter[2];
                break;
            default:
                break;
        }
    }

    //Modifies:
    //side[3][0] side[3][1] side[3][2] side[3][3] side[3][4] side[3][5] side[3][6]
    //Rotates down face
    public void rowFourth(String direction, String[][] center, String[][] left, String[][] right, String[][] down) {
        String[][] copyCenter = arrayCopy(center);
        String[][] copyLeft = arrayCopy(left);
        String[][] copyRight = arrayCopy(right);
        String[][] copyDown = arrayCopy(down);
        switch (direction){
            case "left":
                center[3] = copyRight[3];
                left[3] = copyCenter[3];
                right[3] = copyLeft[3];
                // Rotating Bottom Face
                down[0][0] = copyDown[3][6];
                down[1][0] = copyDown[2][4];
                down[1][1] = copyDown[3][5];
                down[1][2] = copyDown[3][4];
                down[2][0] = copyDown[1][2];
                down[2][1] = copyDown[2][3];
                // down[2][2] = copyDown[2][2]; // Center: Doesn't change
                down[2][3] = copyDown[3][3];
                down[2][4] = copyDown[3][2];
                down[3][0] = copyDown[0][0];
                down[3][1] = copyDown[1][1];
                down[3][2] = copyDown[1][0];
                down[3][3] = copyDown[2][1];
                down[3][4] = copyDown[2][0];
                down[3][5] = copyDown[3][1];
                down[3][6] = copyDown[3][0];
                break;
            case "right":
                center[3] = copyLeft[3];
                left[3] = copyRight[3];
                right[3] = copyCenter[3];
                // Rotating Bottom Face
                down[0][0] = copyDown[3][0];
                down[1][0] = copyDown[3][2];
                down[1][1] = copyDown[3][1];
                down[1][2] = copyDown[2][0];
                down[2][0] = copyDown[3][4];
                down[2][1] = copyDown[3][3];
                // down[2][2] = copyDown[2][2]; // Center: Doesn't Change
                down[2][3] = copyDown[2][1];
                down[2][4] = copyDown[1][0];
                down[3][0] = copyDown[3][6];
                down[3][1] = copyDown[3][5];
                down[3][2] = copyDown[2][4];
                down[3][3] = copyDown[2][3];
                down[3][4] = copyDown[1][2];
                down[3][5] = copyDown[1][1];
                down[3][6] = copyDown[0][0];
                break;
            default:
                break;
        }
    }

    //Modifies:
    //  Center: side[3][0]
    //  Left: side[3][6]
    //  Down: side[0][0]
    public void leftDiagonalFirst(String direction, String[][] center, String[][] left, String[][] right, String[][] down) {
        String[][] copyCenter = arrayCopy(center);
        String[][] copyLeft = arrayCopy(left);
        // String[][] copyRight = arrayCopy(right); //Redundant in this scenario
        String[][] copyDown = arrayCopy(down);
        switch(direction) {
            case "left":
                center[3][0] = copyDown[0][0];
                left[3][6] = copyCenter[3][0];
                down[0][0] = copyLeft[3][6];
                break;
            case "right":
                center[3][0] = copyLeft[3][6];
                left[3][6] = copyDown[0][0];
                down[0][0] = copyCenter[3][0];
                break;
            default:
                break;
        }
    }

    //Modifies:
    //  Center:
    //             side[2][0]
    //side[3][0]   side[3][1]  side[3][2]
    //  Left:
    //             side[2][4]
    //side[3][4]   side[3][5]  side[3][6]
    //  Down:
    //             side[0][0]
    //side[1][0]   side[1][1]  side[1][2]
    public void leftDiagonalSecond(String direction, String[][] center, String[][] left, String[][] right, String[][] down) {
        String[][] copyCenter = arrayCopy(center);
        String[][] copyLeft = arrayCopy(left);
        // String[][] copyRight = arrayCopy(right); //Redundant in this scenario
        String[][] copyDown = arrayCopy(down);
        switch(direction) {
            case "left":
                center[2][0] = copyDown[1][2];
                center[3][0] = copyDown[0][0];
                center[3][1] = copyDown[1][1];
                center[3][2] = copyDown[1][0];
                left[2][4] = copyCenter[3][2];
                left[3][4] = copyCenter[2][0];
                left[3][5] = copyCenter[3][1];
                left[3][6] = copyCenter[3][0];
                down[0][0] = copyLeft[3][6];
                down[1][0] = copyLeft[2][4];
                down[1][1] = copyLeft[3][5];
                down[1][2] = copyLeft[3][4];
                break;
            case "right":
                center[2][0] = copyLeft[3][4];
                center[3][0] = copyLeft[3][6];
                center[3][1] = copyLeft[3][5];
                center[3][2] = copyLeft[2][4];
                left[2][4] = copyDown[1][0];
                left[3][4] = copyDown[1][2];
                left[3][5] = copyDown[1][1];
                left[3][6] = copyDown[0][0];
                down[0][0] = copyCenter[3][0];
                down[1][0] = copyCenter[3][2];
                down[1][1] = copyCenter[3][1];
                down[1][2] = copyCenter[2][0];
                break;
            default:
                break;
        }
    }

    //Modifies:
    //  Center:
    //  side[1][0]
    //              side[2][1]  side[2][2]
    //                                      side[3][3]  side[3][4]
    //  Left:
    //  side[1][2]
    //              side[2][2]  side[2][3]
    //              side[3][2]  side[3][3]
    //  Down:
    //  side[2][0]  side[2][1]  side[2][2]  side[2][3]  side[2][4]
    public void leftDiagonalThird(String direction, String[][] center, String[][] left, String[][] right, String[][] down) {
        String[][] copyCenter = arrayCopy(center);
        String[][] copyLeft = arrayCopy(left);
        // String[][] copyRight = arrayCopy(right); //Redundant in this scenario
        String[][] copyDown = arrayCopy(down);
        switch(direction) {
            case "left":
                center[1][0] = copyDown[2][4];
                center[2][1] = copyDown[2][3];
                center[2][2] = copyDown[2][2];
                center[3][3] = copyDown[2][1];
                center[3][4] = copyDown[2][0];
                left[3][2] = copyCenter[1][0];
                left[3][3] = copyCenter[2][1];
                left[2][2] = copyCenter[2][2];
                left[2][3] = copyCenter[3][3];
                left[1][2] = copyCenter[3][4];
                down[2][0] = copyLeft[1][2];
                down[2][1] = copyLeft[2][3];
                down[2][2] = copyLeft[2][2];
                down[2][3] = copyLeft[3][3];
                down[2][4] = copyLeft[3][2];
                break;
            case "right":

                center[1][0] = copyLeft[3][2];
                center[2][1] = copyLeft[3][3];
                center[2][2] = copyLeft[2][2];
                center[3][3] = copyLeft[2][3];
                center[3][4] = copyLeft[1][2];
                left[1][2] = copyDown[2][0];
                left[2][2] = copyDown[2][1];
                left[2][3] = copyDown[2][2];
                left[3][2] = copyDown[2][4];
                left[3][3] = copyDown[2][3];
                down[2][0] = copyCenter[3][4];
                down[2][1] = copyCenter[3][3];
                down[2][2] = copyCenter[2][2];
                down[2][3] = copyCenter[2][1];
                down[2][4] = copyCenter[1][0];
                break;
            default:
                break;
        }
    }

    //Modifies:
    //  Center:
    //  side[0][0]
    //              side[1][1]  side[1][2]
    //                                      side[2][3]  side[2][4]
    //                                                              side[3][5]  side[3][6]
    //  Left:
    //  side[0][0]
    //  side[1][0]  side[1][1]
    //  side[2][0]  side[2][1]
    //  side[3][0]  side[3][1]
    //
    //  Down:
    //  side[3][0]  side[3][1]  side[3][2]  side[3][3]  side[3][4]  side[3][5]  side[3][6]
    //  Rotates Right Face
    public void leftDiagonalFourth(String direction, String[][] center, String[][] left, String[][] right, String[][] down) {
        String[][] copyCenter = arrayCopy(center);
        String[][] copyLeft = arrayCopy(left);
        String[][] copyRight = arrayCopy(right);
        String[][] copyDown = arrayCopy(down);
        switch(direction) {
            case "left":
                center[0][0] = copyDown[3][6];
                center[1][1] = copyDown[3][5];
                center[1][2] = copyDown[3][4];
                center[2][3] = copyDown[3][3];
                center[2][4] = copyDown[3][2];
                center[3][5] = copyDown[3][1];
                center[3][6] = copyDown[3][0];
                left[0][0] = copyCenter[3][6];
                left[1][0] = copyCenter[3][5];
                left[1][1] = copyCenter[2][4];
                left[2][0] = copyCenter[2][3];
                left[2][1] = copyCenter[1][2];
                left[3][0] = copyCenter[1][1];
                left[3][1] = copyCenter[0][0];
                down[3][0] = copyLeft[0][0];
                down[3][1] = copyLeft[1][1];
                down[3][2] = copyLeft[1][0];
                down[3][3] = copyLeft[2][1];
                down[3][4] = copyLeft[2][0];
                down[3][5] = copyLeft[3][1];
                down[3][6] = copyLeft[3][0];
                // Rotating Right Face
                right[0][0] = copyRight[3][6];
                right[1][0] = copyRight[2][4];
                right[1][1] = copyRight[3][5];
                right[1][2] = copyRight[3][4];
                right[2][0] = copyRight[1][2];
                right[2][1] = copyRight[2][3];
                // right[2][2] = copyRight[2][2]; // Center: Doesn't change
                right[2][3] = copyRight[3][3];
                right[2][4] = copyRight[3][2];
                right[3][0] = copyRight[0][0];
                right[3][1] = copyRight[1][1];
                right[3][2] = copyRight[1][0];
                right[3][3] = copyRight[2][1];
                right[3][4] = copyRight[2][0];
                right[3][5] = copyRight[3][1];
                right[3][6] = copyRight[3][0];
                break;
            case "right":
                center[0][0] = copyLeft[3][0];
                center[1][1] = copyLeft[3][1];
                center[1][2] = copyLeft[2][0];
                center[2][3] = copyLeft[2][1];
                center[2][4] = copyLeft[1][0];
                center[3][5] = copyLeft[1][1];
                center[3][6] = copyLeft[0][0];
                left[0][0] = copyDown[3][0];
                left[1][1] = copyDown[3][1];
                left[1][0] = copyDown[3][2];
                left[2][1] = copyDown[3][3];
                left[2][0] = copyDown[3][4];
                left[3][1] = copyDown[3][5];
                left[3][0] = copyDown[3][6];
                down[3][6] = copyCenter[0][0];
                down[3][5] = copyCenter[1][1];
                down[3][4] = copyCenter[1][2];
                down[3][3] = copyCenter[2][3];
                down[3][2] = copyCenter[2][4];
                down[3][1] = copyCenter[3][5];
                down[3][0] = copyCenter[3][6];
                //Rotating Right Face
                right[0][0] = copyRight[3][0];
                right[1][0] = copyRight[3][2];
                right[1][1] = copyRight[3][1];
                right[1][2] = copyRight[2][0];
                right[2][0] = copyRight[3][4];
                right[2][1] = copyRight[3][3];
                // right[2][2] = copyRight[2][2]; // Center: Doesn't Change
                right[2][3] = copyRight[2][1];
                right[2][4] = copyRight[1][0];
                right[3][0] = copyRight[3][6];
                right[3][1] = copyRight[3][5];
                right[3][2] = copyRight[2][4];
                right[3][3] = copyRight[2][3];
                right[3][4] = copyRight[1][2];
                right[3][5] = copyRight[1][1];
                right[3][6] = copyRight[0][0];
                break;
            default:
                break;
        }
    }

    //Modifies:
    //  Center:
    //    side[3][6];
    //  Right:
    //    side[3][0];
    //  Down:
    //    side[3][6];
    public void rightDiagonalFirst(String direction, String[][] center, String[][] left, String[][] right, String[][] down) {
        String[][] copyCenter = arrayCopy(center);
        // String[][] copyLeft = arrayCopy(left); //Redundant in this scenario
        String[][] copyRight = arrayCopy(right);
        String[][] copyDown = arrayCopy(down);
        switch(direction) {
            case "left":
                center[3][6] = copyRight[3][0];
                right[3][0] = copyDown[3][6];
                down[3][6] = copyCenter[3][6];
                break;
            case "right":
                center[3][6] = copyDown[3][6];
                right[3][0] = copyCenter[3][6];
                down[3][6] = copyRight[3][0];
                break;
            default:
                break;
        }
    }

    //Modifies:
    //  Center:
    //      side[2][4]
    //      side[3][4]  side[3][5]  side[3][6]
    //  Right:
    //      side[2][0]
    //      side[3][0]  side[3][1]  side[3][2]
    //  Down:
    //      side[2][4]
    //      side[3][4]  side[3][5]  side[3][6]
    public void rightDiagonalSecond(String direction, String[][] center, String[][] left, String[][] right, String[][] down) {
        String[][] copyCenter = arrayCopy(center);
        // String[][] copyLeft = arrayCopy(left); //Redundant in this scenario
        String[][] copyRight = arrayCopy(right);
        String[][] copyDown = arrayCopy(down);
        switch(direction) {
            case "left":
                center[2][4] = copyRight[3][2];
                center[3][4] = copyRight[2][0];
                center[3][5] = copyRight[3][1];
                center[3][6] = copyRight[3][0];
                right[2][0] = copyDown[3][4];
                right[3][0] = copyDown[3][6];
                right[3][1] = copyDown[3][5];
                right[3][2] = copyDown[2][4];
                down[2][4] = copyCenter[2][4];
                down[3][4] = copyCenter[3][4];
                down[3][5] = copyCenter[3][5];
                down[3][6] = copyCenter[3][6];
                break;
            case "right":
                center[2][4] = copyDown[2][4];
                center[3][4] = copyDown[3][4];
                center[3][5] = copyDown[3][5];
                center[3][6] = copyDown[3][6];
                right[2][0] = copyCenter[3][4];
                right[3][0] = copyCenter[3][6];
                right[3][1] = copyCenter[3][5];
                right[3][2] = copyCenter[2][4];
                down[2][4] = copyRight[3][2];
                down[3][4] = copyRight[2][0];
                down[3][5] = copyRight[3][1];
                down[3][6] = copyRight[3][0];
                break;
            default:
                break;
        }
    }

    //Modifies:
    //  Center:
    //  side[1][2]
    //              side[2][2]  side[2][3]
    //              side[3][2]  side[3][3]
    //  Right:
    //  side[1][0]
    //              side[2][1]  side[2][2]
    //                                      side[3][3]  side[3][4]
    //  Down:
    //  side[1][2]
    //              side[2][2]  side[2][3]
    //              side[3][2]  side[3][3]
    public void rightDiagonalThird(String direction, String[][] center, String[][] left, String[][] right, String[][] down) {
        String[][] copyCenter = arrayCopy(center);
        // String[][] copyLeft = arrayCopy(left); //Redundant in this scenario
        String[][] copyRight = arrayCopy(right);
        String[][] copyDown = arrayCopy(down);
        switch(direction) {
            case "left":
                center[1][2] = copyRight[3][4];
                center[2][3] = copyRight[3][3];
                center[2][2] = copyRight[2][2];
                center[3][3] = copyRight[2][1];
                center[3][2] = copyRight[1][0];
                right[1][0] = copyDown[3][2];
                right[2][1] = copyDown[3][3];
                right[2][2] = copyDown[2][2];
                right[3][3] = copyDown[2][3];
                right[3][4] = copyDown[1][2];
                down[1][2] = copyCenter[1][2];
                down[2][2] = copyCenter[2][2];
                down[2][3] = copyCenter[2][3];
                down[3][2] = copyCenter[3][2];
                down[3][3] = copyCenter[3][3];
                break;
            case "right":
                center[1][2] = copyDown[1][2];
                center[2][2] = copyDown[2][2];
                center[2][3] = copyDown[2][3];
                center[3][2] = copyDown[3][2];
                center[3][3] = copyDown[3][3];
                right[1][0] = copyCenter[3][2];
                right[2][1] = copyCenter[3][3];
                right[2][2] = copyCenter[2][2];
                right[3][3] = copyCenter[2][3];
                right[3][4] = copyCenter[1][2];
                down[1][2] = copyRight[3][4];
                down[2][3] = copyRight[3][3];
                down[2][2] = copyRight[2][2];
                down[3][3] = copyRight[2][1];
                down[3][2] = copyRight[1][0];
                break;
            default:
                break;
        }
    }

    //Modifies:
    //  Center:
    //  side[0][0]
    //  side[1][0]  side[1][1]
    //  side[2][0]  side[2][1]
    //  side[3][0]  side[3][1]
    //
    //  Right:
    //  side[0][0]
    //              side[1][1]  side[1][2]
    //                                      side[2][3]  side[2][4]
    //                                                              side[3][5]  side[3][6]
    //  Down:
    //  side[0][0]
    //  side[1][0]  side[1][1]
    //  side[2][0]  side[2][1]
    //  side[3][0]  side[3][1]
    //
    //  Rotates Left Face
    public void rightDiagonalFourth(String direction, String[][] center, String[][] left, String[][] right, String[][] down) {
        String[][] copyCenter = arrayCopy(center);
        String[][] copyLeft = arrayCopy(left); //Redundant in this scenario
        String[][] copyRight = arrayCopy(right);
        String[][] copyDown = arrayCopy(down);
        switch(direction) {
            case "left":
                center[0][0] = copyRight[3][6];
                center[1][1] = copyRight[3][5];
                center[1][0] = copyRight[2][4];
                center[2][1] = copyRight[2][3];
                center[2][0] = copyRight[1][2];
                center[3][1] = copyRight[1][1];
                center[3][0] = copyRight[0][0];
                right[0][0] = copyDown[3][0];
                right[1][1] = copyDown[3][1];
                right[1][2] = copyDown[2][0];
                right[2][3] = copyDown[2][1];
                right[2][4] = copyDown[1][0];
                right[3][5] = copyDown[1][1];
                right[3][6] = copyDown[0][0];
                down[0][0] = copyCenter[0][0];
                down[1][0] = copyCenter[1][0];
                down[1][1] = copyCenter[1][1];
                down[2][0] = copyCenter[2][0];
                down[2][1] = copyCenter[2][1];
                down[3][0] = copyCenter[3][0];
                down[3][1] = copyCenter[3][1];
                // Rotating Left Face
                left[0][0] = copyLeft[3][6];
                left[1][0] = copyLeft[2][4];
                left[1][1] = copyLeft[3][5];
                left[1][2] = copyLeft[3][4];
                left[2][0] = copyLeft[1][2];
                left[2][1] = copyLeft[2][3];
                // left[2][2] = copyLeft[2][2]; // Center: Doesn't change
                left[2][3] = copyLeft[3][3];
                left[2][4] = copyLeft[3][2];
                left[3][0] = copyLeft[0][0];
                left[3][1] = copyLeft[1][1];
                left[3][2] = copyLeft[1][0];
                left[3][3] = copyLeft[2][1];
                left[3][4] = copyLeft[2][0];
                left[3][5] = copyLeft[3][1];
                left[3][6] = copyLeft[3][0];
                break;
            case "right":
                center[0][0] = copyDown[0][0];
                center[1][0] = copyDown[1][0];
                center[1][1] = copyDown[1][1];
                center[2][0] = copyDown[2][0];
                center[2][1] = copyDown[2][1];
                center[3][0] = copyDown[3][0];
                center[3][1] = copyDown[3][1];
                right[0][0] = copyCenter[3][0];
                right[1][1] = copyCenter[3][1];
                right[1][2] = copyCenter[2][0];
                right[2][3] = copyCenter[2][1];
                right[2][4] = copyCenter[1][0];
                right[3][5] = copyCenter[1][1];
                right[3][6] = copyCenter[0][0];
                down[3][0] = copyRight[0][0];
                down[3][1] = copyRight[1][1];
                down[2][0] = copyRight[1][2];
                down[2][1] = copyRight[2][3];
                down[1][0] = copyRight[2][4];
                down[1][1] = copyRight[3][5];
                down[0][0] = copyRight[3][6];
                //Rotating Left Face
                left[0][0] = copyLeft[3][0];
                left[1][0] = copyLeft[3][2];
                left[1][1] = copyLeft[3][1];
                left[1][2] = copyLeft[2][0];
                left[2][0] = copyLeft[3][4];
                left[2][1] = copyLeft[3][3];
                // left[2][2] = copyLeft[2][2]; // Center: Doesn't Change
                left[2][3] = copyLeft[2][1];
                left[2][4] = copyLeft[1][0];
                left[3][0] = copyLeft[3][6];
                left[3][1] = copyLeft[3][5];
                left[3][2] = copyLeft[2][4];
                left[3][3] = copyLeft[2][3];
                left[3][4] = copyLeft[1][2];
                left[3][5] = copyLeft[1][1];
                left[3][6] = copyLeft[0][0];
                break;
            default:
                break;
        }
    }
}
