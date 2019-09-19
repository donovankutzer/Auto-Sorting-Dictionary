package v1;

public class Word {
	private String term = "";
	private String definition = "";

	public Word() {
		term = "";
		definition = "";
	}

	public Word(String x) {
		term = x;
		definition = "";
	}

	public Word(String x, String y) {
		term = x;
		definition = y;
	}

	// Create less typing for Array class when comparing
	public int compareTo(Word x) {
		return this.term.compareTo(x.getTerm());
	}

	public String getTerm() {
		return term;
	}

	public String getDefinition() {
		return definition;
	}

	public void setTerm(String x) throws IllegalArgumentException {
		// Ensures term contain no space and is non empty
		if (x.contains(" ") || x.equals(""))
			throw new IllegalArgumentException();
		// Normalize Terms
		String norm = x.substring(0, 1).toUpperCase() + x.substring(1).toLowerCase();
		term = norm;
	}

	public void setDefinition(String x) {
		// Normalize Definitions
		String norm = x.substring(0, 1).toUpperCase() + x.substring(1).toLowerCase();
		definition = norm;
	}

	public String toString() {
		return (term + " " + definition);
	}
}