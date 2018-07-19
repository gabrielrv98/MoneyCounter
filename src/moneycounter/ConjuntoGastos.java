/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moneycounter;

import java.util.ArrayList;
import java.util.List;
import static moneycounter.Auxiliar.*;
import static moneycounter.Storage.MONTH;
import nu.xom.*;

/**
 *
 * @author reyga
 */
public class ConjuntoGastos {
    
    private static final String DINERO_F_MES="DINERO_F_MES";
    
    private static final String OCIO="OCIO";
    private static final String PAN="PAN";
    private static final String SUPER_M="SUPER_M";
    private static final String GASOLEO="GASOLEO";
    private static final String OTROS="OTROS";
    private static final String RECIBOS="RECIBOS";
    
    public  static final String COMPRAS="COMPRAS";
    
    private int dinFinDeMes;
    
    private List<Gasto> ocio;
    private List<Gasto> pan;
    private List<Gasto> superM;
    private List<Gasto> gasoleo;
    private List<Gasto> otros;
    private List<Gasto> recibos;
    

    // extraer din a fin de mes, y las listas de los gastos
    public ConjuntoGastos(Element data) {
        
        Element eltoDin =data.getFirstChildElement(DINERO_F_MES);
        
        if(eltoDin == null)
            dinFinDeMes= -1;
        else {
            int aux = parseInt(eltoDin.toString());
            dinFinDeMes= (aux == -1) ? -1 : aux/100;
        }
        
        Element eltoAux;
        
        eltoAux= data.getFirstChildElement(OCIO);
        if(eltoAux != null) ocio= startQueue(eltoAux);
        else ocio= new ArrayList<>();
        
        eltoAux = data.getFirstChildElement(PAN);
        if(eltoAux != null) pan= startQueue(eltoAux);
        else pan= new ArrayList<>();
        
        eltoAux = data.getFirstChildElement(SUPER_M);
        if(eltoAux != null) superM=startQueue(eltoAux);
        else superM= new ArrayList<>();
        
        eltoAux = data.getFirstChildElement(GASOLEO);
        if(eltoAux != null) gasoleo= startQueue(eltoAux);
        else gasoleo= new ArrayList<>();
        
        eltoAux = data.getFirstChildElement(OTROS);
        if(eltoAux != null) otros = startQueue(eltoAux);
        else otros= new ArrayList<>();
        
        eltoAux = data.getFirstChildElement(RECIBOS);
        if(eltoAux != null) recibos = startQueue(eltoAux);
        recibos= new ArrayList<>();
        
    }
    
    
    private  List<Gasto> startQueue( Element e){
        Elements eltoGasto =  e.getChildElements(COMPRAS);
        ArrayList<Gasto> lista = new ArrayList<>(eltoGasto.size());    
        for (int i = 0; i < eltoGasto.size(); i++) 
            lista.add(new Gasto(eltoGasto.get(i)));
        
        return lista;
    }
    
    private Element  engage (List<Gasto> list , String st){
        Element eltoAux = new Element(st);
        for (Gasto gasto : list) {
            //acabar, se puede copiar de LL
        }
    }
    
    public Element toDOM(){
        Element raiz = new Element(MONTH);
        Element eltoDinFMes = new Element(DINERO_F_MES);
        Element eltoOcio = new Element(OCIO);
        Element eltoPan = new Element(PAN);
        Element eltoSuperM = new Element (SUPER_M);
        Element eltoGasoleo = new Element(GASOLEO);
        
        
    }
}
