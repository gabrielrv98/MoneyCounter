/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moneycounter;

import nu.xom.Element;
import static moneycounter.Auxiliar.parseInt;
import static moneycounter.ConjuntoGastos.COMPRAS;

/**
 *
 * @author reyga
 */
class Gasto {
    private static final String CANTIDAD= "CANTIDAD";
    private static final String COMENTARIO= "COMENTARIO";
    
    private String comentario;
    private int cantidad;
    
    
    public Gasto (Element data){
        
        Element eltoCant=data.getFirstChildElement(CANTIDAD);
        
        cantidad= parseInt(eltoCant.toString());
        if (cantidad != -1) cantidad/=100;
        
        Element eltoComent= data.getFirstChildElement(COMENTARIO);
        
        if( eltoComent== null) comentario="";
        else comentario = eltoComent.toString();
    }
    
    
    
    
    
    public Element toDom(){
        Element raiz = new Element(COMPRAS);
        Element eltoComent= new Element(COMENTARIO);
        Element eltoCant = new Element(CANTIDAD);
        
        eltoComent.appendChild(comentario);
        eltoCant.appendChild(Integer.toString(cantidad*100));// se guarda en centimos
        
        //eltoResponseTime.appendChild(eltoT);
        raiz.appendChild(eltoComent);
        raiz.appendChild(eltoCant);
        
        return raiz;
    }
}
