Word Class:
	Word constructor that consists of term and definition Strings. Can be created empty, with only the term, and also term and def.
	compareTo(Word x) method is just to shorten typing so I can avoid having to type .term and .getTerm every time. 
	The getter methods are straight forward.
	setTerm(String x) and setDefinition(String x) are created so that every string entered into it will have the first letter capitalized and everything else lowercase so everything is the same.
	setTerm(String x) will also throw IllegalArgumentException if the term contains a space or is empty.
	toString() returns the term and definition seperated by a space.

ArrayBasedSortedList Class:
	Constructor that contains an array of Words of MAXSIZE and a counter.
	size() returns number of terms.
	get(int x) will return whatever Word object is in the index given and will throw IndexOutOfBoundsException if input is incorrect.
	expand() will double the previous MAXSIZE, make an array of new MAXSIZE, and copy all terms of previous array into the new one. Used only when adding a Word at the end of the 	array and in checkAndExpand().
	checkAndExpand() is used when number of terms is < MAXSIZE and > 0. Will check if number of terms == terms.length and will expand if true.
	insert(Word x, Scanner s) will insert the Word alphabetically in the array. 
		When adding somewhere among the middle of the array, it will call on the checkAndExpand() method, and expand() if necessary. 
		It will also shift the remaining objects using my shift() method to the right and increase number of terms.
	remove(Scanner s) will compare the scanner input to the terms in each index. If no terms or no match, will return false. If found, will call shiftLeft() method and delete the 	last object in the array and return true. Number of terms -1.
	findMeaning(scanner s) takes a string, compares it to every term in the array, and if true, will return the definition of the Word at index i, else will return "Word not found."
	shift(int start) creates a for loop starting at total number of terms, and will loop until it reaches start (The index where Word x is no longer greater than index i). Every iteration will make terms[i] equal terms[i-1].
	shiftLeft(int start) is same as shift but opposite. Start at start until i < number of terms and increase. terms[i] will equal terms[i+1] for every iteration.
	toString() makes use of the toString made in the Word class, and creates a string of the sum of toStrings of Words seperated by new lines and then returned out.

Dictionary Class:
	Creates an ArrayBasedSortedList object named wordList.
	save(Scanner s) will create a file off of user input, print wordList.toString() to the file, and close. Will throw IOException if necessary.
	load(Scanner s) Will load a file named of use input. While loop will continue while the file has a next token. A Word will be created setting the term to the next token, and definition to the rest of the line. It will then insert
		the word into the array. Will throw FileNotFoundException if necessary.
	save(Scanner s) and load(Scanner s) will both check if user input ends in ".txt" and will add it if not.
	run() displays the options to the user, takes user input, and will go through a switch statement. The user input is non case sensitive for ease of use. Each case will run the 	assigned method, and will use recursion of the run() method
		to continue program, preserving what has already been done. If incorrect input, will display "incorrect option", and run() again.

Test Class:
	Very minimal.
	Will create a dictionary object, and call on the run() method.