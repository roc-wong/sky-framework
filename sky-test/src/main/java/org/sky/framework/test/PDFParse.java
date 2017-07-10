package org.sky.framework.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFParse {
	
	public static String getContent(File f) throws Exception {
		FileInputStream fis = new FileInputStream(f);
		PDFParser p = new PDFParser((RandomAccessRead) fis);
		p.parse();
		PDDocument pdd = p.getPDDocument();
		PDFTextStripper ts = new PDFTextStripper();
		String c = ts.getText(pdd);
		pdd.close();
		fis.close();
		return c;
	}

	public static void main(String[] args) throws Exception {

		try {
			DataInputStream fin = new DataInputStream(
					new BufferedInputStream(
							new FileInputStream(
									"D:\\Jersey Engagement letter FINAL - APR 30 2015.pdf")));
			File file = new File("D:\\java2.pdf");
			if (!file.exists())
				file.createNewFile();
			DataOutputStream fout = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)));
			byte[] buff = new byte[1024];
			int bytesRead;
			while (-1 != (bytesRead = fin.read(buff, 0, buff.length))) {
				fout.write(buff, 0, bytesRead);
			}
			fout.close();
			fin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file = new File("D:\\Jersey Engagement letter FINAL - APR 30 2015.pdf");
		String content = getContent(file);
		System.out.println(content);
		
	}

	
	
}
