/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;

/**
 *
 * @author LeAnh
 * @param <T>
 */
public class Node<T> implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private   T data;
    private  Node<T> next;
    private  Node<T> previous;

    public Node(T data) {
        this.data = data;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * @return the next
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * @return the previous
     */
    public Node<T> getPrevious() {
        return previous;
    }

    /**
     * @param previous the previous to set
     */
    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

}
