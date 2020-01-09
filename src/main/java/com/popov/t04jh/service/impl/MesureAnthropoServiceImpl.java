package com.popov.t04jh.service.impl;

import com.popov.t04jh.service.MesureAnthropoService;
import com.popov.t04jh.domain.MesureAnthropo;
import com.popov.t04jh.repository.MesureAnthropoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MesureAnthropo}.
 */
@Service
@Transactional
public class MesureAnthropoServiceImpl implements MesureAnthropoService {

    private final Logger log = LoggerFactory.getLogger(MesureAnthropoServiceImpl.class);

    private final MesureAnthropoRepository mesureAnthropoRepository;

    public MesureAnthropoServiceImpl(MesureAnthropoRepository mesureAnthropoRepository) {
        this.mesureAnthropoRepository = mesureAnthropoRepository;
    }

    /**
     * Save a mesureAnthropo.
     *
     * @param mesureAnthropo the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MesureAnthropo save(MesureAnthropo mesureAnthropo) {
        log.debug("Request to save MesureAnthropo : {}", mesureAnthropo);
        return mesureAnthropoRepository.save(mesureAnthropo);
    }

    /**
     * Get all the mesureAnthropos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MesureAnthropo> findAll(Pageable pageable) {
        log.debug("Request to get all MesureAnthropos");
        return mesureAnthropoRepository.findAll(pageable);
    }


    /**
     * Get one mesureAnthropo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MesureAnthropo> findOne(Long id) {
        log.debug("Request to get MesureAnthropo : {}", id);
        return mesureAnthropoRepository.findById(id);
    }

    /**
     * Delete the mesureAnthropo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MesureAnthropo : {}", id);
        mesureAnthropoRepository.deleteById(id);
    }
}
