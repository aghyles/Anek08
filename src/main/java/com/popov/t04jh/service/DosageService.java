package com.popov.t04jh.service;

import com.popov.t04jh.domain.Dosage;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Dosage}.
 */
public interface DosageService {

    /**
     * Save a dosage.
     *
     * @param dosage the entity to save.
     * @return the persisted entity.
     */
    Dosage save(Dosage dosage);

    /**
     * Get all the dosages.
     *
     * @return the list of entities.
     */
    List<Dosage> findAll();


    /**
     * Get the "id" dosage.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Dosage> findOne(Long id);

    /**
     * Delete the "id" dosage.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
