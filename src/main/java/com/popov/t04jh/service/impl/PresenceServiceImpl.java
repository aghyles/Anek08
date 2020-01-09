package com.popov.t04jh.service.impl;

import com.popov.t04jh.service.PresenceService;
import com.popov.t04jh.domain.Presence;
import com.popov.t04jh.repository.PresenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Presence}.
 */
@Service
@Transactional
public class PresenceServiceImpl implements PresenceService {

    private final Logger log = LoggerFactory.getLogger(PresenceServiceImpl.class);

    private final PresenceRepository presenceRepository;

    public PresenceServiceImpl(PresenceRepository presenceRepository) {
        this.presenceRepository = presenceRepository;
    }

    /**
     * Save a presence.
     *
     * @param presence the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Presence save(Presence presence) {
        log.debug("Request to save Presence : {}", presence);
        return presenceRepository.save(presence);
    }

    /**
     * Get all the presences.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Presence> findAll(Pageable pageable) {
        log.debug("Request to get all Presences");
        return presenceRepository.findAll(pageable);
    }


    /**
     * Get one presence by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Presence> findOne(Long id) {
        log.debug("Request to get Presence : {}", id);
        return presenceRepository.findById(id);
    }

    /**
     * Delete the presence by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Presence : {}", id);
        presenceRepository.deleteById(id);
    }
}
