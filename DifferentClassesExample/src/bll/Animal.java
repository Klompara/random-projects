package bll;
import java.util.*; 

public class Animal {
    private String identifier; 
    private int nrOfFeet;
    private AnimalType type = null; 
	
    public Animal(String identifier, int nrOfFeet, AnimalType type) throws FeetException {
		super();
		this.identifier = identifier;
		this.setNrOfFeet(nrOfFeet);
		this.type = type; 
	}

    public static class AnimalType{
    	private int id; 
    	private String type;
    	private static List<AnimalType> list = new ArrayList<AnimalType>(); 
		public AnimalType(int id, String type) {
			super();
			this.id = id;
			this.type = type;
		}
		
		public static List<AnimalType> getList(){
			if( list.size()== 0){
				list.add( new AnimalType(1, "Reptils"));
				list.add( new AnimalType(2, "Mammals")); 
				list.add( new AnimalType(3, "Insects")); 
			}
			return list; 
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}     	
    	
    }
    
    public void setAnimalType( AnimalType t ){
    	this.type = t; 
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

	public void setNrOfFeet(int nrOfFeet) throws FeetException  {
		class CheckNrOfFeet{
			boolean checkNr(int nr) throws FeetException{
				boolean result = false; 
				if( nr > 0 && nr < 1000){
					result = true; 
				}
				else
				{
					throw new FeetException( "Die Anzahl "+ nrOfFeet+" ist nicht erlaubt!"); 
				}
				return result; 
			}
		}
		CheckNrOfFeet c = new CheckNrOfFeet(); 
		if( c.checkNr( nrOfFeet )){
			this.nrOfFeet = nrOfFeet;
		}
	}

	@Override
	public String toString() {
		return "Animal [identifier=" + identifier + ", nrOfFeet=" + nrOfFeet + type.getType()+"]";
	} 
	
}
