package com.popov.t04jh.service.impl;

import com.popov.t04jh.service.ObjectifService;
import com.popov.t04jh.domain.Objectif;
import com.popov.t04jh.repository.ObjectifRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Objectif}.
 */
@Service
@Transactional
public class ObjectifServiceImpl implements ObjectifService {

    private final Logger log = LoggerFactory.getLogger(ObjectifServiceImpl.class);

    private final ObjectifRepository objectifRepository;

    public ObjectifServiceImpl(ObjectifRepository objectifRepository) {
        this.objectifRepository = objectifRepository;
    }

    /**
     * Save a objectif.
     *
     * @param objectif the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Objectif save(Objectif objectif) {
        log.debug("Request to save Objectif : {}", objectif);
        return objectifRepository.save(objectif);
    }

    /**
     * Get all the objectifs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Objectif> findAll(Pageable pageable) {
        log.debug("Request to get all Objectifs");
        return objectifRepository.findAll(pageable);
    }

    /**
     * Get all the objectifs with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Objectif> findAllWithEagerRelationships(Pageable pageable) {
        return objectifRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one objectif by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Objectif> findOne(Long id) {
        log.debug("Request to get Objectif : {}", id);
        return objectifRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the objectif by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Objectif : {}", id);
        objectifRepository.deleteById(id);
    }
}
