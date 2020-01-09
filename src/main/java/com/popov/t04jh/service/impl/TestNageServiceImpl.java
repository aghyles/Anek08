package com.popov.t04jh.service.impl;

import com.popov.t04jh.service.TestNageService;
import com.popov.t04jh.domain.TestNage;
import com.popov.t04jh.repository.TestNageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TestNage}.
 */
@Service
@Transactional
public class TestNageServiceImpl implements TestNageService {

    private final Logger log = LoggerFactory.getLogger(TestNageServiceImpl.class);

    private final TestNageRepository testNageRepository;

    public TestNageServiceImpl(TestNageRepository testNageRepository) {
        this.testNageRepository = testNageRepository;
    }

    /**
     * Save a testNage.
     *
     * @param testNage the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TestNage save(TestNage testNage) {
        log.debug("Request to save TestNage : {}", testNage);
        return testNageRepository.save(testNage);
    }

    /**
     * Get all the testNages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TestNage> findAll(Pageable pageable) {
        log.debug("Request to get all TestNages");
        return testNageRepository.findAll(pageable);
    }


    /**
     * Get one testNage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TestNage> findOne(Long id) {
        log.debug("Request to get TestNage : {}", id);
        return testNageRepository.findById(id);
    }

    /**
     * Delete the testNage by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TestNage : {}", id);
        testNageRepository.deleteById(id);
    }
}
