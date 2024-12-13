//Allows us to use the FileChooser wizard GUI to pick files
import javax.swing.*;
//Needed imports for working w/ IO (input/output)
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static java.nio.file.StandardOpenOption.CREATE;


/*
 * These class incorporates the ReadFiles and WriteFiles programs we worked with previously.
 * These two programs have been rewritten as methods with the IOHelper class.
 * You can call the openFile method in your Main class by calling IOHelper.openFile().
 * Repeat for all methods you want to use in this clas.
 */

public class IOHelper {
    private static final JFileChooser chooser = new JFileChooser();

    /*
        Method Summary:
        * Allows the user to pick a file from the JFileChooser Wizard GUI
        * Returns selected file name back to the user
        * Stores each line of the selected file in the ArrayList passed in by the user as a parameter
        * This method DOES NOT read the file - it stores the lines from the file in the ArrayList
        * The file can be read by looping through and printing the ArrayList in your Main class
    */
    public static String openFile(ArrayList <String> list) throws IOException{
        try {
            File workingDirectory = new File(System.getProperty("user.dir"));

            //This will make the JFileChooser GUI default to look in the workingDirectory first
            //User can still navigate out of this folder if desired
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                //InputStream is needed in order to create our Buffered Reader
                //InputStream allows bytes of data to be read from a file
                //BufferedReader is our actual "reader" tool that will be used to read characters from file
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                //Rec holds what the reader finds on the line
                String rec = "";

                while (reader.ready()) {
                    rec = reader.readLine();
                    list.add(rec);
                }
                reader.close(); // must close the file to seal it and clear buffer
                System.out.println("\n\nData file opened!");
                return file.getFileName().toString();
            }else{
                System.out.println("File not selected. Please restart program.");
                System.exit(0);
            }
        }
        //This catch block is hit when the user file the user attempts to open a file that can not be found
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
        //This catch block is hit for all other IO Exceptions
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
        Method Summary:
        * Allows the user to store the contents of the ArrayList in a txt file
        * User must pass in the ArrayList that is being written to the txt file as a parameter
        * User must pass in the name of file that they are writing
        * You can think of this method as "saving" your data
    */
    public static void writeFile(ArrayList<String> list, String name) throws IOException{
        //Sample data that is being added to an ArrayList named recs
        ArrayList<String> recs = list;
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory+"\\"+name);
        System.out.println(file);
        file.toFile().createNewFile(); //this creates a new file if it doesnt exist. tsk tsk tsk herr george you didnt think of this

        try {
            //OutputStream is needed in order to create our Buffered Writer
            //OutputStream allows bytes of data to be written to a file
            //BufferedWriter is our actual writing tool that will be used to write characters to file
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            //Actually writing data from recs to new file
            for (String r : recs) {
                //r is the String being written
                //0 is the index of the String the writer starts writing at
                //r.length is the index of the String the writer stops writing at
                writer.write(r, 0, r.length());
                //need new line added before we write more data - ensures next bit of data is put on own line
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        }

        catch (IOException e) {e.printStackTrace();}
    }
}