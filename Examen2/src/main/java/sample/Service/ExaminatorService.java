package sample.Service;

import sample.Domain.Examinator;
import sample.Repository.IRepository;

import java.util.List;

public class ExaminatorService {
    private IRepository<Examinator> repository;

    /**
     * Constructs a service.
     *
     * @param repository the backing repository.
     */
    public ExaminatorService(IRepository<Examinator> repository) {
        this.repository = repository;
    }


    /**
     * adauga un examinator
     * @param id - examinator
     * @param nume
     * @param CNP
     */
    public void add(int id, String nume, String CNP) {

        Examinator persoana = new Examinator(id, nume, CNP);
        repository.upsert(persoana);

    }


    /**
     * obtinem lista examinatorilor adaugati
     * @return lista examinatori.
     */
    public List<Examinator> getAll() {
        return repository.getAll();
    }



  }


