package com.popov.t04jh.web.rest;

import com.popov.t04jh.domain.TestAutre;
import com.popov.t04jh.service.TestAutreService;
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
 * REST controller for managing {@link com.popov.t04jh.domain.TestAutre}.
 */
@RestController
@RequestMapping("/api")
public class TestAutreResource {

    private final Logger log = LoggerFactory.getLogger(TestAutreResource.class);

    private static final String ENTITY_NAME = "testAutre";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TestAutreService testAutreService;

    public TestAutreResource(TestAutreService testAutreService) {
        this.testAutreService = testAutreService;
    }

    /**
     * {@code POST  /test-autres} : Create a new testAutre.
     *
     * @param testAutre the testAutre to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new testAutre, or with status {@code 400 (Bad Request)} if the testAutre has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/test-autres")
    public ResponseEntity<TestAutre> createTestAutre(@RequestBody TestAutre testAutre) throws URISyntaxException {
        log.debug("REST request to save TestAutre : {}", testAutre);
        if (testAutre.getId() != null) {
            throw new BadRequestAlertException("A new testAutre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TestAutre result = testAutreService.save(testAutre);
        return ResponseEntity.created(new URI("/api/test-autres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /test-autres} : Updates an existing testAutre.
     *
     * @param testAutre the testAutre to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated testAutre,
     * or with status {@code 400 (Bad Request)} if the testAutre is not valid,
     * or with status {@code 500 (Internal Server Error)} if the testAutre couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/test-autres")
    public ResponseEntity<TestAutre> updateTestAutre(@RequestBody TestAutre testAutre) throws URISyntaxException {
        log.debug("REST request to update TestAutre : {}", testAutre);
        if (testAutre.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TestAutre result = testAutreService.save(testAutre);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, testAutre.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /test-autres} : get all the testAutres.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of testAutres in body.
     */
    @GetMapping("/test-autres")
    public ResponseEntity<List<TestAutre>> getAllTestAutres(Pageable pageable) {
        log.debug("REST request to get a page of TestAutres");
        Page<TestAutre> page = testAutreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /test-autres/:id} : get the "id" testAutre.
     *
     * @param id the id of the testAutre to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the testAutre, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/test-autres/{id}")
    public ResponseEntity<TestAutre> getTestAutre(@PathVariable Long id) {
        log.debug("REST request to get TestAutre : {}", id);
        Optional<TestAutre> testAutre = testAutreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(testAutre);
    }

    /**
     * {@code DELETE  /test-autres/:id} : delete the "id" testAutre.
     *
     * @param id the id of the testAutre to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/test-autres/{id}")
    public ResponseEntity<Void> deleteTestAutre(@PathVariable Long id) {
        log.debug("REST request to delete TestAutre : {}", id);
        testAutreService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
