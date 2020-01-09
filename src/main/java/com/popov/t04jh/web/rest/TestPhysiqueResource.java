package com.popov.t04jh.web.rest;

import com.popov.t04jh.domain.TestPhysique;
import com.popov.t04jh.service.TestPhysiqueService;
import com.popov.t04jh.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.popov.t04jh.domain.TestPhysique}.
 */
@RestController
@RequestMapping("/api")
public class TestPhysiqueResource {

    private final Logger log = LoggerFactory.getLogger(TestPhysiqueResource.class);

    private static final String ENTITY_NAME = "testPhysique";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TestPhysiqueService testPhysiqueService;

    public TestPhysiqueResource(TestPhysiqueService testPhysiqueService) {
        this.testPhysiqueService = testPhysiqueService;
    }

    /**
     * {@code POST  /test-physiques} : Create a new testPhysique.
     *
     * @param testPhysique the testPhysique to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new testPhysique, or with status {@code 400 (Bad Request)} if the testPhysique has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/test-physiques")
    public ResponseEntity<TestPhysique> createTestPhysique(@RequestBody TestPhysique testPhysique) throws URISyntaxException {
        log.debug("REST request to save TestPhysique : {}", testPhysique);
        if (testPhysique.getId() != null) {
            throw new BadRequestAlertException("A new testPhysique cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TestPhysique result = testPhysiqueService.save(testPhysique);
        return ResponseEntity.created(new URI("/api/test-physiques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /test-physiques} : Updates an existing testPhysique.
     *
     * @param testPhysique the testPhysique to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated testPhysique,
     * or with status {@code 400 (Bad Request)} if the testPhysique is not valid,
     * or with status {@code 500 (Internal Server Error)} if the testPhysique couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/test-physiques")
    public ResponseEntity<TestPhysique> updateTestPhysique(@RequestBody TestPhysique testPhysique) throws URISyntaxException {
        log.debug("REST request to update TestPhysique : {}", testPhysique);
        if (testPhysique.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TestPhysique result = testPhysiqueService.save(testPhysique);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, testPhysique.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /test-physiques} : get all the testPhysiques.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of testPhysiques in body.
     */
    @GetMapping("/test-physiques")
    public ResponseEntity<List<TestPhysique>> getAllTestPhysiques(Pageable pageable) {
        log.debug("REST request to get a page of TestPhysiques");
        Page<TestPhysique> page = testPhysiqueService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /test-physiques/:id} : get the "id" testPhysique.
     *
     * @param id the id of the testPhysique to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the testPhysique, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/test-physiques/{id}")
    public ResponseEntity<TestPhysique> getTestPhysique(@PathVariable Long id) {
        log.debug("REST request to get TestPhysique : {}", id);
        Optional<TestPhysique> testPhysique = testPhysiqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(testPhysique);
    }

    /**
     * {@code DELETE  /test-physiques/:id} : delete the "id" testPhysique.
     *
     * @param id the id of the testPhysique to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/test-physiques/{id}")
    public ResponseEntity<Void> deleteTestPhysique(@PathVariable Long id) {
        log.debug("REST request to delete TestPhysique : {}", id);
        testPhysiqueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
