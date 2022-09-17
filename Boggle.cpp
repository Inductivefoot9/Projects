
#include "Dictionary.h"
#include "Boggle.h"

// Your code here

/*
 * Function: function will return dict
 *
 * Precondition:
 * none
 *
 * Postcondition: returns dict
 */
Dictionary Boggle::GetDictionary() {
    return dict;
}
/*
 * Function: function will return wordsFound
 *
 * Precondition:
 * none
 *
 * Postcondition: returns wordsFound
 */
Dictionary Boggle::WordsFound() {
    return wordsFound;
}

/*
 * Function:  Default constructor initializes board and visited matrix
 *
 * Precondition:
 * none
 *
 * Postcondition:
 * All member variables will be initialized and the both matrices will be set to starting values
 */
Boggle::Boggle() {

    ifstream  infile;
    infile.open("dictionary.txt");

    dict.LoadDictionaryFile("dictionary.txt");

    for(int i = 0; i < BOARD_SIZE; i++){
        for(int j = 0; j < BOARD_SIZE; j++){
            board[i][j] = "";
        }
    }
    for(int i = 0; i < BOARD_SIZE; i++){
        for(int j = 0; j < BOARD_SIZE; j++){
            visited[i][j] = 0;
        }
    }


}
/*
 * Function:  Default constructor initializes board and visited matrix
 *
 * Precondition: Takes in a string that should be the name of a file
 *
 * Postcondition:
 * All member variables will be initialized and the both matrices will be set to starting values
 */
Boggle::Boggle(string filename) {
    ifstream  infile;
    infile.open(filename);

    if(infile.fail()){
        throw DictionaryError("Invalid character");
    }

    dict.LoadDictionaryFile(filename);

    for(int i = 0; i < BOARD_SIZE; i++){
        for(int j = 0; j < BOARD_SIZE; j++){
                board[i][j] = "";
        }
    }

    for(int i = 0; i < BOARD_SIZE; i++){
        for(int j = 0; j < BOARD_SIZE; j++){
            visited[i][j] = 0;
        }
    }


}
/*
 * Function: This function will copy each entry of board
 *
 * Precondition: function takes in a string board matrix
 *
 * Postcondition: If the precondition is met then the board passed in will be copied
 *
 */
void Boggle::SetBoard(string (*board)[4]) {

    for(int i = 0; i < BOARD_SIZE; i++){
        for(int j = 0; j < BOARD_SIZE; j++){

            this->board[i][j] = board[i][j];
        }
    }

}
/*
 * Function: This function will reset wordsFound and serve as a wrapper for solve board helper
 * which will find all teh words on the current board
 *
 * Precondition: function takes in a boolean variable and an ostream object
 *
 * Postcondition: If the precondition is met then the wordsFound will be reset and both
 * print board boolean variable and ostream output object will be passed to the solveBoardHelper function
 *
 */
void Boggle::SolveBoard(bool printBoard, ostream &output) {

    wordsFound.MakeEmpty();

    for(int  i=0; i < BOARD_SIZE; i++){
        for(int j =0; j < BOARD_SIZE; j++){

            SolveBoardHelper(printBoard, output, 1, visited, i, j, "");
            for(int r = 0; r < BOARD_SIZE; r++){
                for(int c = 0; c < BOARD_SIZE; c++){
                    visited[r][c] = 0;
                }
            }
        }
    }
}

/*
 * Function: This function will find all the words on the current board and call the
 * printBoard function
 *
 * Precondition: function takes in  a boolean variable to printBoard, ostream output object, tracking integer variable,
 * an int variable matrix to track visited positions, int row variable to represent rows in matrix,
 * int col variable to represent columns in the matrix, a string variable to hold current prefix, and a dictionary
 * object to hold words found
 *
 * Postcondition: If the precondition is met then solveBoardHelper will find all the words on the current board and may
 * or may not output them to the user.
 *
 */
void Boggle::SolveBoardHelper(bool printBoard, ostream &output, int track, int visited[][BOARD_SIZE], int row , int col, string currPre) {

    // if the row or col is out of bounds return
    if((row < 0 || row >= BOARD_SIZE) || (col < 0 || col >= BOARD_SIZE) ) {
        return;
    }

    // if we visited a space already return
    if(visited[row][col] != 0 ){
        return;
    }

    currPre += board[row][col];
    //if the current currPre is a prefix keep going else return
    if(dict.IsPrefix(currPre)) {
        visited[row][col] = track;
        track++;

    }
    else{
        //visited[row][col] = 0;
        currPre.pop_back();
        return;
    }

    // if currPre is a word it will send it the ostream object and add it to words found
    if(dict.IsWord(currPre) && !(wordsFound.IsWord(currPre)) ){

            // add word to the dictionary
            wordsFound.AddWord(currPre);

            //debugging cout statements
//            cout << "isword" << endl;
//            cout << currPre << endl;

            if(printBoard) {
                output << "Word: " << currPre << endl;
                output << "Number of Words: " << wordsFound.WordCount() << endl;
                PrintBoard(output);
                //debugging cout statements
//                cout << "the board" << endl;
            }
            else {
                output << wordsFound.WordCount() << "\t" << currPre << endl;
            }

    }

    SolveBoardHelper(printBoard, output, track, visited, row - 1, col, currPre);
    SolveBoardHelper(printBoard, output, track, visited, row - 1, col + 1, currPre);
    SolveBoardHelper(printBoard, output, track, visited, row, col + 1, currPre);
    SolveBoardHelper(printBoard, output, track, visited, row + 1, col +1, currPre);
    SolveBoardHelper(printBoard, output, track, visited, row + 1 , col , currPre);
    SolveBoardHelper(printBoard, output, track, visited, row + 1, col - 1, currPre);
    SolveBoardHelper(printBoard, output, track, visited, row, col - 1, currPre);
    SolveBoardHelper(printBoard, output, track, visited, row - 1, col - 1, currPre);

    visited[row][col] = 0;
}
/*
 * Function: This function will save teh words found to a file
 *
 * Precondition: Function takes in a string for a file name
 *
 * Postcondition: If the precondition is met then the words found will be saved to a file
 *
 */
void Boggle::SaveSolve(string filename) {

    wordsFound.SaveDictionaryFile(filename);
}

/*
 * Function: This function will output the board matrix and visited matrix to an ostream object
 *
 * Precondition: function takes in an ostream object
 *
 * Postcondition: If the precondition is met then both matrices will be output to the passed in
 * ostream object
 *
 *
 *
 */
void Boggle::PrintBoard(ostream &output) {

    for(int i = 0; i < BOARD_SIZE; i++){
        for(int j = 0; j < BOARD_SIZE; j++){
            if(visited[i][j] > 0){
                output << " '" << board[i][j] << "' ";
            }

            else{
                output << "  " << board[i][j] << "  ";
            }
        }

        output << "\t";
        for(int k = 0; k < BOARD_SIZE; k++){
            output << "  " << visited[i][k] << "  ";

        }
        output << endl;
    }

    output << endl;
}




