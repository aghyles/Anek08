package com.popov.t04jh.web.rest;

import com.popov.t04jh.domain.TestNage;
import com.popov.t04jh.service.TestNageService;
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
 * REST controller for managing {@link com.popov.t04jh.domain.TestNage}.
 */
@RestController
@RequestMapping("/api")
public class TestNageResource {

    private final Logger log = LoggerFactory.getLogger(TestNageResource.class);

    private static final String ENTITY_NAME = "testNage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TestNageService testNageService;

    public TestNageResource(TestNageService testNageService) {
        this.testNageService = testNageService;
    }

    /**
     * {@code POST  /test-nages} : Create a new testNage.
     *
     * @param testNage the testNage to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new testNage, or with status {@code 400 (Bad Request)} if the testNage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/test-nages")
    public ResponseEntity<TestNage> createTestNage(@RequestBody TestNage testNage) throws URISyntaxException {
        log.debug("REST request to save TestNage : {}", testNage);
        if (testNage.getId() != null) {
            throw new BadRequestAlertException("A new testNage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TestNage result = testNageService.save(testNage);
        return ResponseEntity.created(new URI("/api/test-nages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /test-nages} : Updates an existing testNage.
     *
     * @param testNage the testNage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated testNage,
     * or with status {@code 400 (Bad Request)} if the testNage is not valid,
     * or with status {@code 500 (Internal Server Error)} if the testNage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/test-nages")
    public ResponseEntity<TestNage> updateTestNage(@RequestBody TestNage testNage) throws URISyntaxException {
        log.debug("REST request to update TestNage : {}", testNage);
        if (testNage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TestNage result = testNageService.save(testNage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, testNage.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /test-nages} : get all the testNages.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of testNages in body.
     */
    @GetMapping("/test-nages")
    public ResponseEntity<List<TestNage>> getAllTestNages(Pageable pageable) {
        log.debug("REST request to get a page of TestNages");
        Page<TestNage> page = testNageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /test-nages/:id} : get the "id" testNage.
     *
     * @param id the id of the testNage to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the testNage, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/test-nages/{id}")
    public ResponseEntity<TestNage> getTestNage(@PathVariable Long id) {
        log.debug("REST request to get TestNage : {}", id);
        Optional<TestNage> testNage = testNageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(testNage);
    }

    /**
     * {@code DELETE  /test-nages/:id} : delete the "id" testNage.
     *
     * @param id the id of the testNage to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/test-nages/{id}")
    public ResponseEntity<Void> deleteTestNage(@PathVariable Long id) {
        log.debug("REST request to delete TestNage : {}", id);
        testNageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
