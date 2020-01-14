package data_structures;

import java.util.Iterator;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayLinearList <E> implements LinearListADT<E> {
	
	private E[] list;
	private int currentSize, maxSize;
	
	public ArrayLinearList() {
		currentSize = 0;
		maxSize = DEFAULT_MAX_CAPACITY;
		list = (E[]) new Object[maxSize + 1];	
	}
	
	@Override
	public void addLast(E obj) {
		// TODO Auto-generated method stub
		if(currentSize == maxSize)
			   resizeArray();		   
		   list[++currentSize] = obj;		
	}
	
	public void resizeArray() {
		maxSize *= 2;
		E[] tmp = (E[]) new Object[maxSize + 1];
		for(int i = 1; i < currentSize + 1; i++)
			tmp[i] = list[i];
		
		list = tmp;
	}
	
	public void resizeArray2(E[] list) {
		maxSize /= 2;
		E[] tmp = (E[]) new Object[maxSize + 1];
		for(int i = 1; i < currentSize + 1; i++)
			tmp[i] = list[i];
		list = tmp;
	}

	@Override
	public void addFirst(E obj) {
		// TODO Auto-generated method stub
		if(currentSize == maxSize)
			resizeArray();
		for(int i = currentSize; i > 0; i--)
			list[i + 1] = list[i];
		list[1] = obj;
		currentSize++;
	}

	@Override
	public void insert(E obj, int location) {
		// TODO Auto-generated method stub
		if(currentSize == maxSize)
			resizeArray();
		if(location > size() + 1)
			throw new RuntimeException();
		if(list[location] == null) {
			list[location] = obj;
			currentSize++;
		}
		else {
			for(int i = currentSize; i >= location; i--) 
				list[i + 1] = list[i];
			list[location] = obj;
			currentSize++;
		}
	}

	@Override
	public E remove(int location) {
		// TODO Auto-generated method stub
		E tmp;
		if(list[location] == null)
			throw new RuntimeException();
		else {
			tmp = list[location];
			if(size() <= 0.25 * maxSize)
				resizeArray2(list);
			for(int i = location; i < currentSize; i++)
				list[i] = list[i + 1];
			currentSize--;
		}
		return tmp;
	}

	@Override
	public E remove(E obj) {
		// TODO Auto-generated method stub
		
		int j = locate(obj);
    	if(isEmpty())
    		return null;
    	return remove(j);	
	}

	@Override
	public E removeFirst() {
		// TODO Auto-generated method stub	
		return remove(1);
	}

	@Override
	public E removeLast() {
		// TODO Auto-generated method stub
		return remove(currentSize);
	}

	@Override
	public E get(int location) {
		// TODO Auto-generated method stub
		if(list[location] != null)
			return list[location];
		throw new RuntimeException();
	}

	@Override
	public boolean contains(E obj) {
		// TODO Auto-generated method stub
		for(int i = 1; i <= maxSize; i++)
			if(((Comparable<E>)obj).compareTo(list[i]) == 0)
				return true;
		return false;
	}

	@Override
	public int locate(E obj) {
		// TODO Auto-generated method stub
		for(int i = 1; i <= maxSize; i++)
    		if(((Comparable<E>)obj).compareTo(list[i]) == 0)
    			return i;
    	return -1;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		currentSize = 0;
		
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

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new IteratorH();
	}
	
	public class IteratorH<E> implements Iterator<E> {
			int iterIndex;
			public IteratorH() {
				iterIndex = 1;
			}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return iterIndex <= currentSize;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			if(!hasNext())
				throw new NoSuchElementException();
			return (E)(list[iterIndex++]);
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();	
		}
	};
}
