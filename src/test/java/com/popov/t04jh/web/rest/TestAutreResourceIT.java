package com.popov.t04jh.web.rest;

import com.popov.t04jh.T04JhApp;
import com.popov.t04jh.domain.TestAutre;
import com.popov.t04jh.repository.TestAutreRepository;
import com.popov.t04jh.service.TestAutreService;
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
 * Integration tests for the {@link TestAutreResource} REST controller.
 */
@SpringBootTest(classes = T04JhApp.class)
public class TestAutreResourceIT {

    private static final String DEFAULT_TEST_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TEST_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_OBS_1 = "AAAAAAAAAA";
    private static final String UPDATED_OBS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_OBS_2 = "AAAAAAAAAA";
    private static final String UPDATED_OBS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_OBS_3 = "AAAAAAAAAA";
    private static final String UPDATED_OBS_3 = "BBBBBBBBBB";

    private static final String DEFAULT_OBS_4 = "AAAAAAAAAA";
    private static final String UPDATED_OBS_4 = "BBBBBBBBBB";

    private static final String DEFAULT_OBS_5 = "AAAAAAAAAA";
    private static final String UPDATED_OBS_5 = "BBBBBBBBBB";

    private static final String DEFAULT_OBS_6 = "AAAAAAAAAA";
    private static final String UPDATED_OBS_6 = "BBBBBBBBBB";

    private static final String DEFAULT_OBS_7 = "AAAAAAAAAA";
    private static final String UPDATED_OBS_7 = "BBBBBBBBBB";

    @Autowired
    private TestAutreRepository testAutreRepository;

    @Autowired
    private TestAutreService testAutreService;

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

    private MockMvc restTestAutreMockMvc;

    private TestAutre testAutre;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TestAutreResource testAutreResource = new TestAutreResource(testAutreService);
        this.restTestAutreMockMvc = MockMvcBuilders.standaloneSetup(testAutreResource)
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
    public static TestAutre createEntity(EntityManager em) {
        TestAutre testAutre = new TestAutre()
            .testTitle(DEFAULT_TEST_TITLE)
            .obs1(DEFAULT_OBS_1)
            .obs2(DEFAULT_OBS_2)
            .obs3(DEFAULT_OBS_3)
            .obs4(DEFAULT_OBS_4)
            .obs5(DEFAULT_OBS_5)
            .obs6(DEFAULT_OBS_6)
            .obs7(DEFAULT_OBS_7);
        return testAutre;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TestAutre createUpdatedEntity(EntityManager em) {
        TestAutre testAutre = new TestAutre()
            .testTitle(UPDATED_TEST_TITLE)
            .obs1(UPDATED_OBS_1)
            .obs2(UPDATED_OBS_2)
            .obs3(UPDATED_OBS_3)
            .obs4(UPDATED_OBS_4)
            .obs5(UPDATED_OBS_5)
            .obs6(UPDATED_OBS_6)
            .obs7(UPDATED_OBS_7);
        return testAutre;
    }

    @BeforeEach
    public void initTest() {
        testAutre = createEntity(em);
    }

    @Test
    @Transactional
    public void createTestAutre() throws Exception {
        int databaseSizeBeforeCreate = testAutreRepository.findAll().size();

        // Create the TestAutre
        restTestAutreMockMvc.perform(post("/api/test-autres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(testAutre)))
            .andExpect(status().isCreated());

        // Validate the TestAutre in the database
        List<TestAutre> testAutreList = testAutreRepository.findAll();
        assertThat(testAutreList).hasSize(databaseSizeBeforeCreate + 1);
        TestAutre testTestAutre = testAutreList.get(testAutreList.size() - 1);
        assertThat(testTestAutre.getTestTitle()).isEqualTo(DEFAULT_TEST_TITLE);
        assertThat(testTestAutre.getObs1()).isEqualTo(DEFAULT_OBS_1);
        assertThat(testTestAutre.getObs2()).isEqualTo(DEFAULT_OBS_2);
        assertThat(testTestAutre.getObs3()).isEqualTo(DEFAULT_OBS_3);
        assertThat(testTestAutre.getObs4()).isEqualTo(DEFAULT_OBS_4);
        assertThat(testTestAutre.getObs5()).isEqualTo(DEFAULT_OBS_5);
        assertThat(testTestAutre.getObs6()).isEqualTo(DEFAULT_OBS_6);
        assertThat(testTestAutre.getObs7()).isEqualTo(DEFAULT_OBS_7);
    }

    @Test
    @Transactional
    public void createTestAutreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = testAutreRepository.findAll().size();

        // Create the TestAutre with an existing ID
        testAutre.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTestAutreMockMvc.perform(post("/api/test-autres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(testAutre)))
            .andExpect(status().isBadRequest());

        // Validate the TestAutre in the database
        List<TestAutre> testAutreList = testAutreRepository.findAll();
        assertThat(testAutreList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTestAutres() throws Exception {
        // Initialize the database
        testAutreRepository.saveAndFlush(testAutre);

        // Get all the testAutreList
        restTestAutreMockMvc.perform(get("/api/test-autres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(testAutre.getId().intValue())))
            .andExpect(jsonPath("$.[*].testTitle").value(hasItem(DEFAULT_TEST_TITLE)))
            .andExpect(jsonPath("$.[*].obs1").value(hasItem(DEFAULT_OBS_1)))
            .andExpect(jsonPath("$.[*].obs2").value(hasItem(DEFAULT_OBS_2)))
            .andExpect(jsonPath("$.[*].obs3").value(hasItem(DEFAULT_OBS_3)))
            .andExpect(jsonPath("$.[*].obs4").value(hasItem(DEFAULT_OBS_4)))
            .andExpect(jsonPath("$.[*].obs5").value(hasItem(DEFAULT_OBS_5)))
            .andExpect(jsonPath("$.[*].obs6").value(hasItem(DEFAULT_OBS_6)))
            .andExpect(jsonPath("$.[*].obs7").value(hasItem(DEFAULT_OBS_7)));
    }
    
    @Test
    @Transactional
    public void getTestAutre() throws Exception {
        // Initialize the database
        testAutreRepository.saveAndFlush(testAutre);

        // Get the testAutre
        restTestAutreMockMvc.perform(get("/api/test-autres/{id}", testAutre.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(testAutre.getId().intValue()))
            .andExpect(jsonPath("$.testTitle").value(DEFAULT_TEST_TITLE))
            .andExpect(jsonPath("$.obs1").value(DEFAULT_OBS_1))
            .andExpect(jsonPath("$.obs2").value(DEFAULT_OBS_2))
            .andExpect(jsonPath("$.obs3").value(DEFAULT_OBS_3))
            .andExpect(jsonPath("$.obs4").value(DEFAULT_OBS_4))
            .andExpect(jsonPath("$.obs5").value(DEFAULT_OBS_5))
            .andExpect(jsonPath("$.obs6").value(DEFAULT_OBS_6))
            .andExpect(jsonPath("$.obs7").value(DEFAULT_OBS_7));
    }

    @Test
    @Transactional
    public void getNonExistingTestAutre() throws Exception {
        // Get the testAutre
        restTestAutreMockMvc.perform(get("/api/test-autres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTestAutre() throws Exception {
        // Initialize the database
        testAutreService.save(testAutre);

        int databaseSizeBeforeUpdate = testAutreRepository.findAll().size();

        // Update the testAutre
        TestAutre updatedTestAutre = testAutreRepository.findById(testAutre.getId()).get();
        // Disconnect from session so that the updates on updatedTestAutre are not directly saved in db
        em.detach(updatedTestAutre);
        updatedTestAutre
            .testTitle(UPDATED_TEST_TITLE)
            .obs1(UPDATED_OBS_1)
            .obs2(UPDATED_OBS_2)
            .obs3(UPDATED_OBS_3)
            .obs4(UPDATED_OBS_4)
            .obs5(UPDATED_OBS_5)
            .obs6(UPDATED_OBS_6)
            .obs7(UPDATED_OBS_7);

        restTestAutreMockMvc.perform(put("/api/test-autres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTestAutre)))
            .andExpect(status().isOk());

        // Validate the TestAutre in the database
        List<TestAutre> testAutreList = testAutreRepository.findAll();
        assertThat(testAutreList).hasSize(databaseSizeBeforeUpdate);
        TestAutre testTestAutre = testAutreList.get(testAutreList.size() - 1);
        assertThat(testTestAutre.getTestTitle()).isEqualTo(UPDATED_TEST_TITLE);
        assertThat(testTestAutre.getObs1()).isEqualTo(UPDATED_OBS_1);
        assertThat(testTestAutre.getObs2()).isEqualTo(UPDATED_OBS_2);
        assertThat(testTestAutre.getObs3()).isEqualTo(UPDATED_OBS_3);
        assertThat(testTestAutre.getObs4()).isEqualTo(UPDATED_OBS_4);
        assertThat(testTestAutre.getObs5()).isEqualTo(UPDATED_OBS_5);
        assertThat(testTestAutre.getObs6()).isEqualTo(UPDATED_OBS_6);
        assertThat(testTestAutre.getObs7()).isEqualTo(UPDATED_OBS_7);
    }

    @Test
    @Transactional
    public void updateNonExistingTestAutre() throws Exception {
        int databaseSizeBeforeUpdate = testAutreRepository.findAll().size();

        // Create the TestAutre

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTestAutreMockMvc.perform(put("/api/test-autres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(testAutre)))
            .andExpect(status().isBadRequest());

        // Validate the TestAutre in the database
        List<TestAutre> testAutreList = testAutreRepository.findAll();
        assertThat(testAutreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTestAutre() throws Exception {
        // Initialize the database
        testAutreService.save(testAutre);

        int databaseSizeBeforeDelete = testAutreRepository.findAll().size();

        // Delete the testAutre
        restTestAutreMockMvc.perform(delete("/api/test-autres/{id}", testAutre.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TestAutre> testAutreList = testAutreRepository.findAll();
        assertThat(testAutreList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
