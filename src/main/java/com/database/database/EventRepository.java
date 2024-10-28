package com.database.database;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {
    // This will be AUTO IMPLEMENTED by Spring into a Bean called eventRepository
    // CRUD refers Create, Read, Update, Delete
}
