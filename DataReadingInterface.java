import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReadingInterface{
    protected static String readFile(ArrayList<String> list) throws IOException{
        list.clear();
        final JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
            final File file = chooser.getSelectedFile();
            final Scanner scan = new Scanner(dataFile);

            while (scan.hasNextLine()){list.add(scan.nextLine());}

            return file.getName();

        }else{System.out.println("You didn't select a file. Try again.");}
        return null;
    }

    protected static void writeFile(ArrayList<String> list, String name) throws IOException{
        if(!name.endsWith(".txt")){name+=".txt";}

        File file = new File(System.getProperty("user.dir")+"\\"+name);
        FileWriter writer = new FileWriter(file);
        
        for(String line:list){writer.write(line+"\n");}
        writer.close();
    }
}
