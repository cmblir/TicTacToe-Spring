Tic-Tac-Toe using Spring Framework

This project is a Tic-Tac-Toe game implemented using the Spring Framework. The game allows two players to take turns placing their respective symbols, O and X, on a 3x3 board. The implementation employs the alpha-beta pruning algorithm for efficient move selection.

Installation
To run this Tic-Tac-Toe game locally, follow these steps:

Clone this repository to your local machine.
Ensure you have Java and Maven installed.
Navigate to the project directory in your terminal.
Run mvn clean install to build the project.
Execute the generated JAR file using java -jar <JAR_FILE_NAME>.
How to Play
Start the game by running the application.
Two players take turns to make their moves by selecting an empty cell on the board.
The game continues until one player wins by placing three of their symbols in a row, column, or diagonal, or the board is filled with no winner (a draw).
Alpha-Beta Pruning Algorithm
The alpha-beta pruning algorithm is a technique used in decision trees and game trees to reduce the number of nodes evaluated during the search for the best move. It works by eliminating branches that cannot possibly influence the final decision, thus significantly improving the efficiency of the search process in games like Tic-Tac-Toe.

Contributors
Yoo nicholas
Feel free to contribute to this project by forking it and submitting a pull request!

License
This project is licensed under the MIT License - see the LICENSE file for details.

Feel free to customize this template to better fit your project's specifics!
