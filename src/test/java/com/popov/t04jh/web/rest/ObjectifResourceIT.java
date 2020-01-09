package com.popov.t04jh.web.rest;

import com.popov.t04jh.T04JhApp;
import com.popov.t04jh.domain.Objectif;
import com.popov.t04jh.repository.ObjectifRepository;
import com.popov.t04jh.service.ObjectifService;
import com.popov.t04jh.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.popov.t04jh.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ObjectifResource} REST controller.
 */
@SpringBootTest(classes = T04JhApp.class)
public class ObjectifResourceIT {

    private static final String DEFAULT_OBJECTIF_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OBJECTIF_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ObjectifRepository objectifRepository;

    @Mock
    private ObjectifRepository objectifRepositoryMock;

    @Mock
    private ObjectifService objectifServiceMock;

    @Autowired
    private ObjectifService objectifService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restObjectifMockMvc;

    private Objectif objectif;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ObjectifResource objectifResource = new ObjectifResource(objectifService);
        this.restObjectifMockMvc = MockMvcBuilders.standaloneSetup(objectifResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Objectif createEntity(EntityManager em) {
        Objectif objectif = new Objectif()
            .objectifName(DEFAULT_OBJECTIF_NAME)
            .description(DEFAULT_DESCRIPTION);
        return objectif;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Objectif createUpdatedEntity(EntityManager em) {
        Objectif objectif = new Objectif()
            .objectifName(UPDATED_OBJECTIF_NAME)
            .description(UPDATED_DESCRIPTION);
        return objectif;
    }

    @BeforeEach
    public void initTest() {
        objectif = createEntity(em);
    }

    @Test
    @Transactional
    public void createObjectif() throws Exception {
        int databaseSizeBeforeCreate = objectifRepository.findAll().size();

        // Create the Objectif
        restObjectifMockMvc.perform(post("/api/objectifs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(objectif)))
            .andExpect(status().isCreated());

        // Validate the Objectif in the database
        List<Objectif> objectifList = objectifRepository.findAll();
        assertThat(objectifList).hasSize(databaseSizeBeforeCreate + 1);
        Objectif testObjectif = objectifList.get(objectifList.size() - 1);
        assertThat(testObjectif.getObjectifName()).isEqualTo(DEFAULT_OBJECTIF_NAME);
        assertThat(testObjectif.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createObjectifWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = objectifRepository.findAll().size();

        // Create the Objectif with an existing ID
        objectif.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restObjectifMockMvc.perform(post("/api/objectifs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(objectif)))
            .andExpect(status().isBadRequest());

        // Validate the Objectif in the database
        List<Objectif> objectifList = objectifRepository.findAll();
        assertThat(objectifList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkObjectifNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = objectifRepository.findAll().size();
        // set the field null
        objectif.setObjectifName(null);

        // Create the Objectif, which fails.

        restObjectifMockMvc.perform(post("/api/objectifs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(objectif)))
            .andExpect(status().isBadRequest());

        List<Objectif> objectifList = objectifRepository.findAll();
        assertThat(objectifList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllObjectifs() throws Exception {
        // Initialize the database
        objectifRepository.saveAndFlush(objectif);

        // Get all the objectifList
        restObjectifMockMvc.perform(get("/api/objectifs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(objectif.getId().intValue())))
            .andExpect(jsonPath("$.[*].objectifName").value(hasItem(DEFAULT_OBJECTIF_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllObjectifsWithEagerRelationshipsIsEnabled() throws Exception {
        ObjectifResource objectifResource = new ObjectifResource(objectifServiceMock);
        when(objectifServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restObjectifMockMvc = MockMvcBuilders.standaloneSetup(objectifResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restObjectifMockMvc.perform(get("/api/objectifs?eagerload=true"))
        .andExpect(status().isOk());

        verify(objectifServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllObjectifsWithEagerRelationshipsIsNotEnabled() throws Exception {
        ObjectifResource objectifResource = new ObjectifResource(objectifServiceMock);
            when(objectifServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restObjectifMockMvc = MockMvcBuilders.standaloneSetup(objectifResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restObjectifMockMvc.perform(get("/api/objectifs?eagerload=true"))
        .andExpect(status().isOk());

            verify(objectifServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getObjectif() throws Exception {
        // Initialize the database
        objectifRepository.saveAndFlush(objectif);

        // Get the objectif
        restObjectifMockMvc.perform(get("/api/objectifs/{id}", objectif.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(objectif.getId().intValue()))
            .andExpect(jsonPath("$.objectifName").value(DEFAULT_OBJECTIF_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    public void getNonExistingObjectif() throws Exception {
        // Get the objectif
        restObjectifMockMvc.perform(get("/api/objectifs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateObjectif() throws Exception {
        // Initialize the database
        objectifService.save(objectif);

        int databaseSizeBeforeUpdate = objectifRepository.findAll().size();

        // Update the objectif
        Objectif updatedObjectif = objectifRepository.findById(objectif.getId()).get();
        // Disconnect from session so that the updates on updatedObjectif are not directly saved in db
        em.detach(updatedObjectif);
        updatedObjectif
            .objectifName(UPDATED_OBJECTIF_NAME)
            .description(UPDATED_DESCRIPTION);

        restObjectifMockMvc.perform(put("/api/objectifs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedObjectif)))
            .andExpect(status().isOk());

        // Validate the Objectif in the database
        List<Objectif> objectifList = objectifRepository.findAll();
        assertThat(objectifList).hasSize(databaseSizeBeforeUpdate);
        Objectif testObjectif = objectifList.get(objectifList.size() - 1);
        assertThat(testObjectif.getObjectifName()).isEqualTo(UPDATED_OBJECTIF_NAME);
        assertThat(testObjectif.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingObjectif() throws Exception {
        int databaseSizeBeforeUpdate = objectifRepository.findAll().size();

        // Create the Objectif

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restObjectifMockMvc.perform(put("/api/objectifs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(objectif)))
            .andExpect(status().isBadRequest());

        // Validate the Objectif in the database
        List<Objectif> objectifList = objectifRepository.findAll();
        assertThat(objectifList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteObjectif() throws Exception {
        // Initialize the database
        objectifService.save(objectif);

        int databaseSizeBeforeDelete = objectifRepository.findAll().size();

        // Delete the objectif
        restObjectifMockMvc.perform(delete("/api/objectifs/{id}", objectif.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Objectif> objectifList = objectifRepository.findAll();
        assertThat(objectifList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
