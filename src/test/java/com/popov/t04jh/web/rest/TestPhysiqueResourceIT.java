package com.popov.t04jh.web.rest;

import com.popov.t04jh.T04JhApp;
import com.popov.t04jh.domain.TestPhysique;
import com.popov.t04jh.repository.TestPhysiqueRepository;
import com.popov.t04jh.service.TestPhysiqueService;
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
 * Integration tests for the {@link TestPhysiqueResource} REST controller.
 */
@SpringBootTest(classes = T04JhApp.class)
public class TestPhysiqueResourceIT {

    private static final String DEFAULT_TESTPHY_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TESTPHY_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_TEST_PHY_1 = "AAAAAAAAAA";
    private static final String UPDATED_TEST_PHY_1 = "BBBBBBBBBB";

    private static final String DEFAULT_TEST_PHY_2 = "AAAAAAAAAA";
    private static final String UPDATED_TEST_PHY_2 = "BBBBBBBBBB";

    private static final String DEFAULT_TEST_PHY_3 = "AAAAAAAAAA";
    private static final String UPDATED_TEST_PHY_3 = "BBBBBBBBBB";

    private static final String DEFAULT_TEST_PHY_4 = "AAAAAAAAAA";
    private static final String UPDATED_TEST_PHY_4 = "BBBBBBBBBB";

    private static final String DEFAULT_TEST_PHY_5 = "AAAAAAAAAA";
    private static final String UPDATED_TEST_PHY_5 = "BBBBBBBBBB";

    @Autowired
    private TestPhysiqueRepository testPhysiqueRepository;

    @Autowired
    private TestPhysiqueService testPhysiqueService;

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

    private MockMvc restTestPhysiqueMockMvc;

    private TestPhysique testPhysique;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TestPhysiqueResource testPhysiqueResource = new TestPhysiqueResource(testPhysiqueService);
        this.restTestPhysiqueMockMvc = MockMvcBuilders.standaloneSetup(testPhysiqueResource)
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
    public static TestPhysique createEntity(EntityManager em) {
        TestPhysique testPhysique = new TestPhysique()
            .testphyTitle(DEFAULT_TESTPHY_TITLE)
            .testPhy1(DEFAULT_TEST_PHY_1)
            .testPhy2(DEFAULT_TEST_PHY_2)
            .testPhy3(DEFAULT_TEST_PHY_3)
            .testPhy4(DEFAULT_TEST_PHY_4)
            .testPhy5(DEFAULT_TEST_PHY_5);
        return testPhysique;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TestPhysique createUpdatedEntity(EntityManager em) {
        TestPhysique testPhysique = new TestPhysique()
            .testphyTitle(UPDATED_TESTPHY_TITLE)
            .testPhy1(UPDATED_TEST_PHY_1)
            .testPhy2(UPDATED_TEST_PHY_2)
            .testPhy3(UPDATED_TEST_PHY_3)
            .testPhy4(UPDATED_TEST_PHY_4)
            .testPhy5(UPDATED_TEST_PHY_5);
        return testPhysique;
    }

    @BeforeEach
    public void initTest() {
        testPhysique = createEntity(em);
    }

    @Test
    @Transactional
    public void createTestPhysique() throws Exception {
        int databaseSizeBeforeCreate = testPhysiqueRepository.findAll().size();

        // Create the TestPhysique
        restTestPhysiqueMockMvc.perform(post("/api/test-physiques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(testPhysique)))
            .andExpect(status().isCreated());

        // Validate the TestPhysique in the database
        List<TestPhysique> testPhysiqueList = testPhysiqueRepository.findAll();
        assertThat(testPhysiqueList).hasSize(databaseSizeBeforeCreate + 1);
        TestPhysique testTestPhysique = testPhysiqueList.get(testPhysiqueList.size() - 1);
        assertThat(testTestPhysique.getTestphyTitle()).isEqualTo(DEFAULT_TESTPHY_TITLE);
        assertThat(testTestPhysique.getTestPhy1()).isEqualTo(DEFAULT_TEST_PHY_1);
        assertThat(testTestPhysique.getTestPhy2()).isEqualTo(DEFAULT_TEST_PHY_2);
        assertThat(testTestPhysique.getTestPhy3()).isEqualTo(DEFAULT_TEST_PHY_3);
        assertThat(testTestPhysique.getTestPhy4()).isEqualTo(DEFAULT_TEST_PHY_4);
        assertThat(testTestPhysique.getTestPhy5()).isEqualTo(DEFAULT_TEST_PHY_5);
    }

    @Test
    @Transactional
    public void createTestPhysiqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = testPhysiqueRepository.findAll().size();

        // Create the TestPhysique with an existing ID
        testPhysique.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTestPhysiqueMockMvc.perform(post("/api/test-physiques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(testPhysique)))
            .andExpect(status().isBadRequest());

        // Validate the TestPhysique in the database
        List<TestPhysique> testPhysiqueList = testPhysiqueRepository.findAll();
        assertThat(testPhysiqueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTestPhysiques() throws Exception {
        // Initialize the database
        testPhysiqueRepository.saveAndFlush(testPhysique);

        // Get all the testPhysiqueList
        restTestPhysiqueMockMvc.perform(get("/api/test-physiques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(testPhysique.getId().intValue())))
            .andExpect(jsonPath("$.[*].testphyTitle").value(hasItem(DEFAULT_TESTPHY_TITLE)))
            .andExpect(jsonPath("$.[*].testPhy1").value(hasItem(DEFAULT_TEST_PHY_1)))
            .andExpect(jsonPath("$.[*].testPhy2").value(hasItem(DEFAULT_TEST_PHY_2)))
            .andExpect(jsonPath("$.[*].testPhy3").value(hasItem(DEFAULT_TEST_PHY_3)))
            .andExpect(jsonPath("$.[*].testPhy4").value(hasItem(DEFAULT_TEST_PHY_4)))
            .andExpect(jsonPath("$.[*].testPhy5").value(hasItem(DEFAULT_TEST_PHY_5)));
    }
    
    @Test
    @Transactional
    public void getTestPhysique() throws Exception {
        // Initialize the database
        testPhysiqueRepository.saveAndFlush(testPhysique);

        // Get the testPhysique
        restTestPhysiqueMockMvc.perform(get("/api/test-physiques/{id}", testPhysique.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(testPhysique.getId().intValue()))
            .andExpect(jsonPath("$.testphyTitle").value(DEFAULT_TESTPHY_TITLE))
            .andExpect(jsonPath("$.testPhy1").value(DEFAULT_TEST_PHY_1))
            .andExpect(jsonPath("$.testPhy2").value(DEFAULT_TEST_PHY_2))
            .andExpect(jsonPath("$.testPhy3").value(DEFAULT_TEST_PHY_3))
            .andExpect(jsonPath("$.testPhy4").value(DEFAULT_TEST_PHY_4))
            .andExpect(jsonPath("$.testPhy5").value(DEFAULT_TEST_PHY_5));
    }

    @Test
    @Transactional
    public void getNonExistingTestPhysique() throws Exception {
        // Get the testPhysique
        restTestPhysiqueMockMvc.perform(get("/api/test-physiques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTestPhysique() throws Exception {
        // Initialize the database
        testPhysiqueService.save(testPhysique);

        int databaseSizeBeforeUpdate = testPhysiqueRepository.findAll().size();

        // Update the testPhysique
        TestPhysique updatedTestPhysique = testPhysiqueRepository.findById(testPhysique.getId()).get();
        // Disconnect from session so that the updates on updatedTestPhysique are not directly saved in db
        em.detach(updatedTestPhysique);
        updatedTestPhysique
            .testphyTitle(UPDATED_TESTPHY_TITLE)
            .testPhy1(UPDATED_TEST_PHY_1)
            .testPhy2(UPDATED_TEST_PHY_2)
            .testPhy3(UPDATED_TEST_PHY_3)
            .testPhy4(UPDATED_TEST_PHY_4)
            .testPhy5(UPDATED_TEST_PHY_5);

        restTestPhysiqueMockMvc.perform(put("/api/test-physiques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTestPhysique)))
            .andExpect(status().isOk());

        // Validate the TestPhysique in the database
        List<TestPhysique> testPhysiqueList = testPhysiqueRepository.findAll();
        assertThat(testPhysiqueList).hasSize(databaseSizeBeforeUpdate);
        TestPhysique testTestPhysique = testPhysiqueList.get(testPhysiqueList.size() - 1);
        assertThat(testTestPhysique.getTestphyTitle()).isEqualTo(UPDATED_TESTPHY_TITLE);
        assertThat(testTestPhysique.getTestPhy1()).isEqualTo(UPDATED_TEST_PHY_1);
        assertThat(testTestPhysique.getTestPhy2()).isEqualTo(UPDATED_TEST_PHY_2);
        assertThat(testTestPhysique.getTestPhy3()).isEqualTo(UPDATED_TEST_PHY_3);
        assertThat(testTestPhysique.getTestPhy4()).isEqualTo(UPDATED_TEST_PHY_4);
        assertThat(testTestPhysique.getTestPhy5()).isEqualTo(UPDATED_TEST_PHY_5);
    }

    @Test
    @Transactional
    public void updateNonExistingTestPhysique() throws Exception {
        int databaseSizeBeforeUpdate = testPhysiqueRepository.findAll().size();

        // Create the TestPhysique

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTestPhysiqueMockMvc.perform(put("/api/test-physiques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(testPhysique)))
            .andExpect(status().isBadRequest());

        // Validate the TestPhysique in the database
        List<TestPhysique> testPhysiqueList = testPhysiqueRepository.findAll();
        assertThat(testPhysiqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTestPhysique() throws Exception {
        // Initialize the database
        testPhysiqueService.save(testPhysique);

        int databaseSizeBeforeDelete = testPhysiqueRepository.findAll().size();

        // Delete the testPhysique
        restTestPhysiqueMockMvc.perform(delete("/api/test-physiques/{id}", testPhysique.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TestPhysique> testPhysiqueList = testPhysiqueRepository.findAll();
        assertThat(testPhysiqueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
