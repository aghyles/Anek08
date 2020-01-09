package com.popov.t04jh.service.impl;

import com.popov.t04jh.service.FicheSeanceService;
import com.popov.t04jh.domain.FicheSeance;
import com.popov.t04jh.repository.FicheSeanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FicheSeance}.
 */
@Service
@Transactional
public class FicheSeanceServiceImpl implements FicheSeanceService {

    private final Logger log = LoggerFactory.getLogger(FicheSeanceServiceImpl.class);

    private final FicheSeanceRepository ficheSeanceRepository;

    public FicheSeanceServiceImpl(FicheSeanceRepository ficheSeanceRepository) {
        this.ficheSeanceRepository = ficheSeanceRepository;
    }

    /**
     * Save a ficheSeance.
     *
     * @param ficheSeance the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FicheSeance save(FicheSeance ficheSeance) {
        log.debug("Request to save FicheSeance : {}", ficheSeance);
        return ficheSeanceRepository.save(ficheSeance);
    }

    /**
     * Get all the ficheSeances.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FicheSeance> findAll(Pageable pageable) {
        log.debug("Request to get all FicheSeances");
        return ficheSeanceRepository.findAll(pageable);
    }

    /**
     * Get all the ficheSeances with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<FicheSeance> findAllWithEagerRelationships(Pageable pageable) {
        return ficheSeanceRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one ficheSeance by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FicheSeance> findOne(Long id) {
        log.debug("Request to get FicheSeance : {}", id);
        return ficheSeanceRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the ficheSeance by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FicheSeance : {}", id);
        ficheSeanceRepository.deleteById(id);
    }
}
