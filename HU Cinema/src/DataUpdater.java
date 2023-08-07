import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class DataUpdater {
	private static File data=new File("assets\\data\\backup.dat");
	
	
	public static void updateData() throws IOException {
		FileWriter writer = new FileWriter(data);

        try {
        	writePersons(writer);
        	writeSeat(writer);      
          }
          catch(FileNotFoundException e)
          {
              e.printStackTrace();
          }
        writer.close();
	}
	private static void writePersons(FileWriter writer) throws IOException {
		for(User u:DataManager.persons) {
    		
    		writer.write("user"+ "\t" +u.getUsername()+"\t"+u.getPassword()+"\t"+u.getMemberClub()+"\t"+u.getAdmin()+"\n");
    	}
	}
	private static void writeSeat(FileWriter writer) throws IOException {
		for(int i=0;i<DataManager.films.size();i++) {	
			Film f = DataManager.films.get(i);
    		writer.write("film"+ "\t" +f.getName()+"\t"+f.getPath()+"\t"+f.getDuration()+"\n");	
    		for(int k=0;k<DataManager.halls.size();k++) {
    			Hall h = DataManager.halls.get(k);
    			if(h.getFilmName().equals(f.getName())) {
    				writer.write("hall"+ "\t" +h.getFilmName()+"\t"+h.getHallName()+"\t"+h.getPrice()+"\t"+h.getRow()+"\t"+h.getColumn()+"\n");
    				for(int j=0;j<DataManager.seats.size();j++) {
    					Seat seat = DataManager.seats.get(j);
            			if(seat.getHallName().equals(h.getHallName())) {
            				writer.write("seat"+"\t"+h.getFilmName()+"\t"+h.getHallName()
            				+"\t"+seat.getRow()+"\t"+seat.getColumn()+"\t"+seat.getOwner()
            				+"\t"+seat.getPrice()+"\n");
            			}
            		}	
    			}
        	}
		}
	}	
}
