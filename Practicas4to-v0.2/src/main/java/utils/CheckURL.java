package utils;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.io.*;



public class CheckURL {

//	public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {
//		//		String url = "www.revolucion.cu";
//		//		String elements [] = url.split("[.]");
//		//		for (int i = 0; i < elements.length; i ++) {
//		//			
//		//			System.out.print("This is the value : " + elements[i]);
//		//		}
//
//		String url = "http://www.fidel-malo.cu/bunny-xxx.html";
//		HashMap <String, Double> kw = new HashMap<String, Double>();
//		kw.put("revolucion",(double)  1/5);
//		kw.put("fidel", (double) 1/5);
//		kw.put("bunny", (double)1/5 );
//		kw.put("xxx", (double) 1/5);
//		kw.put("puta", (double) 1/5);
//
//		ArrayList <String> prickURL = new ArrayList<String>(prickledURL(url));
////		double entropy = calculateEntropy(prickURL, kw);
//
//		System.out.print("This is the entropy: " + entropy);
//	}

	public  static HashMap <String, Double> dictionaryKeyWords (String keyWords) throws ClassNotFoundException, IOException {

		//*** save into a dictionary keyWords and its occurrence probabilities, from directory file

		ArrayList<String> kw = Utils.readFile(keyWords);
		HashMap<String, Double> dictionaryKW = new HashMap<String, Double>();

		if (!kw.isEmpty()) {

			kw.removeIf( s -> s.isEmpty());	

			if (!kw.isEmpty()) {

				int size = kw.size();

				for (int i = 0; i < size; i++) {

					dictionaryKW.put( kw.get(i), (double) 1/size);					

				}
			}
		}

		return dictionaryKW;
	}



	public static ArrayList <String> prickledURL(String url_log) throws MalformedURLException  {

		//*** save into an array descompressed url (host,path and ref)

		//String prickdUrl[] = new String [30];
		ArrayList <String> prickedUrl = new ArrayList <String> ();

		URL url = new URL (url_log);

		String protocol = url.getProtocol();
		//		String authority = url.getAuthority();
		String  host = url.getHost();
		String ref = url.getRef();
		String path = url.getPath();
		String query = url.getQuery();

		List<String> listArr = new ArrayList <String>();
		
		
		//*** host array
		if(!host.equals("")) {
		String hostElements[] = host.split("[.]");
		Collections.addAll(listArr, hostElements);
		prickedUrl.addAll( listArr);  
		listArr.clear();
		}
		
		
		if (protocol.equalsIgnoreCase("http")) {

			//*** path array
			if(!path.equals("")) {
			String pathElements[] = path.substring(0, path.indexOf(".")).split("/");
			Collections.addAll(listArr, pathElements);
			prickedUrl.addAll( listArr);  
			listArr.clear();
			}
			
			//*** query array
//			if(!query.equals("")) {
//			String queryElements[] = query.split("[=]"); 
//			Collections.addAll(listArr, queryElements);
//			prickedUrl.addAll( listArr);  
//			listArr.clear();
//			}

			//*** ref array
//			if (!ref.equals("")) {
//			String refElements[] = ref.split("");
//			Collections.addAll(listArr, refElements);
//			prickedUrl.addAll( listArr);  
//			listArr.clear();
//			}
		}

		return prickedUrl;
	}


	public static double calculateEntropy (String url_log, String keyWords ) throws ClassNotFoundException, IOException {

		//*** calculate entropy from Url and Keywords array

		double entropy = 0;
		double pi = 1;
		HashMap <String, Double> dictionaryKW = new HashMap <String, Double> (dictionaryKeyWords(keyWords));
		ArrayList <String> prickUrl = new ArrayList <String> (prickledURL(url_log));
		
		for (int i = 0; i < prickUrl.size(); i++) {

			if ( ! prickUrl.isEmpty() || prickUrl.get(i) == null) {
				
				String text = Utils.cleanString(prickUrl.get(i));
				prickUrl.set(i, text);
				prickUrl.get(i).toLowerCase();

				for (Entry<String, Double> entry : dictionaryKW.entrySet()) {

					if ( prickUrl.get(i).contains(entry.getKey()) ) {

						pi = entry.getValue();

					}
					entropy = entropy - pi * Math.log(pi);
					pi = 1;
				}
			}
		}

		return entropy;
	}

}
