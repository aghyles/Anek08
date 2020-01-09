package com.popov.t04jh.web.rest;

import com.popov.t04jh.T04JhApp;
import com.popov.t04jh.domain.FicheTest;
import com.popov.t04jh.repository.FicheTestRepository;
import com.popov.t04jh.service.FicheTestService;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.popov.t04jh.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FicheTestResource} REST controller.
 */
@SpringBootTest(classes = T04JhApp.class)
public class FicheTestResourceIT {

    private static final String DEFAULT_TYPE_TEST = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_TEST = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATETEST = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATETEST = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private FicheTestRepository ficheTestRepository;

    @Autowired
    private FicheTestService ficheTestService;

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

    private MockMvc restFicheTestMockMvc;

    private FicheTest ficheTest;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FicheTestResource ficheTestResource = new FicheTestResource(ficheTestService);
        this.restFicheTestMockMvc = MockMvcBuilders.standaloneSetup(ficheTestResource)
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
    public static FicheTest createEntity(EntityManager em) {
        FicheTest ficheTest = new FicheTest()
            .typeTest(DEFAULT_TYPE_TEST)
            .datetest(DEFAULT_DATETEST);
        return ficheTest;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FicheTest createUpdatedEntity(EntityManager em) {
        FicheTest ficheTest = new FicheTest()
            .typeTest(UPDATED_TYPE_TEST)
            .datetest(UPDATED_DATETEST);
        return ficheTest;
    }

    @BeforeEach
    public void initTest() {
        ficheTest = createEntity(em);
    }

    @Test
    @Transactional
    public void createFicheTest() throws Exception {
        int databaseSizeBeforeCreate = ficheTestRepository.findAll().size();

        // Create the FicheTest
        restFicheTestMockMvc.perform(post("/api/fiche-tests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficheTest)))
            .andExpect(status().isCreated());

        // Validate the FicheTest in the database
        List<FicheTest> ficheTestList = ficheTestRepository.findAll();
        assertThat(ficheTestList).hasSize(databaseSizeBeforeCreate + 1);
        FicheTest testFicheTest = ficheTestList.get(ficheTestList.size() - 1);
        assertThat(testFicheTest.getTypeTest()).isEqualTo(DEFAULT_TYPE_TEST);
        assertThat(testFicheTest.getDatetest()).isEqualTo(DEFAULT_DATETEST);
    }

    @Test
    @Transactional
    public void createFicheTestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ficheTestRepository.findAll().size();

        // Create the FicheTest with an existing ID
        ficheTest.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFicheTestMockMvc.perform(post("/api/fiche-tests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficheTest)))
            .andExpect(status().isBadRequest());

        // Validate the FicheTest in the database
        List<FicheTest> ficheTestList = ficheTestRepository.findAll();
        assertThat(ficheTestList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTypeTestIsRequired() throws Exception {
        int databaseSizeBeforeTest = ficheTestRepository.findAll().size();
        // set the field null
        ficheTest.setTypeTest(null);

        // Create the FicheTest, which fails.

        restFicheTestMockMvc.perform(post("/api/fiche-tests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficheTest)))
            .andExpect(status().isBadRequest());

        List<FicheTest> ficheTestList = ficheTestRepository.findAll();
        assertThat(ficheTestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDatetestIsRequired() throws Exception {
        int databaseSizeBeforeTest = ficheTestRepository.findAll().size();
        // set the field null
        ficheTest.setDatetest(null);

        // Create the FicheTest, which fails.

        restFicheTestMockMvc.perform(post("/api/fiche-tests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficheTest)))
            .andExpect(status().isBadRequest());

        List<FicheTest> ficheTestList = ficheTestRepository.findAll();
        assertThat(ficheTestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFicheTests() throws Exception {
        // Initialize the database
        ficheTestRepository.saveAndFlush(ficheTest);

        // Get all the ficheTestList
        restFicheTestMockMvc.perform(get("/api/fiche-tests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ficheTest.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeTest").value(hasItem(DEFAULT_TYPE_TEST)))
            .andExpect(jsonPath("$.[*].datetest").value(hasItem(DEFAULT_DATETEST.toString())));
    }
    
    @Test
    @Transactional
    public void getFicheTest() throws Exception {
        // Initialize the database
        ficheTestRepository.saveAndFlush(ficheTest);

        // Get the ficheTest
        restFicheTestMockMvc.perform(get("/api/fiche-tests/{id}", ficheTest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ficheTest.getId().intValue()))
            .andExpect(jsonPath("$.typeTest").value(DEFAULT_TYPE_TEST))
            .andExpect(jsonPath("$.datetest").value(DEFAULT_DATETEST.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFicheTest() throws Exception {
        // Get the ficheTest
        restFicheTestMockMvc.perform(get("/api/fiche-tests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFicheTest() throws Exception {
        // Initialize the database
        ficheTestService.save(ficheTest);

        int databaseSizeBeforeUpdate = ficheTestRepository.findAll().size();

        // Update the ficheTest
        FicheTest updatedFicheTest = ficheTestRepository.findById(ficheTest.getId()).get();
        // Disconnect from session so that the updates on updatedFicheTest are not directly saved in db
        em.detach(updatedFicheTest);
        updatedFicheTest
            .typeTest(UPDATED_TYPE_TEST)
            .datetest(UPDATED_DATETEST);

        restFicheTestMockMvc.perform(put("/api/fiche-tests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFicheTest)))
            .andExpect(status().isOk());

        // Validate the FicheTest in the database
        List<FicheTest> ficheTestList = ficheTestRepository.findAll();
        assertThat(ficheTestList).hasSize(databaseSizeBeforeUpdate);
        FicheTest testFicheTest = ficheTestList.get(ficheTestList.size() - 1);
        assertThat(testFicheTest.getTypeTest()).isEqualTo(UPDATED_TYPE_TEST);
        assertThat(testFicheTest.getDatetest()).isEqualTo(UPDATED_DATETEST);
    }

    @Test
    @Transactional
    public void updateNonExistingFicheTest() throws Exception {
        int databaseSizeBeforeUpdate = ficheTestRepository.findAll().size();

        // Create the FicheTest

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheTestMockMvc.perform(put("/api/fiche-tests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ficheTest)))
            .andExpect(status().isBadRequest());

        // Validate the FicheTest in the database
        List<FicheTest> ficheTestList = ficheTestRepository.findAll();
        assertThat(ficheTestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFicheTest() throws Exception {
        // Initialize the database
        ficheTestService.save(ficheTest);

        int databaseSizeBeforeDelete = ficheTestRepository.findAll().size();

        // Delete the ficheTest
        restFicheTestMockMvc.perform(delete("/api/fiche-tests/{id}", ficheTest.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FicheTest> ficheTestList = ficheTestRepository.findAll();
        assertThat(ficheTestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
