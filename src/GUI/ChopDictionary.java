package GUI;

import java.text.MessageFormat;
import java.util.*;

public class ChopDictionary<T> {
    protected ArrayList<DoubleArray<T>> list;
    
    public ChopDictionary(Map<String, T> dictionary){
    	this.list = new ArrayList<DoubleArray<T>>(); 
        this.populate(dictionary);
        
    }
    private void populate(Map<String, T> dictionary){
    	
    	DoubleArray<T> doubleArray = new DoubleArray<T>(); 
    	this.list.add(doubleArray);
    	for (String key: dictionary.keySet()){
    		doubleArray.add(dictionary.get(key));
    		if (doubleArray.isFull()){
    			doubleArray = returnNode(); 
    			this.list.add(doubleArray);   //the same idea of current in linked-list 
    			System.out.println(doubleArray);
    		} 
    	}
    }
    private DoubleArray<T> returnNode(){
    	return new DoubleArray<T>(); 
    }
    public ArrayList<DoubleArray<T>> getList(){
    	return this.list; 
    }
    public String toString(){
    	String str = ""; 
    	for (DoubleArray<T> node: this.list){
    		str += node.toString(); 
    	}
    	return str; 
    }
//    public static void main(String[] args){
//    	Map dictionary = new HashMap<String, Integer>(); 
//    	dictionary.put("Sahair", 20); 
//    	dictionary.put("Anthony", 18); 
//    	dictionary.put("Steven", 18); 
//    	dictionary.put("Sam", 16); 
//    	dictionary.put("Arvind", 12); 
//    	dictionary.put("Shahin", 15); 
//    	dictionary.put("Shit" , 14); 
//    	ChopDictionary c = new ChopDictionary(dictionary); 
//        System.out.println(c.getList());
//    }
    
}
