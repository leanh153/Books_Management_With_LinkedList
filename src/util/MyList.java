/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.Book;
import java.io.Serializable;

/**
 *
 * @author LeAnh
 */
public class MyList implements Serializable {

    private static final long serialVersionUID = 1L;

    private Node<Book> head, tail;
    private int size;

    public MyList() {
        head = null;
        tail = null;
        size = 0;
    }

    //check if the list is empty or not
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param b book info to be added
     * @return added book
     */
    public Node<Book> addLast(Book b) {

        Node<Book> newNode = new Node<>(b);
        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
            return head;
        }
        newNode.setPrevious(tail);
        tail.setNext(newNode);
        tail = newNode;
        size++;
        return tail;
    }

    /**
     * @param b book info to be added
     * @return added book
     */
    public Node<Book> addFirst(Book b) {

        // allocate node and put in data
        Node<Book> newNode = new Node<>(b);
        // if there aren't any node in list then head and tail is at the same
        // node
        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
            return head;
        }
        // set next node of new node is head
        newNode.setNext(head);
        // set Previous of the current head
        head.setPrevious(newNode);
        // move head to the next node
        head = newNode;
        // add size by one
        size++;
        return head;
    }

    /**
     * output information of all books in the list
     */
    public void traverse() {

        Node<Book> current = head;
        if (current != null) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println(String.format("%-10s%-20s%-10s%-10s%-10s%-10s", "Code", "Title",
                    "Quantity", "Lended", "Price", "Value"));
            while (current != null) {
                System.out.println(current.getData().toString());
                current = current.getNext();

            }
            System.out.println("-------------------------------------------------------------------");

        }
    }

    /**already counted
     * @return number of node
     */
    public int size() {
        return size;
    }

    /**count number of the node in list
     * @return number of node*/
    public int getSize() {
        size = 0;
        Node<Book> current = head;
        while (current != null) {
            current = current.getNext();
            size++;
        }

        return size;
    }

    //return a Node at position k, starting position is 0
    public Node<Book> getNode(int k) {

        Node<Book> current = head;
        int pos = 0;
        while (current != null) {
            if (pos == k) {
                return current;
            }
            current = current.getNext();
            pos++;

        }
        return null;
    }

    /**
     * @param k add a new book after this
     * @param b is new book to be added
     * @return book has added
     */
    public Node<Book> addAfter(Book b, int k) {

        Node<Book> current = head;
        int pos = 0;
        while (current != null) {
            if (pos == k) {
                Node<Book> newNode = new Node<>(b);
                newNode.setNext(current.getNext());
                newNode.setPrevious(current);
                current.getNext().setPrevious(newNode);// this link fisrt
                current.setNext(newNode); // then this
                // add node after tail
                if (current == tail) {
                    tail = newNode;
                    
                }
                // return node at position k + 1 to test if b's inserted,yet
                size++;
                return getNode(k + 1);
            }
            current = current.getNext();
            pos++;

        }
        return null;
    }

    /**
     * @param k where element located
     * @return true delete successfully
     */
    public boolean deleteAt(int k) {
        if (head == null) {
            System.out.println("List of Books are empty");
            return false;
        }

        if (k < 0 || k > (size - 1)) {
            System.out.println("Input out of bound!");
            return false;
        }

        Node<Book> current = head;
        int pos = 0;

        while (current != null) {
            // delete at head
            if (k == 0) {
                head = current.getNext();
                size--;
                return true;
            }
            if (pos == k) {
                Node<Book> next = current.getNext();
                Node<Book> previous = current.getPrevious();

                // delete at tail
                if (next == null) {
                    tail = previous;
                    tail.setNext(null);
                    size--;
                    return true;
                }

                // other position
                previous.setNext(next);
                next.setPrevious(previous);
                current.setData(null);
                size--;
                return true;
            }
            current = current.getNext();
            pos++;

        }

        return false;
    }

    //search a Node by a given book code
    public Node<Book> search(String bCode) {

        Node<Book> current = head;

        while (current != null) {
            if (current.getData().getbCode().equalsIgnoreCase(bCode)) {
                return current;
            }
            current = current.getNext();

        }
        return null;
    }

    /**
     * Using insertion sort algorithm moving node data of pointer only not the
     * node position
     *
     * @param byCode sort by book's name ascending(ignore case), else by price
     * ascending
     */
    public void sortBooks(boolean byCode) {
        // current start from the second element
        Node<Book> current = head.getNext();
        while (current != null) {
            // store nextCurrent for next interation
            Node<Book> nextCurrent = current.getNext();
            // pointer 
            Node<Book> pointer = current.getPrevious();
            // store currentData to move this data to the right position
            Book currentData = current.getData();

            // if no move so no change tempPointer data
            boolean moved = false;
            // this is where current data move to
            Node<Book> tempPointer = pointer;
            while (pointer != null && (byCode
                    ? (pointer.getData().getbCode()).compareToIgnoreCase(currentData.getbCode()) > 0
                    : pointer.getData().getPrice() > currentData.getPrice())) {

                // assign pointer before it changes
                tempPointer = pointer;
                // move data of pointer to the next
                pointer.getNext().setData(pointer.getData());
                moved = true;
                // get previous pointer node to compare nextCurrent
                pointer = pointer.getPrevious();
            }

            // change data of node which is last element have value greate than
            // value of current
            if (moved) {
                tempPointer.setData(currentData);
            }
            // change current to next
            current = nextCurrent;

        }
        traverse();
    }

}
