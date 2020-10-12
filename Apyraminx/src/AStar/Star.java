package AStar;

import Layout.Side;
import Moving.Movements;

import java.util.Arrays;

public class Star{

    // Preparing the open lists and closed lists for A*
    public void searchStart(Side index, Movements moving, String[][] yellow, String[][] red, String[][] blue, String[][] green, int k) {
        CalculationHelpers calculations = new CalculationHelpers();

        // Setting up Open List
        OpenListAttributes openListEntry = new OpenListAttributes();
        openListEntry.depth = 0;
        openListEntry.heuristic = calculations.heuristic(red,yellow,blue,green);
        openListEntry.parent = null;
        openListEntry.openListEntry = new String[][][]{moving.arrayCopy(red),moving.arrayCopy(yellow),moving.arrayCopy(blue),moving.arrayCopy(green)};
        openListEntry.cost = 0;

        // Adding entry to openList
        OpenListAttributes[] openList = {openListEntry};

        // Setting up closed list
        ClosedListAttributes[] closedList = {};

        search(openList,closedList,calculations, index, moving, k);
    }

    // A* Algorithm
    public void search(OpenListAttributes[] openList, ClosedListAttributes[] closedList, CalculationHelpers calculations, Side index, Movements moving, int k) {
        String[][] openRed = {};
        String[][] openYellow = {};
        String[][] openBlue = {};
        String[][] openGreen = {};
        int currentHeuristic = 0;
        int currentDepth = 0;
        int currentCost = 0;
        int nodesTraversed = 0;

        // While the open list is not empty
        while(openList.length != 0) {

            //Determining which value in the open list to use
            for(int i = 0; i < openList.length; i++) {

                // If first iteration
                if( i == 0 ) {
                    openRed = moving.arrayCopy(openList[i].openListEntry[0]);
                    openYellow = moving.arrayCopy(openList[i].openListEntry[1]);
                    openBlue = moving.arrayCopy(openList[i].openListEntry[2]);
                    openGreen = moving.arrayCopy(openList[i].openListEntry[3]);
                    currentHeuristic = openList[i].heuristic;
                    currentDepth = openList[i].depth;
                    currentCost = openList[i].cost;
                }
                // If iteration is lower than lowest iteration so far
                else if(  openList[i].cost + openList[i].heuristic <  currentCost  + currentHeuristic ){ // Modified this
                    // If open list entry is cheaper than current cheapest
                    openRed = moving.arrayCopy(openList[i].openListEntry[0]);
                    openYellow = moving.arrayCopy(openList[i].openListEntry[1]);
                    openBlue = moving.arrayCopy(openList[i].openListEntry[2]);
                    openGreen = moving.arrayCopy(openList[i].openListEntry[3]);
                    currentHeuristic = openList[i].heuristic;
                    currentDepth = openList[i].depth;
                    currentCost = openList[i].cost;
                }
            }

            // Increase number of nodes traversed
            nodesTraversed++;

           // This is just to make it easier to output Pyraminx -> Will probably be removed before submission
            String[][][] openCurrentEntry = {openRed, openYellow, openBlue, openGreen};
            String[][][][] output = {openCurrentEntry};

            // If the pyraminx is solved, end loop - WORKING
            if(calculations.isGoal(openRed,openYellow,openBlue,openGreen)) {
                System.out.println("SUCCESS at k = " + k);
                System.out.println("Depth = " +   (currentDepth));
                System.out.println("Nodes Traversed = " + nodesTraversed);
                System.out.println(" ");
                break;
            }

            // Getting successors of selected entry - WORKING
            String[][][][] successors = calculations.getSuccessors(moving,openRed,openYellow,openBlue,openGreen);
            int successorCost = 0;

            // Removing Selected Open List value from open list
            openList = calculations.decreaseOpenList(openList, calculations.isInOpenList(openCurrentEntry,openList));

            // For each successor
            for(int i = 0; i < successors.length; i++) {
                 if(currentDepth > k) continue;

                 // Calculating cost
                successorCost = currentHeuristic  + currentDepth ;

                // If successor is in open list
                if(calculations.isInOpenList(successors[i],openList) != -1) {
                    // If cost of node successor is less than or equal to successor cost -> Skip Successor
                    if(openList[calculations.isInOpenList(successors[i],openList)].cost < successorCost)  {
                        // remove from open list
                         openList = calculations.decreaseOpenList(openList, calculations.isInOpenList(successors[i],openList));
                        continue;
                    }
                }

                // If successor is in closed list
                else if(calculations.isInClosedList(successors[i], closedList) != -1) {
                    // If cost of node successor is less than or equal to successor cost -> Skip Successor
                    if(successorCost < closedList[calculations.isInClosedList(successors[i],closedList)].cost ) {
                        // Remove from closed list
                         closedList = calculations.decreaseClosedList(closedList, calculations.isInClosedList(successors[i],closedList));
                        continue;
                    } else {
                        // Move successor from closed list to open list
                        OpenListAttributes newOpen = new OpenListAttributes();
                        newOpen.openListEntry = Arrays.copyOf(successors[i],successors[i].length);
                        newOpen.cost = successorCost;
                        newOpen.parent = Arrays.copyOf(openCurrentEntry,openCurrentEntry.length);
                        newOpen.depth = currentDepth + 1;
                        newOpen.heuristic = calculations.heuristic(successors[i][0],successors[i][1],successors[i][2],successors[i][3]);
                        openList = calculations.increaseOpenList(openList, newOpen);
                        closedList = calculations.decreaseClosedList(closedList, calculations.isInClosedList(successors[i],closedList));
                    }


                }

                // If successor is not in a list
                else {
                    // Add successor to open list
                    OpenListAttributes newOpen = new OpenListAttributes();
                    newOpen.openListEntry = Arrays.copyOf(successors[i],successors[i].length);
                    newOpen.heuristic = calculations.heuristic(successors[i][0],successors[i][1],successors[i][2],successors[i][3]);
                    newOpen.depth = currentDepth + 1;
                    newOpen.cost = successorCost;
                    newOpen.parent = Arrays.copyOf(openCurrentEntry,openCurrentEntry.length);
                    openList = calculations.increaseOpenList(openList, newOpen);
                }

            }

            // Add node to closed list
            ClosedListAttributes newClosed = new ClosedListAttributes();
            newClosed.closedListEntry = Arrays.copyOf(openCurrentEntry,openCurrentEntry.length);
            newClosed.heuristic = currentHeuristic;
            newClosed.cost = successorCost;
            closedList = calculations.increaseClosedList(closedList,newClosed);

        }

    }

    // Outputting successor list - Really only for debugging purposes
    public void printSuccessors(String[][][][] successorList) {
        for(int i = 0; i < successorList.length; i++) {
            //Outputting Everything At the Start
            System.out.println("   " + successorList[i][1][0][0] + "              " + successorList[i][0][0][0] + "              " + successorList[i][2][0][0]);
            System.out.println("  " + successorList[i][1][1][0] + successorList[i][1][1][1] + successorList[i][1][1][2] + "            " + successorList[i][0][1][0] + successorList[i][0][1][1] + successorList[i][0][1][2] + "            " + successorList[i][2][1][0] + successorList[i][2][1][1] + successorList[i][2][1][2]);
            System.out.println(" " + successorList[i][1][2][0] + successorList[i][1][2][1]+ successorList[i][1][2][2] + successorList[i][1][2][3] + successorList[i][1][2][4] + "          " + successorList[i][0][2][0] + successorList[i][0][2][1] + successorList[i][0][2][2] + successorList[i][0][2][3] + successorList[i][0][2][4] + "          " + successorList[i][2][2][0]+ successorList[i][2][2][1] + successorList[i][2][2][2] + successorList[i][2][2][3] + successorList[i][2][2][4]);
            System.out.println(successorList[i][1][3][0]+ successorList[i][1][3][1] + successorList[i][1][3][2] + successorList[i][1][3][3] + successorList[i][1][3][4] + successorList[i][1][3][5] + successorList[i][1][3][6] + "        " + successorList[i][0][3][0] + successorList[i][0][3][1] + successorList[i][0][3][2] + successorList[i][0][3][3] + successorList[i][0][3][4] + successorList[i][0][3][5] + successorList[i][0][3][6] + "        " + successorList[i][2][3][0] + successorList[i][2][3][1] + successorList[i][2][3][2] + successorList[i][2][3][3] + successorList[i][2][3][4] + successorList[i][2][3][5] + successorList[i][2][3][6]);
            System.out.println("                  " + successorList[i][3][0][0]);
            System.out.println("                 " + successorList[i][3][1][0] + successorList[i][3][1][1] + successorList[i][3][1][2]);
            System.out.println("                " + successorList[i][3][2][0] + successorList[i][3][2][1] + successorList[i][3][2][2] + successorList[i][3][2][3] + successorList[i][3][2][4]);
            System.out.println("               " + successorList[i][3][3][0] + successorList[i][3][3][1] + successorList[i][3][3][2] + successorList[i][3][3][3] + successorList[i][3][3][4] + successorList[i][3][3][5] + successorList[i][3][3][6]);
            System.out.println("");
        }
    }
}