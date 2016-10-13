package model.beans;

import java.io.Serializable;


public class PaiementBean implements Serializable {

    public boolean checkNumCarte(String numCarte){
        if(numCarte.matches("([0-9]{4}-){4}"));
        return true;
    }
    
    public boolean checkDateExp(String dateExp){
        
        if(dateExp.matches("(0[1-9]|1[012])/(20\\d)"));
        
        
        return true;
    }
    
    public boolean checkCodeCrypto(String codeCrypto){
        
        if(codeCrypto.matches("([0-9]{3})"));
        return true;
    }
    
   
    
}
