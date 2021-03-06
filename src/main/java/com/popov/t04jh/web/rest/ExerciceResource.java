package com.popov.t04jh.web.rest;

import com.popov.t04jh.domain.Exercice;
import com.popov.t04jh.service.ExerciceService;
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
 * REST controller for managing {@link com.popov.t04jh.domain.Exercice}.
 */
@RestController
@RequestMapping("/api")
public class ExerciceResource {

    private final Logger log = LoggerFactory.getLogger(ExerciceResource.class);

    private static final String ENTITY_NAME = "exercice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExerciceService exerciceService;

    public ExerciceResource(ExerciceService exerciceService) {
        this.exerciceService = exerciceService;
    }

    /**
     * {@code POST  /exercices} : Create a new exercice.
     *
     * @param exercice the exercice to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new exercice, or with status {@code 400 (Bad Request)} if the exercice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/exercices")
    public ResponseEntity<Exercice> createExercice(@Valid @RequestBody Exercice exercice) throws URISyntaxException {
        log.debug("REST request to save Exercice : {}", exercice);
        if (exercice.getId() != null) {
            throw new BadRequestAlertException("A new exercice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Exercice result = exerciceService.save(exercice);
        return ResponseEntity.created(new URI("/api/exercices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /exercices} : Updates an existing exercice.
     *
     * @param exercice the exercice to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated exercice,
     * or with status {@code 400 (Bad Request)} if the exercice is not valid,
     * or with status {@code 500 (Internal Server Error)} if the exercice couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/exercices")
    public ResponseEntity<Exercice> updateExercice(@Valid @RequestBody Exercice exercice) throws URISyntaxException {
        log.debug("REST request to update Exercice : {}", exercice);
        if (exercice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Exercice result = exerciceService.save(exercice);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, exercice.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /exercices} : get all the exercices.
     *

     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of exercices in body.
     */
    @GetMapping("/exercices")
    public ResponseEntity<List<Exercice>> getAllExercices(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Exercices");
        Page<Exercice> page;
        if (eagerload) {
            page = exerciceService.findAllWithEagerRelationships(pageable);
        } else {
            page = exerciceService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /exercices/:id} : get the "id" exercice.
     *
     * @param id the id of the exercice to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the exercice, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/exercices/{id}")
    public ResponseEntity<Exercice> getExercice(@PathVariable Long id) {
        log.debug("REST request to get Exercice : {}", id);
        Optional<Exercice> exercice = exerciceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(exercice);
    }

    /**
     * {@code DELETE  /exercices/:id} : delete the "id" exercice.
     *
     * @param id the id of the exercice to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/exercices/{id}")
    public ResponseEntity<Void> deleteExercice(@PathVariable Long id) {
        log.debug("REST request to delete Exercice : {}", id);
        exerciceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
