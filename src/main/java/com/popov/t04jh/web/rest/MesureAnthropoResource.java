package com.popov.t04jh.web.rest;

import com.popov.t04jh.domain.MesureAnthropo;
import com.popov.t04jh.service.MesureAnthropoService;
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
 * REST controller for managing {@link com.popov.t04jh.domain.MesureAnthropo}.
 */
@RestController
@RequestMapping("/api")
public class MesureAnthropoResource {

    private final Logger log = LoggerFactory.getLogger(MesureAnthropoResource.class);

    private static final String ENTITY_NAME = "mesureAnthropo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MesureAnthropoService mesureAnthropoService;

    public MesureAnthropoResource(MesureAnthropoService mesureAnthropoService) {
        this.mesureAnthropoService = mesureAnthropoService;
    }

    /**
     * {@code POST  /mesure-anthropos} : Create a new mesureAnthropo.
     *
     * @param mesureAnthropo the mesureAnthropo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mesureAnthropo, or with status {@code 400 (Bad Request)} if the mesureAnthropo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mesure-anthropos")
    public ResponseEntity<MesureAnthropo> createMesureAnthropo(@Valid @RequestBody MesureAnthropo mesureAnthropo) throws URISyntaxException {
        log.debug("REST request to save MesureAnthropo : {}", mesureAnthropo);
        if (mesureAnthropo.getId() != null) {
            throw new BadRequestAlertException("A new mesureAnthropo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MesureAnthropo result = mesureAnthropoService.save(mesureAnthropo);
        return ResponseEntity.created(new URI("/api/mesure-anthropos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mesure-anthropos} : Updates an existing mesureAnthropo.
     *
     * @param mesureAnthropo the mesureAnthropo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mesureAnthropo,
     * or with status {@code 400 (Bad Request)} if the mesureAnthropo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mesureAnthropo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mesure-anthropos")
    public ResponseEntity<MesureAnthropo> updateMesureAnthropo(@Valid @RequestBody MesureAnthropo mesureAnthropo) throws URISyntaxException {
        log.debug("REST request to update MesureAnthropo : {}", mesureAnthropo);
        if (mesureAnthropo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MesureAnthropo result = mesureAnthropoService.save(mesureAnthropo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mesureAnthropo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mesure-anthropos} : get all the mesureAnthropos.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mesureAnthropos in body.
     */
    @GetMapping("/mesure-anthropos")
    public ResponseEntity<List<MesureAnthropo>> getAllMesureAnthropos(Pageable pageable) {
        log.debug("REST request to get a page of MesureAnthropos");
        Page<MesureAnthropo> page = mesureAnthropoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /mesure-anthropos/:id} : get the "id" mesureAnthropo.
     *
     * @param id the id of the mesureAnthropo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mesureAnthropo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mesure-anthropos/{id}")
    public ResponseEntity<MesureAnthropo> getMesureAnthropo(@PathVariable Long id) {
        log.debug("REST request to get MesureAnthropo : {}", id);
        Optional<MesureAnthropo> mesureAnthropo = mesureAnthropoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mesureAnthropo);
    }

    /**
     * {@code DELETE  /mesure-anthropos/:id} : delete the "id" mesureAnthropo.
     *
     * @param id the id of the mesureAnthropo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mesure-anthropos/{id}")
    public ResponseEntity<Void> deleteMesureAnthropo(@PathVariable Long id) {
        log.debug("REST request to delete MesureAnthropo : {}", id);
        mesureAnthropoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
