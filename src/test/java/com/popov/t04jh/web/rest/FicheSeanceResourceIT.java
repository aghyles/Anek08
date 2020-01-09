package com.popov.t04jh.web.rest;

import com.popov.t04jh.T04JhApp;
import com.popov.t04jh.domain.FicheSeance;
import com.popov.t04jh.repository.FicheSeanceRepository;
import com.popov.t04jh.service.FicheSeanceService;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.popov.t04jh.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FicheSeanceResource} REST controller.
 */
@SpringBootTest(classes = T04JhApp.class)
public class FicheSeanceResourceIT {

    private static final Long DEFAULT_FICHE_S_NUM = 1L;
    private static final Long UPDATED_FICHE_S_NUM = 2L;

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_OBSERVATION = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATION = "BBBBBBBBBB";

    private static final Long DEFAULT_VOLUME = 1L;
    private static final Long UPDATED_VOLUME = 2L;

    private static final String DEFAULT_BILAN = "AAAAAAAAAA";
    private static final String UPDATED_BILAN = "BBBBBBBBBB";

    @Autowired
    private FicheSeanceRepository ficheSeanceRepository;

    @Mock
    private FicheSeanceRepository ficheSeanceRepositoryMock;

    @Mock
    private FicheSeanceService ficheSeanceServiceMock;

    @Autowired
    private FicheSeanceService ficheSeanceService;

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

    private MockMvc restFicheSeanceMockMvc;

    private FicheSeance ficheSeance;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FicheSeanceResource ficheSeanceResource = new FicheSeanceResource(ficheSeanceService);
        this.restFicheSeanceMockMvc = MockMvcBuilders.standaloneSetup(ficheSeanceResource)
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
    public static FicheSeance createEntity(EntityManager em) {
        FicheSeance ficheSeance = new FicheSeance()
            .ficheSNum(DEFAULT_FICHE_S_NUM)
            .date(DEFAULT_DATE)
            .observation(DEFAULT_OBSERVATION)
            .volume(DEFAULT_VOLUME)
            .bilan(DEFAULT_BILAN);
        return ficheSeance;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FicheSeance createUpdatedEntity(EntityManager em) {
        FicheSeance ficheSeance = new FicheSeance()
            .ficheSNum(UPDATED_FICHE_S_NUM)
            .date(UPDATED_DATE)
            .observation(UPDATED_OBSERVATION)
            .volume(UPDATED_VOLUME)
            .bilan(UPDATED_BILAN);
        return ficheSeance;
    }

    @BeforeEach
    public void initTest() {
        ficheSeance = createEntity(em);
    }

    @Test
    @Transactional
    public void createFicheSeance() throws Exception {
        int databaseSizeBeforeCreate = ficheSeanceRepository.findAll().size();

        // Create the FicheSeance
        restFicheSeanceMockMvc.perform(post("/api/fiche-seances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficheSeance)))
            .andExpect(status().isCreated());

        // Validate the FicheSeance in the database
        List<FicheSeance> ficheSeanceList = ficheSeanceRepository.findAll();
        assertThat(ficheSeanceList).hasSize(databaseSizeBeforeCreate + 1);
        FicheSeance testFicheSeance = ficheSeanceList.get(ficheSeanceList.size() - 1);
        assertThat(testFicheSeance.getFicheSNum()).isEqualTo(DEFAULT_FICHE_S_NUM);
        assertThat(testFicheSeance.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testFicheSeance.getObservation()).isEqualTo(DEFAULT_OBSERVATION);
        assertThat(testFicheSeance.getVolume()).isEqualTo(DEFAULT_VOLUME);
        assertThat(testFicheSeance.getBilan()).isEqualTo(DEFAULT_BILAN);
    }

    @Test
    @Transactional
    public void createFicheSeanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ficheSeanceRepository.findAll().size();

        // Create the FicheSeance with an existing ID
        ficheSeance.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFicheSeanceMockMvc.perform(post("/api/fiche-seances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficheSeance)))
            .andExpect(status().isBadRequest());

        // Validate the FicheSeance in the database
        List<FicheSeance> ficheSeanceList = ficheSeanceRepository.findAll();
        assertThat(ficheSeanceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFicheSNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = ficheSeanceRepository.findAll().size();
        // set the field null
        ficheSeance.setFicheSNum(null);

        // Create the FicheSeance, which fails.

        restFicheSeanceMockMvc.perform(post("/api/fiche-seances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficheSeance)))
            .andExpect(status().isBadRequest());

        List<FicheSeance> ficheSeanceList = ficheSeanceRepository.findAll();
        assertThat(ficheSeanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = ficheSeanceRepository.findAll().size();
        // set the field null
        ficheSeance.setDate(null);

        // Create the FicheSeance, which fails.

        restFicheSeanceMockMvc.perform(post("/api/fiche-seances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficheSeance)))
            .andExpect(status().isBadRequest());

        List<FicheSeance> ficheSeanceList = ficheSeanceRepository.findAll();
        assertThat(ficheSeanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFicheSeances() throws Exception {
        // Initialize the database
        ficheSeanceRepository.saveAndFlush(ficheSeance);

        // Get all the ficheSeanceList
        restFicheSeanceMockMvc.perform(get("/api/fiche-seances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ficheSeance.getId().intValue())))
            .andExpect(jsonPath("$.[*].ficheSNum").value(hasItem(DEFAULT_FICHE_S_NUM.intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].observation").value(hasItem(DEFAULT_OBSERVATION)))
            .andExpect(jsonPath("$.[*].volume").value(hasItem(DEFAULT_VOLUME.intValue())))
            .andExpect(jsonPath("$.[*].bilan").value(hasItem(DEFAULT_BILAN)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllFicheSeancesWithEagerRelationshipsIsEnabled() throws Exception {
        FicheSeanceResource ficheSeanceResource = new FicheSeanceResource(ficheSeanceServiceMock);
        when(ficheSeanceServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restFicheSeanceMockMvc = MockMvcBuilders.standaloneSetup(ficheSeanceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restFicheSeanceMockMvc.perform(get("/api/fiche-seances?eagerload=true"))
        .andExpect(status().isOk());

        verify(ficheSeanceServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllFicheSeancesWithEagerRelationshipsIsNotEnabled() throws Exception {
        FicheSeanceResource ficheSeanceResource = new FicheSeanceResource(ficheSeanceServiceMock);
            when(ficheSeanceServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restFicheSeanceMockMvc = MockMvcBuilders.standaloneSetup(ficheSeanceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restFicheSeanceMockMvc.perform(get("/api/fiche-seances?eagerload=true"))
        .andExpect(status().isOk());

            verify(ficheSeanceServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getFicheSeance() throws Exception {
        // Initialize the database
        ficheSeanceRepository.saveAndFlush(ficheSeance);

        // Get the ficheSeance
        restFicheSeanceMockMvc.perform(get("/api/fiche-seances/{id}", ficheSeance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ficheSeance.getId().intValue()))
            .andExpect(jsonPath("$.ficheSNum").value(DEFAULT_FICHE_S_NUM.intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.observation").value(DEFAULT_OBSERVATION))
            .andExpect(jsonPath("$.volume").value(DEFAULT_VOLUME.intValue()))
            .andExpect(jsonPath("$.bilan").value(DEFAULT_BILAN));
    }

    @Test
    @Transactional
    public void getNonExistingFicheSeance() throws Exception {
        // Get the ficheSeance
        restFicheSeanceMockMvc.perform(get("/api/fiche-seances/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFicheSeance() throws Exception {
        // Initialize the database
        ficheSeanceService.save(ficheSeance);

        int databaseSizeBeforeUpdate = ficheSeanceRepository.findAll().size();

        // Update the ficheSeance
        FicheSeance updatedFicheSeance = ficheSeanceRepository.findById(ficheSeance.getId()).get();
        // Disconnect from session so that the updates on updatedFicheSeance are not directly saved in db
        em.detach(updatedFicheSeance);
        updatedFicheSeance
            .ficheSNum(UPDATED_FICHE_S_NUM)
            .date(UPDATED_DATE)
            .observation(UPDATED_OBSERVATION)
            .volume(UPDATED_VOLUME)
            .bilan(UPDATED_BILAN);

        restFicheSeanceMockMvc.perform(put("/api/fiche-seances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFicheSeance)))
            .andExpect(status().isOk());

        // Validate the FicheSeance in the database
        List<FicheSeance> ficheSeanceList = ficheSeanceRepository.findAll();
        assertThat(ficheSeanceList).hasSize(databaseSizeBeforeUpdate);
        FicheSeance testFicheSeance = ficheSeanceList.get(ficheSeanceList.size() - 1);
        assertThat(testFicheSeance.getFicheSNum()).isEqualTo(UPDATED_FICHE_S_NUM);
        assertThat(testFicheSeance.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testFicheSeance.getObservation()).isEqualTo(UPDATED_OBSERVATION);
        assertThat(testFicheSeance.getVolume()).isEqualTo(UPDATED_VOLUME);
        assertThat(testFicheSeance.getBilan()).isEqualTo(UPDATED_BILAN);
    }

    @Test
    @Transactional
    public void updateNonExistingFicheSeance() throws Exception {
        int databaseSizeBeforeUpdate = ficheSeanceRepository.findAll().size();

        // Create the FicheSeance

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheSeanceMockMvc.perform(put("/api/fiche-seances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficheSeance)))
            .andExpect(status().isBadRequest());

        // Validate the FicheSeance in the database
        List<FicheSeance> ficheSeanceList = ficheSeanceRepository.findAll();
        assertThat(ficheSeanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFicheSeance() throws Exception {
        // Initialize the database
        ficheSeanceService.save(ficheSeance);

        int databaseSizeBeforeDelete = ficheSeanceRepository.findAll().size();

        // Delete the ficheSeance
        restFicheSeanceMockMvc.perform(delete("/api/fiche-seances/{id}", ficheSeance.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FicheSeance> ficheSeanceList = ficheSeanceRepository.findAll();
        assertThat(ficheSeanceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
