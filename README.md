README:

A* on a Pyraminx
Written By: Alexander Bix
Programming Language: Java (JDK version 14.0.2)

    Running the program:
      Running the Program through a terminal:
        -Download and install JDK 14.0.2 from here: https://www.oracle.com/java/technologies/javase-downloads.html
        -In a terminal, go to the directory of the 'Pyraminx' folder.
        -Then in the terminal, go to the directory of the 'src' folder.
        -Run the following command to compile the program: 'javac Main.java'
          -If this command doesn't work, make sure that environment variables are configured properly:
            -Instructions on how to do this: https://www.java.com/en/download/help/path.xml
        -Run the following command to start the program: 'java Main'
          -The program should be running now, prompting the user to:
            -Enter the number of random movements they want to happen
            -Enter 'r' to reset the Pyraminx
            -Enter 'q' to quit the program

	Running the Program with Intellij: (Recommended way to run the program as this IDE was used to program the assignment)
		-Download and install JDK 14.0.2 from here: https://www.oracle.com/java/technologies/javase-downloads.html
		-Download and install from Intellij (Community Edition) from here: https://www.jetbrains.com/idea/download/#section=windows
		-Open Intellij.
		-Click on 'File' on the top bar of Intellij
		-Click on 'Open' on the drop-down menu that should appear
		-Set the directory to the location of the 'Pyraminx' source code folder
		-Click on 'Run' on the top bar of Intellij 
		-Click on 'Run...' on the drop-down menu that should appear
		-Click 'Main' on the menu that should appear in the center of the screen
		-The program should be running now, prompting the user to:
			-Enter the number of random movements they want to happen
			-Enter 'r' to reset the Pyraminx
			-Enter 'q' to quit the program
			

    Description of Program: (Data Structures and Heuristic)
      -Data Structures in A*:
        -Open List:
          -An array of classes is used to store the contents of the open list. Each class entry contains:
            -The layout of Pyraminx on said move
            -The depth of the Pyraminx on said move
            -The cost of the current move on the Pyraminx
            -The heuristic value of the Pyraminx on said move
            -The parent Pyraminx layout for said move
          -When selecting an entry from the open list, the entry with the lowest (cost + heuristic) is chosen, and the successor moves are stored in an array.
            -Then for each successor, the successor cost is calculated, which is used to determine whether or not to ignore the successor if it is already in the open and closed list.
            -If a successor is not in a list, it is added to the open list. 
            -If a sucessor is already in the open list and it's cost is less than the successor cost, the successor is ignored.
            -If a successor is already in the closed list and it's cost is less than the successor cost, the successor is ignored. Otherwise the value is moved from the closed list to the open list.
          -After evaluating all successors with an entry in the open list, the entry is removed
        -Closed List:
          -An array of classes is used to store the contents of the closed list. Each class entry contains:
            -The layout of the Pyraminx on said move
            -The heuristic vlaue of the Pyraminx on said move
            -The cost of the current move on the Pyraminx
      -Heuristic:
        -Each face of the Pyraminx has three corner 'tri-pieces', each containing 4 triangle nodes on the Pyraminx. For example:
                  c
                 ccc
                cgggc
               cccgccc
          -Each triangle node that is labeled as 'c' is part of a 'tri-piece'
        -The Heuristic value is calculated by taking the amount of 'tri-pieces' that are not all the same color for a faces' given color. For example:
                  b
                 yrg
                ggggg
               ggggggg
          -This green face has 1 'tri-piece' that is incorrect
        -After adding all incorrect 'tri-pieces' across every face of the Pyraminx, that value is divided by 3, since making a move on a 'tri-piece' will affect 'tri-pieces' on 3 sides of the Pyraminx:
          -Formula Representation: (Total Number of Mismatched 'tri-pieces') / 3
        -This will give a Heuristic value of anywhere from 0-4.

