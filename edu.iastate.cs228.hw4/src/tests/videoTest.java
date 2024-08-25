package tests;

import edu.iastate.cs228.hw4.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.FileNotFoundException;

import org.junit.*;
import org.junit.jupiter.api.Test;

public class videoTest {

	public static void main(String[] args) throws FileNotFoundException, IllegalArgumentException,
			FilmNotInInventoryException, AllCopiesRentedOutException {

		VideoStore store = new VideoStore("videoList1.txt");

		System.out.println(store.findVideo("The Godfather"));

		store.videoRent("The Godfather", 1);
		store.bulkRent("videoList2.txt");
		store.videoRent("Brokeback Mountain", 1);
		store.videoReturn("Slumdog Millionaire", 2);

		String film = "Silence of the Lambs";

		try {
			store.videoRent(film, 1);
		} catch (IllegalArgumentException e) {
			System.out.println("Film " + film + " has an invalid request\n");
		} catch (FilmNotInInventoryException f) {
			System.out.println("Film " + film + " is not in inventory\n");
		} catch (AllCopiesRentedOutException g) {
			System.out.println("Film " + film + " has been rented out\n");
		}
		
		store.videoRent("Singin' in the Rain", 2);
		store.bulkReturn("videoList3.txt");

		System.out.println(store.transactionsSummary());

	}

}
