package com.popov.t04jh.web.rest;

import com.popov.t04jh.T04JhApp;
import com.popov.t04jh.domain.Exercice;
import com.popov.t04jh.repository.ExerciceRepository;
import com.popov.t04jh.service.ExerciceService;
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
 * Integration tests for the {@link ExerciceResource} REST controller.
 */
@SpringBootTest(classes = T04JhApp.class)
public class ExerciceResourceIT {

    private static final String DEFAULT_EXERCICE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EXERCICE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RECOMENDATION = "AAAAAAAAAA";
    private static final String UPDATED_RECOMENDATION = "BBBBBBBBBB";

    private static final Long DEFAULT_DOC_SCHEMA = 1L;
    private static final Long UPDATED_DOC_SCHEMA = 2L;

    @Autowired
    private ExerciceRepository exerciceRepository;

    @Mock
    private ExerciceRepository exerciceRepositoryMock;

    @Mock
    private ExerciceService exerciceServiceMock;

    @Autowired
    private ExerciceService exerciceService;

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

    private MockMvc restExerciceMockMvc;

    private Exercice exercice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExerciceResource exerciceResource = new ExerciceResource(exerciceService);
        this.restExerciceMockMvc = MockMvcBuilders.standaloneSetup(exerciceResource)
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
    public static Exercice createEntity(EntityManager em) {
        Exercice exercice = new Exercice()
            .exerciceName(DEFAULT_EXERCICE_NAME)
            .recomendation(DEFAULT_RECOMENDATION)
            .docSchema(DEFAULT_DOC_SCHEMA);
        return exercice;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Exercice createUpdatedEntity(EntityManager em) {
        Exercice exercice = new Exercice()
            .exerciceName(UPDATED_EXERCICE_NAME)
            .recomendation(UPDATED_RECOMENDATION)
            .docSchema(UPDATED_DOC_SCHEMA);
        return exercice;
    }

    @BeforeEach
    public void initTest() {
        exercice = createEntity(em);
    }

    @Test
    @Transactional
    public void createExercice() throws Exception {
        int databaseSizeBeforeCreate = exerciceRepository.findAll().size();

        // Create the Exercice
        restExerciceMockMvc.perform(post("/api/exercices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exercice)))
            .andExpect(status().isCreated());

        // Validate the Exercice in the database
        List<Exercice> exerciceList = exerciceRepository.findAll();
        assertThat(exerciceList).hasSize(databaseSizeBeforeCreate + 1);
        Exercice testExercice = exerciceList.get(exerciceList.size() - 1);
        assertThat(testExercice.getExerciceName()).isEqualTo(DEFAULT_EXERCICE_NAME);
        assertThat(testExercice.getRecomendation()).isEqualTo(DEFAULT_RECOMENDATION);
        assertThat(testExercice.getDocSchema()).isEqualTo(DEFAULT_DOC_SCHEMA);
    }

    @Test
    @Transactional
    public void createExerciceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = exerciceRepository.findAll().size();

        // Create the Exercice with an existing ID
        exercice.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExerciceMockMvc.perform(post("/api/exercices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exercice)))
            .andExpect(status().isBadRequest());

        // Validate the Exercice in the database
        List<Exercice> exerciceList = exerciceRepository.findAll();
        assertThat(exerciceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkExerciceNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = exerciceRepository.findAll().size();
        // set the field null
        exercice.setExerciceName(null);

        // Create the Exercice, which fails.

        restExerciceMockMvc.perform(post("/api/exercices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exercice)))
            .andExpect(status().isBadRequest());

        List<Exercice> exerciceList = exerciceRepository.findAll();
        assertThat(exerciceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllExercices() throws Exception {
        // Initialize the database
        exerciceRepository.saveAndFlush(exercice);

        // Get all the exerciceList
        restExerciceMockMvc.perform(get("/api/exercices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(exercice.getId().intValue())))
            .andExpect(jsonPath("$.[*].exerciceName").value(hasItem(DEFAULT_EXERCICE_NAME)))
            .andExpect(jsonPath("$.[*].recomendation").value(hasItem(DEFAULT_RECOMENDATION)))
            .andExpect(jsonPath("$.[*].docSchema").value(hasItem(DEFAULT_DOC_SCHEMA.intValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllExercicesWithEagerRelationshipsIsEnabled() throws Exception {
        ExerciceResource exerciceResource = new ExerciceResource(exerciceServiceMock);
        when(exerciceServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restExerciceMockMvc = MockMvcBuilders.standaloneSetup(exerciceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restExerciceMockMvc.perform(get("/api/exercices?eagerload=true"))
        .andExpect(status().isOk());

        verify(exerciceServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllExercicesWithEagerRelationshipsIsNotEnabled() throws Exception {
        ExerciceResource exerciceResource = new ExerciceResource(exerciceServiceMock);
            when(exerciceServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restExerciceMockMvc = MockMvcBuilders.standaloneSetup(exerciceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restExerciceMockMvc.perform(get("/api/exercices?eagerload=true"))
        .andExpect(status().isOk());

            verify(exerciceServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getExercice() throws Exception {
        // Initialize the database
        exerciceRepository.saveAndFlush(exercice);

        // Get the exercice
        restExerciceMockMvc.perform(get("/api/exercices/{id}", exercice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(exercice.getId().intValue()))
            .andExpect(jsonPath("$.exerciceName").value(DEFAULT_EXERCICE_NAME))
            .andExpect(jsonPath("$.recomendation").value(DEFAULT_RECOMENDATION))
            .andExpect(jsonPath("$.docSchema").value(DEFAULT_DOC_SCHEMA.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingExercice() throws Exception {
        // Get the exercice
        restExerciceMockMvc.perform(get("/api/exercices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExercice() throws Exception {
        // Initialize the database
        exerciceService.save(exercice);

        int databaseSizeBeforeUpdate = exerciceRepository.findAll().size();

        // Update the exercice
        Exercice updatedExercice = exerciceRepository.findById(exercice.getId()).get();
        // Disconnect from session so that the updates on updatedExercice are not directly saved in db
        em.detach(updatedExercice);
        updatedExercice
            .exerciceName(UPDATED_EXERCICE_NAME)
            .recomendation(UPDATED_RECOMENDATION)
            .docSchema(UPDATED_DOC_SCHEMA);

        restExerciceMockMvc.perform(put("/api/exercices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedExercice)))
            .andExpect(status().isOk());

        // Validate the Exercice in the database
        List<Exercice> exerciceList = exerciceRepository.findAll();
        assertThat(exerciceList).hasSize(databaseSizeBeforeUpdate);
        Exercice testExercice = exerciceList.get(exerciceList.size() - 1);
        assertThat(testExercice.getExerciceName()).isEqualTo(UPDATED_EXERCICE_NAME);
        assertThat(testExercice.getRecomendation()).isEqualTo(UPDATED_RECOMENDATION);
        assertThat(testExercice.getDocSchema()).isEqualTo(UPDATED_DOC_SCHEMA);
    }

    @Test
    @Transactional
    public void updateNonExistingExercice() throws Exception {
        int databaseSizeBeforeUpdate = exerciceRepository.findAll().size();

        // Create the Exercice

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExerciceMockMvc.perform(put("/api/exercices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exercice)))
            .andExpect(status().isBadRequest());

        // Validate the Exercice in the database
        List<Exercice> exerciceList = exerciceRepository.findAll();
        assertThat(exerciceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExercice() throws Exception {
        // Initialize the database
        exerciceService.save(exercice);

        int databaseSizeBeforeDelete = exerciceRepository.findAll().size();

        // Delete the exercice
        restExerciceMockMvc.perform(delete("/api/exercices/{id}", exercice.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Exercice> exerciceList = exerciceRepository.findAll();
        assertThat(exerciceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
