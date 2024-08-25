package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *  
 * @author Ezra Odole
 *
 */

/**
 * 
 * The Transactions class simulates video transactions at a video store.
 *
 */
public class Transactions {

	/**
	 * The main method generates a simulation of rental and return activities.
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		// 1. Construct a VideoStore object.
		VideoStore store = new VideoStore("videoList1.txt");
		// 2. Simulate transactions as in the example given in Section 4 of the
		// the project description.
		Scanner scan = new Scanner(System.in);

		System.out.println("Transactions at a Video Store\n" + "keys: 1 (rent)     2 (bulk rent)\n"
				+ "      3 (return)   4 (bulk return)\n" + "      5 (summary)  6 (exit)\n");
		System.out.print("Transaction: ");

		int key = scan.nextInt();

		while (key == 1 || key == 2 || key == 3 || key == 4 || key == 5) 
		{
			String filename;

			if (key == 1)// rent video from line input
			{
				System.out.print("Film to rent: ");
				scan.nextLine();
				String line = scan.nextLine();

				String film = VideoStore.parseFilmName(line);

				try {
					store.videoRent(film, VideoStore.parseNumCopies(line));
				} catch (IllegalArgumentException e) {
					System.out.println("Film " + film + " has an invalid request");

				} catch (FilmNotInInventoryException f) {

					System.out.println("Film " + film + " is not in inventory");
				} catch (AllCopiesRentedOutException g) {
					System.out.println("Film " + film + " has been rented out");
				}

				System.out.println();
			}

			else if (key == 2)// bulk rent from file
			{
				System.out.print("Video file (rent): ");
				filename = scan.next();
				try {
					store.bulkRent(filename);
				} catch (FileNotFoundException | IllegalArgumentException | FilmNotInInventoryException
						| AllCopiesRentedOutException e) {
				}

				System.out.println();
			}

			else if (key == 3)// return video from line input
			{
				System.out.print("Film to return: ");
				scan.nextLine();
				String line = scan.nextLine();

				String film = VideoStore.parseFilmName(line);

				try {
					store.videoReturn(film, VideoStore.parseNumCopies(line));
				} catch (IllegalArgumentException e) {
					System.out.println("Film " + film + " has an invalid request");

				} catch (FilmNotInInventoryException f) {

					System.out.println("Film " + film + " is not in inventory");
				}

				System.out.println();
			}

			else if (key == 4)// bulk return from file input
			{
				System.out.print("Video file (return): ");
				filename = scan.next();
				try {
					store.bulkReturn(filename);
				} catch (FileNotFoundException | IllegalArgumentException | FilmNotInInventoryException e) {
				}

				System.out.println();
			}

			else if (key == 5)// print summary
			{
				System.out.println(store.transactionsSummary());
			}

			System.out.print("Transaction: ");
			key = scan.nextInt();
		}

		scan.close();

	}
}
