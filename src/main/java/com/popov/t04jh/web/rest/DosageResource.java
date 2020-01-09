package com.popov.t04jh.web.rest;

import com.popov.t04jh.domain.Dosage;
import com.popov.t04jh.service.DosageService;
import com.popov.t04jh.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.popov.t04jh.domain.Dosage}.
 */
@RestController
@RequestMapping("/api")
public class DosageResource {

    private final Logger log = LoggerFactory.getLogger(DosageResource.class);

    private static final String ENTITY_NAME = "dosage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DosageService dosageService;

    public DosageResource(DosageService dosageService) {
        this.dosageService = dosageService;
    }

    /**
     * {@code POST  /dosages} : Create a new dosage.
     *
     * @param dosage the dosage to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dosage, or with status {@code 400 (Bad Request)} if the dosage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dosages")
    public ResponseEntity<Dosage> createDosage(@Valid @RequestBody Dosage dosage) throws URISyntaxException {
        log.debug("REST request to save Dosage : {}", dosage);
        if (dosage.getId() != null) {
            throw new BadRequestAlertException("A new dosage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Dosage result = dosageService.save(dosage);
        return ResponseEntity.created(new URI("/api/dosages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dosages} : Updates an existing dosage.
     *
     * @param dosage the dosage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dosage,
     * or with status {@code 400 (Bad Request)} if the dosage is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dosage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dosages")
    public ResponseEntity<Dosage> updateDosage(@Valid @RequestBody Dosage dosage) throws URISyntaxException {
        log.debug("REST request to update Dosage : {}", dosage);
        if (dosage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Dosage result = dosageService.save(dosage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dosage.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dosages} : get all the dosages.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dosages in body.
     */
    @GetMapping("/dosages")
    public List<Dosage> getAllDosages() {
        log.debug("REST request to get all Dosages");
        return dosageService.findAll();
    }

    /**
     * {@code GET  /dosages/:id} : get the "id" dosage.
     *
     * @param id the id of the dosage to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dosage, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dosages/{id}")
    public ResponseEntity<Dosage> getDosage(@PathVariable Long id) {
        log.debug("REST request to get Dosage : {}", id);
        Optional<Dosage> dosage = dosageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dosage);
    }

    /**
     * {@code DELETE  /dosages/:id} : delete the "id" dosage.
     *
     * @param id the id of the dosage to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dosages/{id}")
    public ResponseEntity<Void> deleteDosage(@PathVariable Long id) {
        log.debug("REST request to delete Dosage : {}", id);
        dosageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
