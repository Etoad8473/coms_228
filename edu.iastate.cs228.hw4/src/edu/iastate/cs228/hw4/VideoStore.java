package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 
 * @author Ezra Odole
 *
 */

public class VideoStore {
	protected SplayTree<Video> inventory; // all the videos at the store

	// ------------
	// Constructors
	// ------------

	/**
	 * Default constructor sets inventory to an empty tree.
	 */
	public VideoStore() {
		// no need to implement.
	}

	/**
	 * Constructor accepts a video file to create its inventory. Refer to Section
	 * 3.2 of the project description for details regarding the format of a video
	 * file.
	 * 
	 * Calls setUpInventory().
	 * 
	 * @param videoFile no format checking on the file
	 * @throws FileNotFoundException
	 */
	public VideoStore(String videoFile) throws FileNotFoundException {
		setUpInventory(videoFile);
	}

	/**
	 * Accepts a video file to initialize the splay tree inventory. To be efficient,
	 * add videos to the inventory by calling the addBST() method, which does not
	 * splay.
	 * 
	 * Refer to Section 3.2 for the format of video file.
	 * 
	 * @param videoFile correctly formated if exists
	 * @throws FileNotFoundException
	 */
	public void setUpInventory(String videoFile) throws FileNotFoundException {
		inventory = new SplayTree<Video>();

		ArrayList<Video> videos = videoFileToList(videoFile);

		for (Video i : videos) {
			inventory.addBST(i);
		}

	}

	/**
	 * Turns txt file containing movies into an arrayList of video objects
	 * 
	 * @param videoFile
	 * @return arrayList of video objects
	 * @throws FileNotFoundException
	 */
	private ArrayList<Video> videoFileToList(String videoFile) throws FileNotFoundException {
		ArrayList<Video> list = new ArrayList<Video>();

		File f = new File(videoFile);
		Scanner scan = new Scanner(f);
		while (scan.hasNextLine()) {

			String filmLine = scan.nextLine();

			list.add(new Video(parseFilmName(filmLine), parseNumCopies(filmLine)));
		}
		scan.close();

		return list;
	}

	// ------------------
	// Inventory Addition
	// ------------------

	/**
	 * Find a Video object by film title.
	 * 
	 * @param film
	 * @return
	 */
	public Video findVideo(String film) {
		Video comp = new Video(film);
		return inventory.findElement(comp);
	}

	/**
	 * Updates the splay tree inventory by adding a number of video copies of the
	 * film. (Splaying is justified as new videos are more likely to be rented.)
	 * 
	 * Calls the add() method of SplayTree to add the video object.
	 * 
	 * a) If true is returned, the film was not on the inventory before, and has
	 * been added. b) If false is returned, the film is already on the inventory.
	 * 
	 * The root of the splay tree must store the corresponding Video object for the
	 * film. Update the number of copies for the film.
	 * 
	 * @param film title of the film
	 * @param n    number of video copies
	 */
	public void addVideo(String film, int n) {
		boolean added = inventory.add(new Video(film, n));

		if (!added)// film already exists and was splayed to root
			inventory.root.data.addNumCopies(n);// add n copies to root node
	}

	/**
	 * Add one video copy of the film.
	 * 
	 * @param film title of the film
	 */
	public void addVideo(String film) {
		findVideo(film).addNumCopies(1);
	}

	/**
	 * Update the splay trees inventory by adding videos. Perform binary search
	 * additions by calling addBST() without splaying.
	 * 
	 * The videoFile format is given in Section 3.2 of the project description.
	 * 
	 * @param videoFile correctly formated if exists
	 * @throws FileNotFoundException
	 */
	public void bulkImport(String videoFile) throws FileNotFoundException {
		ArrayList<Video> videos = videoFileToList(videoFile);

		for (Video i : videos) {
			inventory.addBST(i);
		}
	}

	// ----------------------------
	// Video Query, Rental & Return
	// ----------------------------

	/**
	 * Search the splay tree inventory to determine if a video is available.
	 * 
	 * @param film
	 * @return true if available
	 */
	public boolean available(String film) {
		Video v = findVideo(film);

		if (v == null || v.getNumAvailableCopies() <= 0)
			return false;
		else // not null and availableCopies > 0
			return true;
	}

	/**
	 * Update inventory.
	 * 
	 * Search if the film is in inventory by calling findElement(new Video(film,
	 * 1)).
	 * 
	 * If the film is not in inventory, prints the message "Film <film> is not in
	 * inventory", where <film> shall be replaced with the string that is the value
	 * of the parameter film. If the film is in inventory with no copy left, prints
	 * the message "Film <film> has been rented out".
	 * 
	 * If there is at least one available copy but n is greater than the number of
	 * such copies, rent all available copies. In this case, no
	 * AllCopiesRentedOutException is thrown.
	 * 
	 * @param film
	 * @param n
	 * @throws IllegalArgumentException    if n <= 0 or film == null or
	 *                                     film.isEmpty()
	 * @throws FilmNotInInventoryException if film is not in the inventory
	 * @throws AllCopiesRentedOutException if there is zero available copy for the
	 *                                     film.
	 */
	public void videoRent(String film, int n)
			throws IllegalArgumentException, FilmNotInInventoryException, AllCopiesRentedOutException {
		if (n <= 0)
			throw new IllegalArgumentException();

		else if (findVideo(film) == null)
			throw new FilmNotInInventoryException();

		else if (!available(film))
			throw new AllCopiesRentedOutException();

		Video v = findVideo(film);
		v.rentCopies(n);

	}

	/**
	 * Update inventory.
	 * 
	 * 1. Calls videoRent() repeatedly for every video listed in the file. 2. For
	 * each requested video, do the following: a) If it is not in inventory or is
	 * rented out, an exception will be thrown from videoRent(). Based on the
	 * exception, prints out the following message: "Film <film> is not in
	 * inventory" or "Film <film> has been rented out." In the message, <film> shall
	 * be replaced with the name of the video. b) Otherwise, update the video record
	 * in the inventory.
	 * 
	 * For details on handling of multiple exceptions and message printing, please
	 * read Section 3.4 of the project description.
	 * 
	 * @param videoFile correctly formatted if exists
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException    if the number of copies of any film is <=
	 *                                     0
	 * @throws FilmNotInInventoryException if any film from the videoFile is not in
	 *                                     the inventory
	 * @throws AllCopiesRentedOutException if there is zero available copy for some
	 *                                     film in videoFile
	 */
	public void bulkRent(String videoFile) throws FileNotFoundException, IllegalArgumentException,
			FilmNotInInventoryException, AllCopiesRentedOutException {
		ArrayList<Video> videos = videoFileToList(videoFile);
		String errorMsg = "";

		for (Video i : videos) {
			String film = i.getFilm();

			try {
				videoRent(film, i.getNumCopies());
			} catch (IllegalArgumentException e) {
				errorMsg += "Film " + film + " has an invalid request\n";

			} catch (FilmNotInInventoryException f) {

				errorMsg += "Film " + film + " is not in inventory\n";
			} catch (AllCopiesRentedOutException g) {
				errorMsg += "Film " + film + " has been rented out\n";
			}
		}

		System.out.print(errorMsg);

	}

	/**
	 * Update inventory.
	 * 
	 * If n exceeds the number of rented video copies, accepts up to that number of
	 * rented copies while ignoring the extra copies.
	 * 
	 * @param film
	 * @param n
	 * @throws IllegalArgumentException    if n <= 0 or film == null or
	 *                                     film.isEmpty()
	 * @throws FilmNotInInventoryException if film is not in the inventory
	 */
	public void videoReturn(String film, int n) throws IllegalArgumentException, FilmNotInInventoryException {
		if (n <= 0)
			throw new IllegalArgumentException();

		else if (findVideo(film) == null)
			throw new FilmNotInInventoryException();

		Video v = findVideo(film);
		v.returnCopies(n);
	}

	/**
	 * Update inventory.
	 * 
	 * Handles excessive returned copies of a film in the same way as videoReturn()
	 * does. See Section 3.4 of the project description on how to handle multiple
	 * exceptions.
	 * 
	 * @param videoFile
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException    if the number of return copies of any
	 *                                     film is <= 0
	 * @throws FilmNotInInventoryException if a film from videoFile is not in
	 *                                     inventory
	 */
	public void bulkReturn(String videoFile)
			throws FileNotFoundException, IllegalArgumentException, FilmNotInInventoryException {
		ArrayList<Video> videos = videoFileToList(videoFile);
		String errorMsg = "";

		for (Video i : videos) {
			String film = i.getFilm();

			try {
				videoReturn(film, i.getNumCopies());
			} catch (IllegalArgumentException e) {
				errorMsg += "Film " + film + " has an invalid request";

			} catch (FilmNotInInventoryException f) {

				errorMsg += "Film " + film + " is not in inventory";
			}
		}

		System.out.print(errorMsg);

	}

	// ------------------------
	// Methods without Splaying
	// ------------------------

	/**
	 * Performs inorder traversal on the splay tree inventory to list all the videos
	 * by film title, whether rented or not. Below is a sample string if printed
	 * out:
	 * 
	 * 
	 * Films in inventory:
	 * 
	 * A Streetcar Named Desire (1) Brokeback Mountain (1) Forrest Gump (1) Psycho
	 * (1) Singin' in the Rain (2) Slumdog Millionaire (5) Taxi Driver (1) The
	 * Godfather (1)
	 * 
	 * 
	 * @return
	 */
	public String inventoryList() {
		Iterator<Video> iter = inventory.iterator();
		String out = "Films in inventory:\n\n";

		while (iter.hasNext()) {
			Video v = iter.next();
			out += v.getFilm() + " (" + v.getNumCopies() + ")\n";
		}
		return out;
	}

	/**
	 * Calls rentedVideosList() and unrentedVideosList() sequentially. For the
	 * string format, see Transaction 5 in the sample simulation in Section 4 of the
	 * project description.
	 * 
	 * @return
	 */
	public String transactionsSummary() {
		String out = "";

		out += rentedVideosList() + "\n";
		out += unrentedVideosList();

		return out;
	}

	/**
	 * Performs inorder traversal on the splay tree inventory. Use a splay tree
	 * iterator.
	 * 
	 * Below is a sample return string when printed out:
	 * 
	 * Rented films:
	 * 
	 * Brokeback Mountain (1) Forrest Gump (1) Singin' in the Rain (2) The Godfather
	 * (1)
	 * 
	 * @return
	 */
	public String rentedVideosList() {
		Iterator<Video> iter = inventory.iterator();
		String out = "Rented films:\n\n";

		while (iter.hasNext()) {
			Video v = iter.next();
			if (v.getNumRentedCopies() > 0)
				out += v.getFilm() + " (" + v.getNumRentedCopies() + ")\n";
		}
		return out;
	}

	/**
	 * Performs inorder traversal on the splay tree inventory. Use a splay tree
	 * iterator. Prints only the films that have unrented copies.
	 * 
	 * Below is a sample return string when printed out:
	 * 
	 * 
	 * Films remaining in inventory:
	 * 
	 * A Streetcar Named Desire (1) Forrest Gump (1) Psycho (1) Slumdog Millionaire
	 * (4) Taxi Driver (1)
	 * 
	 * 
	 * @return
	 */
	public String unrentedVideosList() {
		Iterator<Video> iter = inventory.iterator();
		String out = "Films remaining in inventory:\n\n";

		while (iter.hasNext()) {
			Video v = iter.next();
			if (v.getNumAvailableCopies() > 0)
				out += v.getFilm() + " (" + v.getNumAvailableCopies() + ")\n";
		}
		return out;
	}

	/**
	 * Parse the film name from an input line.
	 * 
	 * @param line
	 * @return film name
	 */
	public static String parseFilmName(String line) {
		String out;

		int frontBracketIndex = line.indexOf("(");

		if (frontBracketIndex != -1)
			out = line.substring(0, frontBracketIndex - 1);
		else
			out = line;

		return out;
	}

	/**
	 * Parse the number of copies from an input line.
	 * 
	 * @param line
	 * @return num copies
	 */
	public static int parseNumCopies(String line) {
		int out = 1;

		int frontBracketIndex = line.indexOf("(");

		if (frontBracketIndex != -1)
			out = Integer.parseInt(line.substring(frontBracketIndex + 1, line.indexOf(")")));

		return out;
	}
}
