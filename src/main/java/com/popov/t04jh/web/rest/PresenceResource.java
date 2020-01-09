package com.popov.t04jh.web.rest;

import com.popov.t04jh.domain.Presence;
import com.popov.t04jh.service.PresenceService;
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
 * REST controller for managing {@link com.popov.t04jh.domain.Presence}.
 */
@RestController
@RequestMapping("/api")
public class PresenceResource {

    private final Logger log = LoggerFactory.getLogger(PresenceResource.class);

    private static final String ENTITY_NAME = "presence";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PresenceService presenceService;

    public PresenceResource(PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    /**
     * {@code POST  /presences} : Create a new presence.
     *
     * @param presence the presence to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new presence, or with status {@code 400 (Bad Request)} if the presence has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/presences")
    public ResponseEntity<Presence> createPresence(@RequestBody Presence presence) throws URISyntaxException {
        log.debug("REST request to save Presence : {}", presence);
        if (presence.getId() != null) {
            throw new BadRequestAlertException("A new presence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Presence result = presenceService.save(presence);
        return ResponseEntity.created(new URI("/api/presences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /presences} : Updates an existing presence.
     *
     * @param presence the presence to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated presence,
     * or with status {@code 400 (Bad Request)} if the presence is not valid,
     * or with status {@code 500 (Internal Server Error)} if the presence couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/presences")
    public ResponseEntity<Presence> updatePresence(@RequestBody Presence presence) throws URISyntaxException {
        log.debug("REST request to update Presence : {}", presence);
        if (presence.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Presence result = presenceService.save(presence);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, presence.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /presences} : get all the presences.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of presences in body.
     */
    @GetMapping("/presences")
    public ResponseEntity<List<Presence>> getAllPresences(Pageable pageable) {
        log.debug("REST request to get a page of Presences");
        Page<Presence> page = presenceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /presences/:id} : get the "id" presence.
     *
     * @param id the id of the presence to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the presence, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/presences/{id}")
    public ResponseEntity<Presence> getPresence(@PathVariable Long id) {
        log.debug("REST request to get Presence : {}", id);
        Optional<Presence> presence = presenceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(presence);
    }

    /**
     * {@code DELETE  /presences/:id} : delete the "id" presence.
     *
     * @param id the id of the presence to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/presences/{id}")
    public ResponseEntity<Void> deletePresence(@PathVariable Long id) {
        log.debug("REST request to delete Presence : {}", id);
        presenceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
