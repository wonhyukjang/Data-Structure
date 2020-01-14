import data_structures.*;

import java.util.Iterator;

public class P2Tester2 {
    private LinearListADT<Integer> list;
    
    public P2Tester2() {
        list = new LinearList<Integer>();    
        runTests();
        }
        
    private void runTests() { 
        for(int i=1; i <= 10; i++)
            list.addLast(i);         
        System.out.println("Should print 1 .. 10"); 
        for(int x : list)
            System.out.print(x + " ");
        System.out.println("\n");                   
          
        System.out.println("Now removing them all");           
        for(int i=1; i <= 10; i++)
            list.remove(new Integer(i));  
            
        System.out.println("Current size of list: (should be zero) " 
            + list.size()); 
        
        for(int i=1; i <= 10; i++)
            list.addFirst(new Integer(i));  
        System.out.println("Current size of list: (should be 10)" 
            + list.size()); 
            
        System.out.println("Now using the iterator, should print "
            + "10 .. 1");            
        for(int x : list)
            System.out.print(x + " ");
        System.out.println();
        System.out.println("Current size of list: (should be 10) " + list.size());
        System.out.println(); 
        
        for(int i=1; i <= 10; i++)
            list.removeFirst();
        System.out.println("List should be empty, nothing should " +
            "print below\n\n"); 
        for(int x : list)
            System.out.print(x + " ");
            
        list.addFirst(new Integer(25));
        list.addLast(new Integer(50));
        list.insert(new Integer(75), 2);
        System.out.println("Should print 25 75 50");
        for(int x : list)
            System.out.print(x + " ");
        System.out.println();
        if(!list.contains(75))
            System.out.println("ERROR, element in list not found!"); 
        if(!list.contains(25))
            System.out.println("ERROR, element in list not found!"); 
        if(!list.contains(50))
            System.out.println("ERROR, element in list not found!");                                
        list.remove(new Integer(75));  
        list.remove(new Integer(50));
        list.remove(new Integer(25)); 
        if(list.remove(new Integer(1000000)) != null)
            System.out.println("ERROR, found element not in list!");
        System.out.println("List should be empty, nothing should print below\n\n"); 
        for(int x : list)
            System.out.print(x + " ");        
              
        list.clear();
        System.out.println("Now inserting 100k elements in position #1\n" +
            "Testing the resize and shift operations\n" +
            "Times will vary, about 30 seconds on rohan, " +
            "3 secs on my office machine.");
        long start = System.currentTimeMillis();
        for(int i=1; i <= 100000; i++)
            list.addFirst(new Integer(i));
        long stop = System.currentTimeMillis();
        System.out.println("Operation took " + ((stop-start)/1000) + " seconds");
        System.out.println("Now removing them all.");
        int counter = 100000;

        while(!list.isEmpty()) {
            list.removeFirst();
            counter++;
            }
        System.out.println(counter);
        System.out.println("Done.");
        System.out.println(list.size());
        for(int x = 1; x < 10; x++){
        	list.insert(new Integer(x), x);
    		System.out.print(list.get(x) + " ");
        }
        list.insert(new Integer(1), 2);
		System.out.println();
		System.out.println(list.size());
		while(!list.isEmpty()){
			System.out.print(list.removeFirst() + " ");
		}
		System.out.println(list.isEmpty());
        System.out.println();
        System.out.println(list.size());        

        list.addLast(2);
        list.addLast(4);
   
        System.out.println(list.locate(1));
        System.out.println(list.isEmpty());
        System.out.println(list.remove(new Integer(4)));
        for(int x : list)
        	System.out.print(x + " ");
        System.out.println();
        System.out.println(list.size());
        list.addLast(3);
        list.addFirst(1);
        
        System.out.println(list.size());
        System.out.println(list.size());

    }
    public static void main(String [] args) {
        try {
            new P2Tester2();
            }
        catch(Exception e) {
            System.out.println("ERROR " + e);
            e.printStackTrace();
            }
        }
    }
    

