package model.beans;

import java.io.Serializable;


public class PaiementBean implements Serializable {

    public boolean checkNumCarte(String numCarte){
        return numCarte.matches("([0-9]{4}-){4}");
    }
    
    public boolean checkDateExp(String dateExp){
        return dateExp.matches("(0[1-9]|1[012])/(20\\d)");
    }
    
    public boolean checkCodeCrypto(String codeCrypto){
        return codeCrypto.matches("([0-9]{3})");
    }
    
   
    
}
