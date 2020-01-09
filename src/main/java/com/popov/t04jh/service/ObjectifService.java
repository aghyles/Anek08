package com.popov.t04jh.service;

import com.popov.t04jh.domain.Objectif;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Objectif}.
 */
public interface ObjectifService {

    /**
     * Save a objectif.
     *
     * @param objectif the entity to save.
     * @return the persisted entity.
     */
    Objectif save(Objectif objectif);

    /**
     * Get all the objectifs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Objectif> findAll(Pageable pageable);

    /**
     * Get all the objectifs with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<Objectif> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" objectif.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Objectif> findOne(Long id);

    /**
     * Delete the "id" objectif.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
