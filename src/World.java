import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * World.java
 * 
 * @author Alexandru Mihalache, Vlad Stejeroiu
 * @author Victor Ciobanu, Andrei Predi
 * @version 1.0 - no copyright.
 *
 */
public class World {

	/**
	 * Comparator to perform an anscending order of libraries 
	 * by the sign up process and descending order 
	 * by the number of books they can ship/day. 
	 * This sort getsthe best scores on a), b), c), e).
	 *
	 */
	class SortbySignUP implements Comparator<Library> {

		public int compare(Library a, Library b) {
			if (a.signUp - b.signUp > 0) {
				return 1;
			} else if (a.signUp - b.signUp < 0) {
				return -1;
			} else {
				return b.nrShips - a.nrShips;
			}

		}
	}

	/**
	 * Comparator to perform a descending order 
	 * of libraries by how many books they have.
	 * This sort gets the best score on d).
	 *
	 */
	class SortbyBooksSize implements Comparator<Library> {
		public int compare(Library a, Library b) {
			return b.books.size() - a.books.size();
		}
	}

	/**
	 * Comparator to perform an ascending order of libraries 
	 * by how many books they can ship/day and 
	 * ascending order by how long the sign up process is.
	 * This sort gets the best score on f).
	 *
	 */
	class SortbyShips implements Comparator<Library> {

		public int compare(Library a, Library b) {
			if (a.nrShips - b.nrShips > 0) {
				return 1;
			} else if (a.nrShips - b.nrShips < 0) {
				return -1;
			} else {
				return a.signUp - b.signUp;
			}

		}
	}

	int days;
	int nrBooks;
	int nrLibraries;
	static int[] books = new int[100000];
	ArrayList<Library> libraries = new ArrayList<Library>();

	/**
	 * Parsing all the information from the file to the attributes.
	 * 
	 * @param filename the file to be read.
	 */
	void parse(String filename) {

		int bufferSize = 8 * 1024;

		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(filename), bufferSize);
			String line = bufferedReader.readLine();
			String[] l = line.split(" ");
			nrBooks = intValue(l[0]);
			nrLibraries = intValue(l[1]);
			days = intValue(l[2]);

			String line1 = bufferedReader.readLine();
			String[] l1 = line1.split(" ");

			for (int i = 0; i < nrBooks; i++) {
				books[i] = intValue(l1[i]);
			}

			for (int i = 0; i < nrLibraries; i++) {
				String line2 = bufferedReader.readLine();
				String[] l2 = line2.split(" ");
				int nrB = intValue(l2[0]);
				int signUp = intValue(l2[1]);
				int nrShips = intValue(l2[2]);
				ArrayList<Integer> booksIndices = new ArrayList<Integer>();

				String line3 = bufferedReader.readLine();
				String[] l3 = line3.split(" ");

				for (int j = 0; j < nrB; j++) {

					booksIndices.add(intValue(l3[j]));
				}

				Library lib = new Library(booksIndices, books, signUp, nrShips, i);
				libraries.add(lib);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void simulate() {

		Collections.sort(libraries, new SortbyShips());
	}

	/**
	 * Printing the output to the file.
	 * 
	 * @param filename the file to be write on.
	 */
	void print(String filename) {

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(filename, "UTF-8");

			writer.println(libraries.size());

			for (Library library : libraries) {
				if (library.books.size() != 0) {
					writer.println(library.ID + " " + library.books.size());
					for (int book : library.books) {
						writer.print(book + " ");
					}
					writer.println();
				}
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Converting a string to an Int.
	 * 
	 * @param s string to be converted.
	 * @return the int value of the string.
	 */
	public static int intValue(String s) {
		return Integer.parseInt(s);
	}

}
