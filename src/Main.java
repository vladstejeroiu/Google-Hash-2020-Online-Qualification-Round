/**
 * Main.java
 * 
 * @author Alexandru Mihalache, Vlad Stejeroiu
 * @author Victor Ciobanu, Andrei Predi
 * @version 1.0 - no copyright.
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// An array with all the files names
		String[] inputs = { "a_example", "b_read_on", "c_incunabula", "d_tough_choices", "e_so_many_books",
				"f_libraries_of_the_world" };

		for (String in : inputs) {
			World world = new World();
			world.parse(in + ".txt");
			world.simulate();
			world.print(in + ".out");
		}

	}

}
