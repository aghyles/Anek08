package com.popov.t04jh.service.impl;

import com.popov.t04jh.service.TestEtudeService;
import com.popov.t04jh.domain.TestEtude;
import com.popov.t04jh.repository.TestEtudeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TestEtude}.
 */
@Service
@Transactional
public class TestEtudeServiceImpl implements TestEtudeService {

    private final Logger log = LoggerFactory.getLogger(TestEtudeServiceImpl.class);

    private final TestEtudeRepository testEtudeRepository;

    public TestEtudeServiceImpl(TestEtudeRepository testEtudeRepository) {
        this.testEtudeRepository = testEtudeRepository;
    }

    /**
     * Save a testEtude.
     *
     * @param testEtude the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TestEtude save(TestEtude testEtude) {
        log.debug("Request to save TestEtude : {}", testEtude);
        return testEtudeRepository.save(testEtude);
    }

    /**
     * Get all the testEtudes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TestEtude> findAll(Pageable pageable) {
        log.debug("Request to get all TestEtudes");
        return testEtudeRepository.findAll(pageable);
    }


    /**
     * Get one testEtude by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TestEtude> findOne(Long id) {
        log.debug("Request to get TestEtude : {}", id);
        return testEtudeRepository.findById(id);
    }

    /**
     * Delete the testEtude by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TestEtude : {}", id);
        testEtudeRepository.deleteById(id);
    }
}
