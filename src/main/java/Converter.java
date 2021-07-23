package src.main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Converter {
    ArrayList<File> files = new ArrayList<File>();
    File json = new File("./jsonFiles");
    FileReader reader;
    FileWriter writer;

    public Converter(File folder) {
        json.mkdir();
        String xmlString;
        // Scanning for files with xml extension in folder
        for (File file : folder.listFiles()) {

            String fileName = file.getName();
            if (fileName.contains(".xml")) {
                files.add(file);
                // System.out.println(fileName);

            }
        }

        for (File file : files) {
            try {
                reader = new FileReader(file);
                int tmp;
                xmlString = "";
                do {
                    tmp = reader.read();
                    if (tmp != -1)
                        xmlString += (char) tmp;
                } while (tmp != -1);

                String fileName = file.getName();

                File newFile = new File(json, fileName.replace("xml", "json"));

                try {
                    newFile.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String jsonString = "";
                JSONObject json = new JSONObject();
                try {
                    json = XML.toJSONObject(xmlString);
                    jsonString = json.toString(4);
                    System.out.println(jsonString);

                } catch (JSONException e) {
                    System.out.println("Exception here!!!");
                }
                writer = new FileWriter(newFile);
                writer.write(jsonString);
                writer.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Błąd zapisu!!");
            }

        }

    }
}
