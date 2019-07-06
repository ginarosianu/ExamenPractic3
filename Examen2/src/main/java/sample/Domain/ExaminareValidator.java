package sample.Domain;

    /**
     * valideaza o examinare - rezultatul sa fie admis sau respins;
     * @param
     */


public class ExaminareValidator implements IValidator<Examinare> {
    public void validate(Examinare examinare) {
        if (!( examinare.getRezultat().equals( "admis" ) ||
                examinare.getRezultat().equals( "respins" ) )
                && (examinare.getData() <=1 &&  examinare.getData() >=30))
        { throw new RuntimeException( "Rezultatul trebuie sa fie admis sau respins si data intre 1 si 30" );
        }
    }
}