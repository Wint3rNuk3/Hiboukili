package model.beans;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaiementBean implements Serializable {

    private Pattern pattern;
    private Matcher matcher;

    private final String DATE_PATTERN = "(0[1-9]|1[012])/(20\\d)";

    public PaiementBean() {
        pattern = Pattern.compile(DATE_PATTERN);
    }
    
    public boolean check(String numCarte, String codeCrypto, String dateExp){
        
        
        return true;
    }

    // controle sur le champs carte bancaire. Respect du 

//    public boolean check(String numCarte, String codeCrypto, final String dateExp) {
//
//        matcher = pattern.matcher(dateExp);
//        if (matcher.matches()) {
//            matcher.reset();
//        }
//        if (matcher.find()) {
//
//            String month = matcher.group(1);
//            String year = matcher.group(2);
//
//            if (month.equals("02")) {
//                if (year.equals("02")) {
//                    return true;
//                } else {
//                    return false;
//                }
//
//            }
//        }
//
//        return true;
//
//    }
    
    
}
