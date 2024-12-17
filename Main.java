import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static boolean isChanged = false;
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Red", "Orange", "Yellow", "Green", "Teal", "Blue", "Indigo", "Purple", "Black", "White"));

        do {
            final String[] menu = {"\nA - Add an item to the list", "D – Delete an item from the list", "V – View the list", "C - Clear the list","O - Open a list from .txt file", "S - Save a list to a .txt file","Q – Quit the program"};
            for (String option : menu) {System.out.println(option);}

            //for some reason the regex pattern did not work for me so i had to improvise
            String menuOption = InputHelper.getStringInArray(new String[]{"A","D","V","C","O","S","D","Q","Add","View","Delete","Open","Save","Clear","Quit"}, "\nWhat would you like to do?").toUpperCase();
            menuOption = String.valueOf(menuOption.charAt(0));
            switch (menuOption){
                case "A":
                    addItem(list);
                    break;
                case "D":
                    deleteItem(list);
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
                case "Q":
                    end();
                    break;
            }

        }while(true); //this is whiletrue because the quit method will already force stop the system
    }

    private static void addItem(ArrayList<String> list){
        String item = InputHelper.getString("Enter what you'd like to add.");
        list.add(item);
        System.out.println("\nTask completed successfully.\n");
    }

    private static void deleteItem(ArrayList<String> list){
        if(list.size()>1){
            int index = InputHelper.getRangedInt("Enter the ID number of the item you'd like to erase.", 0, list.size())-1; //index is decreased by 1 bc indexes start at zero but they're displayed starting at 1
            System.out.println("Deleted item "+list.get(index)+".\n");
            list.remove(index);
        }else{System.out.println("You have no items to remove.");}
    }

    //does this really need to be an entire method
    private static void printList(ArrayList<String> list) {
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

    private static ArrayList<String> openFile(ArrayList<String> list) throws IOException{
        boolean keepGoing = InputHelper.getYN("This will clear your current list. Proceed?");
        ArrayList<String> tempList = new ArrayList<>();

        if(keepGoing){
            System.out.println("[the window opens BEHIND the window! minimize the window!]");

            IOHelper.openFile(tempList);
            printList(tempList, false);

            boolean keepChanges = InputHelper.getYN("\nIs this correct?");
            if(keepChanges){
                list.clear(); //clears so the new items from the txt can load
                isChanged = false;
                return tempList;
            }else{System.out.println("Changes not saved.");}
        }
        return list;
    }
    private static void end(){
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
