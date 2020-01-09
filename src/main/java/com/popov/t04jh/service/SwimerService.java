package com.popov.t04jh.service;

import com.popov.t04jh.domain.Swimer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Swimer}.
 */
public interface SwimerService {

    /**
     * Save a swimer.
     *
     * @param swimer the entity to save.
     * @return the persisted entity.
     */
    Swimer save(Swimer swimer);

    /**
     * Get all the swimers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Swimer> findAll(Pageable pageable);


    /**
     * Get the "id" swimer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Swimer> findOne(Long id);

    /**
     * Delete the "id" swimer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
