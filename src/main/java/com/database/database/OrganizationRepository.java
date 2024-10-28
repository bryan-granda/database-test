package com.database.database;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepository extends CrudRepository<Organization, Integer> {
    // This will be AUTO IMPLEMENTED by Spring into a Bean called organizationRepository
    // CRUD refers Create, Read, Update, Delete
}
