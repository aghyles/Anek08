package com.popov.t04jh.service;

import com.popov.t04jh.domain.Exercice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Exercice}.
 */
public interface ExerciceService {

    /**
     * Save a exercice.
     *
     * @param exercice the entity to save.
     * @return the persisted entity.
     */
    Exercice save(Exercice exercice);

    /**
     * Get all the exercices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Exercice> findAll(Pageable pageable);

    /**
     * Get all the exercices with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<Exercice> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" exercice.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Exercice> findOne(Long id);

    /**
     * Delete the "id" exercice.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
