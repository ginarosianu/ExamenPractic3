package sample.Domain;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import sample.Service.PersoanaIdException;

public class ExaminatorValidator implements IValidator<Examinator>{
   public void validate (Examinator examinator){
       if (examinator.getNume().length()<3){
           throw new PersoanaIdException("Numele trebuie sa aiba minim 3 litere");
       }
       if (examinator.getCNP().length()!=13){
           throw new PersoanaIdException("CNP trebuie sa aiba 13 cifre");
       }
       String variabila = examinator.getCNP();
       for (char c : variabila.toCharArray()) {
           if (!Character.isDigit(c)) {
               throw new PersoanaIdException("CNP-ul trebuie sa contina doar cifre");
           }

       }

   }
}
