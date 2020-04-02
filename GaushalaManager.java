package org.gopathon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaurav on 3/19/2020.
 */
public class GaushalaManager {
    List<String> gaushalaNameList;

    public List<String> getGaushalaNameList() throws IOException {
        gaushalaNameList = new ArrayList<String>();
        File file = new File("E:\\gaurav\\Learning\\Gopathon\\names.txt");
        String currentLine = null;
        int count = 0;
        BufferedReader reader = new BufferedReader(
                new FileReader("E:\\gaurav\\Learning\\Gopathon\\names.txt")

        );

        while ((currentLine = reader.readLine()) != null) {
            if ((!currentLine.contains("farmer")) && (currentLine.contains("gaushala") ||
                    currentLine.contains("Goshala") ||
                    currentLine.contains("goshala") ||
                    currentLine.contains("gir") ||
                    currentLine.contains("gau") ||
                    currentLine.contains("Gau") ||
                    currentLine.contains("Gir") ||
                    currentLine.contains("farm") ||
                    currentLine.contains("dairy") ||
                    currentLine.contains("Dairy") ||
                    currentLine.contains("surabhi") ||
                    currentLine.contains("mandir") ||
                    currentLine.contains("Mandir") ||
                    currentLine.contains("Shree") ||
                    currentLine.contains("Radha") ||
                    currentLine.contains("radha") ||
                    currentLine.contains("Raksha") ||
                    currentLine.contains("raksha") ||
                    currentLine.contains("Samvardhan") ||
                    currentLine.contains("samvardhan") ||
                    currentLine.contains("Krishna") ||
                    currentLine.contains("seva") ||
                    currentLine.contains("Seva") ||
                    currentLine.contains("sanstha") ||
                    currentLine.contains("Sanstha") ||
                    currentLine.contains("shree") ||
                    currentLine.contains("Shree") ||
                    currentLine.contains("Shri") ||
                    currentLine.contains("Surabhi") ||
                    currentLine.contains("Panjrapole") ||
                    currentLine.contains("panjrapole") ||
                    currentLine.contains("Dairy") ||
                    currentLine.contains("Farm") ||
                    currentLine.contains("Trust") ||
                    currentLine.contains("trust") ||
                    currentLine.contains("farm"))) {
                System.out.println(count++ + "  " + currentLine);
                gaushalaNameList.add(currentLine);
            }
        }
        return gaushalaNameList;
    }

}
