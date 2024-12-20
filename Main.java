import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static String filename;
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        boolean isChanged = false;
        boolean optionAdded = false;
        final ArrayList<String> menu = new ArrayList<>(Arrays.asList("\nA - Add an item to the list", "D – Delete an item from the list", "V – View the list", "C - Clear the list","O - Open a list from .txt file", "S - Save a list to .txt file","Q – Quit the program"));

        do{
            if(filename!=null&&!optionAdded){
                menu.add(6,"F - Save list to a different .txt file");
                menu.set(5, "S - Save list to currently opened .txt file");
                optionAdded=true;
            }
            for (String option : menu){System.out.println(option);}
            if(filename!=null){System.out.println("Currently reading from file "+filename);}


            //for some reason the regex pattern did not work for me so i had to improvise
            String menuOption = InputHelper.getString("\nWhat would you like to do?").toUpperCase();
            menuOption = String.valueOf(menuOption.charAt(0));
            switch (menuOption) {
                case "A":
                    addItem(list);
                    isChanged = true;
                    break;
                case "D":
                    deleteItem(list);
                    isChanged = true;
                    break;
                case "V":
                    printList(list, true);
                    break;
                case "C":
                    clearList(list);
                    break;
                case "O":
                    list = openFile(list);
                    break;
                case "S":
                    saveFile(list, true);
                    isChanged = false;
                    break;
                case "F":
                    saveFile(list, false);
                    break;
                case "Q":
                    end(isChanged);
                    break;
            }
        }while(true); //this is whiletrue because the quit method will already force stop the system. this wont throw an exception intellij is a little liar
    }

    private static void addItem(ArrayList<String> list){
        String item = InputHelper.getString("Enter what you'd like to add.");
        list.add(item);
        System.out.println("\nTask completed successfully.");
    }

    private static void deleteItem(ArrayList<String> list){
        if(list.size()>1){
            int index = InputHelper.getRangedInt("Enter the number of the item you'd like to erase.", 1, list.size()); //index is decreased by 1 bc indexes start at zero but they're displayed starting at 1
            index--; //subtracts by 1 because indexes start at 0 but the list is displayed starting at 1
            System.out.println("Deleted item "+list.get(index)+".\n");
            list.remove(index);
        }else{System.out.println("You have no items to remove.");}
    }

    //does this really need to be an entire method
    private static void printList(ArrayList<String> list, boolean isConfirmation) {
        Scanner scan = new Scanner(System.in);

        if(!list.isEmpty()) {
            for (String item : list) {
                System.out.println(list.indexOf(item) + 1 + "- " + item);
            }

            if(isConfirmation){ //this is here because this is also called when opening a file, and it's a bit redundant to have the enter prompt bc it already asks for an input
                System.out.println("\nWhen you're done viewing the list, Hit [ENTER].");
                scan.nextLine();
            }
        }else{System.out.println("You have nothing to print.\n");}
    }

    private static void clearList(ArrayList<String> list){
        list.clear();
        System.out.println("\nTask completed successfully.\n");
    }

    private static ArrayList<String> openFile(ArrayList<String> list) throws IOException{
        boolean keepGoing = true;
        if(!list.isEmpty()){keepGoing = InputHelper.getYN("This will clear your current list. Proceed?");}
        ArrayList<String> tempList = new ArrayList<>();

        if(keepGoing){
            System.out.println("[the window opens BEHIND the window! minimize the window!]");
            String tempFilename = DataReadingInterface.readFile(tempList);
            printList(tempList, false);

            boolean keepChanges = InputHelper.getYN("\nIs this correct?");
            if(keepChanges){
                list.clear(); //clears so the new items from the txt can load
                filename = tempFilename;
                return tempList;
            }else{System.out.println("Changes not saved.");}
        }
        return list;
    }

    private static void saveFile(ArrayList<String> list, boolean sameFile) throws IOException{
        printList(list, false);

        boolean keepChanges = InputHelper.getYN("\nIs this the list you want to save?");
        if(keepChanges){
            if(!sameFile||filename==null){filename = InputHelper.getString("Enter a name for your list.");}
            if(!filename.endsWith(".txt")){filename+=".txt";}
            DataReadingInterface.writeFile(list, filename); //i had to add a line to the DataReadingInterface to create a new file if it doesnt exist... tsk tsk tsk herr george... im better at java because i already knew how to read/write files from when i made snake im sooo freaking good at coding (IM JOKING IM JOKING)
        }else{System.out.println("Changes not saved.");}
    }

    private static void end(boolean isChanged){
        boolean checkOne = true;
        boolean checkTwo = false;

        if(isChanged){checkOne = InputHelper.getYN("You have unsaved changes. Proceed?");}

        if(checkOne){checkTwo = InputHelper.getYN("Are you sure you want to quit?");}

        if(checkTwo) {
            System.out.println("Goodbye!");
            System.exit(0);
        }
    }
}
