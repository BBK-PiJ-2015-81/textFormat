/**
 * Created by andre on 10/08/2016.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Format {

    public static void main(String [] args){

        String myFile = "french_test_UTF.txt";
        String line = null;
        List<String> unsortedRows = new ArrayList<String>();


        try {
            File file = new File(myFile);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(myFile), "UTF-8"));

            StringBuffer outputStringBuffer = new StringBuffer();
            outputStringBuffer.delete(0, outputStringBuffer.length());


            while ((line = bufferedReader.readLine()) != null) {

                if (line.startsWith("\uFEFF")) {
                    //Ignore this the first line
                } else {

                    boolean startsWithDigit = Character.isDigit(line.charAt(0));


                    if (startsWithDigit) {

                        int spaceIndex = line.indexOf(" ");

                        // Append Rank

                        String rank = line.substring(0, spaceIndex);

                        if (rank.length() == 1) {
                            rank = "000" + rank;
                        }
                        if (rank.length() == 2) {
                            rank = "00" + rank;
                        }
                        if (rank.length() == 3) {
                            rank = "0" + rank;
                        }

                        outputStringBuffer.append(rank + ",");
                        //outputStringBuffer.append(line.substring(0, spaceIndex)+",");

                        // Append French Word
                        outputStringBuffer.append(line.split(" ")[1] + ",");

                        // Append English Definition
                        if (line.split(" ")[2].equals("to")) {

                            String verb = line.split(" ")[3];

                            if (verb.substring(verb.length() - 1).equals(",")) {
                                verb = verb.substring(0, verb.length() - 1);
                            }
                            outputStringBuffer.append(line.split(" ")[2] + " " + verb);

                        } else {
                            String definition = line.split(" ")[2];

                            if (definition.substring(definition.length() - 1).equals(",")) {
                                definition = definition.substring(0, definition.length() - 1);
                            }

                            outputStringBuffer.append(definition);
                        }

                        //outputStringBuffer.append("\n");
                        unsortedRows.add(outputStringBuffer.toString());
                        outputStringBuffer.delete(0, outputStringBuffer.length());

                    }
                }
            }

            fileReader.close();
            Collections.sort(unsortedRows);

            String [] unsortedRowsArr = new String[unsortedRows.size()];
            unsortedRowsArr = unsortedRows.toArray(unsortedRowsArr);

            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("output3.txt")));

            for(String s : unsortedRowsArr)
                System.out.println(s);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

