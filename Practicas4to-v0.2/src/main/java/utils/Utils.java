package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.sample.Log;


public class Utils implements Serializable {


	private static final long serialVersionUID = 1L;

	public static ArrayList<String> readFile (String filename) throws ClassNotFoundException, IOException{
		
		//*** READING FILE

		RandomAccessFile f = new RandomAccessFile(filename, "rw");	
		String line;
		ArrayList<String> arr = new ArrayList<String>();

		while((line = f.readLine()) != null) {

			arr.add(line);

		}

		f.close();
		return arr;

	}

	public static void escribirCSV(String filenamecsv, Log x) throws IOException{
		
		//*** WRITING  CSV OBJECT LOG !!!!!!	
		
		File file = new File(filenamecsv);
		String simulacion = 
				  x.getTimeRequest() + ";"
				+ x.getIp() + ";"
				+ x.getUsername() + ";"
				+ x.getUrlAndEntropy()[0] + ";"
				+ x.getUrlAndEntropy()[1] + ";"
				+ x.getCodeStatusHTTP() + ";"
				+ x.isIncurrent() + ";"
				+ "\n";

		// if file does'nt exists,create it
		
		if (!file.exists()) {   

			String encabezado = 
					 "Fecha de solicitud"+";"
					+"IP"+";"
					+"Username"+";"
					+"URL"+";"
					+"Entropy"+";"
					+"C�digo de estado HTTP"+";"
					+"Incidente"+";"
					+ "\n";   

			BufferedWriter	escrituraSalida = new BufferedWriter(new FileWriter(filenamecsv));
			escrituraSalida.write(encabezado);
			escrituraSalida.write(simulacion);
			escrituraSalida.close();

		}
		else{
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			//*** Write in file
			bw.write(simulacion);        
			bw.close();
		}      

	}

	public static ArrayList<Log> parseLogs (ArrayList <String> arr) {

		ArrayList<Log> logsObject = new ArrayList<Log>();

		for ( int i = 0; i < arr.size(); i++) {
			
			if (! arr.get(i).isEmpty() ) {	
				
				String myLine = arr.get(i).replaceAll(" +", " ");
				String line [] =  myLine.split(" ");
				logsObject.add( createObject(line) );
			}
	
		}

		return logsObject;
	}

	public static Log createObject( String line []) {

		//*** TimeRequest

		String timeRequest = line [5];

		//*** IP

		String IP = line[8];

		//*** Code Status HTTP

		String codes [] = line[9].split("/");
		int codeStatusHTTP = (Integer.parseInt(codes[1]));

		//*** URL

		String URL = line[12];
		Object urlEntropy []= {URL,0.0};
		

		//*** Username

		String username = line[13];

		return  new Log(username, timeRequest, codeStatusHTTP, IP, urlEntropy, false);

	}
	
	public static String cleanString (String text) {
		
		text = Normalizer.normalize(text, Normalizer.Form.NFD);
		text = text.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		
		return text;
	}

}
