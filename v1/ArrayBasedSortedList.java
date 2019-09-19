package v1;

import java.util.Scanner;

public class ArrayBasedSortedList {
	private static int MAXSIZE = 5; // Initial starting size of array
	private int numTerms;
	private Word[] terms;

	// Creating a basic constructor
	public ArrayBasedSortedList() {
		terms = new Word[MAXSIZE];
		numTerms = 0;
	}

	// Returns total amount of Terms in Array
	public int size() {
		return numTerms;
	}

	// Intakes index number and returns the Term and definition associated with it
	public String get(int x) throws IndexOutOfBoundsException {
		if (x >= numTerms)
			throw new IndexOutOfBoundsException();
		return terms[x].toString();
	}

	public void expand() { // Creates new array with double size, copies contents into new one
		MAXSIZE *= 2;
		Word newArray[] = new Word[MAXSIZE];
		for (int i = 0; i < numTerms; i++)
			newArray[i] = terms[i];
		this.terms = newArray;
	}

	// To use instead of manually checking every time an entry is added
	private void checkAndExpand() {
		if (numTerms == terms.length)
			expand();
	}

	// Insert function for load method
	public void insert(Word x, Scanner s) {
		String answer = "";
		int i = 0;

		// Allows to initialize the Array when numTerms == 0
		if (numTerms == 0) {
			terms[0] = x;
			numTerms++;
			return;
		}

		// If numTerms != 0
		while (i < numTerms && x.compareTo(terms[i]) > 0) {
			i++;
		}
		if (i == numTerms) {
			expand();
			terms[i] = x;
			numTerms++;
			return;
		}

		if (x.compareTo(terms[i]) == 0) { // Equals the term
			System.out.println("This term already exists, would you like to update the definition? (y/n) \n");
			// Makes user enter y or n
			while (!answer.equalsIgnoreCase("y") || !answer.equalsIgnoreCase("n")) {
				answer = s.nextLine();
			}
			if (answer.equalsIgnoreCase("y")) {
				terms[i] = x;
			}
			return;
		}
		// Normal case
		checkAndExpand();
		shift(i);
		terms[i] = x;
		numTerms++;
	}

	// Function to remove word s from array and shift left if necessary
	public boolean remove(Scanner s) {

		// Empty array
		if (numTerms == 0) {
			System.out.println("No terms to delete.");
			return false;
		}
		System.out.println("Please enter the term that you would like to remove");
		Word x = new Word();
		x.setTerm(s.nextLine());
		int i = 0;

		// While word is greater than ith array object and not at end of array
		while (i < numTerms && x.compareTo(terms[i]) > 0) {
			i++;
		}

		// At end of array
		if (i == numTerms) {
			if (x.compareTo(terms[i - 1]) == 0) {
				terms[i - 1] = null;
				numTerms--;
				return true;
			}
			System.out.println("Cannot remove term");
			return false;
		}
		// Rest of cases
		if (x.compareTo(terms[i]) == 0) {
			shiftLeft(i);
			terms[numTerms - 1] = null;
			numTerms--;
			return true;
		}
		return false;
	}

	// To locate a specific word and print definition
	public String findMeaning(Scanner s) {
		System.out.println("Please enter the word you would like to find the meaning to: ");
		Word x = new Word();
		x.setTerm(s.nextLine());
		for (int i = 0; i < numTerms; i++) {
			if (x.compareTo(terms[i]) == 0) {
				return terms[i].getDefinition();
			}
		}
		return "Word not found.";
	}

	// To use inside insert function. Will shift everything after start one index to
	// the right
	private void shift(int start) {
		for (int i = numTerms; i > start; i--) {
			terms[i] = terms[i - 1];
		}
	}

	// To use inside remove function. Will shift everything to the left after start.
	private void shiftLeft(int start) {
		for (int i = start; i < numTerms - 1; i++) {
			terms[i] = terms[i + 1];
		}

	}

	// Returns all Terms and Definitions in the Array
	public String toString() {
		String toString = "";
		for (int i = 0; i < this.numTerms; i++) {
			toString += terms[i] + "\n";
		}
		return toString;
	}
}
