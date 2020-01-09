package com.popov.t04jh.service;

import com.popov.t04jh.domain.TestEtude;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link TestEtude}.
 */
public interface TestEtudeService {

    /**
     * Save a testEtude.
     *
     * @param testEtude the entity to save.
     * @return the persisted entity.
     */
    TestEtude save(TestEtude testEtude);

    /**
     * Get all the testEtudes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TestEtude> findAll(Pageable pageable);


    /**
     * Get the "id" testEtude.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TestEtude> findOne(Long id);

    /**
     * Delete the "id" testEtude.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
