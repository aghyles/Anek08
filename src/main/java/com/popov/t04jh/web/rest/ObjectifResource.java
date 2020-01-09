package com.popov.t04jh.web.rest;

import com.popov.t04jh.domain.Objectif;
import com.popov.t04jh.service.ObjectifService;
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
 * REST controller for managing {@link com.popov.t04jh.domain.Objectif}.
 */
@RestController
@RequestMapping("/api")
public class ObjectifResource {

    private final Logger log = LoggerFactory.getLogger(ObjectifResource.class);

    private static final String ENTITY_NAME = "objectif";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ObjectifService objectifService;

    public ObjectifResource(ObjectifService objectifService) {
        this.objectifService = objectifService;
    }

    /**
     * {@code POST  /objectifs} : Create a new objectif.
     *
     * @param objectif the objectif to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new objectif, or with status {@code 400 (Bad Request)} if the objectif has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/objectifs")
    public ResponseEntity<Objectif> createObjectif(@Valid @RequestBody Objectif objectif) throws URISyntaxException {
        log.debug("REST request to save Objectif : {}", objectif);
        if (objectif.getId() != null) {
            throw new BadRequestAlertException("A new objectif cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Objectif result = objectifService.save(objectif);
        return ResponseEntity.created(new URI("/api/objectifs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /objectifs} : Updates an existing objectif.
     *
     * @param objectif the objectif to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated objectif,
     * or with status {@code 400 (Bad Request)} if the objectif is not valid,
     * or with status {@code 500 (Internal Server Error)} if the objectif couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/objectifs")
    public ResponseEntity<Objectif> updateObjectif(@Valid @RequestBody Objectif objectif) throws URISyntaxException {
        log.debug("REST request to update Objectif : {}", objectif);
        if (objectif.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Objectif result = objectifService.save(objectif);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, objectif.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /objectifs} : get all the objectifs.
     *

     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of objectifs in body.
     */
    @GetMapping("/objectifs")
    public ResponseEntity<List<Objectif>> getAllObjectifs(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Objectifs");
        Page<Objectif> page;
        if (eagerload) {
            page = objectifService.findAllWithEagerRelationships(pageable);
        } else {
            page = objectifService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /objectifs/:id} : get the "id" objectif.
     *
     * @param id the id of the objectif to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the objectif, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/objectifs/{id}")
    public ResponseEntity<Objectif> getObjectif(@PathVariable Long id) {
        log.debug("REST request to get Objectif : {}", id);
        Optional<Objectif> objectif = objectifService.findOne(id);
        return ResponseUtil.wrapOrNotFound(objectif);
    }

    /**
     * {@code DELETE  /objectifs/:id} : delete the "id" objectif.
     *
     * @param id the id of the objectif to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/objectifs/{id}")
    public ResponseEntity<Void> deleteObjectif(@PathVariable Long id) {
        log.debug("REST request to delete Objectif : {}", id);
        objectifService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
