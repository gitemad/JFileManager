package Model;

import java.util.Stack;

public class SizedStack<T> extends Stack<T> {
    
	public static int maxSize;

    public SizedStack(int size) {
        super();
        maxSize = size;
    }
    
    public static void setMaxSize(int max) {
    	maxSize = max;
    }

    @Override
    public T push(T object) {
        //If the stack is too big, remove elements until it's the right size.
        while (this.size() >= maxSize) {
            this.remove(0);
        }
        return super.push(object);
    }
}