package data_structures;

import java.util.Iterator;

public class Stack <E> implements Iterable<E>{
	private LinearListADT<E> list;
	
	public Stack() {
		list = new LinearList<E>();
	}
	
	 public void push(E obj) { //Put object in the stack
		 list.addFirst(obj);
	 }
	    public E pop() { //Take object, inserted last, out of from the stack
	    	return list.removeFirst();
	    }        
	    public int size() { 
	    	return list.size();
	    }
	    public boolean isEmpty() { //Check stack is empty
	    	return list.isEmpty();
	    }
	    public E peek() { //Check the last object inserted into the stack
	    	E obj = list.get(1);
	    	return obj;
	    	
	    }
	    public boolean contains(E obj) {
	    	return list.contains(obj);
	    }   
	    public void makeEmpty() {
	    	list.clear();
	    }
	    public Iterator<E> iterator() {
	    	return list.iterator();
	    }        
	    /*public String toString(){
	    	String s = "";
	    	for( E obj: this)
	    		s = s+"   "+obj;
	    	return s;
	    	
	    }*/
	}    
