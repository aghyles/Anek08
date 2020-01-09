package com.popov.t04jh.web.rest;

import com.popov.t04jh.domain.TestEtude;
import com.popov.t04jh.service.TestEtudeService;
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
 * REST controller for managing {@link com.popov.t04jh.domain.TestEtude}.
 */
@RestController
@RequestMapping("/api")
public class TestEtudeResource {

    private final Logger log = LoggerFactory.getLogger(TestEtudeResource.class);

    private static final String ENTITY_NAME = "testEtude";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TestEtudeService testEtudeService;

    public TestEtudeResource(TestEtudeService testEtudeService) {
        this.testEtudeService = testEtudeService;
    }

    /**
     * {@code POST  /test-etudes} : Create a new testEtude.
     *
     * @param testEtude the testEtude to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new testEtude, or with status {@code 400 (Bad Request)} if the testEtude has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/test-etudes")
    public ResponseEntity<TestEtude> createTestEtude(@RequestBody TestEtude testEtude) throws URISyntaxException {
        log.debug("REST request to save TestEtude : {}", testEtude);
        if (testEtude.getId() != null) {
            throw new BadRequestAlertException("A new testEtude cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TestEtude result = testEtudeService.save(testEtude);
        return ResponseEntity.created(new URI("/api/test-etudes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /test-etudes} : Updates an existing testEtude.
     *
     * @param testEtude the testEtude to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated testEtude,
     * or with status {@code 400 (Bad Request)} if the testEtude is not valid,
     * or with status {@code 500 (Internal Server Error)} if the testEtude couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/test-etudes")
    public ResponseEntity<TestEtude> updateTestEtude(@RequestBody TestEtude testEtude) throws URISyntaxException {
        log.debug("REST request to update TestEtude : {}", testEtude);
        if (testEtude.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TestEtude result = testEtudeService.save(testEtude);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, testEtude.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /test-etudes} : get all the testEtudes.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of testEtudes in body.
     */
    @GetMapping("/test-etudes")
    public ResponseEntity<List<TestEtude>> getAllTestEtudes(Pageable pageable) {
        log.debug("REST request to get a page of TestEtudes");
        Page<TestEtude> page = testEtudeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /test-etudes/:id} : get the "id" testEtude.
     *
     * @param id the id of the testEtude to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the testEtude, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/test-etudes/{id}")
    public ResponseEntity<TestEtude> getTestEtude(@PathVariable Long id) {
        log.debug("REST request to get TestEtude : {}", id);
        Optional<TestEtude> testEtude = testEtudeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(testEtude);
    }

    /**
     * {@code DELETE  /test-etudes/:id} : delete the "id" testEtude.
     *
     * @param id the id of the testEtude to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/test-etudes/{id}")
    public ResponseEntity<Void> deleteTestEtude(@PathVariable Long id) {
        log.debug("REST request to delete TestEtude : {}", id);
        testEtudeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
