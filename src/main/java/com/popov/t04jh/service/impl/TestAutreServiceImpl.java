package com.popov.t04jh.service.impl;

import com.popov.t04jh.service.TestAutreService;
import com.popov.t04jh.domain.TestAutre;
import com.popov.t04jh.repository.TestAutreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TestAutre}.
 */
@Service
@Transactional
public class TestAutreServiceImpl implements TestAutreService {

    private final Logger log = LoggerFactory.getLogger(TestAutreServiceImpl.class);

    private final TestAutreRepository testAutreRepository;

    public TestAutreServiceImpl(TestAutreRepository testAutreRepository) {
        this.testAutreRepository = testAutreRepository;
    }

    /**
     * Save a testAutre.
     *
     * @param testAutre the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TestAutre save(TestAutre testAutre) {
        log.debug("Request to save TestAutre : {}", testAutre);
        return testAutreRepository.save(testAutre);
    }

    /**
     * Get all the testAutres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TestAutre> findAll(Pageable pageable) {
        log.debug("Request to get all TestAutres");
        return testAutreRepository.findAll(pageable);
    }


    /**
     * Get one testAutre by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TestAutre> findOne(Long id) {
        log.debug("Request to get TestAutre : {}", id);
        return testAutreRepository.findById(id);
    }

    /**
     * Delete the testAutre by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TestAutre : {}", id);
        testAutreRepository.deleteById(id);
    }
}
