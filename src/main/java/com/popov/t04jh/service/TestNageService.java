package com.popov.t04jh.service;

import com.popov.t04jh.domain.TestNage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link TestNage}.
 */
public interface TestNageService {

    /**
     * Save a testNage.
     *
     * @param testNage the entity to save.
     * @return the persisted entity.
     */
    TestNage save(TestNage testNage);

    /**
     * Get all the testNages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TestNage> findAll(Pageable pageable);


    /**
     * Get the "id" testNage.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TestNage> findOne(Long id);

    /**
     * Delete the "id" testNage.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
