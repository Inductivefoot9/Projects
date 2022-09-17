#include "Dictionary.h"

// Your code here

/*
 * Function:  Default constructor initializes root and member variables
 *
 * Precondition:
 * none
 *
 * Postcondition:
 * All member variables and teh root will be initialized
 */
Dictionary::Dictionary() {
    root = new Node;
    numWords = 0;
}

/*
 * Function: Deconstructor will pass root to destroy helper
 *
 * Precondition:
 * none
 *
 * Postcondition:
 *  root will be passed to destroy helper
 */
Dictionary::~Dictionary() {
    destroyHelper(root);
}

/*
 * Function:  copy constructor initializes root and member variables and passes
 * otherDict to copy other
 *
 * Precondition: Passes in a dictionary object
 *
 * Postcondition:
 * All member variables and the root will be initialized and otherDict will be passed to copyOther
 */
Dictionary::Dictionary(const Dictionary &otherDict) {
    root = new Node;
    numWords = 0;
    copyOther(otherDict);
}
/*
 * Function: Constructor initializes a root and member variables and opens a file and adds words to dictionary
 *
 * Precondition:A string that is a file name should be passed in
 *
 * Postcondition: If the precondition is met then root  and variables will be initialized and words from file
 * will add to the dictionary
 */
Dictionary::Dictionary(string filename) {
   string word;
   ifstream infile;
   infile.open(filename);

   root = new Node;
   numWords = 0;

   if(infile.fail()){
        throw DictionaryError("Invalid character");
   }

   while(infile >> word){
       AddWord(word);
   }


}

/*
 * Function: Function will overload the = to copy the dictionaries
 *
 * Precondition:A dictionary object is passed in
 *
 * Postcondition: If the precondition is met then the dictionary object will be passed to the
 * copy other function
 */
Dictionary &Dictionary::operator=(const Dictionary &otherDict) {

    if(this != &otherDict){
        copyOther(otherDict);
    }

    return *this;
}

/*
 * Function: function loads all words from a file into the dictionary
 *
 * Precondition:A string that is a file name should be passed in
 *
 * Postcondition: If the precondition is met then and the file is opened successfully then the file will be
 * loaded into the dictionary, but if the file fails to open then an error will be thrown.
 */
void Dictionary::LoadDictionaryFile(string filename) {

    string word;
    ifstream infile;
    infile.open(filename);

    if(infile.fail()){
        throw DictionaryError("Invalid character");
    }

    while(infile >> word ){
        AddWord(word);
    }
}

/*
 * Function: This function serves as wrapper function to saveDictionaryHelper
 *
 * Precondition:A string that is a file name should be passed in
 *
 * Postcondition: If the precondition is met then and teh file ifs opened successfully then the file name, root and
 * string will be passed to saveDictionaryHelper. if unsuccessful an error will be thrown.
 */
void Dictionary::SaveDictionaryFile(string filename) {

    ofstream outFile;
    outFile.open(filename);

    if(outFile.fail()){
        throw DictionaryError("Invalid character");
    }

    // should this just be root or root->alphabet[i]
    SaveDictionaryHelper(root, "", outFile);


}

/*
 * Function: This Function will add words to the dicitonary
 *
 * Precondition:A word will be passed in as a string
 *
 * Postcondition: If the precondition is met then the word will be converted to ints and added to the dictionary,
 * if unsuccessful an error wil be thrown.
 */
void Dictionary::AddWord(string word) {
    int index = 0;

    Node *currNode = root;
    // for loop goes till the length of  the word
    for(int i = 0; i < word.length(); i++){
        // subtract 'a' from the current letter in order to get the index
        index = word[i]  - 'a';

        if(word[i] < 97 || word[i] > 122){
            throw DictionaryError("Invalid character");
        }

        // check to see if the branch is already there
        // trouble right here
        if(currNode->alphabet[index] == nullptr ){
           //create a new branch for the letter index
            currNode->alphabet[index] = new Node;
        }

        //branch to the next level
        currNode = currNode->alphabet[index];
        //maybe set the next  set to null
    }
    //isword set to true in the index representing the last letter in the word
    currNode->isWord = true;
    numWords++;
}
/*
 * Function: This Function is wrapper for destroy helper that resets teh tree
 *
 * Precondition: none
 *
 * Postcondition: If the precondition is met then the root will be passed to the destroyHelper and reset and along woth numWords
 */
void Dictionary::MakeEmpty() {

    if( root == nullptr){
        return;
    }
    destroyHelper(root);
    // talk to professor about this
    // recreate the new node

    root = new Node;
    numWords = 0;


}

/*
 * Function: This Function will check to see if a word is already in the dictionary
 *
 * Precondition:A word will be passed in as a string
 *
 * Postcondition: If the precondition is met then the word passed in will be checked to see if its in the dictionary
 * if failed then an error wil be thrown.
 */
bool Dictionary::IsWord(string word) {
    int index = 0;

    Node *currNode = root;
    // for loop goes till the length of  the word
    for(int i = 0; i < word.length(); i++){
        // subtract 'a' from the current letter in order to get the index
        index = word[i]  - 'a';
    //checks to see if it is a valid letter
        if(word[i] < 97 || word[i] > 122){
            throw DictionaryError("Invalid character");
        }

        // check to see if the branch is already there
        if(currNode-> alphabet[index] == nullptr ){
            //create a new branch for the letter index
            return false;
        }

        //branch to the next level
        currNode = currNode->alphabet[index];
    }
    return currNode-> isWord ;
}

/*
 * Function: This Function will check to see if the string passed in is a valid prefix
 *
 * Precondition:A string will be passed in
 *
 * Postcondition: If the precondition is met then the string will be checked to see if its is a
 * valid prefix or an error will be thrown if it fails.
 */
bool Dictionary::IsPrefix(string word) {
    int index = 0;
    Node *currNode = root;

    // for loop goes till the length of  the word
    for(int i = 0; i < word.length(); i++){
        // subtract 'a' from the current letter in order to get the index
        index = word[i]  - 'a';

        //will check to see if teh char from teh string is in the alphabet
        if(word[i] < 97 || word[i] > 122){
            throw DictionaryError("Invalid character");
        }

        // checks to see if letter is there
        if(currNode->alphabet[index] == nullptr ){
            //returns false if no letter is there
            return false;
        }
        //branch to the next level
        currNode = currNode->alphabet[index];
    }
    return true ;
}
/*
 * Function: This function will return the word count
 *
 * Precondition:
 *
 * Postcondition: will return the word count
 *
 */
int Dictionary::WordCount() {
    return numWords;
}

/*
 * Function: This Function will act as a wrapper function for copyHelper
 *
 * Precondition:A dictionary object will be passed in.
 *
 * Postcondition: If the precondition is met then the dictionary object will be passed to copyHelper along with
 * this root.
 */
void Dictionary::copyOther(const Dictionary &otherDict) {
   this->numWords = otherDict.numWords;
    for(int i = 0; i < NUM_CHARS; i++){

        copyHelper(this->root->alphabet[i] , otherDict.root->alphabet[i]);
    }
}
/*
 * Function: This Function will delete all the nodes in the tree
 *
 * Precondition: A node will be passed in.
 *
 * Postcondition: If the precondition is met then the tree will be deleted
 */
void Dictionary::destroyHelper(Dictionary::Node *thisTree) {

    if(thisTree == nullptr){
        return;
    }

    for(int i = 0; i < NUM_CHARS; i++){

        destroyHelper(thisTree->alphabet[i]);

    }
    delete thisTree;

}

/*
 * Function: This Function will copy all words from otherTree to thisTree
 *
 * Precondition: two Nodes will be passed in
 *
 * Postcondition: If the precondition is met then otherTree will be copied to thisTree
 */
void Dictionary::copyHelper(Dictionary::Node *&thisTree, Dictionary::Node *otherTree) {
    if(otherTree == nullptr){
        thisTree = nullptr;
        return;
    }

    thisTree = new Node;
    thisTree->isWord = otherTree->isWord;
    for(int i = 0; i < NUM_CHARS; i++){

        copyHelper(thisTree->alphabet[i], otherTree->alphabet[i]);

    }
}

/*
 * Function: This Function will find every word in the tree and save it to a file.
 *
 * Precondition: the current node, a string and ofstream file will be passed in.
 *
 * Postcondition: If the precondition is met then every word in the tree will be saved to a file.
 */
void Dictionary::SaveDictionaryHelper(Dictionary::Node *curr, string currPrefix, ofstream &outFile) {
    if(curr == nullptr){
        return;
    }

    // if the current branching route is a word
    if (curr->isWord) {
        // store translated word in the file
        outFile << currPrefix << endl;
    }

    for(int i = 0; i < NUM_CHARS; i++ ) {
        currPrefix += i + 'a';

        // concatenates the new index into the string to paa into the recursion
        SaveDictionaryHelper(curr->alphabet[i], currPrefix, outFile);
        currPrefix.pop_back();
    }
}

/*
 * Function: This constructor will set node and member variables  to starting values
 *
 * Precondition:
 *
 * Postcondition: all member variables and nodes will be set
 *
 */
Dictionary::Node::Node() {
    for(int i= 0; i < NUM_CHARS; i++){
        alphabet[i] = nullptr;
    }
    isWord = false;
}
