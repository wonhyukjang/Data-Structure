/* Wonhyuk Jang
 * masc0812
 */
package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import data_structures.HashTable.DictionaryNode;
import data_structures.HashTable.IteratorHelper;
import data_structures.HashTable.KeyIteratorHelper;
import data_structures.HashTable.ValueIteratorHelper;

public class HashTable <K,V> implements DictionaryADT<K,V>{
	private LinearListADT<DictionaryNode<K,V>> [] list;
	private int maxSize;
	private int tableSize;
	private int currentSize;
	private long modCounter;
	public HashTable(int max) {
		maxSize = max;
		tableSize = (int) (max * 1.3f);
		list = new LinearList[(int) tableSize];
		for(int i = 0; i < tableSize; i++)
			list[i] = new LinearList<DictionaryNode<K,V>>();
	}
	class DictionaryNode<K,V> implements Comparable <DictionaryNode<K,V>> {
		K key;
		V value;
		public DictionaryNode(K k, V v) {
			key = k;
			value = v;
		}
		public int compareTo(DictionaryNode<K, V> node) {
			// TODO Auto-generated method stub
			return ((Comparable<K>)key).compareTo(node.key);
		}
	}
	
	private int getHashCode(K k) {
		return (k.hashCode() & 0x7FFFFFFF) %tableSize;
	}
	
	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		if(list[getHashCode(key)].contains(new DictionaryNode<K,V>(key,null)))
			return true;
		return false;
	}
	
	// Adds the given key/value pair to the dictionary.  Returns 
	// false if the dictionary is full, or if the key is a duplicate.
	// Returns true if addition succeeded.
	
	@Override
	public boolean add(K key, V value) {
		// TODO Auto-generated method stub
		if(isFull())
			return false;
		if(contains(key))
			return false;
		list[getHashCode(key)].addLast(new DictionaryNode<K,V>(key, value));
		currentSize++;
		modCounter++;
		return true;
	}
	
	// Deletes the key/value pair identified by the key parameter.
	// Returns true if the key/value pair was found and removed,
	// otherwise false.
	
	@Override
	public boolean delete(K key) {
		// TODO Auto-generated method stub
		DictionaryNode<K,V> tmp;
		int loc = list[getHashCode(key)].locate(new DictionaryNode<K,V>(key, null));
		if(loc == -1)
			return false;
		tmp = list[getHashCode(key)].get(loc);
		list[getHashCode(key)].remove(tmp);
		currentSize--;
		modCounter++;			
		return true;
	}
	
	// Returns the value associated with the parameter key.  Returns
	// null if the key is not found or the dictionary is empty.
	
	@Override
	public V getValue(K key) {
		// TODO Auto-generated method stub
		DictionaryNode<K,V> tmp;
		int loc = list[getHashCode(key)].locate(new DictionaryNode<K,V>(key, null));
		if(loc == -1)
			return null;
		tmp = list[getHashCode(key)].get(loc);
		return tmp.value;
	}
	@Override
	public K getKey(V value) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < tableSize; i++)
			for(DictionaryNode<K,V> node : list[i])
				if(((Comparable<V>)value).compareTo(node.value) == 0)
					return node.key;
				
		
		return null;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return currentSize;
	}
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return currentSize == maxSize;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return currentSize == 0;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		currentSize = 0;
		for(LinearListADT<HashTable<K, V>.DictionaryNode<K, V>> tempList: list)
			tempList.clear();
		modCounter=0;
	}
	abstract class IteratorHelper<E> implements Iterator<E>{
		protected DictionaryNode<K,V> [] nodes;
		protected int idx;
		protected long modCheck;
		
		private  DictionaryNode<K,V>[] shellSort(DictionaryNode<K,V> nodes[]) {
			HashTable<K, V>.DictionaryNode<K, V>[] n = nodes;
			HashTable<K, V>.DictionaryNode<K, V> temp;
			int in, out, h = 1;		
			int size = n.length;
			while(h <= size/3)
				h = h*3 + 1;
			while(h > 0) {
				for(out = h; out < size; out++) {
					temp = n[out];
					in = out;
					while(in > h-1 && ((Comparable<K>)n[in-h].key).compareTo(temp.key)>=0) {
						n[in] = n[in-h];
						in -= h;
					}
				n[in] = temp;
				}	
				h = (h-1) / 3;
			}
			return n;
		}
		
		public IteratorHelper() {
			nodes = new DictionaryNode[currentSize];
			idx = 0;
			int j = 0;
			modCheck = modCounter;
			for(int i = 0; i < tableSize; i++)
				for(DictionaryNode n : list[i])
					nodes[j++] = n;
			nodes = shellSort(nodes);
		}
		public boolean hasNext() {
			if(modCheck != modCounter)
				throw new ConcurrentModificationException();
			return idx < currentSize;
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
			return (K) nodes[idx++].key;
		}
	}
	class ValueIteratorHelper<V> extends IteratorHelper<V> {
		public ValueIteratorHelper() {
			super();
		}
		public V next() {
			return (V) nodes[idx++].value;
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
		HashTable<String,Double> tab  = new HashTable<String,Double>(123);
		//tab.add(key, value);
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
		System.out.println(tab.delete(" "));
		Iterator<String> keysIter = tab.keys();
		Iterator<Double> valuesIter = tab.values();
		while(keysIter.hasNext())
			System.out.println(keysIter.next()+"   "+valuesIter.next() );
		

	} 
	
}