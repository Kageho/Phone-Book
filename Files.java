package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * class opens and reads information from files*/
public class Files {
    private List<Contact> phoneBook;
    private List<Contact> searchList;

    public Files() {
        this.phoneBook = new ArrayList<>();
        this.searchList = new ArrayList<>();
        start();
    }

    private void start() {
        openPhoneBookAndReadData();
        openSearchFileAndReadIt();
    }

    private boolean openPhoneBookAndReadData() {
        try (Scanner fileScanner = new Scanner(new File("/home/sadautov/Downloads/directory.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                phoneBook.add(new Contact(line, true));
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found");
            return false;
        }
        return true;
    }

    private boolean openSearchFileAndReadIt() {
        try (Scanner fileScanner = new Scanner(new File("/home/sadautov/Downloads/find.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                searchList.add(new Contact(line, false));
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found");
            fnf.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Contact> getPhoneBook() {
        return phoneBook;
    }

    public List<Contact> getSearchList() {
        return searchList;
    }
}
