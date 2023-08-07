import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.image.Image;

public class DataManager {
	
	
	static ArrayList<User> persons = new ArrayList<User>();
	static ArrayList<Hall> halls = new ArrayList<Hall>();
	static ArrayList<Film> films = new ArrayList<Film>();
	static ArrayList<Seat> seats = new ArrayList<Seat>();
	static File dataFile =  new File("assets\\data\\backup.dat");
	static File propFile =  new File("assets\\data\\properties.dat");
	static String title;
	static int maxError;
	static int discount_percentage;
	static int block_time;
	static Image icon;

	public static void readData() throws IOException {
		loadIcon();
		readProperties();
		Scanner sc = null;
        try {
           
            sc = new Scanner(dataFile);     
            String line;
            while (sc.hasNextLine()) {
              line = sc.nextLine();
              String[] datas = line.trim().split("\t");
              if(datas[0].equals("user")) {
            	  Boolean clubMember=false;
            	  Boolean admin=false;
            	  if(datas[3].equals("true")) {
            		  clubMember = true;
            	  }
            	  else {
					clubMember = false;
				}
            	  if(datas[4].equals("true")) {
            		  admin = true;
            	  }
            	  else {
					admin = false;
				}
            	  persons.add(new User(datas[1], datas[2],clubMember,admin));
            	  
              }
              else if(datas[0].equals("hall")) {
            
            	  halls.add(new Hall(datas[1], datas[2], Integer.valueOf(datas[3]), 
            			  Integer.valueOf(datas[4]), Integer.valueOf(datas[5])));
				
              }
              else if(datas[0].equals("film")) {
            	  films.add(new Film(datas[1], datas[2], datas[3]));
              }
              else if(datas[0].equals("seat")) {
            	
            	  seats.add(new Seat(datas[1], datas[2], Integer.valueOf(datas[3]), 
            			  Integer.valueOf(datas[4]), datas[5], Integer.valueOf(datas[6])));
              }
            }
          }
          catch(FileNotFoundException e)
          {
              e.printStackTrace();
          }
          finally {
            if (sc != null) sc.close();
          }
       
	}
	public static void readProperties() {
		Scanner sc = null;
        try {
           
            sc = new Scanner(propFile);     
            String line;
            while (sc.hasNextLine()) {
            	line = sc.nextLine();
            	
                String[] datas = line.trim().split("=");
                if (datas[0].equals("title")) {
                	title = datas[1];
					
				}
                else if (datas[0].equals("block-time")) {
                	block_time = Integer.valueOf(datas[1]);
					
				}
                else if (datas[0].equals("discount-percentage")) {
                	discount_percentage = Integer.valueOf(datas[1]);
					
				}
                else if (datas[0].equals("maximum-error-without-getting-blocked")) {
                	maxError = Integer.valueOf(datas[1]);
					
				}

             
            }
          }
          catch(FileNotFoundException e)
          {
              e.printStackTrace();
          }
          finally {
            if (sc != null) sc.close();
          }
		
	}
	public static void loadIcon() throws FileNotFoundException {
		InputStream stream = new FileInputStream("assets\\icons\\logo.png");
	    icon = new Image(stream);
		
	}
	
}
