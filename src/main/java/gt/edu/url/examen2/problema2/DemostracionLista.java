/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.examen2.problema2;

/**
 *
 * @author JoseCarlos
 */
public class DemostracionLista implements DemoList{

    /**
     *Implementacion de la Lista
     * @return retorna la lista creada
     */
    @Override
    public List<Integer> crearDemoLista() {
        List nuevaLista = new Lista();
       nuevaLista.add(0, 4);
       nuevaLista.add(0, 3);
       nuevaLista.add(0, 2); 
       nuevaLista.add(2, 1); 
       nuevaLista.add(1, 5); 
       nuevaLista.add(1, 6); 
       nuevaLista.add(3, 7); 
       nuevaLista.add(0, 8);

       return nuevaLista;
    }
    
}
