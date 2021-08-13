package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import bll.Reisetag;
import gui.MainFrame;

public class Start {

	public static void main(String[] args) {
		ArrayList<Reisetag> r = new ArrayList<Reisetag>();
		SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy");
		try {
			r.add(new Reisetag(dt.parse("02.07.2018"), "Florenz", 135.0D, ""));
			r.add(new Reisetag(dt.parse("03.07.2018"), "Florenz/Pisa", 135.0D, ""));
			r.add(new Reisetag(dt.parse("04.07.2018"), "Siena", 135.0D, ""));
			r.add(new Reisetag(dt.parse("05.07.2018"), "Siena", 135.0D, ""));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		new MainFrame(r);
	}

}
