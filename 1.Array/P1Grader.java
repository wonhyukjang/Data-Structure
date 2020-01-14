/*  Grader program for CS310 SP 2015 Program #1
    Alan Riggins
*/    

import data_structures.*;
import java.util.Iterator;

public class P1Grader {
    private LinearListADT<Person> list;
    private LinearListADT<Integer> list2;    
    private String [] fnames = {"John", "Robert", "Sam", "Henry", "Bill", "William",
    "Alan","Cyril","Gregory","Dennis","David","James","Joseph","Jerome","Gary"};
    private String [] lnames = {"Doe","Jones","Roberts","Shapiro","Martinez","Perez",
    "Wong","Nguyen","White","Bell","Brown","Black","Green","Trenton","Nagy"};
    private Person [] fullName;
    private Person sentinal = new Person("Aaron Burr");
    
    public P1Grader() {
        fullName = new Person[100];        
        for(int i=0; i < 100; i++) {
            int ind1 = (int) (15.0f*Math.random());
            int ind2 = (int) (15.0f*Math.random());                                    
            fullName[i] = new Person(fnames[ind1] + " " + lnames[ind2]);
//System.out.println(fullName[i]);    
            }        
        list = new ArrayLinearList<Person>();   
        runTests();
        }
        
    private void runTests() { 
        for(int i=1; i <= 5; i++)
            list.addFirst(fullName[i]);         
        for(Person x : list)
            System.out.print(x + " \n");
        System.out.println("\n");       
        list.clear();
try {        
        for(int i=0; i < 100; i++)
            list.addFirst(fullName[i]);
        

        

        for(int i=99; i >= 0; i--)
            if(((Comparable<Person>)fullName[i]).compareTo(list.removeFirst()) != 0)
                System.out.println("ERROR, elements don't match");
}
catch(Exception e) {
    e.printStackTrace();
    }                
        list.clear();
try {        
        for(int i=0; i < 100; i++)
            list.insert(fullName[i], (i+1));
        for(int i=0; i <100; i++)
            if(((Comparable<Person>)fullName[i]).compareTo(list.removeFirst()) != 0)
                System.out.println("ERROR, elements don't match"); 
}
catch(Exception e) {
    e.printStackTrace();
    }                
        list.clear();
try {        
        for(int i=0; i < 100; i++)
            list.addLast(fullName[i]);

        
        for(int i=1; i <= 100; i++)
            if(((Comparable<Person>)fullName[i-1]).compareTo(list.removeFirst()) != 0)
                System.out.println("ERROR, elements don't match");  
}
catch(Exception e) {
    e.printStackTrace();
    }                
        if(list.size() != 0)
            System.out.println("Size wrong, should be zero but is " + list.size()); 
            
        list.clear();
        for(int i=0; i < 100; i++)
            list.addFirst(fullName[i]); 
        
        try {
            list.insert(new Person("Foobar"), 102);
            }
        catch(RuntimeException e) {}
        try {
            list.insert(new Person("Foobar"), 0);
            }
        catch(RuntimeException e) {}     
        try {   
            if(list.locate(new Person("Foobar")) != -1)  
                System.out.println("ERROR, found an invalid element"); 
            }
        catch(RuntimeException e) {}
        catch(Exception e) {
            System.out.println("ERROR, failed with negative location "+e);
            
            }
            
        // did they use equals or == ?
        list.addFirst(sentinal);
        if(list.locate(new Person("Aaron Burr")) != 1)
            System.out.println("ERROR, could not find valid element");

//===========
        list2 = new ArrayLinearList<Integer>();
        for(int i=1; i < 50; i++)
            list2.addLast(new Integer(i));
            
        // this will remove all the even numbers, only the odds print below.
        for(int i=2; i <= 25; i++)
            list2.remove(i);  
            
        for(Integer x : list2)
            System.out.print(x+" ");
        System.out.println();                                      
                    
            System.out.println("Done.");  
                              
        }
        
    public static void main(String [] args) {
        try {
            new P1Grader();
            }
        catch(Exception e) {
            System.out.println("ERROR: " + e);
            e.printStackTrace();
            }
        }
        
        
// implements Comparable but NOT equals        
    class Person implements Comparable<Person> {
        String name;
        
        public Person(String n) {
            name = n;
            }
            
        public String getName() {
            return name;
            }
            
        public int compareTo(Person p) {
            return name.compareTo(p.name);
            }
            
        public String toString() {
            return name;
            }
        }
    }