package sample.Service;
import sample.Domain.Examinare;
import sample.Domain.Examinator;
import sample.Repository.IRepository;

import java.util.ArrayList;
import java.util.List;


public class ExaminareService {

    private IRepository< Examinare > examinareRepository;
    private IRepository< Examinator > examinatorRepository;

    public ExaminareService(IRepository<Examinare> examinareRepository, IRepository < Examinator > examinatorRepository) {
        this.examinareRepository = examinareRepository;
        this.examinatorRepository = examinatorRepository;
    }

    /**
     * functie de adaugare examinare;
     * @param id
     * @param idPersExam
     * @param rezultat
     * @param data
     */
        public void add (int id, int idPersExam, String rezultat, int data) {

            Examinator existingExaminator = examinatorRepository.findById( idPersExam );
            Examinare examinare = new Examinare(id, idPersExam, rezultat, data);

            if (existingExaminator == null && examinare.getData()<=1 && examinare.getData()>=30){
                throw new RuntimeException( "Examinatorul cu aces id nu exista sau data nu e este intre 1 si 30" );
            }



//            if (examinare.getData()<=1 && examinare.getData()>=30){
//                throw new RuntimeException( "Data examinarii trebuie sa fie intre 1 si 30!");
//            }
            examinareRepository.upsert(examinare);
     }

    /**
     *
     * @return lista examinarilor adaugate;
     */
        public List<Examinare> getAll() {
            return examinareRepository.getAll();
     }


    public List < Examinator > fullTextSearch(String text) {
        List < Examinator > results = new ArrayList <>();
        for (Examinator examinatori : examinatorRepository.getAll()) {
            if (examinatori.getNume().contains(text)) {
                results.add(examinatori);
            }
        }
        return results;
    }

    public List<Examinator> getRaport(String x) {
        List<Examinator> results = new ArrayList<>();
        for (Examinator p : examinatorRepository.getAll()) {
            for (Examinare e : examinareRepository.getAll()) {
                if (p.getId()== e.getIdPersExam()) {
                    results.add(p);
                }
            }
        }
        return results;
    }
}

