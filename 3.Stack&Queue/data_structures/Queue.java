package data_structures;

import java.util.Iterator;

public class Queue<E> implements Iterable<E> {
	private LinearListADT<E> list;
	
	public Queue() {
		list = new LinearList<E> ();
	}
	
		public void enqueue(E obj) { //Put object onto the queue
			list.addLast(obj);
		}   
		public E dequeue() { //Take object, inserted first, out of from the queue
			return list.removeFirst();
		}   
	    public int size() {
	    	return list.size();
	    }
	    public boolean isEmpty() {
	    	return list.isEmpty();
	    }
	    public E peek() { //Check the object inserted first
	    	E obj = list.get(1);
	    	return obj;
	    }
	    public boolean contains(E obj) {
	    	return list.contains(obj);
	    }
	    public void makeEmpty() {
	    	list.clear();
	    } 

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return list.iterator();
	}

}
