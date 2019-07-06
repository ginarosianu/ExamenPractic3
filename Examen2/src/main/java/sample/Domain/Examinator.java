package sample.Domain;

public class Examinator extends Entity {
    private String nume;
    private String CNP;

    public Examinator(int id, String nume, String CNP) {
        super( id );
        this.nume = nume;
        this.CNP = CNP;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }
}
