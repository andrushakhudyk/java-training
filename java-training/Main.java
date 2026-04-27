import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static public boolean isRunning = true;

    public static void main(String[] args) {
        while (isRunning) {
            showMenu();
        }
    }

    static ArrayList<User> users = new ArrayList<>();

    public static void showMenu() {
        System.out.println(
                "What are you want to do? (1: create user, 2: show users, 3:update user, 4: delete user, 5: exit)");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                createUser(scanner);
                break;
            case 2:
                showUsers();
                break;
            case 3:
                if (users.isEmpty()) {
                    System.out.println("No users to update.");
                } else {
                    updateUser(scanner);
                }
                break;
            case 4:
                if (users.isEmpty()) {
                    System.out.println("No users to delete.");
                } else {
                    deleteUser(scanner);
                }
                break;
            case 5:
                System.out.println("Exiting...");
                isRunning = false;
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public static void createUser(Scanner scanner) {
        User user = new User();
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        if (name.isEmpty() || name.length() >= 20) {
            System.out.println("Invalid name. Please enter a name with less than 20 characters.");
            return;
        } else {
            user.setName(name);
        }
        System.out.println("Enter mail:");
        String mail = scanner.nextLine();
        if (mail.isEmpty() || mail.length() >= 50 || !mail.contains("@")) {
            System.out.println("Invalid mail. Please enter a mail with less than 50 characters and a valid format.");
            return;
        } else {
            user.setMail(mail);
        }
        System.out.println("Enter age:");
        try {
            user.setAge(scanner.nextInt());
        } catch (Exception e) {
            System.out.println("Invalid age. Please enter a number.");
            return;
        }
        users.add(user);
        System.out.println("User created.");
    }

    public static void showUsers() {
        System.out.println("Users:");
        for (User user : users) {
            System.out.println("Name: " + user.getName() + ", Mail: " + user.getMail() + ", Age: " + user.getAge());
        }
        return;
    }

    public static void updateUser(Scanner scanner) {
        System.out.println("Enter mail of user to update:");
        String mail = scanner.next();
        for (User user : users) {
            if (user.getMail().equals(mail)) {
                System.out.println("Enter new mail:");
                String newMail = scanner.nextLine();
                if (newMail.isEmpty() || newMail.length() >= 50 || !newMail.contains("@")) {
                    System.out.println(
                            "Invalid mail. Please enter a mail with less than 50 characters and a valid format.");
                    return;
                }
                System.out.println("Enter new age:");
                try {
                    user.setAge(scanner.nextInt());
                    user.setMail(newMail);
                } catch (Exception e) {
                    System.out.println("Invalid age. Please enter a number.");
                    return;
                }
                System.out.println("User updated.");
                return;
            }
        }
        System.out.println("User not found.");
    }

    public static void deleteUser(Scanner scanner) {
        System.out.println("Enter mail of user to delete:");
        String mail = scanner.nextLine();
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = (User) iterator.next();
            if (user.getMail().equals(mail)) {
                iterator.remove();
                System.out.println("User deleted.");
                return;
            }
        }
        System.out.println("User not found.");
    }
}
