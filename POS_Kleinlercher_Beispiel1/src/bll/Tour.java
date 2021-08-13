package bll;

import java.util.ArrayList;
import java.util.List;

public class Tour {
	
	private String name;
	private String startPoint;
	private String endPoint;
	private int travelType;
	private int duration;
	private static int idcounter = 0;
	private int id;
	private TourType Type;
	
	public Tour(String name, String startPoint, String endPoint, int travelType, int duration) {
		this.name = name;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.travelType = travelType;
		this.duration = duration;
		Tour.idcounter++;
		this.id = idcounter;
		Type.getTourTypes();
	}
	
	public String Information() {
		String rgw = "this class has\nString name\nString startPoint\nString endPoint\nint travelType\nint duration\nint id\n static int idcounter is the counter which decides what id the next Tour gets\n";
		rgw = rgw + "Functions:\ngetter and setter of the properties\n";
		return rgw;
	}
	
	
	public String toString() {
		return "Name=" + getName() +", startPoint=" + getStartPoint() + ", endPoint=" + getEndPoint() + ", travelType: " + getTravelType() +", duration: " + getDuration() + " Days";
	}
	
	public String getName() {
		return name;
	}
	
	public String getStartPoint() {
		return startPoint;
	}
	
	public String getEndPoint() {
		return endPoint;
	}
	
	public int getTravelType() {
		return travelType;
	}
	
	public int getDuration() {
		return duration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public static class TourType {
		private static List<TourType> TourTypes = new ArrayList<TourType>();
		
		private int id;
		private String name;
		
		public TourType(int id, String name) {
			this.id = id;
			this.name = name;
			TourTypes.add(this);
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}
		
		public List<TourType> getTourTypes() {
			if(TourTypes.size() == 0) {
				TourTypes.add(new TourType(1, "Short Trip"));
				TourTypes.add(new TourType(2, "City Trip"));
				TourTypes.add(new TourType(3, "Business Trip"));
				TourTypes.add(new TourType(4, "Long Trip"));
				TourTypes.add(new TourType(5, "Wellness"));
			}
			return TourTypes;
		}
	}
}
