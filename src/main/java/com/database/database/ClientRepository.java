package com.database.database;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    // This will be AUTO IMPLEMENTED by Spring into a Bean called clientRepository
    // CRUD refers Create, Read, Update, Delete
}
