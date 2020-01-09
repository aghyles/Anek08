package com.popov.t04jh.web.rest;

import com.popov.t04jh.T04JhApp;
import com.popov.t04jh.domain.TestNage;
import com.popov.t04jh.repository.TestNageRepository;
import com.popov.t04jh.service.TestNageService;
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
 * Integration tests for the {@link TestNageResource} REST controller.
 */
@SpringBootTest(classes = T04JhApp.class)
public class TestNageResourceIT {

    private static final String DEFAULT_TEST_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TEST_TITLE = "BBBBBBBBBB";

    private static final Long DEFAULT_NL_50 = 1L;
    private static final Long UPDATED_NL_50 = 2L;

    private static final Long DEFAULT_NL_100 = 1L;
    private static final Long UPDATED_NL_100 = 2L;

    private static final Long DEFAULT_NL_200 = 1L;
    private static final Long UPDATED_NL_200 = 2L;

    private static final Long DEFAULT_NL_400 = 1L;
    private static final Long UPDATED_NL_400 = 2L;

    private static final Long DEFAULT_NL_800 = 1L;
    private static final Long UPDATED_NL_800 = 2L;

    private static final Long DEFAULT_NL_1500 = 1L;
    private static final Long UPDATED_NL_1500 = 2L;

    private static final Long DEFAULT_PAP_50 = 1L;
    private static final Long UPDATED_PAP_50 = 2L;

    private static final Long DEFAULT_PAP_100 = 1L;
    private static final Long UPDATED_PAP_100 = 2L;

    private static final Long DEFAULT_PAP_200 = 1L;
    private static final Long UPDATED_PAP_200 = 2L;

    private static final Long DEFAULT_DOS_50 = 1L;
    private static final Long UPDATED_DOS_50 = 2L;

    private static final Long DEFAULT_DOS_100 = 1L;
    private static final Long UPDATED_DOS_100 = 2L;

    private static final Long DEFAULT_DOS_200 = 1L;
    private static final Long UPDATED_DOS_200 = 2L;

    private static final Long DEFAULT_BRASSE_50 = 1L;
    private static final Long UPDATED_BRASSE_50 = 2L;

    private static final Long DEFAULT_BRASSE_100 = 1L;
    private static final Long UPDATED_BRASSE_100 = 2L;

    private static final Long DEFAULT_BRASSE_200 = 1L;
    private static final Long UPDATED_BRASSE_200 = 2L;

    private static final Long DEFAULT_NA_4_GE_100 = 1L;
    private static final Long UPDATED_NA_4_GE_100 = 2L;

    private static final Long DEFAULT_NA_4_GE_200 = 1L;
    private static final Long UPDATED_NA_4_GE_200 = 2L;

    private static final Long DEFAULT_NA_4_GE_400 = 1L;
    private static final Long UPDATED_NA_4_GE_400 = 2L;

    private static final Long DEFAULT_H_1_NL = 1L;
    private static final Long UPDATED_H_1_NL = 2L;

    private static final String DEFAULT_AUTRE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE = "BBBBBBBBBB";

    @Autowired
    private TestNageRepository testNageRepository;

    @Autowired
    private TestNageService testNageService;

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

    private MockMvc restTestNageMockMvc;

    private TestNage testNage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TestNageResource testNageResource = new TestNageResource(testNageService);
        this.restTestNageMockMvc = MockMvcBuilders.standaloneSetup(testNageResource)
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
    public static TestNage createEntity(EntityManager em) {
        TestNage testNage = new TestNage()
            .testTitle(DEFAULT_TEST_TITLE)
            .nl50(DEFAULT_NL_50)
            .nl100(DEFAULT_NL_100)
            .nl200(DEFAULT_NL_200)
            .nl400(DEFAULT_NL_400)
            .nl800(DEFAULT_NL_800)
            .nl1500(DEFAULT_NL_1500)
            .pap50(DEFAULT_PAP_50)
            .pap100(DEFAULT_PAP_100)
            .pap200(DEFAULT_PAP_200)
            .dos50(DEFAULT_DOS_50)
            .dos100(DEFAULT_DOS_100)
            .dos200(DEFAULT_DOS_200)
            .brasse50(DEFAULT_BRASSE_50)
            .brasse100(DEFAULT_BRASSE_100)
            .brasse200(DEFAULT_BRASSE_200)
            .na4ge100(DEFAULT_NA_4_GE_100)
            .na4ge200(DEFAULT_NA_4_GE_200)
            .na4ge400(DEFAULT_NA_4_GE_400)
            .h1nl(DEFAULT_H_1_NL)
            .autre(DEFAULT_AUTRE);
        return testNage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TestNage createUpdatedEntity(EntityManager em) {
        TestNage testNage = new TestNage()
            .testTitle(UPDATED_TEST_TITLE)
            .nl50(UPDATED_NL_50)
            .nl100(UPDATED_NL_100)
            .nl200(UPDATED_NL_200)
            .nl400(UPDATED_NL_400)
            .nl800(UPDATED_NL_800)
            .nl1500(UPDATED_NL_1500)
            .pap50(UPDATED_PAP_50)
            .pap100(UPDATED_PAP_100)
            .pap200(UPDATED_PAP_200)
            .dos50(UPDATED_DOS_50)
            .dos100(UPDATED_DOS_100)
            .dos200(UPDATED_DOS_200)
            .brasse50(UPDATED_BRASSE_50)
            .brasse100(UPDATED_BRASSE_100)
            .brasse200(UPDATED_BRASSE_200)
            .na4ge100(UPDATED_NA_4_GE_100)
            .na4ge200(UPDATED_NA_4_GE_200)
            .na4ge400(UPDATED_NA_4_GE_400)
            .h1nl(UPDATED_H_1_NL)
            .autre(UPDATED_AUTRE);
        return testNage;
    }

    @BeforeEach
    public void initTest() {
        testNage = createEntity(em);
    }

    @Test
    @Transactional
    public void createTestNage() throws Exception {
        int databaseSizeBeforeCreate = testNageRepository.findAll().size();

        // Create the TestNage
        restTestNageMockMvc.perform(post("/api/test-nages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(testNage)))
            .andExpect(status().isCreated());

        // Validate the TestNage in the database
        List<TestNage> testNageList = testNageRepository.findAll();
        assertThat(testNageList).hasSize(databaseSizeBeforeCreate + 1);
        TestNage testTestNage = testNageList.get(testNageList.size() - 1);
        assertThat(testTestNage.getTestTitle()).isEqualTo(DEFAULT_TEST_TITLE);
        assertThat(testTestNage.getNl50()).isEqualTo(DEFAULT_NL_50);
        assertThat(testTestNage.getNl100()).isEqualTo(DEFAULT_NL_100);
        assertThat(testTestNage.getNl200()).isEqualTo(DEFAULT_NL_200);
        assertThat(testTestNage.getNl400()).isEqualTo(DEFAULT_NL_400);
        assertThat(testTestNage.getNl800()).isEqualTo(DEFAULT_NL_800);
        assertThat(testTestNage.getNl1500()).isEqualTo(DEFAULT_NL_1500);
        assertThat(testTestNage.getPap50()).isEqualTo(DEFAULT_PAP_50);
        assertThat(testTestNage.getPap100()).isEqualTo(DEFAULT_PAP_100);
        assertThat(testTestNage.getPap200()).isEqualTo(DEFAULT_PAP_200);
        assertThat(testTestNage.getDos50()).isEqualTo(DEFAULT_DOS_50);
        assertThat(testTestNage.getDos100()).isEqualTo(DEFAULT_DOS_100);
        assertThat(testTestNage.getDos200()).isEqualTo(DEFAULT_DOS_200);
        assertThat(testTestNage.getBrasse50()).isEqualTo(DEFAULT_BRASSE_50);
        assertThat(testTestNage.getBrasse100()).isEqualTo(DEFAULT_BRASSE_100);
        assertThat(testTestNage.getBrasse200()).isEqualTo(DEFAULT_BRASSE_200);
        assertThat(testTestNage.getNa4ge100()).isEqualTo(DEFAULT_NA_4_GE_100);
        assertThat(testTestNage.getNa4ge200()).isEqualTo(DEFAULT_NA_4_GE_200);
        assertThat(testTestNage.getNa4ge400()).isEqualTo(DEFAULT_NA_4_GE_400);
        assertThat(testTestNage.geth1nl()).isEqualTo(DEFAULT_H_1_NL);
        assertThat(testTestNage.getAutre()).isEqualTo(DEFAULT_AUTRE);
    }

    @Test
    @Transactional
    public void createTestNageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = testNageRepository.findAll().size();

        // Create the TestNage with an existing ID
        testNage.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTestNageMockMvc.perform(post("/api/test-nages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(testNage)))
            .andExpect(status().isBadRequest());

        // Validate the TestNage in the database
        List<TestNage> testNageList = testNageRepository.findAll();
        assertThat(testNageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTestNages() throws Exception {
        // Initialize the database
        testNageRepository.saveAndFlush(testNage);

        // Get all the testNageList
        restTestNageMockMvc.perform(get("/api/test-nages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(testNage.getId().intValue())))
            .andExpect(jsonPath("$.[*].testTitle").value(hasItem(DEFAULT_TEST_TITLE)))
            .andExpect(jsonPath("$.[*].nl50").value(hasItem(DEFAULT_NL_50.intValue())))
            .andExpect(jsonPath("$.[*].nl100").value(hasItem(DEFAULT_NL_100.intValue())))
            .andExpect(jsonPath("$.[*].nl200").value(hasItem(DEFAULT_NL_200.intValue())))
            .andExpect(jsonPath("$.[*].nl400").value(hasItem(DEFAULT_NL_400.intValue())))
            .andExpect(jsonPath("$.[*].nl800").value(hasItem(DEFAULT_NL_800.intValue())))
            .andExpect(jsonPath("$.[*].nl1500").value(hasItem(DEFAULT_NL_1500.intValue())))
            .andExpect(jsonPath("$.[*].pap50").value(hasItem(DEFAULT_PAP_50.intValue())))
            .andExpect(jsonPath("$.[*].pap100").value(hasItem(DEFAULT_PAP_100.intValue())))
            .andExpect(jsonPath("$.[*].pap200").value(hasItem(DEFAULT_PAP_200.intValue())))
            .andExpect(jsonPath("$.[*].dos50").value(hasItem(DEFAULT_DOS_50.intValue())))
            .andExpect(jsonPath("$.[*].dos100").value(hasItem(DEFAULT_DOS_100.intValue())))
            .andExpect(jsonPath("$.[*].dos200").value(hasItem(DEFAULT_DOS_200.intValue())))
            .andExpect(jsonPath("$.[*].brasse50").value(hasItem(DEFAULT_BRASSE_50.intValue())))
            .andExpect(jsonPath("$.[*].brasse100").value(hasItem(DEFAULT_BRASSE_100.intValue())))
            .andExpect(jsonPath("$.[*].brasse200").value(hasItem(DEFAULT_BRASSE_200.intValue())))
            .andExpect(jsonPath("$.[*].na4ge100").value(hasItem(DEFAULT_NA_4_GE_100.intValue())))
            .andExpect(jsonPath("$.[*].na4ge200").value(hasItem(DEFAULT_NA_4_GE_200.intValue())))
            .andExpect(jsonPath("$.[*].na4ge400").value(hasItem(DEFAULT_NA_4_GE_400.intValue())))
            .andExpect(jsonPath("$.[*].h1nl").value(hasItem(DEFAULT_H_1_NL.intValue())))
            .andExpect(jsonPath("$.[*].autre").value(hasItem(DEFAULT_AUTRE)));
    }
    
    @Test
    @Transactional
    public void getTestNage() throws Exception {
        // Initialize the database
        testNageRepository.saveAndFlush(testNage);

        // Get the testNage
        restTestNageMockMvc.perform(get("/api/test-nages/{id}", testNage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(testNage.getId().intValue()))
            .andExpect(jsonPath("$.testTitle").value(DEFAULT_TEST_TITLE))
            .andExpect(jsonPath("$.nl50").value(DEFAULT_NL_50.intValue()))
            .andExpect(jsonPath("$.nl100").value(DEFAULT_NL_100.intValue()))
            .andExpect(jsonPath("$.nl200").value(DEFAULT_NL_200.intValue()))
            .andExpect(jsonPath("$.nl400").value(DEFAULT_NL_400.intValue()))
            .andExpect(jsonPath("$.nl800").value(DEFAULT_NL_800.intValue()))
            .andExpect(jsonPath("$.nl1500").value(DEFAULT_NL_1500.intValue()))
            .andExpect(jsonPath("$.pap50").value(DEFAULT_PAP_50.intValue()))
            .andExpect(jsonPath("$.pap100").value(DEFAULT_PAP_100.intValue()))
            .andExpect(jsonPath("$.pap200").value(DEFAULT_PAP_200.intValue()))
            .andExpect(jsonPath("$.dos50").value(DEFAULT_DOS_50.intValue()))
            .andExpect(jsonPath("$.dos100").value(DEFAULT_DOS_100.intValue()))
            .andExpect(jsonPath("$.dos200").value(DEFAULT_DOS_200.intValue()))
            .andExpect(jsonPath("$.brasse50").value(DEFAULT_BRASSE_50.intValue()))
            .andExpect(jsonPath("$.brasse100").value(DEFAULT_BRASSE_100.intValue()))
            .andExpect(jsonPath("$.brasse200").value(DEFAULT_BRASSE_200.intValue()))
            .andExpect(jsonPath("$.na4ge100").value(DEFAULT_NA_4_GE_100.intValue()))
            .andExpect(jsonPath("$.na4ge200").value(DEFAULT_NA_4_GE_200.intValue()))
            .andExpect(jsonPath("$.na4ge400").value(DEFAULT_NA_4_GE_400.intValue()))
            .andExpect(jsonPath("$.h1nl").value(DEFAULT_H_1_NL.intValue()))
            .andExpect(jsonPath("$.autre").value(DEFAULT_AUTRE));
    }

    @Test
    @Transactional
    public void getNonExistingTestNage() throws Exception {
        // Get the testNage
        restTestNageMockMvc.perform(get("/api/test-nages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTestNage() throws Exception {
        // Initialize the database
        testNageService.save(testNage);

        int databaseSizeBeforeUpdate = testNageRepository.findAll().size();

        // Update the testNage
        TestNage updatedTestNage = testNageRepository.findById(testNage.getId()).get();
        // Disconnect from session so that the updates on updatedTestNage are not directly saved in db
        em.detach(updatedTestNage);
        updatedTestNage
            .testTitle(UPDATED_TEST_TITLE)
            .nl50(UPDATED_NL_50)
            .nl100(UPDATED_NL_100)
            .nl200(UPDATED_NL_200)
            .nl400(UPDATED_NL_400)
            .nl800(UPDATED_NL_800)
            .nl1500(UPDATED_NL_1500)
            .pap50(UPDATED_PAP_50)
            .pap100(UPDATED_PAP_100)
            .pap200(UPDATED_PAP_200)
            .dos50(UPDATED_DOS_50)
            .dos100(UPDATED_DOS_100)
            .dos200(UPDATED_DOS_200)
            .brasse50(UPDATED_BRASSE_50)
            .brasse100(UPDATED_BRASSE_100)
            .brasse200(UPDATED_BRASSE_200)
            .na4ge100(UPDATED_NA_4_GE_100)
            .na4ge200(UPDATED_NA_4_GE_200)
            .na4ge400(UPDATED_NA_4_GE_400)
            .h1nl(UPDATED_H_1_NL)
            .autre(UPDATED_AUTRE);

        restTestNageMockMvc.perform(put("/api/test-nages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTestNage)))
            .andExpect(status().isOk());

        // Validate the TestNage in the database
        List<TestNage> testNageList = testNageRepository.findAll();
        assertThat(testNageList).hasSize(databaseSizeBeforeUpdate);
        TestNage testTestNage = testNageList.get(testNageList.size() - 1);
        assertThat(testTestNage.getTestTitle()).isEqualTo(UPDATED_TEST_TITLE);
        assertThat(testTestNage.getNl50()).isEqualTo(UPDATED_NL_50);
        assertThat(testTestNage.getNl100()).isEqualTo(UPDATED_NL_100);
        assertThat(testTestNage.getNl200()).isEqualTo(UPDATED_NL_200);
        assertThat(testTestNage.getNl400()).isEqualTo(UPDATED_NL_400);
        assertThat(testTestNage.getNl800()).isEqualTo(UPDATED_NL_800);
        assertThat(testTestNage.getNl1500()).isEqualTo(UPDATED_NL_1500);
        assertThat(testTestNage.getPap50()).isEqualTo(UPDATED_PAP_50);
        assertThat(testTestNage.getPap100()).isEqualTo(UPDATED_PAP_100);
        assertThat(testTestNage.getPap200()).isEqualTo(UPDATED_PAP_200);
        assertThat(testTestNage.getDos50()).isEqualTo(UPDATED_DOS_50);
        assertThat(testTestNage.getDos100()).isEqualTo(UPDATED_DOS_100);
        assertThat(testTestNage.getDos200()).isEqualTo(UPDATED_DOS_200);
        assertThat(testTestNage.getBrasse50()).isEqualTo(UPDATED_BRASSE_50);
        assertThat(testTestNage.getBrasse100()).isEqualTo(UPDATED_BRASSE_100);
        assertThat(testTestNage.getBrasse200()).isEqualTo(UPDATED_BRASSE_200);
        assertThat(testTestNage.getNa4ge100()).isEqualTo(UPDATED_NA_4_GE_100);
        assertThat(testTestNage.getNa4ge200()).isEqualTo(UPDATED_NA_4_GE_200);
        assertThat(testTestNage.getNa4ge400()).isEqualTo(UPDATED_NA_4_GE_400);
        assertThat(testTestNage.geth1nl()).isEqualTo(UPDATED_H_1_NL);
        assertThat(testTestNage.getAutre()).isEqualTo(UPDATED_AUTRE);
    }

    @Test
    @Transactional
    public void updateNonExistingTestNage() throws Exception {
        int databaseSizeBeforeUpdate = testNageRepository.findAll().size();

        // Create the TestNage

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTestNageMockMvc.perform(put("/api/test-nages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(testNage)))
            .andExpect(status().isBadRequest());

        // Validate the TestNage in the database
        List<TestNage> testNageList = testNageRepository.findAll();
        assertThat(testNageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTestNage() throws Exception {
        // Initialize the database
        testNageService.save(testNage);

        int databaseSizeBeforeDelete = testNageRepository.findAll().size();

        // Delete the testNage
        restTestNageMockMvc.perform(delete("/api/test-nages/{id}", testNage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TestNage> testNageList = testNageRepository.findAll();
        assertThat(testNageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
