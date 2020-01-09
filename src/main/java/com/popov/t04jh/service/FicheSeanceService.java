package com.popov.t04jh.service;

import com.popov.t04jh.domain.FicheSeance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link FicheSeance}.
 */
public interface FicheSeanceService {

    /**
     * Save a ficheSeance.
     *
     * @param ficheSeance the entity to save.
     * @return the persisted entity.
     */
    FicheSeance save(FicheSeance ficheSeance);

    /**
     * Get all the ficheSeances.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FicheSeance> findAll(Pageable pageable);

    /**
     * Get all the ficheSeances with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<FicheSeance> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" ficheSeance.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FicheSeance> findOne(Long id);

    /**
     * Delete the "id" ficheSeance.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
