/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.examen2.problema5;

/**
 *
 * @author JoseCarlos
 */
public class DynamicArrayStack implements Stack<Object>{
    private int tos ;
    private int size;
    private Object[] array;
    
    public DynamicArrayStack(final int size) {
    tos = -1;
    this.size = size;
    array = (Object[])new Object[size];
}
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (tos == -1);
    }
/**
 * Metodo que hace que crezca indenfinidamente la estructura 
 * @param e el objeto a agregar
 */
    @Override
    public void push(Object e) {
        if(isStackFull()) {
        Object[] array2 = (Object[])new Object[size * 2];
        System.arraycopy(array, 0, array2, 0, array.length);
        array = array2;
    }
    array[++ tos] = e;
    }

    @Override
    public Object top() {
        return array[tos];
    }

    @Override
    public Object pop() {
        return array[tos -- ];
    }

    @Override
    public boolean isStackFull() {
        return (tos == size - 1 );
    }
    
}
