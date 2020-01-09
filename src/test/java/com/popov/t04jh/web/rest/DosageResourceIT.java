package com.popov.t04jh.web.rest;

import com.popov.t04jh.T04JhApp;
import com.popov.t04jh.domain.Dosage;
import com.popov.t04jh.repository.DosageRepository;
import com.popov.t04jh.service.DosageService;
import com.popov.t04jh.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.popov.t04jh.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DosageResource} REST controller.
 */
@SpringBootTest(classes = T04JhApp.class)
public class DosageResourceIT {

    private static final String DEFAULT_DOSAGE_S = "AAAAAAAAAA";
    private static final String UPDATED_DOSAGE_S = "BBBBBBBBBB";

    private static final Long DEFAULT_DOSAGELONG = 1L;
    private static final Long UPDATED_DOSAGELONG = 2L;

    @Autowired
    private DosageRepository dosageRepository;

    @Autowired
    private DosageService dosageService;

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

    private MockMvc restDosageMockMvc;

    private Dosage dosage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DosageResource dosageResource = new DosageResource(dosageService);
        this.restDosageMockMvc = MockMvcBuilders.standaloneSetup(dosageResource)
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
    public static Dosage createEntity(EntityManager em) {
        Dosage dosage = new Dosage()
            .dosageS(DEFAULT_DOSAGE_S)
            .dosagelong(DEFAULT_DOSAGELONG);
        return dosage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dosage createUpdatedEntity(EntityManager em) {
        Dosage dosage = new Dosage()
            .dosageS(UPDATED_DOSAGE_S)
            .dosagelong(UPDATED_DOSAGELONG);
        return dosage;
    }

    @BeforeEach
    public void initTest() {
        dosage = createEntity(em);
    }

    @Test
    @Transactional
    public void createDosage() throws Exception {
        int databaseSizeBeforeCreate = dosageRepository.findAll().size();

        // Create the Dosage
        restDosageMockMvc.perform(post("/api/dosages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dosage)))
            .andExpect(status().isCreated());

        // Validate the Dosage in the database
        List<Dosage> dosageList = dosageRepository.findAll();
        assertThat(dosageList).hasSize(databaseSizeBeforeCreate + 1);
        Dosage testDosage = dosageList.get(dosageList.size() - 1);
        assertThat(testDosage.getDosageS()).isEqualTo(DEFAULT_DOSAGE_S);
        assertThat(testDosage.getDosagelong()).isEqualTo(DEFAULT_DOSAGELONG);
    }

    @Test
    @Transactional
    public void createDosageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dosageRepository.findAll().size();

        // Create the Dosage with an existing ID
        dosage.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDosageMockMvc.perform(post("/api/dosages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dosage)))
            .andExpect(status().isBadRequest());

        // Validate the Dosage in the database
        List<Dosage> dosageList = dosageRepository.findAll();
        assertThat(dosageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDosageSIsRequired() throws Exception {
        int databaseSizeBeforeTest = dosageRepository.findAll().size();
        // set the field null
        dosage.setDosageS(null);

        // Create the Dosage, which fails.

        restDosageMockMvc.perform(post("/api/dosages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dosage)))
            .andExpect(status().isBadRequest());

        List<Dosage> dosageList = dosageRepository.findAll();
        assertThat(dosageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDosagelongIsRequired() throws Exception {
        int databaseSizeBeforeTest = dosageRepository.findAll().size();
        // set the field null
        dosage.setDosagelong(null);

        // Create the Dosage, which fails.

        restDosageMockMvc.perform(post("/api/dosages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dosage)))
            .andExpect(status().isBadRequest());

        List<Dosage> dosageList = dosageRepository.findAll();
        assertThat(dosageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDosages() throws Exception {
        // Initialize the database
        dosageRepository.saveAndFlush(dosage);

        // Get all the dosageList
        restDosageMockMvc.perform(get("/api/dosages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dosage.getId().intValue())))
            .andExpect(jsonPath("$.[*].dosageS").value(hasItem(DEFAULT_DOSAGE_S)))
            .andExpect(jsonPath("$.[*].dosagelong").value(hasItem(DEFAULT_DOSAGELONG.intValue())));
    }
    
    @Test
    @Transactional
    public void getDosage() throws Exception {
        // Initialize the database
        dosageRepository.saveAndFlush(dosage);

        // Get the dosage
        restDosageMockMvc.perform(get("/api/dosages/{id}", dosage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dosage.getId().intValue()))
            .andExpect(jsonPath("$.dosageS").value(DEFAULT_DOSAGE_S))
            .andExpect(jsonPath("$.dosagelong").value(DEFAULT_DOSAGELONG.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDosage() throws Exception {
        // Get the dosage
        restDosageMockMvc.perform(get("/api/dosages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDosage() throws Exception {
        // Initialize the database
        dosageService.save(dosage);

        int databaseSizeBeforeUpdate = dosageRepository.findAll().size();

        // Update the dosage
        Dosage updatedDosage = dosageRepository.findById(dosage.getId()).get();
        // Disconnect from session so that the updates on updatedDosage are not directly saved in db
        em.detach(updatedDosage);
        updatedDosage
            .dosageS(UPDATED_DOSAGE_S)
            .dosagelong(UPDATED_DOSAGELONG);

        restDosageMockMvc.perform(put("/api/dosages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDosage)))
            .andExpect(status().isOk());

        // Validate the Dosage in the database
        List<Dosage> dosageList = dosageRepository.findAll();
        assertThat(dosageList).hasSize(databaseSizeBeforeUpdate);
        Dosage testDosage = dosageList.get(dosageList.size() - 1);
        assertThat(testDosage.getDosageS()).isEqualTo(UPDATED_DOSAGE_S);
        assertThat(testDosage.getDosagelong()).isEqualTo(UPDATED_DOSAGELONG);
    }

    @Test
    @Transactional
    public void updateNonExistingDosage() throws Exception {
        int databaseSizeBeforeUpdate = dosageRepository.findAll().size();

        // Create the Dosage

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDosageMockMvc.perform(put("/api/dosages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dosage)))
            .andExpect(status().isBadRequest());

        // Validate the Dosage in the database
        List<Dosage> dosageList = dosageRepository.findAll();
        assertThat(dosageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDosage() throws Exception {
        // Initialize the database
        dosageService.save(dosage);

        int databaseSizeBeforeDelete = dosageRepository.findAll().size();

        // Delete the dosage
        restDosageMockMvc.perform(delete("/api/dosages/{id}", dosage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Dosage> dosageList = dosageRepository.findAll();
        assertThat(dosageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
