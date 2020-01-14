/*  Tester program for CS310 SP 2015 Program #1
    Alan Riggins
*/    

import data_structures.*;

import java.util.Iterator;

public class P1Tester {
    private LinearListADT<Integer> list;
    
    public P1Tester() {
        list = new ArrayLinearList<Integer>();    
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
        if(list.remove(new Integer(100)) != null)
            System.out.println("ERROR, found element not in list!");
        System.out.println("List should be empty, nothing should print below\n\n"); 
        for(int x : list)
            System.out.print(x + " ");    
        
              
        list.clear();
        
        for(int i = 0; i < 10; i++)
        	list.addFirst(new Integer(i));
        
        for(int i = 0; i < 10; i++)
        	System.out.println(list.removeFirst());
        
        
        System.out.println("Now inserting 100k elements in position #1\n" +
            "Testing the resize and shift operations\n" +
            "Times will vary, about 30 seconds on rohan, " +
            "3 secs on my office machine.");
        long start, stop;
        int n = 10000;
        int counter = 5000;
        for(int i=0; i < 20; i++) {
            list.clear();
            // build the structure
            for(int j=0; j < n; j++)
                list.addLast(j);
            
            // now time
            start = System.currentTimeMillis();
            for(int k=n; k < n + counter; k++) {
                list.addLast(k);
                list.removeLast();
                }
            stop = System.currentTimeMillis();
            System.out.println("Time with n = " + n + 
               " is: " + (stop-start) + " milliseconds");
            n += 10000;
           
            }     
       /* for(int i = 0; i < 40000; i++)
        	list.addFirst(i);
        long start = System.currentTimeMillis();
        for(int i = 0; i < 40000; i++)
        	list.remove(new Integer(i));
        long stop = System.currentTimeMillis();
        System.out.println("Operation took " + ((stop-start)) + "mili seconds");*/

       /* for(int i=0; i < 20; i++) {
            list.clear();
            // build the structure
            for(int j=0; j < n; j++)
                list.addLast(j);
            
            // now time
            start = System.currentTimeMillis();
            for(int k=0; k < 5000; k++) {
                list.addFirst(k);
                list.remove(list.size());
                }
            stop = System.currentTimeMillis();
            System.out.println("Time with n = " + n + 
               " is: " + (stop-start) + " milliseconds");
            n += 10000;
            }  

        int index = (int)Math.random() * (list.size()-1);
    	index = (index == 0)?1:index;


  

    for(int i=0; i < 10; i++) {
        list.clear();
        // build the structure
        for(int j=1; j <= n; j++)
            list.insert(j,index);
        
        // now time
        start = System.currentTimeMillis();
        for(int k=1; k <= 5000; k++) {
        	
        	list.insert(new Integer(k),index); 
        	list.removeFirst();
            }
        stop = System.currentTimeMillis();
        System.out.println("Time with n = " + n + 
           " is: " + (stop-start) + " milliseconds");
        //n += 10000;
        }  */
        System.out.println("Now removing them all.");
        System.out.println("size = "+ list.size());

        
    	System.out.println("Now removing them all.");
    	System.out.println("size = "+ list.size());
        System.out.println("Now removing them all.");
      
        /*while(!list.isEmpty()) {
            list.removeLast();
            counter++;
            }*/
        System.out.println("Done.");
        }
        
    public static void main(String [] args) {
        try {
            new P1Tester();
            }
        catch(Exception e) {
            System.out.println("ERROR " + e);
            e.printStackTrace();
            }
        }
    }