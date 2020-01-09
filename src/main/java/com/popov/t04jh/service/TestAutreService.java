package com.popov.t04jh.service;

import com.popov.t04jh.domain.TestAutre;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link TestAutre}.
 */
public interface TestAutreService {

    /**
     * Save a testAutre.
     *
     * @param testAutre the entity to save.
     * @return the persisted entity.
     */
    TestAutre save(TestAutre testAutre);

    /**
     * Get all the testAutres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TestAutre> findAll(Pageable pageable);


    /**
     * Get the "id" testAutre.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TestAutre> findOne(Long id);

    /**
     * Delete the "id" testAutre.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
