/* Wonhyuk Jang
 * masc0812
 */
import java.util.Iterator;
import java.text.*;

import data_structures.*;

public class ProductLookup {
	private DictionaryADT<String,Double> dictionary; 

	// creates a new Dictionary with max capacity.
	// suitable for hashtable implementations
	public ProductLookup(int capacity) {
		dictionary = new HashTable<String,Double>(capacity);
	}

	// creates a new Dictionary with no max capacity.
	// suitable for tree implementations
	public ProductLookup() {
		dictionary = new BinarySearchTree<String,Double>();
	}        

	// inserts a new Product and its price
	public boolean insertSku(String sku, Double price) {
		return dictionary.add(sku, price);
	}

	// removes the key value pair that is identified by the key from the dictionary
	public boolean deleteSku(String sku) {
		return dictionary.delete(sku);


	}

	// looks up the price of an Product
	public Double getPrice(String sku) {
		return dictionary.getValue(sku);
	}

	// returns the first sku found with the given price, null otherwise
	public String getSku(Double price) {
		return dictionary.getKey(price);
	}

	// returns true if the sku is already in the dictionary
	public boolean containsSku(String sku) {
		return dictionary.contains(sku);
	}

	// returns all of the keys in the dictionary within the range start .. finish
	// inclusive, in sorted order. Neither value 'start' or 'finish' need be in the
	// dictionary.  Returns null if there are no keys in the range specified.    
	public String[] getRange(String start, String finish) {
		Iterator<String> keyIter = sku_s();
		int counter = 0;
		while(keyIter.hasNext()) {
			String current = keyIter.next();
			//  current > start  ||   current <= finish 
			if(((Comparable)current).compareTo(start) >= 0 && ((Comparable)current).compareTo(finish) <= 0) {
				counter++;
			}
		}
		String [] result = new String[counter];
		int i = 0;
		Iterator<String> keyIter2 = sku_s();
		while(keyIter2.hasNext()) {
			String current = keyIter2.next();
			//  current > start  ||   current <= finish 
			if(((Comparable)current).compareTo(start) >= 0 && ((Comparable)current).compareTo(finish) <= 0)
				result[i++]=current;
		}

			return result;
	}

	// returns an Iterator of the sku's (the keys) in the dictionary,
	// in sorted order.
	public Iterator<String> sku_s() {
		return dictionary.keys();
	}

	// returns the prices in the dictionary, in exactly the same order
	// as the sku_s() Iterator
	public Iterator<Double> prices() {
		return dictionary.values();
	}
	/* public static void main(String args[]) {
		double d = 29.95789;
        double e = 30;
        double f = 10;
        double z = 21;
        NumberFormat nf = NumberFormat.getCurrencyInstance();  
        ProductLookup test = new ProductLookup();
        for(int i = 0; i < 100; i++)
        	test.insertSku(Integer.toString(i), 2.3+i);
        test.insertSku("0",1.2);
        test.insertSku("1",2.2);
        test.insertSku("2",2.3);
        test.insertSku("3",4.3);
        test.insertSku("4",3.4);
        test.insertSku("5",5.6);
        test.insertSku("12",4.5);
        System.out.println(test.getSku(4.3));
        System.out.println(test.getPrice("12"));
        System.out.println(test.getPrice(" "));
        System.out.println(test.getSku(5.0));
        System.out.println(test.containsSku("5"));
        System.out.println(test.deleteSku("5"));
        System.out.println(test.containsSku("5"));
        
        for(String x : test.getRange("0","3"))
        	System.out.println(x);

        System.out.println(nf.format(d));
        System.out.println(nf.format(e));   
        
      //  while(keyIter.hasNext())
	  //		System.out.println(keyIter.next()+"   "+valueIter.next() );
		//System.out.println(valuesIter.next());
		//syste...print( keysIter.next()); 
	} */
}   