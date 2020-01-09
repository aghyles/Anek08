package com.popov.t04jh.web.rest;

import com.popov.t04jh.domain.Swimer;
import com.popov.t04jh.service.SwimerService;
import com.popov.t04jh.web.rest.errors.BadRequestAlertException;
import com.popov.t04jh.service.dto.SwimerCriteria;
import com.popov.t04jh.service.SwimerQueryService;

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
 * REST controller for managing {@link com.popov.t04jh.domain.Swimer}.
 */
@RestController
@RequestMapping("/api")
public class SwimerResource {

    private final Logger log = LoggerFactory.getLogger(SwimerResource.class);

    private static final String ENTITY_NAME = "swimer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SwimerService swimerService;

    private final SwimerQueryService swimerQueryService;

    public SwimerResource(SwimerService swimerService, SwimerQueryService swimerQueryService) {
        this.swimerService = swimerService;
        this.swimerQueryService = swimerQueryService;
    }

    /**
     * {@code POST  /swimers} : Create a new swimer.
     *
     * @param swimer the swimer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new swimer, or with status {@code 400 (Bad Request)} if the swimer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/swimers")
    public ResponseEntity<Swimer> createSwimer(@Valid @RequestBody Swimer swimer) throws URISyntaxException {
        log.debug("REST request to save Swimer : {}", swimer);
        if (swimer.getId() != null) {
            throw new BadRequestAlertException("A new swimer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Swimer result = swimerService.save(swimer);
        return ResponseEntity.created(new URI("/api/swimers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /swimers} : Updates an existing swimer.
     *
     * @param swimer the swimer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated swimer,
     * or with status {@code 400 (Bad Request)} if the swimer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the swimer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/swimers")
    public ResponseEntity<Swimer> updateSwimer(@Valid @RequestBody Swimer swimer) throws URISyntaxException {
        log.debug("REST request to update Swimer : {}", swimer);
        if (swimer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Swimer result = swimerService.save(swimer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, swimer.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /swimers} : get all the swimers.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of swimers in body.
     */
    @GetMapping("/swimers")
    public ResponseEntity<List<Swimer>> getAllSwimers(SwimerCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Swimers by criteria: {}", criteria);
        Page<Swimer> page = swimerQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /swimers/count} : count all the swimers.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/swimers/count")
    public ResponseEntity<Long> countSwimers(SwimerCriteria criteria) {
        log.debug("REST request to count Swimers by criteria: {}", criteria);
        return ResponseEntity.ok().body(swimerQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /swimers/:id} : get the "id" swimer.
     *
     * @param id the id of the swimer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the swimer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/swimers/{id}")
    public ResponseEntity<Swimer> getSwimer(@PathVariable Long id) {
        log.debug("REST request to get Swimer : {}", id);
        Optional<Swimer> swimer = swimerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(swimer);
    }

    /**
     * {@code DELETE  /swimers/:id} : delete the "id" swimer.
     *
     * @param id the id of the swimer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/swimers/{id}")
    public ResponseEntity<Void> deleteSwimer(@PathVariable Long id) {
        log.debug("REST request to delete Swimer : {}", id);
        swimerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
