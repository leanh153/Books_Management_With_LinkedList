/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Book;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import util.MyList;
import util.Node;

/**
 *
 * @author LeAnh
 */
public class BookList {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final String DATA = "bookData.txt";
    //a list of book
    private MyList books = null;

    public BookList() {
        books = new MyList();
    }

    public  void importData() {

        System.out.println("Importing data");
        try (FileInputStream fis = new FileInputStream(DATA);
                ObjectInputStream ois = new ObjectInputStream(fis);) {

            books = (MyList) ois.readObject();
            // update size
            books.size();
            ois.close();
            System.out.println("Import data complete!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Importing data problem: " + e.getMessage());
        }

    }

    public void saveBooks() {
        try (FileOutputStream fos = new FileOutputStream(DATA);
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {

            oos.writeObject(books);
            oos.close();
            System.out.println("Books list are saved");
        } catch (IOException e) {
            System.out.println("Saving file problem: " + e.toString());
        }

    }

    //1.0 accept information of a Book
    public Book getBook() {
        Book book = new Book();
        System.out.println("Enter information of a Book");
        book.setbCode(stringReader("Book Code: ", true));
        book.setTitle(stringReader("Book title: ", false));
        book.setQuantity(intReader("Book Quantity: ", 0, 1000000000));
        book.setLended(intReader("Book Lended: ", 0, 1000000000));
        book.setPrice(doubleReader("Book Price: "));
        return book;
    }

    // get double number from user
    private double doubleReader(String msg) {
        double doubleNum = 0;
        boolean notValid = true;
        while (notValid) {
            try {
                System.out.print(msg);
                doubleNum = Double.parseDouble(br.readLine());
                notValid = false;
            } catch (IOException | NumberFormatException e) {
                System.out.println("Invalid input!");

            }

        }
        return doubleNum;
    }

    // get string from user input
    private String stringReader(String msg, boolean isGetBookCode) {
        boolean notValid = true;
        String str = null;

        while (notValid) {
            System.out.print(msg);

            try {
                str = br.readLine();
                if (!(str.trim().isEmpty())) {
                    if (isGetBookCode) {
                        if (books.search(str) != null) {
                            System.out.println("Book code must be unique");
                        } else {
                            notValid = false;
                        }
                    } else {
                        notValid = false;
                    }

                } else {
                    System.out.println("Not allow empty");

                }
            } catch (IOException e) {
            }

        }

        return str;
    }

    // this gets integer from user
    public int intReader(String msg, int startRange, int endRange) {
        int choice = 0;
        boolean notValid = true;
        while (notValid) {
            try {
                System.out.print(msg);
                choice = Integer.parseInt(br.readLine());
                if (choice >= startRange && choice <= endRange) {
                    notValid = false;
                } else {
                    System.out.println("Check your number!");
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Invalid input!");
            }

        }
        return choice;
    }

    //1.1 accept and add a new Book to the end of book list
    public void addLast() {
        Book book = getBook();
        Node<Book> insertedBook = books.addLast(book);

        if (book.getbCode().equalsIgnoreCase(insertedBook.getData().getbCode())) {
            System.out.println("Book is added to the end");
            System.out.println(insertedBook.getData().toString());
        } else {
            System.out.println("Book is not add to list, yet");
        }
    }

    //1.2 output information of book list
    public void list() {
        if (books.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        books.traverse();
    }

    //1.3 search book by book code
    public void search() {
        if (books.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        String bookCode = stringReader("Enter Book Code: ", false);
        Node<Book> node = books.search(bookCode);
        if (node != null) {
            System.out.println("Information of node Code: " + bookCode);
            System.out.println(node.getData().toString());
        } else {
            System.out.println("Book is not found!");
        }

    }

    //1.4 accept and add a new Book to the begining of book list
    public void addFirst() {
        Book book = getBook();
        Node<Book> addedBook = books.addFirst(book);

        if (book.getbCode().equalsIgnoreCase(addedBook.getData().getbCode())) {
            System.out.println("Book is added to the start ");
            System.out.println(addedBook.getData().toString());
        } else {
            System.out.println("Book is not add to list, yet");
        }
    }

    //1.5 Add a new Book after a position k
    public void addAfter() {
        int pos = intReader("Enter adding position: ", 0, 1000000000);
        if (books.isEmpty()) {
            System.out.println("List is empty, please add at least 1 book");
            return;
        }
        
        int size = books.size();
        if (pos > (size -1)) {
            System.out.println("Position must less than: " + size);
            return;
        }
        
        Book book = getBook();
        Node<Book> addedNode = books.addAfter(book, pos);
        
        if (book.getbCode().equalsIgnoreCase(addedNode.getData().getbCode())) {
            System.out.println("Book is added to the posision: " + (pos + 1));

        } else {
            System.out.println("Book is not add to list, yet");
        }
    }

    //1.6 Delete a Book at position k
    public void deleteAt() {
        if (books.isEmpty()) {
            System.out.println("Book list is empty!");
            return;
        }
        int pos = intReader("Enter book's position to delete: ", 0, 1000000000);

        if (books.deleteAt(pos)) {
            System.out.println("Book at position: " + pos + " is deleted");
        } 
    }

    void sortBooks(boolean byCode) {
        if (books.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        books.sortBooks(byCode);
    }


}
