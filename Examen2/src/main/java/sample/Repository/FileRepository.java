package sample.Repository;

import com.google.gson.Gson;
import sample.Domain.Entity;
import sample.Domain.IValidator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileRepository<T extends Entity> implements IRepository<T>{

    private IValidator<T> validator;
    private String filename;
    private Map<Integer, T> storage = new HashMap<>();
    private Type type;

    /**
     * Constructs a file repository.
     *
     * @param validator the validator.
     * @param filename  the filename used to store objects.
     * @param type      the type of the objects stored (of T).
     */
    public FileRepository(IValidator<T> validator, String filename, Type type) {
        this.validator = validator;
        this.filename = filename;
        this.type = type;
    }

    private void loadFromFile() {
        storage.clear();
        Gson gson = new Gson();
        try (FileReader in = new FileReader(filename)) {
            try (BufferedReader bufferedReader = new BufferedReader(in)) {

                T[] entities = gson.fromJson(bufferedReader, type);
                for (T entity : entities) {
                    storage.put(entity.getId(), entity);
                }
            }
        } catch (Exception ex) {

        }
    }

    private void writeToFile() {
        Gson gson = new Gson();
        try (FileWriter out = new FileWriter(filename)) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(out)) {

                bufferedWriter.write(gson.toJson(storage.values()));
            }
        } catch (Exception ex) {

        }
    }

    /**
     * Gets an entity with a given id.
     *
     * @param id the given id.
     * @return the entity with the given id.
     */
    @Override
    public T findById(int id) {
        loadFromFile();
        return storage.get(id);
    }

    /**
     * Adds or replaces an entity.
     *
     * @param entity the entity to add or replace based on its id.
     */
    @Override
    public void upsert(T entity) {
        loadFromFile();                           /*incarca tot ce e in json*/
        validator.validate(entity);               /*valideaza entitatea*/
        storage.put(entity.getId(), entity);     /* rescrie si pune in storage */
        writeToFile();                           /* rescrie in fisier noua valoare*/
    }

    /**
     * Removes an entity with the given id.
     *
     * @param id the given id.
     * @throws RepositoryException if there is no entity with the given id.
     */
    @Override
    public void remove(Integer id) {
        loadFromFile();
        if (!storage.containsKey(id)) {
            throw new RepositoryException("There is no entity with the given id to remove.");
        }

        storage.remove(id);
        writeToFile();
    }

    /**
     * Gets a list of all entities.
     *
     * @return the list with all the entities.
     */
    @Override
    public List<T> getAll() {
        loadFromFile();
        return new ArrayList<>(storage.values());
    }

}
