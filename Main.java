import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        final ArrayList<String> list = new ArrayList<>(); //placeholder bruh i gotta think of something

        do {
            final String[] menu = {"A - Add an item to the list", "D – Delete an item from the list", "P – Print the list", "Q – Quit the program"};
            for (String option : menu) {System.out.println(option);}

            final String menuOption = InputHelper.getRegEx("What would you like to do?", "AaDdPpQq").toUpperCase();
            switch (menuOption) {
                case "A":
                    addItem(list);
                    break;
                case "D":
                    deleteItem(list);
                    break;
                case "P":
                    break;
                case "Q":

                    break;
            }

        }while(true); //this is whiletrue because the quit method will already force stop the system
    }

    /*you dont actually have to pass list in as a param here you could just prompt the user for a string and do list.add(additem())
    but i did it anyway because i like to have messages display after it's done*/
    public static void addItem(ArrayList<String> list){
        String newItem = InputHelper.getString("Enter the item you'd like to add.");
    }

    public static void deleteItem(ArrayList<String> list){
        int index = InputHelper.getRangedInt("Enter the ID number of the item you'd like to erase.",0,list.size());
        list.remove(index);
        System.out.println("Task completed successfully");
    }
}
