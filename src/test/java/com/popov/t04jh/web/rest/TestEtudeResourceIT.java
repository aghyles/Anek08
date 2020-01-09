package com.popov.t04jh.web.rest;

import com.popov.t04jh.T04JhApp;
import com.popov.t04jh.domain.TestEtude;
import com.popov.t04jh.repository.TestEtudeRepository;
import com.popov.t04jh.service.TestEtudeService;
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
 * Integration tests for the {@link TestEtudeResource} REST controller.
 */
@SpringBootTest(classes = T04JhApp.class)
public class TestEtudeResourceIT {

    private static final String DEFAULT_TEST_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TEST_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_LEVEL_STUDY = "AAAAAAAAAA";
    private static final String UPDATED_LEVEL_STUDY = "BBBBBBBBBB";

    private static final Long DEFAULT_AVERAGE = 1L;
    private static final Long UPDATED_AVERAGE = 2L;

    @Autowired
    private TestEtudeRepository testEtudeRepository;

    @Autowired
    private TestEtudeService testEtudeService;

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

    private MockMvc restTestEtudeMockMvc;

    private TestEtude testEtude;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TestEtudeResource testEtudeResource = new TestEtudeResource(testEtudeService);
        this.restTestEtudeMockMvc = MockMvcBuilders.standaloneSetup(testEtudeResource)
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
    public static TestEtude createEntity(EntityManager em) {
        TestEtude testEtude = new TestEtude()
            .testTitle(DEFAULT_TEST_TITLE)
            .levelStudy(DEFAULT_LEVEL_STUDY)
            .average(DEFAULT_AVERAGE);
        return testEtude;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TestEtude createUpdatedEntity(EntityManager em) {
        TestEtude testEtude = new TestEtude()
            .testTitle(UPDATED_TEST_TITLE)
            .levelStudy(UPDATED_LEVEL_STUDY)
            .average(UPDATED_AVERAGE);
        return testEtude;
    }

    @BeforeEach
    public void initTest() {
        testEtude = createEntity(em);
    }

    @Test
    @Transactional
    public void createTestEtude() throws Exception {
        int databaseSizeBeforeCreate = testEtudeRepository.findAll().size();

        // Create the TestEtude
        restTestEtudeMockMvc.perform(post("/api/test-etudes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(testEtude)))
            .andExpect(status().isCreated());

        // Validate the TestEtude in the database
        List<TestEtude> testEtudeList = testEtudeRepository.findAll();
        assertThat(testEtudeList).hasSize(databaseSizeBeforeCreate + 1);
        TestEtude testTestEtude = testEtudeList.get(testEtudeList.size() - 1);
        assertThat(testTestEtude.getTestTitle()).isEqualTo(DEFAULT_TEST_TITLE);
        assertThat(testTestEtude.getLevelStudy()).isEqualTo(DEFAULT_LEVEL_STUDY);
        assertThat(testTestEtude.getAverage()).isEqualTo(DEFAULT_AVERAGE);
    }

    @Test
    @Transactional
    public void createTestEtudeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = testEtudeRepository.findAll().size();

        // Create the TestEtude with an existing ID
        testEtude.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTestEtudeMockMvc.perform(post("/api/test-etudes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(testEtude)))
            .andExpect(status().isBadRequest());

        // Validate the TestEtude in the database
        List<TestEtude> testEtudeList = testEtudeRepository.findAll();
        assertThat(testEtudeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTestEtudes() throws Exception {
        // Initialize the database
        testEtudeRepository.saveAndFlush(testEtude);

        // Get all the testEtudeList
        restTestEtudeMockMvc.perform(get("/api/test-etudes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(testEtude.getId().intValue())))
            .andExpect(jsonPath("$.[*].testTitle").value(hasItem(DEFAULT_TEST_TITLE)))
            .andExpect(jsonPath("$.[*].levelStudy").value(hasItem(DEFAULT_LEVEL_STUDY)))
            .andExpect(jsonPath("$.[*].average").value(hasItem(DEFAULT_AVERAGE.intValue())));
    }
    
    @Test
    @Transactional
    public void getTestEtude() throws Exception {
        // Initialize the database
        testEtudeRepository.saveAndFlush(testEtude);

        // Get the testEtude
        restTestEtudeMockMvc.perform(get("/api/test-etudes/{id}", testEtude.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(testEtude.getId().intValue()))
            .andExpect(jsonPath("$.testTitle").value(DEFAULT_TEST_TITLE))
            .andExpect(jsonPath("$.levelStudy").value(DEFAULT_LEVEL_STUDY))
            .andExpect(jsonPath("$.average").value(DEFAULT_AVERAGE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTestEtude() throws Exception {
        // Get the testEtude
        restTestEtudeMockMvc.perform(get("/api/test-etudes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTestEtude() throws Exception {
        // Initialize the database
        testEtudeService.save(testEtude);

        int databaseSizeBeforeUpdate = testEtudeRepository.findAll().size();

        // Update the testEtude
        TestEtude updatedTestEtude = testEtudeRepository.findById(testEtude.getId()).get();
        // Disconnect from session so that the updates on updatedTestEtude are not directly saved in db
        em.detach(updatedTestEtude);
        updatedTestEtude
            .testTitle(UPDATED_TEST_TITLE)
            .levelStudy(UPDATED_LEVEL_STUDY)
            .average(UPDATED_AVERAGE);

        restTestEtudeMockMvc.perform(put("/api/test-etudes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTestEtude)))
            .andExpect(status().isOk());

        // Validate the TestEtude in the database
        List<TestEtude> testEtudeList = testEtudeRepository.findAll();
        assertThat(testEtudeList).hasSize(databaseSizeBeforeUpdate);
        TestEtude testTestEtude = testEtudeList.get(testEtudeList.size() - 1);
        assertThat(testTestEtude.getTestTitle()).isEqualTo(UPDATED_TEST_TITLE);
        assertThat(testTestEtude.getLevelStudy()).isEqualTo(UPDATED_LEVEL_STUDY);
        assertThat(testTestEtude.getAverage()).isEqualTo(UPDATED_AVERAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingTestEtude() throws Exception {
        int databaseSizeBeforeUpdate = testEtudeRepository.findAll().size();

        // Create the TestEtude

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTestEtudeMockMvc.perform(put("/api/test-etudes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(testEtude)))
            .andExpect(status().isBadRequest());

        // Validate the TestEtude in the database
        List<TestEtude> testEtudeList = testEtudeRepository.findAll();
        assertThat(testEtudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTestEtude() throws Exception {
        // Initialize the database
        testEtudeService.save(testEtude);

        int databaseSizeBeforeDelete = testEtudeRepository.findAll().size();

        // Delete the testEtude
        restTestEtudeMockMvc.perform(delete("/api/test-etudes/{id}", testEtude.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TestEtude> testEtudeList = testEtudeRepository.findAll();
        assertThat(testEtudeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
