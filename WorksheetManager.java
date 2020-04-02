package org.gopathon; /**
 * Created by Gaurav on 3/22/2020.
 */

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

public class WorksheetManager {

    XSSFWorkbook workbook = new XSSFWorkbook();
    List<Gaushala> gaushalaList;
    File file = new File("E:\\gaurav\\Learning\\Gopathon\\Gaushala.xlsx");

    //Create a blank sheet
    int rownum = 0;
    XSSFSheet sheet = workbook.createSheet("Goshala Database");

    public WorksheetManager(List<Gaushala> gaushalaList) {
        this.gaushalaList = gaushalaList;
    }

    public void createFirstrow() {
        synchronized (gaushalaList) {
            Row row = sheet.createRow(rownum++);
            int cellnum = 0;
            List<String> columnNames = Arrays.asList("City", "State", "Name of Gaushala",
                    "No of Cows", "Address", "E-Mail ID", "Phone No", "Website", "Social media links",
                    "Single Point of Contact", "Referral Contact ", "Remarks / Comment",
                    "Goshala Verified", "Zone");

            for (String columnName : columnNames
                    ) {
                Cell cell = row.createCell(cellnum++);
                cell.setCellValue(columnName);
            }

            System.out.println("Writing started.......");
            if (gaushalaList.size() == 0) {
                System.out.println("waiting.....the size is zero");
                try {
                    gaushalaList.wait();
                    System.out.println("notified");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            for (Gaushala gaushla : gaushalaList
                    ) {

                System.out.println("worksheet for loop started");
                row = sheet.createRow(rownum++);
                cellnum = 0;
                Cell cell1 = row.createCell(cellnum++);
                cell1.setCellValue(gaushla.getCity());
                Cell cell2 = row.createCell(cellnum++);
                cell2.setCellValue(gaushla.getState());
                Cell cell3 = row.createCell(cellnum++);
                cell3.setCellValue(gaushla.getGaushalaName());
                Cell cell4 = row.createCell(cellnum++);
                cell4.setCellValue("");
                Cell cell5 = row.createCell(cellnum++);
                cell5.setCellValue(gaushla.getGaushalaAddress());
                Cell cell6 = row.createCell(cellnum++);
                cell6.setCellValue(gaushla.getEmail());
                Cell cell7 = row.createCell(cellnum++);
                cell7.setCellValue(gaushla.getContactNumber());
                Cell cell8 = row.createCell(cellnum++);
                cell8.setCellValue(gaushla.getWebsite());
                Cell cell9 = row.createCell(cellnum++);
                cell9.setCellValue(""); //social media links
                Cell cell10 = row.createCell(cellnum++);
                cell10.setCellValue("SPOC");
                Cell cell11 = row.createCell(cellnum++);
                cell11.setCellValue(""); //Referral Contact
                Cell cell12 = row.createCell(cellnum++);
                cell12.setCellValue("");//comments
                Cell cell13 = row.createCell(cellnum++);
                cell13.setCellValue(""); //Gaushala Verified
                Cell cell14 = row.createCell(cellnum++);
                cell14.setCellValue(getZone(gaushla.getState())); //Gaushala Verified
            }

            try {
                //Write the workbook in file system

                FileOutputStream out = new FileOutputStream(file);
                workbook.write(out);
                out.close();
                System.out.println("First batch written so notify");
             //   gaushalaList.notify();
                System.out.println("Hari Bollllll.......File Created ... waiting for next batch");
             //   gaushalaList.wait();
            } catch (Exception e) {
                System.out.println(" Please Try again ................some issue");
                e.printStackTrace();
            }
        }

    }

    private String getZone(String state) {

        if(Gaushala.northZone.contains(state.trim())){
            return "North";
        }else if(Gaushala.southZone.contains(state.trim())){
            return "South";
        }else if(Gaushala.eastZone.contains(state.trim())){
            return "East";
        }else if(Gaushala.westZone.contains(state.trim())){
            return "West";
        }else {
            return "Not Found";
        }
    }
}
