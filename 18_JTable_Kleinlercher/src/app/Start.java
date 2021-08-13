package app;

import java.util.ArrayList;

import bll.Fu�baller;
import bll.Fu�baller.VEREINE;
import gui.Mainframe;
import util.DateHelper;

public class Start {

	public static void main(String args[]) {
		DateHelper dh = DateHelper.getInstance();
		ArrayList<Fu�baller> f = new ArrayList<Fu�baller>();
		f.add(new Fu�baller("Richard", "Strebinger", VEREINE.RAPID_WIEN, false, dh.dateformatParse("1.1.2001")));
		f.add(new Fu�baller("Steffen", "Hofmann", VEREINE.RAPID_WIEN, true, dh.dateformatParse("1.1.2001")));
		f.add(new Fu�baller("Alexander", "Kofler", VEREINE.WAC, false, dh.dateformatParse("1.1.2001")));
		f.add(new Fu�baller("Michael", "Sollbauer", VEREINE.WAC, true, dh.dateformatParse("1.1.2001")));
		f.add(new Fu�baller("Daniel", "Offenbacher", VEREINE.WAC, true, dh.dateformatParse("1.1.2001")));
		f.add(new Fu�baller("Louis", "Schaub", VEREINE.RAPID_WIEN, false, dh.dateformatParse("1.1.2001")));
		new Mainframe(f);
	}

}
