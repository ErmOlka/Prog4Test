package com.example.tests;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Demo {

	public static void main(String[] args) throws IOException {
		
		  String str = "Это"
		  		+ "тест"
		  		+ "русских букв";
		  Writer out1 = new OutputStreamWriter(new FileOutputStream("test1.txt"), "Unicode");//Cp1251
		  out1.write(str);
		  out1.close();
	}

}
