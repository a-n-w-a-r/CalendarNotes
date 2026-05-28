import java.util.Scanner;

public class Main {

    DbManager dbManager;

    public Main(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    public static void main(String[] args) {
        DbManager dbManager = new DbManager();
        Main m = new Main(dbManager);
        DbManager.connect();
        dbManager.createTable();
        m.main_menu();
    }

    void main_menu () {
        System.out.println("Welcome");
        System.out.println("    -Create todo");

        Scanner myInput = new Scanner(System.in);
        try {
            System.out.println("Select a menu option:");
            int menu_option = myInput.nextInt();

            switch (menu_option) {
                case 1:
                    System.out.println("Create a todo");
                    createTodo();
                    break;
                case 2:
                    System.out.println("Delete a todo");
                    deleteTodo();
                    break;
                default:
                    System.out.println("Invalid menu choice.");
            }
        } catch (Exception e) {
            System.out.println("Please enter a number.");
        }

    }

    void createTodo(){
        try(Scanner input = new Scanner(System.in)){
            System.out.println("Enter todo description:");
            String todoDescription = input.nextLine();
            System.out.println("Enter todo date:");
            String todoDate = input.nextLine();
            boolean todoComplete = false;
            dbManager.handleCreateTodo(new Todo(todoDescription, todoDate, todoComplete));
        } catch (Exception e){
            System.out.println("Invalid input." + e.getMessage());
        }
    }


    void deleteTodo(){
        try (Scanner input = new Scanner(System.in)){
            String todoId = input.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }



}
