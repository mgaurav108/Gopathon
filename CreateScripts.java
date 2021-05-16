import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class CreateScripts {

    private static String FILE_NAME = "E:\\NVCC_Seva\\Gopathon\\ListofGaushalas.xlsx";

    public static void main(String[] args) {
        StringBuffer rowString = new StringBuffer("");
        int id=0;
        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            int numberofSheets = workbook.getNumberOfSheets();
            System.out.println("sheet name : "+ workbook.getSheetName(0));
            for(int i=0 ; i<1;i++) {
                rowString.setLength(0);
                Sheet datatypeSheet = workbook.getSheetAt(i);
                Iterator<Row> iterator = datatypeSheet.iterator();

                while (iterator.hasNext()) {
                    rowString.append("Insert into gopathon.goshala_base values  ");
                    rowString.append("(");
                    rowString.append(id++ + ",");
                    Row currentRow = iterator.next();
                    Iterator<Cell> cellIterator = currentRow.iterator();

                    while (cellIterator.hasNext()) {

                        Cell currentCell = cellIterator.next();
                        if(currentCell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                            rowString.append(currentCell.getNumericCellValue() + ",");
                        }else if(currentCell.getCellType()==Cell.CELL_TYPE_STRING){
                            rowString.append("\'"+ currentCell.getStringCellValue().replace("\'", "") +"\',");
                        }else if(currentCell.getCellType()==Cell.CELL_TYPE_BLANK){
                            rowString.append("\'"+ null +"\',");
                        }
                        //getCellTypeEnum shown as deprecated for version 3.15
                        //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    }
                    rowString.deleteCharAt(rowString.length()-1);
                    rowString.append(");\n");
                    System.out.println(rowString);
                }
                writeToFile(rowString);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(StringBuffer rowString) throws IOException {
       BufferedWriter writer = new BufferedWriter(new FileWriter("E:\\NVCC_Seva\\Gopathon\\script.sql"));
       writer.write(rowString.toString());
    }
}


