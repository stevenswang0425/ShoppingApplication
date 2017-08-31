package graph;

import java.util.*;

public class Queue<T> {
	private List<T> queue; 
	
    Queue(){
    	this.queue = new ArrayList<T>();
    	
    }
    
    public T pop(){
    	return this.queue.remove(0); 
    }
    
    public void add(T element){
    	this.queue.add(element); 
    }
    
    public boolean isEmpty(){
    	return this.queue.size() == 0; 
    }
}

