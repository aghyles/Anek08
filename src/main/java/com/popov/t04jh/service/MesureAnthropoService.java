package com.popov.t04jh.service;

import com.popov.t04jh.domain.MesureAnthropo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link MesureAnthropo}.
 */
public interface MesureAnthropoService {

    /**
     * Save a mesureAnthropo.
     *
     * @param mesureAnthropo the entity to save.
     * @return the persisted entity.
     */
    MesureAnthropo save(MesureAnthropo mesureAnthropo);

    /**
     * Get all the mesureAnthropos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MesureAnthropo> findAll(Pageable pageable);


    /**
     * Get the "id" mesureAnthropo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MesureAnthropo> findOne(Long id);

    /**
     * Delete the "id" mesureAnthropo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
