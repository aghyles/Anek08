package com.popov.t04jh.service;

import com.popov.t04jh.domain.FicheTest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link FicheTest}.
 */
public interface FicheTestService {

    /**
     * Save a ficheTest.
     *
     * @param ficheTest the entity to save.
     * @return the persisted entity.
     */
    FicheTest save(FicheTest ficheTest);

    /**
     * Get all the ficheTests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FicheTest> findAll(Pageable pageable);


    /**
     * Get the "id" ficheTest.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FicheTest> findOne(Long id);

    /**
     * Delete the "id" ficheTest.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
