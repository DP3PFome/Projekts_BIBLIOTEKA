package lv.rvt.tools;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

import lv.rvt.Book;

public class WorkWithUser {
    // printšana
    public static void print(List<User> userList) {
        Colors Ui = new Colors();//
        System.out.printf("%-40s %-40s%n",
                Ui.PURPLE("Name"),
                Ui.BLUE("Pasword"));

        System.out.println(Ui.YELLOW("--------------------------------------"));
        for (int i = 0; i < userList.size(); i++) {
            System.out.printf("%-40s %-40s%n",
                    Ui.PURPLE(userList.get(i).getNick()),
                    Ui.BLUE(userList.get(i).getPasword()));

        }

    }


public boolean EnterAC(List<User> userList){
    Scanner scanner = new Scanner(System.in);
    System.out.print("Name: ");
    String name = scanner.nextLine();
    System.out.print("Pasword: ");
    String parole = scanner.nextLine();
    for (int i = 0; i < userList.size(); i++) {
        if (userList.get(i).getNick().equals(name)) {
            if (userList.get(i).getPasword().equals(parole)) {
                System.out.println("Enter in ");
                return true;
            }
        }

    }
    System.out.println("Pasword or Name is incorect");
    return false;
}

    // Izveidosana persona
    public static void addUser(List<User> userList) {
        Scanner scanner = new Scanner(System.in);
        Colors Ui = new Colors();
      

            try (BufferedWriter writer = Helper.getWriter("data.csv", StandardOpenOption.APPEND)) {
                while (true) {
                    System.out.print(Ui.PURPLE("Name: "));
                    String name = scanner.nextLine();
                    System.out.print(Ui.BLUE("Pasword: "));
                    String parle = scanner.nextLine();

                    if (userList.contains(name) || name.equals("")) {
                        System.out.println("Name can't be the same. Try again.");
                        continue;
                    }

                    if (userList.contains(parle) || parle.equals("")) {
                        System.out.println("Pasword can't be the same. Try again.");
                        continue;
                    }

                    User book = new User(name, parle);

                    writer.newLine();
                    writer.write(book.toCsvROW());

                    System.out.println("User has addet.");
                    break;
                }
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        
    }
}
