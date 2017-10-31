/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.examen2.problema2;

import java.util.Iterator;

/**
 * Clase que implementa la lista
 * @author JoseCarlos
 */
public class Lista implements List{
        private final Object[] arrayElementos; 
        private int tamaño;
        private final int TAMAÑO_INICIAL = 1; 
    public Lista(){
      arrayElementos = new Object[TAMAÑO_INICIAL]; 
      tamaño = 0;
    }
    @Override
    public int size() {
        return tamaño;
    }

    @Override
    public boolean isEmpty() {
        return arrayElementos.length == 0;
    }
    
    @Override
    public Object get(int i) {
        if (i >= tamaño || i < 0) {
            throw new IndexOutOfBoundsException("Indice Incorrecto");
        }
        return arrayElementos[i];
    }

    @Override
    public Object set(int i, Object e) {
        if (i >= tamaño || i < 0) {
            throw new IndexOutOfBoundsException("Indice Incorrecto");
        }
        return arrayElementos[i] = e;
    }
/**
 * 
 * Agrega en un índice escogido
 */
    @Override
    public void add(int i, Object e) {
        if(i>tamaño || i<0){
            throw new IndexOutOfBoundsException("Indice Incorrecto");
        }
        if(i+1 == arrayElementos.length){
            Object[] arrayAmpliado = new Object[arrayElementos.length * 2];
            System.arraycopy(arrayElementos, 0, arrayAmpliado,0, tamaño);
        }
        if(i < tamaño){
            System.arraycopy(arrayElementos, i, arrayElementos, i+1, tamaño-1);
            
        }
        arrayElementos[i] = e;
        tamaño++;
    }

    @Override
    public Object remove(int i) throws IndexOutOfBoundsException {
        if(i>tamaño || i<0){
            throw new IndexOutOfBoundsException("Indice Incorrecto");
        }
        Object e = arrayElementos[i];
        System.arraycopy(arrayElementos, i+1, arrayElementos, i, tamaño-i+1);
        arrayElementos[tamaño-1] = null;
        tamaño--;
        return e;
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
