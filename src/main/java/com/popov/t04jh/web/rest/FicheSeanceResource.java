package com.popov.t04jh.web.rest;

import com.popov.t04jh.domain.FicheSeance;
import com.popov.t04jh.service.FicheSeanceService;
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
 * REST controller for managing {@link com.popov.t04jh.domain.FicheSeance}.
 */
@RestController
@RequestMapping("/api")
public class FicheSeanceResource {

    private final Logger log = LoggerFactory.getLogger(FicheSeanceResource.class);

    private static final String ENTITY_NAME = "ficheSeance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FicheSeanceService ficheSeanceService;

    public FicheSeanceResource(FicheSeanceService ficheSeanceService) {
        this.ficheSeanceService = ficheSeanceService;
    }

    /**
     * {@code POST  /fiche-seances} : Create a new ficheSeance.
     *
     * @param ficheSeance the ficheSeance to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ficheSeance, or with status {@code 400 (Bad Request)} if the ficheSeance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fiche-seances")
    public ResponseEntity<FicheSeance> createFicheSeance(@Valid @RequestBody FicheSeance ficheSeance) throws URISyntaxException {
        log.debug("REST request to save FicheSeance : {}", ficheSeance);
        if (ficheSeance.getId() != null) {
            throw new BadRequestAlertException("A new ficheSeance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FicheSeance result = ficheSeanceService.save(ficheSeance);
        return ResponseEntity.created(new URI("/api/fiche-seances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fiche-seances} : Updates an existing ficheSeance.
     *
     * @param ficheSeance the ficheSeance to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheSeance,
     * or with status {@code 400 (Bad Request)} if the ficheSeance is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ficheSeance couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fiche-seances")
    public ResponseEntity<FicheSeance> updateFicheSeance(@Valid @RequestBody FicheSeance ficheSeance) throws URISyntaxException {
        log.debug("REST request to update FicheSeance : {}", ficheSeance);
        if (ficheSeance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FicheSeance result = ficheSeanceService.save(ficheSeance);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ficheSeance.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fiche-seances} : get all the ficheSeances.
     *

     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ficheSeances in body.
     */
    @GetMapping("/fiche-seances")
    public ResponseEntity<List<FicheSeance>> getAllFicheSeances(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of FicheSeances");
        Page<FicheSeance> page;
        if (eagerload) {
            page = ficheSeanceService.findAllWithEagerRelationships(pageable);
        } else {
            page = ficheSeanceService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fiche-seances/:id} : get the "id" ficheSeance.
     *
     * @param id the id of the ficheSeance to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ficheSeance, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fiche-seances/{id}")
    public ResponseEntity<FicheSeance> getFicheSeance(@PathVariable Long id) {
        log.debug("REST request to get FicheSeance : {}", id);
        Optional<FicheSeance> ficheSeance = ficheSeanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ficheSeance);
    }

    /**
     * {@code DELETE  /fiche-seances/:id} : delete the "id" ficheSeance.
     *
     * @param id the id of the ficheSeance to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fiche-seances/{id}")
    public ResponseEntity<Void> deleteFicheSeance(@PathVariable Long id) {
        log.debug("REST request to delete FicheSeance : {}", id);
        ficheSeanceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
