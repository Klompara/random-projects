package app;

import java.util.ArrayList;

import bll.Menu;
import bll.Menu.WOCHENTAG;
import gui.Mainframe;

public class Start {

	public static void main(String[] args) {
		ArrayList<Menu> menus = new ArrayList<Menu>();
		menus.add(new Menu(WOCHENTAG.MONTAG, "Nudelsuppe", "Gordon bleu", "Apfelstudel"));
		menus.add(new Menu(WOCHENTAG.DIENSTAG, "Salat", "Pizza", "Eis"));
		menus.add(new Menu(WOCHENTAG.MITTWOCH, "Tomaten", "Reisfleisch", "Marmorkuchen"));
		menus.add(new Menu(WOCHENTAG.DONNERSTAG, "Salat", "Spinat und Spiegelei", "Kompott"));
		menus.add(new Menu(WOCHENTAG.FREITAG, "Zwiebelsuppe", "Forelle", "Obsttorte"));
		new Mainframe(menus);
	}

}
