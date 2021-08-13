package app;

import java.util.ArrayList;

import bll.Fuﬂballer;
import bll.Fuﬂballer.VEREINE;
import gui.Mainframe;
import util.DateHelper;

public class Start {

	public static void main(String args[]) {
		DateHelper dh = DateHelper.getInstance();
		ArrayList<Fuﬂballer> f = new ArrayList<Fuﬂballer>();
		f.add(new Fuﬂballer("Richard", "Strebinger", VEREINE.RAPID_WIEN, false, dh.dateformatParse("1.1.2001")));
		f.add(new Fuﬂballer("Steffen", "Hofmann", VEREINE.RAPID_WIEN, true, dh.dateformatParse("1.1.2001")));
		f.add(new Fuﬂballer("Alexander", "Kofler", VEREINE.WAC, false, dh.dateformatParse("1.1.2001")));
		f.add(new Fuﬂballer("Michael", "Sollbauer", VEREINE.WAC, true, dh.dateformatParse("1.1.2001")));
		f.add(new Fuﬂballer("Daniel", "Offenbacher", VEREINE.WAC, true, dh.dateformatParse("1.1.2001")));
		f.add(new Fuﬂballer("Louis", "Schaub", VEREINE.RAPID_WIEN, false, dh.dateformatParse("1.1.2001")));
		new Mainframe(f);
	}

}
