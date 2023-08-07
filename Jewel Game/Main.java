import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		File gridFile = new File(args[0]);
		File commandFile = new File(args[1]);
		File leadFile = new File("leaderboard.txt");
		File monitorFile = new File("monitroing.txt");
		File outLeadFile = new File("leaderboard.txt");
		
		
		ArrayList<Person> persons = new ArrayList<Person>();
		Scanner reader2 = new Scanner(gridFile);
		int matrixSize=0;
		int columnSize=0;
		while(reader2.hasNextLine()) {String line=reader2.nextLine();matrixSize++;String[] row = line.trim().split(" ");columnSize=row.length;}
		Jewel[][] gridJewel = new Jewel[matrixSize][columnSize];
		Matrix.makeMatrix(gridJewel, gridFile);
		ProcessManager.makeLeaderboard(leadFile, persons);
		
		ProcessManager.gameplay(gridFile,commandFile,persons,gridJewel,monitorFile);
		
		ProcessManager.writeLeaderboard(outLeadFile, persons);
		
		
		
		
		reader2.close();
	}
}
