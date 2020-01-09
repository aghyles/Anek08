package com.popov.t04jh.service.impl;

import com.popov.t04jh.service.ExerciceService;
import com.popov.t04jh.domain.Exercice;
import com.popov.t04jh.repository.ExerciceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Exercice}.
 */
@Service
@Transactional
public class ExerciceServiceImpl implements ExerciceService {

    private final Logger log = LoggerFactory.getLogger(ExerciceServiceImpl.class);

    private final ExerciceRepository exerciceRepository;

    public ExerciceServiceImpl(ExerciceRepository exerciceRepository) {
        this.exerciceRepository = exerciceRepository;
    }

    /**
     * Save a exercice.
     *
     * @param exercice the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Exercice save(Exercice exercice) {
        log.debug("Request to save Exercice : {}", exercice);
        return exerciceRepository.save(exercice);
    }

    /**
     * Get all the exercices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Exercice> findAll(Pageable pageable) {
        log.debug("Request to get all Exercices");
        return exerciceRepository.findAll(pageable);
    }

    /**
     * Get all the exercices with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Exercice> findAllWithEagerRelationships(Pageable pageable) {
        return exerciceRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one exercice by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Exercice> findOne(Long id) {
        log.debug("Request to get Exercice : {}", id);
        return exerciceRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the exercice by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Exercice : {}", id);
        exerciceRepository.deleteById(id);
    }
}
