package com.sample;

import java.util.ArrayList;


import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import utils.Utils;
import utils.CheckURL;

public class Main {


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
			//*** load up the knowledge base
			
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rules");
            
			String keyWordsFile = "E:\\ESCUELA\\3er AÑO\\2doSEMESTRE\\PRACTICAS\\keywords.txt";
			String logfile = "E:\\ESCUELA\\3er AÑO\\2doSEMESTRE\\PRACTICAS\\syslog.txt";
			
			ArrayList<String>linesLogs = Utils.readFile(logfile);
			ArrayList<Log> logs = Utils.parseLogs(linesLogs);

			for (int i = 0; i < logs.size(); i++) {

				logs.get(i).getUrlAndEntropy()[1] = CheckURL.calculateEntropy((String)logs.get(i).getUrlAndEntropy()[0], keyWordsFile);
				kSession.insert( logs.get(i) );
				
				//*** activating coincident rules!
				kSession.fireAllRules();                       
				
				String csvFile = "E:\\ESCUELA\\csvFile.csv";
				Utils.escribirCSV(csvFile, logs.get(i));
			}

		} 
		catch (Throwable t) {
			
			t.printStackTrace();	       
		}

	}
}
