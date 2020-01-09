package com.popov.t04jh.service;

import com.popov.t04jh.domain.Presence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Presence}.
 */
public interface PresenceService {

    /**
     * Save a presence.
     *
     * @param presence the entity to save.
     * @return the persisted entity.
     */
    Presence save(Presence presence);

    /**
     * Get all the presences.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Presence> findAll(Pageable pageable);


    /**
     * Get the "id" presence.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Presence> findOne(Long id);

    /**
     * Delete the "id" presence.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
