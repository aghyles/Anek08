package com.popov.t04jh.service;

import com.popov.t04jh.domain.TestPhysique;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link TestPhysique}.
 */
public interface TestPhysiqueService {

    /**
     * Save a testPhysique.
     *
     * @param testPhysique the entity to save.
     * @return the persisted entity.
     */
    TestPhysique save(TestPhysique testPhysique);

    /**
     * Get all the testPhysiques.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TestPhysique> findAll(Pageable pageable);


    /**
     * Get the "id" testPhysique.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TestPhysique> findOne(Long id);

    /**
     * Delete the "id" testPhysique.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
