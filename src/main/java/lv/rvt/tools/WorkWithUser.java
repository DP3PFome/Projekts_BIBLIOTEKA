package lv.rvt.tools;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

import lv.rvt.Book;

public class WorkWithUser {

    // Lietotāju saraksta izvadīšana
    public static void print(List<User> userList) {
        Colors Ui = new Colors();

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

    // Lietotāja pieteikšanās funkcija
    public static String EnterAC(List<User> userList) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("0 - exite");

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Pasword: ");
            String parole = scanner.nextLine();

            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getNick().equals(name)) {
                    if (userList.get(i).getPasword().equals(parole)) {
                        System.out.println("Enter in ");
                        return name;
                    }
                }
            }

            System.out.println("Pasword or Name is incorect");

            if (name.equals("0")) {
                break;
            }
        }

        return "";
    }

    // Jauna lietotāja pievienošana
    public static void addUser(List<User> userList) {
        Scanner scanner = new Scanner(System.in);
        Colors Ui = new Colors();

        try (BufferedWriter writer = Helper.getWriter("peson.csv", StandardOpenOption.APPEND)) {

            while (true) {
                System.out.print(Ui.PURPLE("Name: "));
                String name = scanner.nextLine();

                System.out.print(Ui.BLUE("Pasword: "));
                String pasword = scanner.nextLine();

                // Pārbaude vai lietotājvārds jau eksistē vai ir tukšs
                if (userList.contains(name) || name.equals("")) {
                    System.out.println("Name can't be the same. Try again.");
                    continue;
                }

                // Pārbaude vai parole jau eksistē vai ir tukša
                if (userList.contains(pasword) || pasword.equals("")) {
                    System.out.println("Pasword can't be the same. Try again.");
                    continue;
                }

                User user = new User(name, pasword);

                writer.newLine();
                writer.write(user.toCsvROW());

                System.out.println("User has addet.");
                break;
            }

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
