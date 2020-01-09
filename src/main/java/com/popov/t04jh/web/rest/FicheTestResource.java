package com.popov.t04jh.web.rest;

import com.popov.t04jh.domain.FicheTest;
import com.popov.t04jh.service.FicheTestService;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.popov.t04jh.domain.FicheTest}.
 */
@RestController
@RequestMapping("/api")
public class FicheTestResource {

    private final Logger log = LoggerFactory.getLogger(FicheTestResource.class);

    private static final String ENTITY_NAME = "ficheTest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FicheTestService ficheTestService;

    public FicheTestResource(FicheTestService ficheTestService) {
        this.ficheTestService = ficheTestService;
    }

    /**
     * {@code POST  /fiche-tests} : Create a new ficheTest.
     *
     * @param ficheTest the ficheTest to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ficheTest, or with status {@code 400 (Bad Request)} if the ficheTest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fiche-tests")
    public ResponseEntity<FicheTest> createFicheTest(@Valid @RequestBody FicheTest ficheTest) throws URISyntaxException {
        log.debug("REST request to save FicheTest : {}", ficheTest);
        if (ficheTest.getId() != null) {
            throw new BadRequestAlertException("A new ficheTest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FicheTest result = ficheTestService.save(ficheTest);
        return ResponseEntity.created(new URI("/api/fiche-tests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fiche-tests} : Updates an existing ficheTest.
     *
     * @param ficheTest the ficheTest to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheTest,
     * or with status {@code 400 (Bad Request)} if the ficheTest is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ficheTest couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fiche-tests")
    public ResponseEntity<FicheTest> updateFicheTest(@Valid @RequestBody FicheTest ficheTest) throws URISyntaxException {
        log.debug("REST request to update FicheTest : {}", ficheTest);
        if (ficheTest.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FicheTest result = ficheTestService.save(ficheTest);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ficheTest.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fiche-tests} : get all the ficheTests.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ficheTests in body.
     */
    @GetMapping("/fiche-tests")
    public ResponseEntity<List<FicheTest>> getAllFicheTests(Pageable pageable) {
        log.debug("REST request to get a page of FicheTests");
        Page<FicheTest> page = ficheTestService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fiche-tests/:id} : get the "id" ficheTest.
     *
     * @param id the id of the ficheTest to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ficheTest, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fiche-tests/{id}")
    public ResponseEntity<FicheTest> getFicheTest(@PathVariable Long id) {
        log.debug("REST request to get FicheTest : {}", id);
        Optional<FicheTest> ficheTest = ficheTestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ficheTest);
    }

    /**
     * {@code DELETE  /fiche-tests/:id} : delete the "id" ficheTest.
     *
     * @param id the id of the ficheTest to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fiche-tests/{id}")
    public ResponseEntity<Void> deleteFicheTest(@PathVariable Long id) {
        log.debug("REST request to delete FicheTest : {}", id);
        ficheTestService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
