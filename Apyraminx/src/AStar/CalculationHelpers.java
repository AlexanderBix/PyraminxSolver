package AStar;

import Moving.Movements;

import java.util.Arrays;

public class CalculationHelpers {
    // Determines if successor is in the open or closed list -> Returns index
    public int isInListIndex(String[][][] successors, String[][][][] list) {
        boolean isOpen = true;
        int index = -1;
        // Determining if successor is in the open list.
        for(int j = 0; j < list.length; j++) { // For each entry
            isOpen = true;
            for(int k = 0; k < list[j].length; k++) { // For each face
                for(int l = 0; l < list[j][k].length; l++) { // For each row
                    for(int m = 0; m < list[j][k][l].length; m++){ // For each triangle node
                        if(!list[j][k][l][m].equals(successors[k][l][m])) {
                            isOpen = false;
                        }
                    }
                }
            }
            if(isOpen) {
                index = j;
                break;
            }
        }
        return index;
    }

    // Determines if successor is in the open or closed list
    public boolean isInList(String[][][] successors, String[][][][] list) {
        boolean isOpen = true;
        // Determining if successor is in the open list.
        for(int j = 0; j < list.length; j++) { // For each entry
            isOpen = true;
            for(int k = 0; k < list[j].length; k++) { // For each face
                for(int l = 0; l < list[j][k].length; l++) { // For each row
                    for(int m = 0; m < list[j][k][l].length; m++){ // For each triangle node
                        if(!list[j][k][l][m].equals(successors[k][l][m])) {
                            isOpen = false;
                        }
                    }
                }
            }
            if(isOpen) return true;
        }
        return false;
    }

    // Determines if successor is in the open or closed list
    public int isInOpenList(String[][][] successors, OpenListAttributes[] openList) {
        boolean isOpen = true;
        // Determining if successor is in the open list.
        for(int j = 0; j < openList.length; j++) { // For each entry
            String[][][] list = openList[j].openListEntry;
            isOpen = true;
            for(int k = 0; k < list.length; k++) { // For each face
                for(int l = 0; l < list[k].length; l++) { // For each row
                    for(int m = 0; m < list[k][l].length; m++){ // For each triangle node
                        if(!list[k][l][m].equals(successors[k][l][m])) {
                            isOpen = false;
                        }
                    }
                }
            }
            if(isOpen) return j;
        }
        return -1;
    }

    // Determines if successor is in the open or closed list
    public int isInClosedList(String[][][] successors, ClosedListAttributes[] closedList) {
        boolean isOpen = true;
        // Determining if successor is in the open list.
        for(int j = 0; j < closedList.length; j++) { // For each entry
            String[][][] list = closedList[j].closedListEntry;
            isOpen = true;
            for(int k = 0; k < list.length; k++) { // For each face
                for(int l = 0; l < list[k].length; l++) { // For each row
                    for(int m = 0; m < list[k][l].length; m++){ // For each triangle node
                        if(!list[k][l][m].equals(successors[k][l][m])) {
                            isOpen = false;
                        }
                    }
                }
            }
            if(isOpen) return j;
        }
        return -1;
    }



    // Creates a new array with i + 1 elements
    public String[][][][] increaseList(String[][][] successor,String[][][][] successorList) {
        String[][][][] successorNew = Arrays.copyOf(successorList, successorList.length + 1);
        successorNew[successorNew.length - 1] = successor;
        return successorNew;
    }

    // Add OpenList Entry to array
    public OpenListAttributes[] increaseOpenList(OpenListAttributes[] list,  OpenListAttributes value) {
        OpenListAttributes[] openList = Arrays.copyOf(list, list.length + 1);
        openList[openList.length - 1] = value;
        return openList;
    }

    // Add ClosedList entry to array
    public ClosedListAttributes[] increaseClosedList(ClosedListAttributes[] list,  ClosedListAttributes value) {
        ClosedListAttributes[] closedList = Arrays.copyOf(list, list.length + 1);
        closedList[closedList.length - 1] = value;
        return closedList;
    }

    // Creates a new array with i - 1 elements
    public String[][][][] decreaseList(String[][][][] successorList, int index) {
        String[][][][] newList = {};
        // For length of list
        for(int i = 0; i < successorList.length; i++) {
            // If i isn't the index, add to new list
            if(i != index) {
                newList = increaseList(successorList[i],newList);
            }
        }
        return newList;
    }

    // Removes array element from open list
    public OpenListAttributes[] decreaseOpenList(OpenListAttributes[] list, int index) {
        OpenListAttributes[] openList = {};

        for(int i = 0; i < list.length; i++) {
            if(i != index) {
                openList = increaseOpenList(openList,list[i]);
            }
        }
        return openList;
    }

    // Removes array element from closed list
    public ClosedListAttributes[] decreaseClosedList(ClosedListAttributes[] list, int index) {
        ClosedListAttributes[] closedList = {};
        for(int i = 0; i < list.length; i++) {
            if(i != index) {
                closedList = increaseClosedList(closedList,list[i]);
            }
        }
        return closedList;
    }

    // Calculates all possible successors of the current node
    public String[][][][] getSuccessors(Movements moving, String[][] red, String[][] yellow, String[][] blue, String[][] green) {
        // Creating Parameters required for face movements to occur
        String direction = "left";
        String[][] center = {};
        String[][] left = {};
        String[][] right = {};
        String[][] down = {};
        String[][] centerCopy;
        String[][] leftCopy;
        String[][] rightCopy;
        String[][] downCopy;
        String[][] yellowCopy = moving.arrayCopy(yellow);
        String[][] redCopy = moving.arrayCopy(red);
        String[][] blueCopy = moving.arrayCopy(blue);
        String[][] greenCopy = moving.arrayCopy(green);
        String[][][] successor= {};
        String[][][][] successorList = { };
        // For Every Relative Center Face Combination
        for (int i = 0; i < 1; i++) {
            switch(i) {
                case 0:
                    center = moving.arrayCopy(redCopy);
                    left = moving.arrayCopy(yellowCopy);
                    right = moving.arrayCopy(blueCopy);
                    down = moving.arrayCopy(greenCopy);
                    break;
                case 1:
                    center = moving.arrayCopy(greenCopy);
                    left = moving.arrayCopy(redCopy);
                    right = moving.arrayCopy(blueCopy);
                    down = moving.arrayCopy(yellowCopy);
                    break;
                case 2:
                    center = moving.arrayCopy(blueCopy);
                    left = moving.arrayCopy(redCopy);
                    right = moving.arrayCopy(yellowCopy);
                    down = moving.arrayCopy(greenCopy);
                    break;
                case 3:
                    center = moving.arrayCopy(yellowCopy);
                    left = moving.arrayCopy(blueCopy);
                    right = moving.arrayCopy(redCopy);
                    down = moving.arrayCopy(greenCopy);
            }
            centerCopy = moving.arrayCopy(center);
            leftCopy = moving.arrayCopy(left);
            rightCopy = moving.arrayCopy(right);
            downCopy = moving.arrayCopy(down);
            for (int j = 0; j < 12; j++) {
                switch(j) {
                    case 0:
                        moving.rowFirst(direction, center, left, right, down);
                        break;
                    case 1:
                        moving.rowSecond(direction, center, left, right, down);
                        break;
                    case 2:
                        moving.rowThird(direction, center, left, right, down);
                        break;
                    case 3:
                        moving.rowFourth(direction,center,left,right,down);
                        break;
                    case 4:
                        moving.leftDiagonalFirst(direction,center,left,right,down);
                        break;
                    case 5:
                        moving.leftDiagonalSecond(direction,center,left,right,down);
                        break;
                    case 6:
                        moving.leftDiagonalThird(direction,center,left,right,down);
                        break;
                    case 7:
                        moving.leftDiagonalFourth(direction,center,left,right,down);
                        break;
                    case 8:
                        moving.rightDiagonalFirst(direction,center,left,right,down);
                        break;
                    case 9:
                        moving.rightDiagonalSecond(direction,center,left,right,down);
                        break;
                    case 10:
                        moving.rightDiagonalThird(direction,center,left,right,down);
                        break;
                    case 11:
                        moving.rightDiagonalFourth(direction,center,left,right,down);
                        break;
                }
                switch(i) {
                    case 0:
                        successor = new String[][][]{center,left,right,down};
                        break;
                    case 1:
                        successor = new String[][][]{left,down,right,center};
                        break;
                    case 2:
                        successor = new String[][][]{left,right,center,down};
                        break;
                    case 3:
                        successor = new String[][][]{right,center,left,down};
                        break;
                }

                successorList = increaseList(successor,successorList);
                center = moving.arrayCopy(centerCopy);
                left = moving.arrayCopy(leftCopy);
                right = moving.arrayCopy(rightCopy);
                down = moving.arrayCopy(downCopy);
            }
        }
        return successorList;
    }

    // Increases smaller arrays
    public int[] increaseInt(int[] list, int value) {
        int[] newList = Arrays.copyOf(list, list.length + 1);
        newList[list.length ] = value; // Check on this
        return newList;
    }

    // Removes smaller arrays
    public int[] decreaseInt(int[] list, int index) {
        int[] newIndex = {};
        // For length of list
        for(int i = 0; i < list.length; i++) {
            if(i != index) {
                newIndex = increaseInt(newIndex,list[i]);
            }
        }
        return newIndex;
    }

    // Determines Heuristic cost
    public int heuristic(String[][] red, String[][] yellow, String[][] blue, String[][] green) {
        int cost = 0;
        if(!red[0][0].equals("r") || !red[1][0].equals("r") || !red[1][1].equals("r") || !red[1][2].equals("r")) cost++;
        if(!red[2][0].equals("r") || !red[3][0].equals("r") || !red[3][1].equals("r") || !red[3][2].equals("r")) cost++;
        if(!red[2][4].equals("r") || !red[3][4].equals("r") || !red[3][5].equals("r") || !red[3][6].equals("r")) cost++;
        if(!yellow[0][0].equals("y") || !yellow[1][1].equals("y") || !yellow[1][1].equals("y") || !yellow[1][2].equals("y")) cost++;
        if(!yellow[2][0].equals("y") || !yellow[3][0].equals("y") || !yellow[3][1].equals("y") || !yellow[3][2].equals("y")) cost++;
        if(!yellow[2][4].equals("y") || !yellow[3][4].equals("y") || !yellow[3][5].equals("y") || !yellow[3][6].equals("y")) cost++;
        if(!blue[0][0].equals("b") || !blue[1][1].equals("b") || !blue[1][1].equals("b") || !blue[1][2].equals("b")) cost++;
        if(!blue[2][0].equals("b") || !blue[3][0].equals("b") || !blue[3][1].equals("b") || !blue[3][2].equals("b")) cost++;
        if(!blue[2][4].equals("b") || !blue[3][4].equals("b") || !blue[3][5].equals("b") || !blue[3][6].equals("b")) cost++;
        if(!green[0][0].equals("g") || !green[1][1].equals("g") || !green[1][1].equals("g") || !green[1][2].equals("g")) cost++;
        if(!green[2][0].equals("g") || !green[3][0].equals("g") || !green[3][1].equals("g") || !green[3][2].equals("g")) cost++;
        if(!green[2][4].equals("g") || !green[3][4].equals("g") || !green[3][5].equals("g") || !green[3][6].equals("g")) cost++;
        return cost / 3;
    }

    // Determines if the goal node has been reached
    public boolean isGoal(String[][] red, String[][] yellow, String[][] blue, String[][] green) {
        for(int i = 0; i < yellow.length; i++) {
            for(int j = 0; j < yellow[i].length; j++) {
                if(!yellow[i][j].equals("y")) {
                    return false;
                }
                if(!red[i][j].equals("r")) {
                    return false;
                }
                if(!green[i][j].equals("g")) {
                    return false;
                }
                if(!blue[i][j].equals("b")) {
                    return false;
                }
            }
        }
        return true;
    }

}
