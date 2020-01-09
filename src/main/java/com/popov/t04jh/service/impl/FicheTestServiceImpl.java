package com.popov.t04jh.service.impl;

import com.popov.t04jh.service.FicheTestService;
import com.popov.t04jh.domain.FicheTest;
import com.popov.t04jh.repository.FicheTestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FicheTest}.
 */
@Service
@Transactional
public class FicheTestServiceImpl implements FicheTestService {

    private final Logger log = LoggerFactory.getLogger(FicheTestServiceImpl.class);

    private final FicheTestRepository ficheTestRepository;

    public FicheTestServiceImpl(FicheTestRepository ficheTestRepository) {
        this.ficheTestRepository = ficheTestRepository;
    }

    /**
     * Save a ficheTest.
     *
     * @param ficheTest the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FicheTest save(FicheTest ficheTest) {
        log.debug("Request to save FicheTest : {}", ficheTest);
        return ficheTestRepository.save(ficheTest);
    }

    /**
     * Get all the ficheTests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FicheTest> findAll(Pageable pageable) {
        log.debug("Request to get all FicheTests");
        return ficheTestRepository.findAll(pageable);
    }


    /**
     * Get one ficheTest by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FicheTest> findOne(Long id) {
        log.debug("Request to get FicheTest : {}", id);
        return ficheTestRepository.findById(id);
    }

    /**
     * Delete the ficheTest by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FicheTest : {}", id);
        ficheTestRepository.deleteById(id);
    }
}
