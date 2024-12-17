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
                case "P":
                    printList(list);
                    break;
                case "C":
                    clearList(list);
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
        Scanner scan = new Scanner(System.in);

        if(!list.isEmpty()) {
            for (String item : list) {
                System.out.println(list.indexOf(item) + 1 + "- " + item);
            }

            System.out.println("\nWhen you're done viewing the list, Hit [ENTER].");
            scan.nextLine();
        }else{
            System.out.println("You have nothing to print.\n");
        }
    }

    private static void clearList(ArrayList<String> list){
        list.clear();
        System.out.println("\nTask completed successfully.\n");
    }
    private static void end(){
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
