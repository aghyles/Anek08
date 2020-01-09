package com.popov.t04jh.web.rest;

import com.popov.t04jh.T04JhApp;
import com.popov.t04jh.domain.Swimer;
import com.popov.t04jh.domain.MesureAnthropo;
import com.popov.t04jh.domain.FicheTest;
import com.popov.t04jh.domain.Document;
import com.popov.t04jh.domain.Groupe;
import com.popov.t04jh.repository.SwimerRepository;
import com.popov.t04jh.service.SwimerService;
import com.popov.t04jh.web.rest.errors.ExceptionTranslator;
import com.popov.t04jh.service.dto.SwimerCriteria;
import com.popov.t04jh.service.SwimerQueryService;

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

import com.popov.t04jh.domain.enumeration.Sexe;
/**
 * Integration tests for the {@link SwimerResource} REST controller.
 */
@SpringBootTest(classes = T04JhApp.class)
public class SwimerResourceIT {

    private static final String DEFAULT_LICENCE_NUM = "AAAAAAAAAA";
    private static final String UPDATED_LICENCE_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final Sexe DEFAULT_SEXE = Sexe.HOMME;
    private static final Sexe UPDATED_SEXE = Sexe.FEMME;

    private static final Instant DEFAULT_BEARTHDAY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BEARTHDAY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_E_MAIL = "Xr@aKn.kM";
    private static final String UPDATED_E_MAIL = "Ve@1R-M.F4l.SH";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_STUDY_TIME = "AAAAAAAAAA";
    private static final String UPDATED_STUDY_TIME = "BBBBBBBBBB";

    private static final Instant DEFAULT_FIRST_SWIM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FIRST_SWIM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_GROUPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GROUPE_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_DOCUMENT = 1L;
    private static final Long UPDATED_DOCUMENT = 2L;
    private static final Long SMALLER_DOCUMENT = 1L - 1L;

    @Autowired
    private SwimerRepository swimerRepository;

    @Autowired
    private SwimerService swimerService;

    @Autowired
    private SwimerQueryService swimerQueryService;

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

    private MockMvc restSwimerMockMvc;

    private Swimer swimer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SwimerResource swimerResource = new SwimerResource(swimerService, swimerQueryService);
        this.restSwimerMockMvc = MockMvcBuilders.standaloneSetup(swimerResource)
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
    public static Swimer createEntity(EntityManager em) {
        Swimer swimer = new Swimer()
            .licenceNum(DEFAULT_LICENCE_NUM)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .sexe(DEFAULT_SEXE)
            .bearthday(DEFAULT_BEARTHDAY)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .eMail(DEFAULT_E_MAIL)
            .address(DEFAULT_ADDRESS)
            .studyTime(DEFAULT_STUDY_TIME)
            .firstSwim(DEFAULT_FIRST_SWIM)
            .groupeName(DEFAULT_GROUPE_NAME)
            .document(DEFAULT_DOCUMENT);
        return swimer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Swimer createUpdatedEntity(EntityManager em) {
        Swimer swimer = new Swimer()
            .licenceNum(UPDATED_LICENCE_NUM)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .sexe(UPDATED_SEXE)
            .bearthday(UPDATED_BEARTHDAY)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .eMail(UPDATED_E_MAIL)
            .address(UPDATED_ADDRESS)
            .studyTime(UPDATED_STUDY_TIME)
            .firstSwim(UPDATED_FIRST_SWIM)
            .groupeName(UPDATED_GROUPE_NAME)
            .document(UPDATED_DOCUMENT);
        return swimer;
    }

    @BeforeEach
    public void initTest() {
        swimer = createEntity(em);
    }

    @Test
    @Transactional
    public void createSwimer() throws Exception {
        int databaseSizeBeforeCreate = swimerRepository.findAll().size();

        // Create the Swimer
        restSwimerMockMvc.perform(post("/api/swimers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(swimer)))
            .andExpect(status().isCreated());

        // Validate the Swimer in the database
        List<Swimer> swimerList = swimerRepository.findAll();
        assertThat(swimerList).hasSize(databaseSizeBeforeCreate + 1);
        Swimer testSwimer = swimerList.get(swimerList.size() - 1);
        assertThat(testSwimer.getLicenceNum()).isEqualTo(DEFAULT_LICENCE_NUM);
        assertThat(testSwimer.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testSwimer.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testSwimer.getSexe()).isEqualTo(DEFAULT_SEXE);
        assertThat(testSwimer.getBearthday()).isEqualTo(DEFAULT_BEARTHDAY);
        assertThat(testSwimer.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testSwimer.geteMail()).isEqualTo(DEFAULT_E_MAIL);
        assertThat(testSwimer.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testSwimer.getStudyTime()).isEqualTo(DEFAULT_STUDY_TIME);
        assertThat(testSwimer.getFirstSwim()).isEqualTo(DEFAULT_FIRST_SWIM);
        assertThat(testSwimer.getGroupeName()).isEqualTo(DEFAULT_GROUPE_NAME);
        assertThat(testSwimer.getDocument()).isEqualTo(DEFAULT_DOCUMENT);
    }

    @Test
    @Transactional
    public void createSwimerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = swimerRepository.findAll().size();

        // Create the Swimer with an existing ID
        swimer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSwimerMockMvc.perform(post("/api/swimers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(swimer)))
            .andExpect(status().isBadRequest());

        // Validate the Swimer in the database
        List<Swimer> swimerList = swimerRepository.findAll();
        assertThat(swimerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = swimerRepository.findAll().size();
        // set the field null
        swimer.setFirstName(null);

        // Create the Swimer, which fails.

        restSwimerMockMvc.perform(post("/api/swimers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(swimer)))
            .andExpect(status().isBadRequest());

        List<Swimer> swimerList = swimerRepository.findAll();
        assertThat(swimerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = swimerRepository.findAll().size();
        // set the field null
        swimer.setLastName(null);

        // Create the Swimer, which fails.

        restSwimerMockMvc.perform(post("/api/swimers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(swimer)))
            .andExpect(status().isBadRequest());

        List<Swimer> swimerList = swimerRepository.findAll();
        assertThat(swimerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSwimers() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList
        restSwimerMockMvc.perform(get("/api/swimers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(swimer.getId().intValue())))
            .andExpect(jsonPath("$.[*].licenceNum").value(hasItem(DEFAULT_LICENCE_NUM)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].sexe").value(hasItem(DEFAULT_SEXE.toString())))
            .andExpect(jsonPath("$.[*].bearthday").value(hasItem(DEFAULT_BEARTHDAY.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].eMail").value(hasItem(DEFAULT_E_MAIL)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].studyTime").value(hasItem(DEFAULT_STUDY_TIME)))
            .andExpect(jsonPath("$.[*].firstSwim").value(hasItem(DEFAULT_FIRST_SWIM.toString())))
            .andExpect(jsonPath("$.[*].groupeName").value(hasItem(DEFAULT_GROUPE_NAME)))
            .andExpect(jsonPath("$.[*].document").value(hasItem(DEFAULT_DOCUMENT.intValue())));
    }

    @Test
    @Transactional
    public void getSwimer() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get the swimer
        restSwimerMockMvc.perform(get("/api/swimers/{id}", swimer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(swimer.getId().intValue()))
            .andExpect(jsonPath("$.licenceNum").value(DEFAULT_LICENCE_NUM))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.sexe").value(DEFAULT_SEXE.toString()))
            .andExpect(jsonPath("$.bearthday").value(DEFAULT_BEARTHDAY.toString()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.eMail").value(DEFAULT_E_MAIL))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.studyTime").value(DEFAULT_STUDY_TIME))
            .andExpect(jsonPath("$.firstSwim").value(DEFAULT_FIRST_SWIM.toString()))
            .andExpect(jsonPath("$.groupeName").value(DEFAULT_GROUPE_NAME))
            .andExpect(jsonPath("$.document").value(DEFAULT_DOCUMENT.intValue()));
    }

    @Test
    @Transactional
    public void getAllSwimersByLicenceNumIsEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where licenceNum equals to DEFAULT_LICENCE_NUM
        defaultSwimerShouldBeFound("licenceNum.equals=" + DEFAULT_LICENCE_NUM);

        // Get all the swimerList where licenceNum equals to UPDATED_LICENCE_NUM
        defaultSwimerShouldNotBeFound("licenceNum.equals=" + UPDATED_LICENCE_NUM);
    }

    @Test
    @Transactional
    public void getAllSwimersByLicenceNumIsNotEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where licenceNum not equals to DEFAULT_LICENCE_NUM
        defaultSwimerShouldNotBeFound("licenceNum.notEquals=" + DEFAULT_LICENCE_NUM);

        // Get all the swimerList where licenceNum not equals to UPDATED_LICENCE_NUM
        defaultSwimerShouldBeFound("licenceNum.notEquals=" + UPDATED_LICENCE_NUM);
    }

    @Test
    @Transactional
    public void getAllSwimersByLicenceNumIsInShouldWork() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where licenceNum in DEFAULT_LICENCE_NUM or UPDATED_LICENCE_NUM
        defaultSwimerShouldBeFound("licenceNum.in=" + DEFAULT_LICENCE_NUM + "," + UPDATED_LICENCE_NUM);

        // Get all the swimerList where licenceNum equals to UPDATED_LICENCE_NUM
        defaultSwimerShouldNotBeFound("licenceNum.in=" + UPDATED_LICENCE_NUM);
    }

    @Test
    @Transactional
    public void getAllSwimersByLicenceNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where licenceNum is not null
        defaultSwimerShouldBeFound("licenceNum.specified=true");

        // Get all the swimerList where licenceNum is null
        defaultSwimerShouldNotBeFound("licenceNum.specified=false");
    }
                @Test
    @Transactional
    public void getAllSwimersByLicenceNumContainsSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where licenceNum contains DEFAULT_LICENCE_NUM
        defaultSwimerShouldBeFound("licenceNum.contains=" + DEFAULT_LICENCE_NUM);

        // Get all the swimerList where licenceNum contains UPDATED_LICENCE_NUM
        defaultSwimerShouldNotBeFound("licenceNum.contains=" + UPDATED_LICENCE_NUM);
    }

    @Test
    @Transactional
    public void getAllSwimersByLicenceNumNotContainsSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where licenceNum does not contain DEFAULT_LICENCE_NUM
        defaultSwimerShouldNotBeFound("licenceNum.doesNotContain=" + DEFAULT_LICENCE_NUM);

        // Get all the swimerList where licenceNum does not contain UPDATED_LICENCE_NUM
        defaultSwimerShouldBeFound("licenceNum.doesNotContain=" + UPDATED_LICENCE_NUM);
    }


    @Test
    @Transactional
    public void getAllSwimersByFirstNameIsEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where firstName equals to DEFAULT_FIRST_NAME
        defaultSwimerShouldBeFound("firstName.equals=" + DEFAULT_FIRST_NAME);

        // Get all the swimerList where firstName equals to UPDATED_FIRST_NAME
        defaultSwimerShouldNotBeFound("firstName.equals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllSwimersByFirstNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where firstName not equals to DEFAULT_FIRST_NAME
        defaultSwimerShouldNotBeFound("firstName.notEquals=" + DEFAULT_FIRST_NAME);

        // Get all the swimerList where firstName not equals to UPDATED_FIRST_NAME
        defaultSwimerShouldBeFound("firstName.notEquals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllSwimersByFirstNameIsInShouldWork() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where firstName in DEFAULT_FIRST_NAME or UPDATED_FIRST_NAME
        defaultSwimerShouldBeFound("firstName.in=" + DEFAULT_FIRST_NAME + "," + UPDATED_FIRST_NAME);

        // Get all the swimerList where firstName equals to UPDATED_FIRST_NAME
        defaultSwimerShouldNotBeFound("firstName.in=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllSwimersByFirstNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where firstName is not null
        defaultSwimerShouldBeFound("firstName.specified=true");

        // Get all the swimerList where firstName is null
        defaultSwimerShouldNotBeFound("firstName.specified=false");
    }
                @Test
    @Transactional
    public void getAllSwimersByFirstNameContainsSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where firstName contains DEFAULT_FIRST_NAME
        defaultSwimerShouldBeFound("firstName.contains=" + DEFAULT_FIRST_NAME);

        // Get all the swimerList where firstName contains UPDATED_FIRST_NAME
        defaultSwimerShouldNotBeFound("firstName.contains=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllSwimersByFirstNameNotContainsSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where firstName does not contain DEFAULT_FIRST_NAME
        defaultSwimerShouldNotBeFound("firstName.doesNotContain=" + DEFAULT_FIRST_NAME);

        // Get all the swimerList where firstName does not contain UPDATED_FIRST_NAME
        defaultSwimerShouldBeFound("firstName.doesNotContain=" + UPDATED_FIRST_NAME);
    }


    @Test
    @Transactional
    public void getAllSwimersByLastNameIsEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where lastName equals to DEFAULT_LAST_NAME
        defaultSwimerShouldBeFound("lastName.equals=" + DEFAULT_LAST_NAME);

        // Get all the swimerList where lastName equals to UPDATED_LAST_NAME
        defaultSwimerShouldNotBeFound("lastName.equals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllSwimersByLastNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where lastName not equals to DEFAULT_LAST_NAME
        defaultSwimerShouldNotBeFound("lastName.notEquals=" + DEFAULT_LAST_NAME);

        // Get all the swimerList where lastName not equals to UPDATED_LAST_NAME
        defaultSwimerShouldBeFound("lastName.notEquals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllSwimersByLastNameIsInShouldWork() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where lastName in DEFAULT_LAST_NAME or UPDATED_LAST_NAME
        defaultSwimerShouldBeFound("lastName.in=" + DEFAULT_LAST_NAME + "," + UPDATED_LAST_NAME);

        // Get all the swimerList where lastName equals to UPDATED_LAST_NAME
        defaultSwimerShouldNotBeFound("lastName.in=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllSwimersByLastNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where lastName is not null
        defaultSwimerShouldBeFound("lastName.specified=true");

        // Get all the swimerList where lastName is null
        defaultSwimerShouldNotBeFound("lastName.specified=false");
    }
                @Test
    @Transactional
    public void getAllSwimersByLastNameContainsSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where lastName contains DEFAULT_LAST_NAME
        defaultSwimerShouldBeFound("lastName.contains=" + DEFAULT_LAST_NAME);

        // Get all the swimerList where lastName contains UPDATED_LAST_NAME
        defaultSwimerShouldNotBeFound("lastName.contains=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllSwimersByLastNameNotContainsSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where lastName does not contain DEFAULT_LAST_NAME
        defaultSwimerShouldNotBeFound("lastName.doesNotContain=" + DEFAULT_LAST_NAME);

        // Get all the swimerList where lastName does not contain UPDATED_LAST_NAME
        defaultSwimerShouldBeFound("lastName.doesNotContain=" + UPDATED_LAST_NAME);
    }


    @Test
    @Transactional
    public void getAllSwimersBySexeIsEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where sexe equals to DEFAULT_SEXE
        defaultSwimerShouldBeFound("sexe.equals=" + DEFAULT_SEXE);

        // Get all the swimerList where sexe equals to UPDATED_SEXE
        defaultSwimerShouldNotBeFound("sexe.equals=" + UPDATED_SEXE);
    }

    @Test
    @Transactional
    public void getAllSwimersBySexeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where sexe not equals to DEFAULT_SEXE
        defaultSwimerShouldNotBeFound("sexe.notEquals=" + DEFAULT_SEXE);

        // Get all the swimerList where sexe not equals to UPDATED_SEXE
        defaultSwimerShouldBeFound("sexe.notEquals=" + UPDATED_SEXE);
    }

    @Test
    @Transactional
    public void getAllSwimersBySexeIsInShouldWork() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where sexe in DEFAULT_SEXE or UPDATED_SEXE
        defaultSwimerShouldBeFound("sexe.in=" + DEFAULT_SEXE + "," + UPDATED_SEXE);

        // Get all the swimerList where sexe equals to UPDATED_SEXE
        defaultSwimerShouldNotBeFound("sexe.in=" + UPDATED_SEXE);
    }

    @Test
    @Transactional
    public void getAllSwimersBySexeIsNullOrNotNull() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where sexe is not null
        defaultSwimerShouldBeFound("sexe.specified=true");

        // Get all the swimerList where sexe is null
        defaultSwimerShouldNotBeFound("sexe.specified=false");
    }

    @Test
    @Transactional
    public void getAllSwimersByBearthdayIsEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where bearthday equals to DEFAULT_BEARTHDAY
        defaultSwimerShouldBeFound("bearthday.equals=" + DEFAULT_BEARTHDAY);

        // Get all the swimerList where bearthday equals to UPDATED_BEARTHDAY
        defaultSwimerShouldNotBeFound("bearthday.equals=" + UPDATED_BEARTHDAY);
    }

    @Test
    @Transactional
    public void getAllSwimersByBearthdayIsNotEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where bearthday not equals to DEFAULT_BEARTHDAY
        defaultSwimerShouldNotBeFound("bearthday.notEquals=" + DEFAULT_BEARTHDAY);

        // Get all the swimerList where bearthday not equals to UPDATED_BEARTHDAY
        defaultSwimerShouldBeFound("bearthday.notEquals=" + UPDATED_BEARTHDAY);
    }

    @Test
    @Transactional
    public void getAllSwimersByBearthdayIsInShouldWork() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where bearthday in DEFAULT_BEARTHDAY or UPDATED_BEARTHDAY
        defaultSwimerShouldBeFound("bearthday.in=" + DEFAULT_BEARTHDAY + "," + UPDATED_BEARTHDAY);

        // Get all the swimerList where bearthday equals to UPDATED_BEARTHDAY
        defaultSwimerShouldNotBeFound("bearthday.in=" + UPDATED_BEARTHDAY);
    }

    @Test
    @Transactional
    public void getAllSwimersByBearthdayIsNullOrNotNull() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where bearthday is not null
        defaultSwimerShouldBeFound("bearthday.specified=true");

        // Get all the swimerList where bearthday is null
        defaultSwimerShouldNotBeFound("bearthday.specified=false");
    }

    @Test
    @Transactional
    public void getAllSwimersByPhoneNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where phoneNumber equals to DEFAULT_PHONE_NUMBER
        defaultSwimerShouldBeFound("phoneNumber.equals=" + DEFAULT_PHONE_NUMBER);

        // Get all the swimerList where phoneNumber equals to UPDATED_PHONE_NUMBER
        defaultSwimerShouldNotBeFound("phoneNumber.equals=" + UPDATED_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void getAllSwimersByPhoneNumberIsNotEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where phoneNumber not equals to DEFAULT_PHONE_NUMBER
        defaultSwimerShouldNotBeFound("phoneNumber.notEquals=" + DEFAULT_PHONE_NUMBER);

        // Get all the swimerList where phoneNumber not equals to UPDATED_PHONE_NUMBER
        defaultSwimerShouldBeFound("phoneNumber.notEquals=" + UPDATED_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void getAllSwimersByPhoneNumberIsInShouldWork() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where phoneNumber in DEFAULT_PHONE_NUMBER or UPDATED_PHONE_NUMBER
        defaultSwimerShouldBeFound("phoneNumber.in=" + DEFAULT_PHONE_NUMBER + "," + UPDATED_PHONE_NUMBER);

        // Get all the swimerList where phoneNumber equals to UPDATED_PHONE_NUMBER
        defaultSwimerShouldNotBeFound("phoneNumber.in=" + UPDATED_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void getAllSwimersByPhoneNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where phoneNumber is not null
        defaultSwimerShouldBeFound("phoneNumber.specified=true");

        // Get all the swimerList where phoneNumber is null
        defaultSwimerShouldNotBeFound("phoneNumber.specified=false");
    }
                @Test
    @Transactional
    public void getAllSwimersByPhoneNumberContainsSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where phoneNumber contains DEFAULT_PHONE_NUMBER
        defaultSwimerShouldBeFound("phoneNumber.contains=" + DEFAULT_PHONE_NUMBER);

        // Get all the swimerList where phoneNumber contains UPDATED_PHONE_NUMBER
        defaultSwimerShouldNotBeFound("phoneNumber.contains=" + UPDATED_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void getAllSwimersByPhoneNumberNotContainsSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where phoneNumber does not contain DEFAULT_PHONE_NUMBER
        defaultSwimerShouldNotBeFound("phoneNumber.doesNotContain=" + DEFAULT_PHONE_NUMBER);

        // Get all the swimerList where phoneNumber does not contain UPDATED_PHONE_NUMBER
        defaultSwimerShouldBeFound("phoneNumber.doesNotContain=" + UPDATED_PHONE_NUMBER);
    }


    @Test
    @Transactional
    public void getAllSwimersByeMailIsEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where eMail equals to DEFAULT_E_MAIL
        defaultSwimerShouldBeFound("eMail.equals=" + DEFAULT_E_MAIL);

        // Get all the swimerList where eMail equals to UPDATED_E_MAIL
        defaultSwimerShouldNotBeFound("eMail.equals=" + UPDATED_E_MAIL);
    }

    @Test
    @Transactional
    public void getAllSwimersByeMailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where eMail not equals to DEFAULT_E_MAIL
        defaultSwimerShouldNotBeFound("eMail.notEquals=" + DEFAULT_E_MAIL);

        // Get all the swimerList where eMail not equals to UPDATED_E_MAIL
        defaultSwimerShouldBeFound("eMail.notEquals=" + UPDATED_E_MAIL);
    }

    @Test
    @Transactional
    public void getAllSwimersByeMailIsInShouldWork() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where eMail in DEFAULT_E_MAIL or UPDATED_E_MAIL
        defaultSwimerShouldBeFound("eMail.in=" + DEFAULT_E_MAIL + "," + UPDATED_E_MAIL);

        // Get all the swimerList where eMail equals to UPDATED_E_MAIL
        defaultSwimerShouldNotBeFound("eMail.in=" + UPDATED_E_MAIL);
    }

    @Test
    @Transactional
    public void getAllSwimersByeMailIsNullOrNotNull() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where eMail is not null
        defaultSwimerShouldBeFound("eMail.specified=true");

        // Get all the swimerList where eMail is null
        defaultSwimerShouldNotBeFound("eMail.specified=false");
    }
                @Test
    @Transactional
    public void getAllSwimersByeMailContainsSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where eMail contains DEFAULT_E_MAIL
        defaultSwimerShouldBeFound("eMail.contains=" + DEFAULT_E_MAIL);

        // Get all the swimerList where eMail contains UPDATED_E_MAIL
        defaultSwimerShouldNotBeFound("eMail.contains=" + UPDATED_E_MAIL);
    }

    @Test
    @Transactional
    public void getAllSwimersByeMailNotContainsSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where eMail does not contain DEFAULT_E_MAIL
        defaultSwimerShouldNotBeFound("eMail.doesNotContain=" + DEFAULT_E_MAIL);

        // Get all the swimerList where eMail does not contain UPDATED_E_MAIL
        defaultSwimerShouldBeFound("eMail.doesNotContain=" + UPDATED_E_MAIL);
    }


    @Test
    @Transactional
    public void getAllSwimersByAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where address equals to DEFAULT_ADDRESS
        defaultSwimerShouldBeFound("address.equals=" + DEFAULT_ADDRESS);

        // Get all the swimerList where address equals to UPDATED_ADDRESS
        defaultSwimerShouldNotBeFound("address.equals=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllSwimersByAddressIsNotEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where address not equals to DEFAULT_ADDRESS
        defaultSwimerShouldNotBeFound("address.notEquals=" + DEFAULT_ADDRESS);

        // Get all the swimerList where address not equals to UPDATED_ADDRESS
        defaultSwimerShouldBeFound("address.notEquals=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllSwimersByAddressIsInShouldWork() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where address in DEFAULT_ADDRESS or UPDATED_ADDRESS
        defaultSwimerShouldBeFound("address.in=" + DEFAULT_ADDRESS + "," + UPDATED_ADDRESS);

        // Get all the swimerList where address equals to UPDATED_ADDRESS
        defaultSwimerShouldNotBeFound("address.in=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllSwimersByAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where address is not null
        defaultSwimerShouldBeFound("address.specified=true");

        // Get all the swimerList where address is null
        defaultSwimerShouldNotBeFound("address.specified=false");
    }
                @Test
    @Transactional
    public void getAllSwimersByAddressContainsSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where address contains DEFAULT_ADDRESS
        defaultSwimerShouldBeFound("address.contains=" + DEFAULT_ADDRESS);

        // Get all the swimerList where address contains UPDATED_ADDRESS
        defaultSwimerShouldNotBeFound("address.contains=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllSwimersByAddressNotContainsSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where address does not contain DEFAULT_ADDRESS
        defaultSwimerShouldNotBeFound("address.doesNotContain=" + DEFAULT_ADDRESS);

        // Get all the swimerList where address does not contain UPDATED_ADDRESS
        defaultSwimerShouldBeFound("address.doesNotContain=" + UPDATED_ADDRESS);
    }


    @Test
    @Transactional
    public void getAllSwimersByStudyTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where studyTime equals to DEFAULT_STUDY_TIME
        defaultSwimerShouldBeFound("studyTime.equals=" + DEFAULT_STUDY_TIME);

        // Get all the swimerList where studyTime equals to UPDATED_STUDY_TIME
        defaultSwimerShouldNotBeFound("studyTime.equals=" + UPDATED_STUDY_TIME);
    }

    @Test
    @Transactional
    public void getAllSwimersByStudyTimeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where studyTime not equals to DEFAULT_STUDY_TIME
        defaultSwimerShouldNotBeFound("studyTime.notEquals=" + DEFAULT_STUDY_TIME);

        // Get all the swimerList where studyTime not equals to UPDATED_STUDY_TIME
        defaultSwimerShouldBeFound("studyTime.notEquals=" + UPDATED_STUDY_TIME);
    }

    @Test
    @Transactional
    public void getAllSwimersByStudyTimeIsInShouldWork() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where studyTime in DEFAULT_STUDY_TIME or UPDATED_STUDY_TIME
        defaultSwimerShouldBeFound("studyTime.in=" + DEFAULT_STUDY_TIME + "," + UPDATED_STUDY_TIME);

        // Get all the swimerList where studyTime equals to UPDATED_STUDY_TIME
        defaultSwimerShouldNotBeFound("studyTime.in=" + UPDATED_STUDY_TIME);
    }

    @Test
    @Transactional
    public void getAllSwimersByStudyTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where studyTime is not null
        defaultSwimerShouldBeFound("studyTime.specified=true");

        // Get all the swimerList where studyTime is null
        defaultSwimerShouldNotBeFound("studyTime.specified=false");
    }
                @Test
    @Transactional
    public void getAllSwimersByStudyTimeContainsSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where studyTime contains DEFAULT_STUDY_TIME
        defaultSwimerShouldBeFound("studyTime.contains=" + DEFAULT_STUDY_TIME);

        // Get all the swimerList where studyTime contains UPDATED_STUDY_TIME
        defaultSwimerShouldNotBeFound("studyTime.contains=" + UPDATED_STUDY_TIME);
    }

    @Test
    @Transactional
    public void getAllSwimersByStudyTimeNotContainsSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where studyTime does not contain DEFAULT_STUDY_TIME
        defaultSwimerShouldNotBeFound("studyTime.doesNotContain=" + DEFAULT_STUDY_TIME);

        // Get all the swimerList where studyTime does not contain UPDATED_STUDY_TIME
        defaultSwimerShouldBeFound("studyTime.doesNotContain=" + UPDATED_STUDY_TIME);
    }


    @Test
    @Transactional
    public void getAllSwimersByFirstSwimIsEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where firstSwim equals to DEFAULT_FIRST_SWIM
        defaultSwimerShouldBeFound("firstSwim.equals=" + DEFAULT_FIRST_SWIM);

        // Get all the swimerList where firstSwim equals to UPDATED_FIRST_SWIM
        defaultSwimerShouldNotBeFound("firstSwim.equals=" + UPDATED_FIRST_SWIM);
    }

    @Test
    @Transactional
    public void getAllSwimersByFirstSwimIsNotEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where firstSwim not equals to DEFAULT_FIRST_SWIM
        defaultSwimerShouldNotBeFound("firstSwim.notEquals=" + DEFAULT_FIRST_SWIM);

        // Get all the swimerList where firstSwim not equals to UPDATED_FIRST_SWIM
        defaultSwimerShouldBeFound("firstSwim.notEquals=" + UPDATED_FIRST_SWIM);
    }

    @Test
    @Transactional
    public void getAllSwimersByFirstSwimIsInShouldWork() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where firstSwim in DEFAULT_FIRST_SWIM or UPDATED_FIRST_SWIM
        defaultSwimerShouldBeFound("firstSwim.in=" + DEFAULT_FIRST_SWIM + "," + UPDATED_FIRST_SWIM);

        // Get all the swimerList where firstSwim equals to UPDATED_FIRST_SWIM
        defaultSwimerShouldNotBeFound("firstSwim.in=" + UPDATED_FIRST_SWIM);
    }

    @Test
    @Transactional
    public void getAllSwimersByFirstSwimIsNullOrNotNull() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where firstSwim is not null
        defaultSwimerShouldBeFound("firstSwim.specified=true");

        // Get all the swimerList where firstSwim is null
        defaultSwimerShouldNotBeFound("firstSwim.specified=false");
    }

    @Test
    @Transactional
    public void getAllSwimersByGroupeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where groupeName equals to DEFAULT_GROUPE_NAME
        defaultSwimerShouldBeFound("groupeName.equals=" + DEFAULT_GROUPE_NAME);

        // Get all the swimerList where groupeName equals to UPDATED_GROUPE_NAME
        defaultSwimerShouldNotBeFound("groupeName.equals=" + UPDATED_GROUPE_NAME);
    }

    @Test
    @Transactional
    public void getAllSwimersByGroupeNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where groupeName not equals to DEFAULT_GROUPE_NAME
        defaultSwimerShouldNotBeFound("groupeName.notEquals=" + DEFAULT_GROUPE_NAME);

        // Get all the swimerList where groupeName not equals to UPDATED_GROUPE_NAME
        defaultSwimerShouldBeFound("groupeName.notEquals=" + UPDATED_GROUPE_NAME);
    }

    @Test
    @Transactional
    public void getAllSwimersByGroupeNameIsInShouldWork() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where groupeName in DEFAULT_GROUPE_NAME or UPDATED_GROUPE_NAME
        defaultSwimerShouldBeFound("groupeName.in=" + DEFAULT_GROUPE_NAME + "," + UPDATED_GROUPE_NAME);

        // Get all the swimerList where groupeName equals to UPDATED_GROUPE_NAME
        defaultSwimerShouldNotBeFound("groupeName.in=" + UPDATED_GROUPE_NAME);
    }

    @Test
    @Transactional
    public void getAllSwimersByGroupeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where groupeName is not null
        defaultSwimerShouldBeFound("groupeName.specified=true");

        // Get all the swimerList where groupeName is null
        defaultSwimerShouldNotBeFound("groupeName.specified=false");
    }
                @Test
    @Transactional
    public void getAllSwimersByGroupeNameContainsSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where groupeName contains DEFAULT_GROUPE_NAME
        defaultSwimerShouldBeFound("groupeName.contains=" + DEFAULT_GROUPE_NAME);

        // Get all the swimerList where groupeName contains UPDATED_GROUPE_NAME
        defaultSwimerShouldNotBeFound("groupeName.contains=" + UPDATED_GROUPE_NAME);
    }

    @Test
    @Transactional
    public void getAllSwimersByGroupeNameNotContainsSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where groupeName does not contain DEFAULT_GROUPE_NAME
        defaultSwimerShouldNotBeFound("groupeName.doesNotContain=" + DEFAULT_GROUPE_NAME);

        // Get all the swimerList where groupeName does not contain UPDATED_GROUPE_NAME
        defaultSwimerShouldBeFound("groupeName.doesNotContain=" + UPDATED_GROUPE_NAME);
    }


    @Test
    @Transactional
    public void getAllSwimersByDocumentIsEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where document equals to DEFAULT_DOCUMENT
        defaultSwimerShouldBeFound("document.equals=" + DEFAULT_DOCUMENT);

        // Get all the swimerList where document equals to UPDATED_DOCUMENT
        defaultSwimerShouldNotBeFound("document.equals=" + UPDATED_DOCUMENT);
    }

    @Test
    @Transactional
    public void getAllSwimersByDocumentIsNotEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where document not equals to DEFAULT_DOCUMENT
        defaultSwimerShouldNotBeFound("document.notEquals=" + DEFAULT_DOCUMENT);

        // Get all the swimerList where document not equals to UPDATED_DOCUMENT
        defaultSwimerShouldBeFound("document.notEquals=" + UPDATED_DOCUMENT);
    }

    @Test
    @Transactional
    public void getAllSwimersByDocumentIsInShouldWork() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where document in DEFAULT_DOCUMENT or UPDATED_DOCUMENT
        defaultSwimerShouldBeFound("document.in=" + DEFAULT_DOCUMENT + "," + UPDATED_DOCUMENT);

        // Get all the swimerList where document equals to UPDATED_DOCUMENT
        defaultSwimerShouldNotBeFound("document.in=" + UPDATED_DOCUMENT);
    }

    @Test
    @Transactional
    public void getAllSwimersByDocumentIsNullOrNotNull() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where document is not null
        defaultSwimerShouldBeFound("document.specified=true");

        // Get all the swimerList where document is null
        defaultSwimerShouldNotBeFound("document.specified=false");
    }

    @Test
    @Transactional
    public void getAllSwimersByDocumentIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where document is greater than or equal to DEFAULT_DOCUMENT
        defaultSwimerShouldBeFound("document.greaterThanOrEqual=" + DEFAULT_DOCUMENT);

        // Get all the swimerList where document is greater than or equal to UPDATED_DOCUMENT
        defaultSwimerShouldNotBeFound("document.greaterThanOrEqual=" + UPDATED_DOCUMENT);
    }

    @Test
    @Transactional
    public void getAllSwimersByDocumentIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where document is less than or equal to DEFAULT_DOCUMENT
        defaultSwimerShouldBeFound("document.lessThanOrEqual=" + DEFAULT_DOCUMENT);

        // Get all the swimerList where document is less than or equal to SMALLER_DOCUMENT
        defaultSwimerShouldNotBeFound("document.lessThanOrEqual=" + SMALLER_DOCUMENT);
    }

    @Test
    @Transactional
    public void getAllSwimersByDocumentIsLessThanSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where document is less than DEFAULT_DOCUMENT
        defaultSwimerShouldNotBeFound("document.lessThan=" + DEFAULT_DOCUMENT);

        // Get all the swimerList where document is less than UPDATED_DOCUMENT
        defaultSwimerShouldBeFound("document.lessThan=" + UPDATED_DOCUMENT);
    }

    @Test
    @Transactional
    public void getAllSwimersByDocumentIsGreaterThanSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);

        // Get all the swimerList where document is greater than DEFAULT_DOCUMENT
        defaultSwimerShouldNotBeFound("document.greaterThan=" + DEFAULT_DOCUMENT);

        // Get all the swimerList where document is greater than SMALLER_DOCUMENT
        defaultSwimerShouldBeFound("document.greaterThan=" + SMALLER_DOCUMENT);
    }


    @Test
    @Transactional
    public void getAllSwimersByMesureAnthropoIsEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);
        MesureAnthropo mesureAnthropo = MesureAnthropoResourceIT.createEntity(em);
        em.persist(mesureAnthropo);
        em.flush();
        swimer.addMesureAnthropo(mesureAnthropo);
        swimerRepository.saveAndFlush(swimer);
        Long mesureAnthropoId = mesureAnthropo.getId();

        // Get all the swimerList where mesureAnthropo equals to mesureAnthropoId
        defaultSwimerShouldBeFound("mesureAnthropoId.equals=" + mesureAnthropoId);

        // Get all the swimerList where mesureAnthropo equals to mesureAnthropoId + 1
        defaultSwimerShouldNotBeFound("mesureAnthropoId.equals=" + (mesureAnthropoId + 1));
    }


    @Test
    @Transactional
    public void getAllSwimersByFicheTestIsEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);
        FicheTest ficheTest = FicheTestResourceIT.createEntity(em);
        em.persist(ficheTest);
        em.flush();
        swimer.addFicheTest(ficheTest);
        swimerRepository.saveAndFlush(swimer);
        Long ficheTestId = ficheTest.getId();

        // Get all the swimerList where ficheTest equals to ficheTestId
        defaultSwimerShouldBeFound("ficheTestId.equals=" + ficheTestId);

        // Get all the swimerList where ficheTest equals to ficheTestId + 1
        defaultSwimerShouldNotBeFound("ficheTestId.equals=" + (ficheTestId + 1));
    }




    @Test
    @Transactional
    public void getAllSwimersByGroupeIsEqualToSomething() throws Exception {
        // Initialize the database
        swimerRepository.saveAndFlush(swimer);
        Groupe groupe = GroupeResourceIT.createEntity(em);
        em.persist(groupe);
        em.flush();
        swimer.setGroupe(groupe);
        swimerRepository.saveAndFlush(swimer);
        Long groupeId = groupe.getId();

        // Get all the swimerList where groupe equals to groupeId
        defaultSwimerShouldBeFound("groupeId.equals=" + groupeId);

        // Get all the swimerList where groupe equals to groupeId + 1
        defaultSwimerShouldNotBeFound("groupeId.equals=" + (groupeId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSwimerShouldBeFound(String filter) throws Exception {
        restSwimerMockMvc.perform(get("/api/swimers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(swimer.getId().intValue())))
            .andExpect(jsonPath("$.[*].licenceNum").value(hasItem(DEFAULT_LICENCE_NUM)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].sexe").value(hasItem(DEFAULT_SEXE.toString())))
            .andExpect(jsonPath("$.[*].bearthday").value(hasItem(DEFAULT_BEARTHDAY.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].eMail").value(hasItem(DEFAULT_E_MAIL)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].studyTime").value(hasItem(DEFAULT_STUDY_TIME)))
            .andExpect(jsonPath("$.[*].firstSwim").value(hasItem(DEFAULT_FIRST_SWIM.toString())))
            .andExpect(jsonPath("$.[*].groupeName").value(hasItem(DEFAULT_GROUPE_NAME)))
            .andExpect(jsonPath("$.[*].document").value(hasItem(DEFAULT_DOCUMENT.intValue())));

        // Check, that the count call also returns 1
        restSwimerMockMvc.perform(get("/api/swimers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSwimerShouldNotBeFound(String filter) throws Exception {
        restSwimerMockMvc.perform(get("/api/swimers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSwimerMockMvc.perform(get("/api/swimers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSwimer() throws Exception {
        // Get the swimer
        restSwimerMockMvc.perform(get("/api/swimers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSwimer() throws Exception {
        // Initialize the database
        swimerService.save(swimer);

        int databaseSizeBeforeUpdate = swimerRepository.findAll().size();

        // Update the swimer
        Swimer updatedSwimer = swimerRepository.findById(swimer.getId()).get();
        // Disconnect from session so that the updates on updatedSwimer are not directly saved in db
        em.detach(updatedSwimer);
        updatedSwimer
            .licenceNum(UPDATED_LICENCE_NUM)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .sexe(UPDATED_SEXE)
            .bearthday(UPDATED_BEARTHDAY)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .eMail(UPDATED_E_MAIL)
            .address(UPDATED_ADDRESS)
            .studyTime(UPDATED_STUDY_TIME)
            .firstSwim(UPDATED_FIRST_SWIM)
            .groupeName(UPDATED_GROUPE_NAME)
            .document(UPDATED_DOCUMENT);

        restSwimerMockMvc.perform(put("/api/swimers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSwimer)))
            .andExpect(status().isOk());

        // Validate the Swimer in the database
        List<Swimer> swimerList = swimerRepository.findAll();
        assertThat(swimerList).hasSize(databaseSizeBeforeUpdate);
        Swimer testSwimer = swimerList.get(swimerList.size() - 1);
        assertThat(testSwimer.getLicenceNum()).isEqualTo(UPDATED_LICENCE_NUM);
        assertThat(testSwimer.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testSwimer.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testSwimer.getSexe()).isEqualTo(UPDATED_SEXE);
        assertThat(testSwimer.getBearthday()).isEqualTo(UPDATED_BEARTHDAY);
        assertThat(testSwimer.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testSwimer.geteMail()).isEqualTo(UPDATED_E_MAIL);
        assertThat(testSwimer.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testSwimer.getStudyTime()).isEqualTo(UPDATED_STUDY_TIME);
        assertThat(testSwimer.getFirstSwim()).isEqualTo(UPDATED_FIRST_SWIM);
        assertThat(testSwimer.getGroupeName()).isEqualTo(UPDATED_GROUPE_NAME);
        assertThat(testSwimer.getDocument()).isEqualTo(UPDATED_DOCUMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingSwimer() throws Exception {
        int databaseSizeBeforeUpdate = swimerRepository.findAll().size();

        // Create the Swimer

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSwimerMockMvc.perform(put("/api/swimers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(swimer)))
            .andExpect(status().isBadRequest());

        // Validate the Swimer in the database
        List<Swimer> swimerList = swimerRepository.findAll();
        assertThat(swimerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSwimer() throws Exception {
        // Initialize the database
        swimerService.save(swimer);

        int databaseSizeBeforeDelete = swimerRepository.findAll().size();

        // Delete the swimer
        restSwimerMockMvc.perform(delete("/api/swimers/{id}", swimer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Swimer> swimerList = swimerRepository.findAll();
        assertThat(swimerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Swimer.class);
        Swimer swimer1 = new Swimer();
        swimer1.setId(1L);
        Swimer swimer2 = new Swimer();
        swimer2.setId(swimer1.getId());
        assertThat(swimer1).isEqualTo(swimer2);
        swimer2.setId(2L);
        assertThat(swimer1).isNotEqualTo(swimer2);
        swimer1.setId(null);
        assertThat(swimer1).isNotEqualTo(swimer2);
    }
}
