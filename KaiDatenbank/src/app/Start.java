package app;

import java.util.ArrayList;

import bll.User;
import dal.DatenbankConnector;

public class Start {

	public static void main(String[] args) {

		User user1 = new User(1, "Hans", 55);
		ArrayList<User> sendingUsers = new ArrayList<User>();
		sendingUsers.add(user1);
		
		DatenbankConnector connector = DatenbankConnector.getInstanz();
		connector.sendDaten(sendingUsers);

		ArrayList<User> users = connector.readDaten();
		System.out.println("Name des ersten users: " + users.get(0).getName());
	}

}
