package v1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Dictionary {

	ArrayBasedSortedList wordList = new ArrayBasedSortedList();

	public void save(Scanner s) throws IOException {
		System.out.println("Please enter the name of the file you'd like to save to: ");
		try {
			String name = s.nextLine();
			if (!name.endsWith(".txt"))
				name += ".txt";
			PrintWriter writer = new PrintWriter(name);
			writer.println(wordList);
			writer.close();
		} catch (IOException e) {
			throw new IOException();
		}
	}

	public void load(Scanner s) throws FileNotFoundException {
		System.out.println("Please enter the name of the file you'd like load: ");
		try {
			String name = s.nextLine();
			if (!name.endsWith(".txt"))
					name += ".txt";
			Scanner load = new Scanner(new FileReader(name));
			while (load.hasNext()) {
				Word x = new Word();
				x.setTerm(load.next());
				x.setDefinition(load.nextLine().substring(1));
				wordList.insert(x, s);
			}
			load.close();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		}
	}

	public void run() throws FileNotFoundException, IOException {
		System.out.println("Possible Operations: \n" + // Providing interface
				"f) find the meaning of a word. \n" + "i) insert an entry (a new pair of word, meaning). \n"
				+ "l) load the dictionary from an input file. \n" + "p) Print the whole dictionary \n"
				+ "r) remove an entry. \n" + "s) save the contents of the dictionary in an output file. \n"
				+ "x) exit \n" + "Please choose an option (f, i, l, r, s, or x): ");
		Scanner s = new Scanner(System.in); // Get user input to put into switch
		String answer = s.nextLine();
		switch (answer.toLowerCase()) { // Will take user input and will report error if incorrect
		case "f":
			System.out.println(wordList.findMeaning(s) + "\n");
			run();
			break;
		case "i":
			Word x = new Word(); // Word to be added to array
			System.out.println("Please enter a word: ");
			x.setTerm(s.nextLine());
			System.out.println("Please enter a definition: ");
			x.setDefinition(s.nextLine());
			wordList.insert(x, s);
			run();
			break;
		case "l":
			load(s);
			run();
			break;
		// Print all terms + defs in array
		case "p":
			System.out.println(wordList);
			run();
			break;
		case "r":
			System.out.println(wordList.remove(s) + "\n");
			run();
			break;
		case "s":
			save(s);
			run();
			break;
		case "x":
			break;
		default: {
			System.out.println("Incorrect Option. Please try again");
			run();
			break;
		}
		}
		s.close();
	}
}