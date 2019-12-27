
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LeAnh
 */
public class Main {

    public static void main(String[] args) {
        boolean keepWorking = true;
        BookList bList = new BookList();
        while (keepWorking) {
            switch (bList.intReader("---------------------------------" 
                    + "\nBook List"
                    + "\n1.Input Book and add to the end"
                    + "\n2.Display books"
                    + "\n3.Search by code"
                    + "\n4.Input Book and add to beginning"
                    + "\n5.Add Book after position k"
                    + "\n6.Delete Book at position k"
                    + "\n7.Sort books by Name"
                    + "\n8.Sort books by Price"
                    + "\n9.Import Books"
                    + "\n10.Save Books"
                    + "\n0.Exit"
                    + "\nYour choice: ", 0, 10)) {

                case 1:
                    bList.addLast();
                    break;
                case 2:
                    bList.list();
                    break;
                case 3:
                    bList.search();
                    break;
                case 4:
                    bList.addFirst();
                    break;
                case 5:
                    bList.addAfter();
                    break;
                case 6:
                    bList.deleteAt();
                    break;
                case 7:
                    bList.sortBooks(true);
                    break;
                case 8:
                    bList.sortBooks(false);
                    break;
                case 9:
                    bList.importData();
                    break;
                case 10:
                    bList.saveBooks();
                    break;
                case 0:
                    keepWorking = false;
                    System.out.println("Exiting program");
                    break;
                default:
                    break;
            }

        }
    }
}
