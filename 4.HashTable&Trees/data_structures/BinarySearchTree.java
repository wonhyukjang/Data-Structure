/* Wonhyuk Jang
 * masc0812
 */
package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import data_structures.HashTable.DictionaryNode;
import data_structures.HashTable.IteratorHelper;
import data_structures.HashTable.KeyIteratorHelper;
import data_structures.HashTable.ValueIteratorHelper;

public class BinarySearchTree<K,V> implements DictionaryADT<K,V> {
	private class Node<K,V> {
		public K key;
		public V value;
		public Node<K,V> leftChild;
		public Node<K,V> rightChild;
		public Node(K k, V v) {
			key = k;
			value = v;
			leftChild = rightChild = null;
		}
	}
	private Node<K,V> root;
	private int currentSize;
	private int modCounter;
	public BinarySearchTree() {
		root = null;
		currentSize = 0;
	}


	public boolean contains(K key) {
		if(root == null)
			return false;
		return getValue(key) != null;
		// TODO Auto-generated method stub
	}



	public boolean add(K key, V value) {
		if (contains(key))
			return false;
		if(root == null)
			root = new Node<K,V>(key,value);
		else
			insert(key,value,root,null,false);
		currentSize++;
		modCounter++;
		return true;	
		// TODO Auto-generated method stub

	}
	private void insert(K k, V v, Node<K,V> n, Node<K,V> parent, boolean wasLeft) {
		if(n == null) {
			if(wasLeft) 
				parent.leftChild = new Node<K,V>(k,v);
			else 
				parent.rightChild = new Node<K,V>(k,v);
		}
		else if(((Comparable<K>)k).compareTo((K)n.key) < 0)
			insert(k,v,n.leftChild,n,true);
		else
			insert(k,v,n.rightChild,n,false);

	}


	public boolean delete(K key) {
		if(isEmpty()) return false;
		if(!remove(key,root,null,false))
			return false;
		currentSize--;
		modCounter++;
		return true;
		// TODO Auto-generated method stub
		//modCounter++;
	}
	private boolean remove(K k, Node<K,V> n, Node<K,V> parent, boolean wasLeft) {
		if(n == null)
			return false;
		int comp = (((Comparable<K>)k).compareTo(n.key));
		if(comp < 0)
			return remove(k,n.leftChild,n,true);
		else if(comp > 0)
			return remove(k,n.rightChild,n,false);
		else {
			if(n.leftChild == null && n.rightChild == null) {
				if(parent == null)
					root = null;
				else if(wasLeft)
					parent.leftChild = null;
				else
					parent.rightChild = null;
			}
			else if(n.leftChild == null) {
				if(parent == null)
					root = n.rightChild;
				else if(wasLeft)
					parent.leftChild = n.rightChild;
				else
					parent.rightChild = n.rightChild;
			}
			else if(n.rightChild == null) {
				if(parent == null)
					root = n.leftChild;
				else if(wasLeft)
					parent.leftChild = n.leftChild;
				else
					parent.rightChild = n.leftChild;
			}
			else {
				Node<K,V> tmp = getSuccessor(n.rightChild);
				if(tmp == null) {
					n.key = n.rightChild.key;
					n.value = n.rightChild.value;
					n.rightChild = n.rightChild.rightChild;
				}
				else{
					n.key = tmp.key;
					n.value = tmp.value;
				}
			}
			return true;
		}
	}
	private Node<K,V> getSuccessor(Node<K,V> n) {
		Node<K,V> parent = null;
		while(n.leftChild != null) {
			parent = n;
			n = n.leftChild;
		}
		if(parent == null)
			return null;
		else
			parent.leftChild = n.rightChild;
		return n;
	}


	public V getValue(K key) {
		// TODO Auto-generated method stub
		return find(key, root);
	}
	private V find(K key, Node<K,V> n) {
		if(n == null){
			return null;
		}
		if(((Comparable<K>)key).compareTo(n.key) < 0) {
			//System.out.println(key + " " + n.key);
			return find(key,n.leftChild);
		
		}
		
		if(((Comparable<K>)key).compareTo(n.key) > 0)
			return find(key,n.rightChild);
		return (V) n.value;
	}

	// Returns the key associated with the parameter value.  Returns
	// null if the value is not found in the dictionary.  If more 
	// than one key exists that matches the given value, returns the
	// first one found. 
	public K getKey(V value) {
		Iterator<V> val= values();
		Iterator<K> key= keys();
		while(val.hasNext()){
			K tempKey = key.next();
			V tempVal = val.next();
			if(((Comparable<V>) tempVal).compareTo(value) == 0)
				return tempKey;
		}
		return null;	
	}



	public int size() {
		// TODO Auto-generated method stub
		return currentSize;
	}


	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size() <= 0;
	}


	public void clear() {
		root = null;
		currentSize = 0;
		// TODO Auto-generated method stub
	}
	abstract class IteratorHelper<E> implements Iterator<E>{
		protected Node<K,V>[] nodes;
		protected int idx = 0;
		protected int idx2;
		protected long modCheck;
		private Node[] inOrder(Node<K,V> n) {
			if(n != null) {
				inOrder(n.leftChild);
				nodes[idx++] = n;
				inOrder(n.rightChild);
			}
			return nodes;
		}
		public IteratorHelper() {
			nodes = new Node[currentSize];
			idx = 0;
			int j = 0;
			idx2 = 0;
			modCheck = modCounter;
			for(Node<K,V> n : nodes)
				nodes[j++] = n;
			nodes = inOrder(root);
		}
		public boolean hasNext() {
			if(modCheck != modCounter)
				throw new ConcurrentModificationException();
			return idx2 < currentSize;
		}
		public abstract E next();
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	class KeyIteratorHelper<K> extends IteratorHelper<K> {
		public KeyIteratorHelper() {
			super();
		}
		public K next() {
			return (K) nodes[idx2++].key;
		}
	}
	class ValueIteratorHelper<V> extends IteratorHelper<V> {
		public ValueIteratorHelper() {
			super();
		}
		public V next() {
			if(hasNext() == false)
				throw new NoSuchElementException();
			return (V) nodes[idx2++].value;
		}
	}
	public Iterator<K> keys() {
		// TODO Auto-generated method stub
		return new KeyIteratorHelper<K>();
	}


	public Iterator<V> values() {
		// TODO Auto-generated method stub
		return new ValueIteratorHelper<V>();
	}
	 public static void main(String args[]){
		BinarySearchTree<String,Double> tab  = new BinarySearchTree<String,Double>();
		//tab.add(key, value);	tab.add(key, value);
		tab.add("hi", 1.2);
		tab.add("hello", 3.4);
		tab.add("bye", 34.3);
		tab.add("bye",34.0);
		tab.delete("hi");
		for(int i = 0; i < 20; i++)
			tab.add(((Integer)i).toString(), 1.2+i);
		//System.out.println(tab.contains("1000"));
		//System.out.println(tab.contains("0"));
		//System.out.println(tab.getValue("hello"))
		System.out.println(tab.getKey(34.0));
		System.out.println(tab.contains("bye"));
		System.out.println(tab.contains("bhj"));
		System.out.println(tab.delete("bhj"));
		System.out.println(tab.size());
		System.out.println(tab.getValue(" "));
		System.out.println(tab.add("hello", 3.4));
		System.out.println(tab.add("hello", 4.5));
		System.out.println(tab.size());
		System.out.println(tab.contains("hello"));
		System.out.println(tab.getValue("hello"));
		System.out.println(tab.getValue(" "));
		for(int i = 120; i >= 0; i--)
			tab.add(Integer.toString(i),1.2 + i);
		System.out.println(tab.add("hi", 1.2));
		tab.add("hello", 34.3);
		tab.add("bye", 34.3);
		System.out.println(tab.add("hi", 3.3));
		System.out.println(tab.contains("0"));
		System.out.println(tab.contains("121"));
		System.out.println(tab.contains("bye"));
		System.out.println(tab.size());
		System.out.println(tab.isFull());
		System.out.println(tab.add("ho", 123.0));
		System.out.println(tab.delete("bye"));
		System.out.println(tab.delete(" 123"));
		System.out.println(tab.contains("bye"));
		System.out.println(tab.getValue("hello"));
		System.out.println(tab.getValue("hi"));
		System.out.println(tab.getValue(" "));
		System.out.println(tab.getKey(2.3));
		System.out.println(tab.contains("hi"));
		System.out.println(tab.getKey(34.3));
		System.out.println(tab.size());
		System.out.println(tab.isFull());
		tab.clear();
		System.out.println(tab.size());
		System.out.println(tab.getValue(" "));
		System.out.println(tab.getKey(2.3));

		Iterator<String> keysIter = tab.keys();
		Iterator<Double> valuesIter = tab.values();
		while(keysIter.hasNext())
			System.out.println(keysIter.next()+"   "+valuesIter.next() );
	} 
}
