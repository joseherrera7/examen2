/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.examen2.problema4;

/**
 *
 * @author JoseCarlos
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaPosicional<E> implements PositionalList<E>, Iterable<E> {

    
/**
 * Funcion que retorna la posicion de Pancho
 * @param i el index donde esta pancho
 * @return 
 */
    @Override
    public Position<E> positionAtIndex(int i) {
        if( i< 0 || i >= size()){
            throw new IndexOutOfBoundsException("Indice no válido");
        }
        else{
            
            Position Pancho = first();
        for(int k= 0; k<i; k++){
            Position Panchito = after(Pancho);
        }
         return Pancho;
  
        }
        
    }

	private static class Node<E> implements Position<E> {
		private E element;
		private Node<E> prev;// Anterior
		private Node<E> next;// Siguiente

		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}

                @Override
		public E getElement() throws IllegalStateException {
			if (next == null) // Nodo no valido
				throw new IllegalStateException("Posicion invalida");
			return element;
		}
		
		public void setElement(E e) {
			element = e;
		}

		public Node<E> getPrev() {
			return prev;
		}

		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

	}
	
	// --- Implmentacion de iteradores
	/**
	 * Implementacion de un iterador basado en Position
	 */
	private class PositionIterator implements Iterator<Position<E>> {
		private Position<E> cursor = first(); // posicion del siguiente elemento
		private Position<E> recent = null; // posicion del ultimo elemento reportado
                @Override
		public boolean hasNext() { return (cursor != null); }
		
                @Override
		public Position<E> next( ) throws NoSuchElementException {
			if (cursor == null) throw new NoSuchElementException("no queda nada");
			recent = cursor;
			cursor = after(cursor);
			return recent;
		}
		
                @Override
		public void remove( ) throws IllegalStateException {
			if (recent == null) throw new IllegalStateException("nothing to remove");
			ListaPosicional.this.remove(recent);
			recent = null;
		}
	}
	
	private class PositionIterable implements Iterable<Position<E>> {
                @Override
		public Iterator<Position<E>> iterator() { return new PositionIterator(); }
	}
	
	/**
	 * Retorna un iterador sobre Positions
     * @return 
	 */
	public Iterable<Position<E>> positions( ) {
		return new PositionIterable();
	}
	
	/**
	 * "Adaptador" del iterador de positions hacia iterador de elementos
	 */
	private class ElementIterator implements Iterator<E> {
		Iterator<Position<E>> posIterator = new PositionIterator( );
                @Override
		public boolean hasNext( ) { return posIterator.hasNext( ); }
                @Override
		public E next( ) { return posIterator.next( ).getElement( ); } // return element!
                @Override
		public void remove( ) { posIterator.remove( ); }
	}
	
    @Override
	public Iterator<E> iterator( ) { return new ElementIterator( ); }
	
	

	private Node<E> header = null;// Referencia
	private Node<E> trailer = null;
	private int size = 0;

	public ListaPosicional() {
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null);
		header.setNext(trailer);
	}

	// Metodos internos
	/**
	 * Valida si una posicion contiene un nodo y el nodo existe
	 */
	public Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node))
			throw new IllegalArgumentException("P invalido");
		Node<E> node = (Node<E>) p; // safe cast
		if (node.getNext() == null)
			throw new IllegalArgumentException("p ya no se encuentra en la lista");
		return node;
	}
	
	/**
	 * "Empaca" un nodo como posicion a menos que sea header o trailer
	 */
	public Position<E> position(Node<E> node) {
		if (node == header || node == trailer)
			return null; // do not expose user to the sentinels
		return node;
	}

	// ---

    @Override
	public int size() {return size;}

    @Override
	public boolean isEmpty() {return size == 0;}

    /**
     *
     * @return
     */
    @Override
	public Position<E> first( ) {
		return position(header.getNext());
	}

    @Override
	public Position<E> last( ) {
		return position(trailer.getPrev());
	}
	
    @Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getPrev());
	}

    @Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getNext());
	}
	
	
	private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
		Node<E> newest = new Node<>(e, pred, succ);
		succ.setPrev(newest);
		pred.setNext(newest);
		size++;
		return newest;
	}
	
    @Override
	public Position<E> addFirst(E e) {
		return addBetween(e, header, header.getNext());
	}

    /**
     *
     * @param e
     * @return
     */
    @Override
	public Position<E> addLast(E e) {
		return addBetween(e, trailer.getPrev(), trailer);
	}
	
    @Override
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException{
		Node<E> node = validate(p);
		return addBetween(e, node.getPrev(), node);
	}
	
    @Override
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException{ 
		Node<E> node = validate(p);
		return addBetween(e, node, node.getNext());
	}

    @Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E answer = node.getElement();
		node.setElement(e);
		return answer;
	}
	
    @Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		E answer = node.getElement();
		node.setElement(null);
		node.setNext(null);
		node.setPrev(null);
		return answer;
	}

}