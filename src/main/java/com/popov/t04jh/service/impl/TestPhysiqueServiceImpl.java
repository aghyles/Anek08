package com.popov.t04jh.service.impl;

import com.popov.t04jh.service.TestPhysiqueService;
import com.popov.t04jh.domain.TestPhysique;
import com.popov.t04jh.repository.TestPhysiqueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TestPhysique}.
 */
@Service
@Transactional
public class TestPhysiqueServiceImpl implements TestPhysiqueService {

    private final Logger log = LoggerFactory.getLogger(TestPhysiqueServiceImpl.class);

    private final TestPhysiqueRepository testPhysiqueRepository;

    public TestPhysiqueServiceImpl(TestPhysiqueRepository testPhysiqueRepository) {
        this.testPhysiqueRepository = testPhysiqueRepository;
    }

    /**
     * Save a testPhysique.
     *
     * @param testPhysique the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TestPhysique save(TestPhysique testPhysique) {
        log.debug("Request to save TestPhysique : {}", testPhysique);
        return testPhysiqueRepository.save(testPhysique);
    }

    /**
     * Get all the testPhysiques.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TestPhysique> findAll(Pageable pageable) {
        log.debug("Request to get all TestPhysiques");
        return testPhysiqueRepository.findAll(pageable);
    }


    /**
     * Get one testPhysique by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TestPhysique> findOne(Long id) {
        log.debug("Request to get TestPhysique : {}", id);
        return testPhysiqueRepository.findById(id);
    }

    /**
     * Delete the testPhysique by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TestPhysique : {}", id);
        testPhysiqueRepository.deleteById(id);
    }
}
