package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

import data_structures.LinearList.IteratorH;
public class LinearList<E> implements LinearListADT<E> {
	
	private class Node<T> {
		T data;
		Node<T> next;
		
		public Node(T data) {
			this.data = data;
			this.next = null;
		}
	}
	private Node<E> head, tail;
	int currentSize;
	public LinearList() {
		head = tail = null;
		currentSize = 0;
	}
	
	@Override
	public void addLast(E obj) {
		// TODO Auto-generated method stub
		Node<E> newNode = new Node<E>(obj);
		if(head == null) {
			head = tail = newNode;
		}
		else {
			tail.next = newNode;
			tail = newNode;
		}
		currentSize++;
	}
	
	@Override
	public void addFirst(E obj) {
		// TODO Auto-generated method stub
		Node<E> newNode = new Node<E>(obj);
		if(head == null)
			head = tail = newNode;
		else {
			newNode.next = head;
			head = newNode;
		}
		currentSize++;		
	}
	@Override
	
//  Inserts the Object obj at the position indicated.  If there is an element at
//  that location, all elements from that location to the end of the list are 
//  shifted down to make room for the new insertion.  The location is one based.
//  If the location > size()+1 then a RuntimeException is thrown. List elements 
//  must be contiguous.
	
	public void insert(E obj, int location) {
		// TODO Auto-generated method stub
		
		Node<E> newNode = new Node<E> (obj);
		Node<E> previous = null, current = head;
		
		if(location == size() + 1) {
			if(current == null)
				head = tail = newNode;
			else {
				tail.next = newNode;
				tail = newNode;
			}		
		}
		else {
			E o = get(location);
			while(current != null && ((Comparable<E>)current.data).compareTo(o) != 0) {
				previous = current;
				current = current.next;
			}
			if(current == head) {
				newNode.next = head;
				head = newNode;
			}
			else {
				previous.next = newNode;
				newNode.next = current;
			}
		}
		if(location <= size() + 1)
			currentSize++;	
		else {
			 throw new RuntimeException();
		}
	}
	@Override
	
//  Removes the object located at the parameter location (one based).
//  Throws a RuntimeException if the location does not map to a valid position within the list.
	
	public E remove(int location) {
		// TODO Auto-generated method stub
		
		E obj = get(location);
		Node<E> previous = null, current = head;
		while(current != null && ((Comparable<E>)current.data).compareTo(obj) != 0) {
			previous = current;
			current = current.next;
		}
		if(current == null)
			throw new RuntimeException();
		if(current == head) 
			head = head.next;
		else if (current == tail) {
			previous.next = current.next;
			tail = previous;
		}
		else 
			previous.next = current.next;
		currentSize--;
		return obj;		
	}
	@Override
//  Removes and returns the parameter object obj from the list if the list contains it, null otherwise.
//  The ordering of the list is preserved.  The list may contain duplicate elements.  This method
//  removes and returns the first matching element found when traversing the list from first position.
	
	public E remove(E obj) {
		// TODO Auto-generated method stub
		Node<E> previous = null, current = head;
		while(current != null && ((Comparable<E>)current.data).compareTo(obj) != 0) {
			previous = current;
			current = current.next;
		}
		if(current == head)
			head = head.next;	
		else if(current == null)
			return null;
		else if(current == tail)
			tail = previous;
		else {
			previous.next = current.next;
		}
		currentSize--;		
		return obj;
	}
	@Override
	
//  Removes and returns the parameter object obj in first position in list if the list is not empty,  
//  null if the list is empty. The ordering of the list is preserved.
	public E removeFirst() {
		// TODO Auto-generated method stub
		
		if(head == null)
			return null;
		E current = head.data;
		if(head.next == null) {
			head = tail = head.next;
			currentSize--;
			return current;
		}
		else {
			head = head.next;
			currentSize--;
		}		
		return current;
	}
	@Override
	
//  Removes and returns the parameter object obj in last position in list if the list is not empty, 
//  null if the list is empty. The ordering of the list is preserved.
	
	public E removeLast() {
		// TODO Auto-generated method stub
		if(tail.data == null)
			return null;
		E obj = tail.data;
		Node<E> current = head, previous = null;
		while(((Comparable<E>)current.data).compareTo(obj) != 0) {
			previous = current;
			current = current.next;
		}
		if(current == head) {
			head = tail = head.next;
		}
		else {
			tail = previous;
			previous.next = current.next;
		}
		currentSize--;
		return obj;
	}
	@Override
	
//  Returns the parameter object located at the parameter location position (one based).
//  Throws a RuntimeException if the location does not map to a valid position within the list.
	
	public E get(int location) {
		// TODO Auto-generated method stub
		

		if(location > currentSize || location < 1)
			throw new RuntimeException();
		Node<E> current = head;
		int i = 1;
		
		while(i < location) {
			current = current.next;
			i++;
		}
		return current.data;		
	}
	@Override
	
//  Returns true if the parameter object obj is in the list, false otherwise.

	public boolean contains(E obj) {
		// TODO Auto-generated method stub
		if(locate(obj) == -1)
			return false; 
		return true;
	}
	@Override
	
//  Returns the one based location of the parameter object obj if it is in the list, -1 otherwise.
//  In the case of duplicates, this method returns the element closest to position #1.
	
	public int locate(E obj) {
		// TODO Auto-generated method stub
		Node<E> current = head;
		if(current == null)
			return -1;
		else {
			int i = 1;
			while(current != null && ((Comparable<E>)current.data).compareTo(obj) != 0) {
				current = current.next;
				i++;
			}
			if(current == null)
				return -1;
			return i;
		}
	}
	
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		currentSize = 0;
		head = tail = null;
		
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return currentSize == 0;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return currentSize;
	}

//  Returns an Iterator of the values in the list, presented in
//  the same order as the underlying order of the list. (position #1 first)
    public Iterator<E> iterator(){
    	return new IteratorH();
    }
    public class IteratorH implements Iterator<E>{
    		Node<E> iterptr;
	    	public IteratorH() {
	    		iterptr = head;
	    	}
    		 
    		public boolean hasNext(){//O(1)
    			if(iterptr == null)
    				return false;
    			if(iterptr == tail)
    				return true;
    			else
    				return iterptr.next != null;
    		}
    		public E next(){//O(1)
    			if(!hasNext())
    				throw new NoSuchElementException();
    			else {
    				E tmp = iterptr.data;
    				iterptr = iterptr.next;   			
    			return tmp;
    			}
    		}
    		public void remove(){
    			throw new UnsupportedOperationException();
    		}
    }
}
