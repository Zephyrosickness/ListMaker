import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        final ArrayList<String> list = new ArrayList<>(); //placeholder bruh i gotta think of something
        list.addAll(Arrays.asList("Red", "Orange","Yellow","Green","Teal","Blue","Indigo","Purple","Black","White"));

        do {
            final String[] menu = {"A - Add an item to the list", "D – Delete an item from the list", "P – Print the list", "Q – Quit the program"};
            for (String option : menu) {System.out.println(option);}

            //for some reason the regex pattern did not work for me so i had to improvise
            final String menuOption = InputHelper.getStringInArray(new String[]{"A","P","D","Q"}, "\nWhat would you like to do?").toUpperCase();
            switch (menuOption) {
                case "A":
                    list.add(addItem());
                    break;
                case "D":
                    deleteItem(list);
                    break;
                case "P":
                    printList(list);
                    break;
                case "Q":
                    end();
                    break;
            }

            if(menuOption.equals("A")||menuOption.equals("D")){System.out.println("\nTask completed successfully.\n");}

        }while(true); //this is whiletrue because the quit method will already force stop the system
    }

    private static String addItem(){return InputHelper.getString("Enter what you'd like to add.");}

    //this functions a little differently than additem even though they feel like they should be similar functions on paper
    //because you need to pass the list as a param to get it's length. u could pass list.size() as an int param here and make this return an index but thats overcomplicating it
    private static void deleteItem(ArrayList<String> list){
        if(list.size()>1){
            int index = InputHelper.getRangedInt("Enter the ID number of the item you'd like to erase.", 0, list.size() - 1); //size is decreased by 1 bc indexes start at zero
            list.remove(index);
        }else{System.out.println("You have no items to remove.");}
    }

    //does this really need to be an entire method
    private static void printList(ArrayList<String> list) {
        Scanner scan = new Scanner(System.in);

        for (String item : list){System.out.println(list.indexOf(item) + "- " + item);}

        System.out.println("\nWhen you're done viewing the list, Hit [ENTER].");
        scan.nextLine();
    }

    private static void end(){
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
