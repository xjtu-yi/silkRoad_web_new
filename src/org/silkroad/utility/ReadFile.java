package org.silkroad.utility;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
* @author : wuke
* @date   : 20170422 10:57:39
* Title   : ReadFile
* Description : Read files which only contain one line
*/
public class ReadFile {
	public static String readFile(String path) {
		String context = null;
		
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		try {
			fileInputStream = new FileInputStream(path);
			inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);

			context = reader.readLine();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return context;
	}
}
