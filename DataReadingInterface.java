import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReadingInterface{
    protected static void readFile(ArrayList<String> list) throws IOException{
        list.clear();
        final JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
            final File dataFile = chooser.getSelectedFile();
            final Scanner scan = new Scanner(dataFile);

            while (scan.hasNextLine()){list.add(scan.nextLine());}

        }else{System.out.println("You didn't select a file. Try again.");}
    }

    protected static void writeFile(ArrayList<String> list, String name) throws IOException{
        if(!name.endsWith(".txt")){name+=".txt";}

        File writtenFile = new File(System.getProperty("user.dir")+"\\"+name);
        FileWriter myWriter = new FileWriter(writtenFile);
        for(String line:list){myWriter.write(line+"\n");}
        myWriter.close();
    }
}
