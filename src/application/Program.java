package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import entities.LogEntry;

public class Program {

	public static void main(String[] args) throws ParseException{
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter file full path: ");
		String path = scan.nextLine();
		
		File file = new File(path);
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			
			Set<LogEntry> set = new HashSet<LogEntry>();
			
			String line = br.readLine();
			
			while(line != null) {
				
				String[] params = line.split(" ");
				String name = params[0];
				Date date = Date.from(Instant.parse(params[1]));
				
				/* 
				 * É importante ressaltar que a estrutura set, 
				 * com base no hashcode e equals implementado na classe LogEntry, 
				 * caso tente inserir objetos com um username que já conste na lista, 
				 * o mesmo não será incluso. 
				*/
				set.add(new LogEntry(name, date));
				
				line = br.readLine();
			}
			
			System.out.println("Total users: " + set.size());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}finally {
			scan.close();
		}
	}

}
