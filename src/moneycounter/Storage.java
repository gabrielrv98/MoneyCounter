/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moneycounter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import static moneycounter.Auxiliar.*;
import nu.xom.*;
/**
 *
 * @author reyga
 */
public class Storage {
    private static final String FILE_NAME="Storage.xml";
    private static final String YEAR="YEAR";
    private static final String YEAR_NAME="YEAR_NAME";
    public  static final String MONTH="MONTH";
    public  static final String MONTH_NAME="MONTH_NAME";
    private static final String SPEND="SPEND";
    private static final String AMMOUNT="AMMOUNT";
    
            
    //private HashMap<String,Box> months;
    private HashMap<String,HashMap> years;
    private int ammount;
        
    public  Storage() {
        
        
        
        try {
            Builder parser = new Builder();
            Document doc = parser.build(new File( FILE_NAME ));
            Element data = doc.getRootElement();
            
            //--change this
            Elements eltoY=data.getChildElements(YEAR);
            
            if (eltoY.size()==0) {
                
                years= new HashMap<>();
                makeNewAmmount();
                
            }else{
                ammount=parseInt(data.getFirstChildElement(AMMOUNT).getValue());
                years= new HashMap<>(eltoY.size());
                Elements eltoM;
                HashMap <String, ConjuntoGastos> months;
                String mes;
                
                for (int i = 0; i < eltoY.size(); i++) {//cada año
                    
                    eltoM = eltoY.get(i).getChildElements(MONTH);
                    months= new HashMap<>();
                    
                    for(int j=0; j<eltoM.size(); j++){//cada mes
                        
                        mes = eltoM.get(i).getFirstChildElement(MONTH_NAME).toString();
                        months.put(mes,new ConjuntoGastos(eltoM.get(i)));
                        
                    }
                    if (!years.containsKey(eltoY.get(i).getChildElements(YEAR_NAME).toString())) {
                         years.put(eltoY.get(i).getChildElements(YEAR_NAME).toString(), months); 
                    }else System.out.println(toRed("ERROR, 2 datos YEAR con el mismo YEAR_NAME"));
                   
                }
                
                System.out.println(toGreen("Datos cargados en la base."));
            }
            
         
            
        } catch (FileNotFoundException exc){
            System.out.println("Bienvenido, es tu primera vez verdad? ");
            error();
            
        } catch (IOException ex) {
            System.out.println(toRed("Error IOException."));
            
            error();
            
        } catch (ParsingException ex) {
            System.out.println(toRed("Error ParsinsException"));
            error();
            
        } 
        
    }
    
    private void error(){
        years= new HashMap<>();
        makeNewAmmount();
    }

    /**
     * inicializa la variable ammount, da 3 intentos y sino la deja en null
     */
    private void makeNewAmmount() {
        
        int i=0;
        int j;
        
        do{
            j=leerNum("Cuanto dinero tienes?- ");
            i++;
        }while(i<3 && j == -1);
        
        ammount = (j == -1) ? 0 : j;
        
    }
    
    public Element toDom(){
        Element raiz = new Element("STORAGE");
        Element eltoResponseTime= new Element("a");
        Element eltoInter = new Element("b");
        
        Element eltoT;
        
        //eltoResponseTime.appendChild(eltoT);
        raiz.appendChild(eltoResponseTime);
        raiz.appendChild(eltoInter);
        
        return raiz;
    }
    
    public void toXML(){
        try{
            FileOutputStream f = new FileOutputStream( FILE_NAME );
            Serializer serial = new Serializer( f );
            Document doc = new Document( this.toDom() );
            serial.write(doc);
        }catch(IOException exc){
            System.out.println(toRed("Error en toXML"));
        }
    }
}
