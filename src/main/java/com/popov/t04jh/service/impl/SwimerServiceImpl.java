package com.popov.t04jh.service.impl;

import com.popov.t04jh.service.SwimerService;
import com.popov.t04jh.domain.Swimer;
import com.popov.t04jh.repository.SwimerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Swimer}.
 */
@Service
@Transactional
public class SwimerServiceImpl implements SwimerService {

    private final Logger log = LoggerFactory.getLogger(SwimerServiceImpl.class);

    private final SwimerRepository swimerRepository;

    public SwimerServiceImpl(SwimerRepository swimerRepository) {
        this.swimerRepository = swimerRepository;
    }

    /**
     * Save a swimer.
     *
     * @param swimer the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Swimer save(Swimer swimer) {
        log.debug("Request to save Swimer : {}", swimer);
        return swimerRepository.save(swimer);
    }

    /**
     * Get all the swimers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Swimer> findAll(Pageable pageable) {
        log.debug("Request to get all Swimers");
        return swimerRepository.findAll(pageable);
    }


    /**
     * Get one swimer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Swimer> findOne(Long id) {
        log.debug("Request to get Swimer : {}", id);
        return swimerRepository.findById(id);
    }

    /**
     * Delete the swimer by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Swimer : {}", id);
        swimerRepository.deleteById(id);
    }
}
