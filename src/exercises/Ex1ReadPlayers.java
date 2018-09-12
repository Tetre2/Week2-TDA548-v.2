package exercises;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/*
 *  Utilities to input/output player data for a command line game
 *
 *  See:
 *  - UseAConstructor
 *  - ObjectArrMeth
 *
 */
public class Ex1ReadPlayers {
	public static void main(String[] args) {
		new Ex1ReadPlayers().program();
	}

	final Scanner sc = new Scanner(in);

	void program() {
		Player[] players = inputPlayers();
		outPlayers(players);
	}

	// This class captures the concept of a Player
	class Player {
		String name;   // A Player has a name and...
		int points = 0;    // ... and points
		
		Player(String name){
			this.name = name;
		}

	}

	// ---------- Methods -------------------

	Player[] inputPlayers() {

		System.out.println("How many players? >");
		int nPlayers = sc.nextInt();
		sc.nextLine();
		Player[] players = new Player[nPlayers];
		
		for (int i = 0; i < players.length; i++) {
			System.out.println("Name for player " + (i + 1) + " >");
			players[i] = new Player(sc.nextLine());
		}
		
		return players;
	}
	

	void outPlayers(Player[] players){
		
		System.out.println("Players are:");
		for (int i = 0; i < players.length; i++) {
			System.out.println("Name " + players[i].name + " points " + players[i].points + " 0");
		}
		
	}


}