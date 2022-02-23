package com.demo.client;

import com.demo.dao.EmployeeDao;
import com.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;


public class EmployeeClient {
  
    public static void main(String args[]) {
    

        Set<String> h = new LinkedHashSet();
        h.add("Apple");    //Adding elements  
        h.add("Banana");      
        h.add("Cat");     
        h.add("Dog");    
        h.add("Elephant"); 

        LinkedHashSet<String> set1 = new LinkedHashSet<>();
        set1.add("A");
        set1.add("B");
        set1.add("C");
        set1.add("D");
        set1.add("E");

        Iterator<String> i=h.iterator();  // Traversing objects  
        while(i.hasNext())  // Return true if the Scanner has another token input  
        {    
        System.out.println(i.next()); // printing elements   
        }    

         for(String b :set1){
					System.out.println(b);
    }   	
         
    
    }
}
