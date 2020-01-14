/* Wonhyuk Jang
 * masc0812
 */
package data_structures;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

public class RedBlackTree<K,V> implements DictionaryADT<K,V> {
	TreeMap<K,V> obj;
	public RedBlackTree() {
		obj = new TreeMap<K,V>();
	}

	// Returns true if the dictionary has an object identified by
	// key in it, otherwise false.
	public boolean contains(K key) {
		return obj.containsKey(key);
	}

	// Adds the given key/value pair to the dictionary.  Returns 
	// false if the dictionary is full, or if the key is a duplicate.
	// Returns true if addition succeeded.
	public boolean add(K key, V value){
		if(obj.containsKey(key))
			return false;
		obj.put(key, value);
		return true;
	}

	// Deletes the key/value pair identified by the key parameter.
	// Returns true if the key/value pair was found and removed,
	// otherwise false.
	public boolean delete(K key){
		V val = obj.remove(key);
		if(val != null)
			return true;
		return false;
	}

	// Returns the value associated with the parameter key.  Returns
	// null if the key is not found or the dictionary is empty.
	public V getValue(K key){
		if(obj.isEmpty())
			return null;
		return obj.get(key);
	}

	// Returns the key associated with the parameter value.  Returns
	// null if the value is not found in the dictionary.  If more 
	// than one key exists that matches the given value, returns the
	// first one found. 
	public K getKey(V value){
		for(Entry<K, V> set : obj.entrySet()){
			if(value.equals(set.getValue())) {
				return set.getKey();
			}
		}
		return null;
	}

	// Returns the number of key/value pairs currently stored 
	// in the dictionary
	public int size(){
		return obj.size();
	}

	// Returns true if the dictionary has a max capacity and is at max capacity
	public boolean isFull(){
		return false;
	}

	// Returns true if the dictionary is empty
	public boolean isEmpty(){
		if(obj.size() <= 0)
			return true;
		return false;
	}

	// Returns the Dictionary object to an empty state.
	public void clear(){
		obj.clear();
		
	}

	// Returns an Iterator of the keys in the dictionary, in ascending
	// sorted order.  The iterator must be fail-fast.
	public Iterator<K> keys() {
		return obj.keySet().iterator();
	}

	// Returns an Iterator of the values in the dictionary.  The
	// order of the values must match the order of the keys. 
	// The iterator must be fail-fast. 
	public Iterator<V> values(){
		return obj.values().iterator();
	}
	/* public static void main(String args[]){
		RedBlackTree<String,Double> tab  = new RedBlackTree<String,Double>();
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
		


		
		
		
		//System.out.println(valuesIter.next());
		//syste...print( keysIter.next());		
	} */
	
}
