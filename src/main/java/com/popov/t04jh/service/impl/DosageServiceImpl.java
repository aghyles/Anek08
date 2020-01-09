package com.popov.t04jh.service.impl;

import com.popov.t04jh.service.DosageService;
import com.popov.t04jh.domain.Dosage;
import com.popov.t04jh.repository.DosageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Dosage}.
 */
@Service
@Transactional
public class DosageServiceImpl implements DosageService {

    private final Logger log = LoggerFactory.getLogger(DosageServiceImpl.class);

    private final DosageRepository dosageRepository;

    public DosageServiceImpl(DosageRepository dosageRepository) {
        this.dosageRepository = dosageRepository;
    }

    /**
     * Save a dosage.
     *
     * @param dosage the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Dosage save(Dosage dosage) {
        log.debug("Request to save Dosage : {}", dosage);
        return dosageRepository.save(dosage);
    }

    /**
     * Get all the dosages.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Dosage> findAll() {
        log.debug("Request to get all Dosages");
        return dosageRepository.findAll();
    }


    /**
     * Get one dosage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Dosage> findOne(Long id) {
        log.debug("Request to get Dosage : {}", id);
        return dosageRepository.findById(id);
    }

    /**
     * Delete the dosage by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dosage : {}", id);
        dosageRepository.deleteById(id);
    }
}
