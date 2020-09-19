package com.sample;

import java.util.ArrayList;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


import utils.Utils;

public class MainDecisionTable {

	public static final void main(String[] args) {
		
		try {

			//*** load up the knowledge base
			
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-dtables");
			
			//*** go !
			
			String logfile = "E:\\ESCUELA\\3er AÑO\\2doSEMESTRE\\PRACTICAS\\syslog.txt";
			ArrayList<String>linesLogs = Utils.readFile(logfile);
			ArrayList<Log> logs = Utils.parseLogs(linesLogs);

			for (int i = 0; i < logs.size(); i++) {

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
