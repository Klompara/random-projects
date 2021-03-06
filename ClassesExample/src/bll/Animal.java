package bll;

import java.util.ArrayList;
import java.util.List;

public class Animal {
	private String identifier;
	private int nrOfFeet;
	private AnimalType type;

	public Animal(String identifier, int nrOfFeet) {
		super();
		this.identifier = identifier;
		this.nrOfFeet = nrOfFeet;
	}

	public static class AnimalType {
		private int id;
		private String identifier;

		public int getId() {
			return id;
		}

		public String getIdentifier() {
			return identifier;
		}

		private static List<AnimalType> list = new ArrayList<AnimalType>();

		public AnimalType(int id, String identifier) {
			this.id = id;
			this.identifier = identifier;
		}

		public static List<AnimalType> getList() {
			if (list.size() == 0) {
				list.add(new AnimalType(1, "Reptiles"));
				list.add(new AnimalType(2, "Mammals"));
			}
			return list;
		}

	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public int getNrOfFeet() {
		return nrOfFeet;
	}

	public void setNrOfFeet(int nrOfFeet) {
		this.nrOfFeet = nrOfFeet;
		class NrOfFeetChecker {
			boolean checkNumberOfFeet(int nrOfFeet) {
				boolean result = true;
				if (nrOfFeet < 0 || nrOfFeet > 1001) {
					result = false;
				}
				return result;
			}
		}
		NrOfFeetChecker c = new NrOfFeetChecker();
		c.checkNumberOfFeet(nrOfFeet);
	}

	@Override
	public String toString() {
		return "Animal [identifier=" + identifier + ", nrOfFeet=" + nrOfFeet + "]";
	}

}
