package org.gopathon;
import java.io.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.util.*;

/**
 * Created by Gaurav on 3/29/2020.
 */

public class PdfToTextConverter {

    public static void main(String[] args){
        PDDocument pd;
        BufferedWriter wr;
        try {
            File input = new File("E:\\gaurav\\Learning\\Gopathon\\Rajasthan.pdf");  // The PDF file from where you would like to extract
            File output = new File("E:\\gaurav\\Learning\\Gopathon\\Rajasthan.txt"); // The text file where you are going to store the extracted data
            pd = PDDocument.load(input);
            System.out.println(pd.getNumberOfPages());
            System.out.println(pd.isEncrypted());
            pd.save("CopyOfInvoice.pdf"); // Creates a copy called "CopyOfInvoice.pdf"
            PDFTextStripper stripper = new PDFTextStripper();
            wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
            stripper.writeText(pd, wr);
            if (pd != null) {
                pd.close();
            }
            // I use close() to flush the stream.
            wr.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}