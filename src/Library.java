import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Library.java
 * 
 * @author Alexandru Mihalache, Vlad Stejeroiu
 * @author Victor Ciobanu, Andrei Predi
 * @version 2.0 - no copyright.
 * Modified by @author Alexandru Mihalache
 */
public class Library {

	ArrayList<Integer> books = new ArrayList<Integer>();
	static int[] scores;
	int signUp;
	int nrShips;
	int ID;
	ArrayList<Integer> chosenBooks;
	int daysLeft;

	/**
	 * Comparator to perform a descending order of books by their scores.
	 */
	class SortbyBookScore implements Comparator<Integer> {
		public int compare(Integer a, Integer b) {
			return World.books[b] - World.books[a];
		}
	}

	/**
	 * Creates a Library.
	 * 
	 * @param books   indices of the book which are in the library.
	 * @param scores  each score of each book.
	 * @param signUp  how long the sign up process it is.
	 * @param nrShips how many books the library can ship/day.
	 * @param ID      the library ID.
	 */
	public Library(ArrayList<Integer> books, int[] scores, int signUp, int nrShips, int ID) {
		this.books = books;
		Library.scores = scores;
		this.signUp = signUp;
		this.nrShips = nrShips;
		this.ID = ID;
		Collections.sort(books, new SortbyBookScore());
		this.chosenBooks = new ArrayList<Integer>();
		this.daysLeft = OptimizedWorld.days;
	}
	
	/**
	 * The maximum score the library l can get.
	 * @param l the library for which we calculate the score.
	 * @return the score.
	 */
	static public int getScore(Library l) {
		int score = 0;

		for (Integer i : l.books) {
			score += scores[i];
		}
		return score;
	}
	
	/**
	 * The maximum score the library l can get
	 * in the time remaining for sign ups. 
	 * @param l the library for which we calculate the score.
	 * @return the score.
	 */
	static public int getScore1(Library l) {

		int numBooksScanned = (l.signUp < l.daysLeft) ? (l.daysLeft - l.signUp) * l.nrShips : 0;
		int score = 0;

		for (int i = 0; i < numBooksScanned && i < l.books.size(); i++) {
			score += scores[l.books.get(i)];
		}

		return score / l.signUp;
	}
}
