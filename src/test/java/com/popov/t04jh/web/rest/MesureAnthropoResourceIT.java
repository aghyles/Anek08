package com.popov.t04jh.web.rest;

import com.popov.t04jh.T04JhApp;
import com.popov.t04jh.domain.MesureAnthropo;
import com.popov.t04jh.repository.MesureAnthropoRepository;
import com.popov.t04jh.service.MesureAnthropoService;
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
 * Integration tests for the {@link MesureAnthropoResource} REST controller.
 */
@SpringBootTest(classes = T04JhApp.class)
public class MesureAnthropoResourceIT {

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_POIDS = 1L;
    private static final Long UPDATED_POIDS = 2L;

    private static final Long DEFAULT_STATURE = 1L;
    private static final Long UPDATED_STATURE = 2L;

    private static final Long DEFAULT_TAILLE_ASSIS = 1L;
    private static final Long UPDATED_TAILLE_ASSIS = 2L;

    private static final Long DEFAULT_LONG_TRONC = 1L;
    private static final Long UPDATED_LONG_TRONC = 2L;

    private static final Long DEFAULT_LONG_MEMBRE_INFERIEURS = 1L;
    private static final Long UPDATED_LONG_MEMBRE_INFERIEURS = 2L;

    private static final Long DEFAULT_LONG_MEMBRE_SUPERIEUR = 1L;
    private static final Long UPDATED_LONG_MEMBRE_SUPERIEUR = 2L;

    private static final Long DEFAULT_ANVERGURE_BRAS = 1L;
    private static final Long UPDATED_ANVERGURE_BRAS = 2L;

    private static final Long DEFAULT_LONG_BRAS = 1L;
    private static final Long UPDATED_LONG_BRAS = 2L;

    private static final Long DEFAULT_LONG_JAMBES = 1L;
    private static final Long UPDATED_LONG_JAMBES = 2L;

    private static final Long DEFAULT_LONG_PIEDS = 1L;
    private static final Long UPDATED_LONG_PIEDS = 2L;

    private static final Long DEFAULT_HAUTEUR_PIED = 1L;
    private static final Long UPDATED_HAUTEUR_PIED = 2L;

    private static final Long DEFAULT_LONG_MAIN = 1L;
    private static final Long UPDATED_LONG_MAIN = 2L;

    private static final Long DEFAULT_DIAM_MAIN = 1L;
    private static final Long UPDATED_DIAM_MAIN = 2L;

    private static final Long DEFAULT_DIAM_BIACROMIAL = 1L;
    private static final Long UPDATED_DIAM_BIACROMIAL = 2L;

    private static final Long DEFAULT_DIAM_BICRETAL = 1L;
    private static final Long UPDATED_DIAM_BICRETAL = 2L;

    private static final Long DEFAULT_DIAM_THORAX = 1L;
    private static final Long UPDATED_DIAM_THORAX = 2L;

    private static final Long DEFAULT_CIRC_THORAX = 1L;
    private static final Long UPDATED_CIRC_THORAX = 2L;

    private static final Long DEFAULT_CIRC_THORAX_INSPIRANT = 1L;
    private static final Long UPDATED_CIRC_THORAX_INSPIRANT = 2L;

    private static final Long DEFAULT_CIRC_THORAX_EXPIRANT = 1L;
    private static final Long UPDATED_CIRC_THORAX_EXPIRANT = 2L;

    private static final Long DEFAULT_CIRC_BRAS_CONTRACTE = 1L;
    private static final Long UPDATED_CIRC_BRAS_CONTRACTE = 2L;

    private static final Long DEFAULT_CIRC_BRAS_DECONTRACT = 1L;
    private static final Long UPDATED_CIRC_BRAS_DECONTRACT = 2L;

    private static final Long DEFAULT_CIRC_CUISSE = 1L;
    private static final Long UPDATED_CIRC_CUISSE = 2L;

    @Autowired
    private MesureAnthropoRepository mesureAnthropoRepository;

    @Autowired
    private MesureAnthropoService mesureAnthropoService;

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

    private MockMvc restMesureAnthropoMockMvc;

    private MesureAnthropo mesureAnthropo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MesureAnthropoResource mesureAnthropoResource = new MesureAnthropoResource(mesureAnthropoService);
        this.restMesureAnthropoMockMvc = MockMvcBuilders.standaloneSetup(mesureAnthropoResource)
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
    public static MesureAnthropo createEntity(EntityManager em) {
        MesureAnthropo mesureAnthropo = new MesureAnthropo()
            .date(DEFAULT_DATE)
            .poids(DEFAULT_POIDS)
            .stature(DEFAULT_STATURE)
            .tailleAssis(DEFAULT_TAILLE_ASSIS)
            .longTronc(DEFAULT_LONG_TRONC)
            .longMembreInferieurs(DEFAULT_LONG_MEMBRE_INFERIEURS)
            .longMembreSuperieur(DEFAULT_LONG_MEMBRE_SUPERIEUR)
            .anvergureBras(DEFAULT_ANVERGURE_BRAS)
            .longBras(DEFAULT_LONG_BRAS)
            .longJambes(DEFAULT_LONG_JAMBES)
            .longPieds(DEFAULT_LONG_PIEDS)
            .hauteurPied(DEFAULT_HAUTEUR_PIED)
            .longMain(DEFAULT_LONG_MAIN)
            .diamMain(DEFAULT_DIAM_MAIN)
            .diamBiacromial(DEFAULT_DIAM_BIACROMIAL)
            .diamBicretal(DEFAULT_DIAM_BICRETAL)
            .diamThorax(DEFAULT_DIAM_THORAX)
            .circThorax(DEFAULT_CIRC_THORAX)
            .circThoraxInspirant(DEFAULT_CIRC_THORAX_INSPIRANT)
            .circThoraxExpirant(DEFAULT_CIRC_THORAX_EXPIRANT)
            .circBrasContracte(DEFAULT_CIRC_BRAS_CONTRACTE)
            .circBrasDecontract(DEFAULT_CIRC_BRAS_DECONTRACT)
            .circCuisse(DEFAULT_CIRC_CUISSE);
        return mesureAnthropo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MesureAnthropo createUpdatedEntity(EntityManager em) {
        MesureAnthropo mesureAnthropo = new MesureAnthropo()
            .date(UPDATED_DATE)
            .poids(UPDATED_POIDS)
            .stature(UPDATED_STATURE)
            .tailleAssis(UPDATED_TAILLE_ASSIS)
            .longTronc(UPDATED_LONG_TRONC)
            .longMembreInferieurs(UPDATED_LONG_MEMBRE_INFERIEURS)
            .longMembreSuperieur(UPDATED_LONG_MEMBRE_SUPERIEUR)
            .anvergureBras(UPDATED_ANVERGURE_BRAS)
            .longBras(UPDATED_LONG_BRAS)
            .longJambes(UPDATED_LONG_JAMBES)
            .longPieds(UPDATED_LONG_PIEDS)
            .hauteurPied(UPDATED_HAUTEUR_PIED)
            .longMain(UPDATED_LONG_MAIN)
            .diamMain(UPDATED_DIAM_MAIN)
            .diamBiacromial(UPDATED_DIAM_BIACROMIAL)
            .diamBicretal(UPDATED_DIAM_BICRETAL)
            .diamThorax(UPDATED_DIAM_THORAX)
            .circThorax(UPDATED_CIRC_THORAX)
            .circThoraxInspirant(UPDATED_CIRC_THORAX_INSPIRANT)
            .circThoraxExpirant(UPDATED_CIRC_THORAX_EXPIRANT)
            .circBrasContracte(UPDATED_CIRC_BRAS_CONTRACTE)
            .circBrasDecontract(UPDATED_CIRC_BRAS_DECONTRACT)
            .circCuisse(UPDATED_CIRC_CUISSE);
        return mesureAnthropo;
    }

    @BeforeEach
    public void initTest() {
        mesureAnthropo = createEntity(em);
    }

    @Test
    @Transactional
    public void createMesureAnthropo() throws Exception {
        int databaseSizeBeforeCreate = mesureAnthropoRepository.findAll().size();

        // Create the MesureAnthropo
        restMesureAnthropoMockMvc.perform(post("/api/mesure-anthropos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mesureAnthropo)))
            .andExpect(status().isCreated());

        // Validate the MesureAnthropo in the database
        List<MesureAnthropo> mesureAnthropoList = mesureAnthropoRepository.findAll();
        assertThat(mesureAnthropoList).hasSize(databaseSizeBeforeCreate + 1);
        MesureAnthropo testMesureAnthropo = mesureAnthropoList.get(mesureAnthropoList.size() - 1);
        assertThat(testMesureAnthropo.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testMesureAnthropo.getPoids()).isEqualTo(DEFAULT_POIDS);
        assertThat(testMesureAnthropo.getStature()).isEqualTo(DEFAULT_STATURE);
        assertThat(testMesureAnthropo.getTailleAssis()).isEqualTo(DEFAULT_TAILLE_ASSIS);
        assertThat(testMesureAnthropo.getLongTronc()).isEqualTo(DEFAULT_LONG_TRONC);
        assertThat(testMesureAnthropo.getLongMembreInferieurs()).isEqualTo(DEFAULT_LONG_MEMBRE_INFERIEURS);
        assertThat(testMesureAnthropo.getLongMembreSuperieur()).isEqualTo(DEFAULT_LONG_MEMBRE_SUPERIEUR);
        assertThat(testMesureAnthropo.getAnvergureBras()).isEqualTo(DEFAULT_ANVERGURE_BRAS);
        assertThat(testMesureAnthropo.getLongBras()).isEqualTo(DEFAULT_LONG_BRAS);
        assertThat(testMesureAnthropo.getLongJambes()).isEqualTo(DEFAULT_LONG_JAMBES);
        assertThat(testMesureAnthropo.getLongPieds()).isEqualTo(DEFAULT_LONG_PIEDS);
        assertThat(testMesureAnthropo.getHauteurPied()).isEqualTo(DEFAULT_HAUTEUR_PIED);
        assertThat(testMesureAnthropo.getLongMain()).isEqualTo(DEFAULT_LONG_MAIN);
        assertThat(testMesureAnthropo.getDiamMain()).isEqualTo(DEFAULT_DIAM_MAIN);
        assertThat(testMesureAnthropo.getDiamBiacromial()).isEqualTo(DEFAULT_DIAM_BIACROMIAL);
        assertThat(testMesureAnthropo.getDiamBicretal()).isEqualTo(DEFAULT_DIAM_BICRETAL);
        assertThat(testMesureAnthropo.getDiamThorax()).isEqualTo(DEFAULT_DIAM_THORAX);
        assertThat(testMesureAnthropo.getCircThorax()).isEqualTo(DEFAULT_CIRC_THORAX);
        assertThat(testMesureAnthropo.getCircThoraxInspirant()).isEqualTo(DEFAULT_CIRC_THORAX_INSPIRANT);
        assertThat(testMesureAnthropo.getCircThoraxExpirant()).isEqualTo(DEFAULT_CIRC_THORAX_EXPIRANT);
        assertThat(testMesureAnthropo.getCircBrasContracte()).isEqualTo(DEFAULT_CIRC_BRAS_CONTRACTE);
        assertThat(testMesureAnthropo.getCircBrasDecontract()).isEqualTo(DEFAULT_CIRC_BRAS_DECONTRACT);
        assertThat(testMesureAnthropo.getCircCuisse()).isEqualTo(DEFAULT_CIRC_CUISSE);
    }

    @Test
    @Transactional
    public void createMesureAnthropoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mesureAnthropoRepository.findAll().size();

        // Create the MesureAnthropo with an existing ID
        mesureAnthropo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMesureAnthropoMockMvc.perform(post("/api/mesure-anthropos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mesureAnthropo)))
            .andExpect(status().isBadRequest());

        // Validate the MesureAnthropo in the database
        List<MesureAnthropo> mesureAnthropoList = mesureAnthropoRepository.findAll();
        assertThat(mesureAnthropoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mesureAnthropoRepository.findAll().size();
        // set the field null
        mesureAnthropo.setDate(null);

        // Create the MesureAnthropo, which fails.

        restMesureAnthropoMockMvc.perform(post("/api/mesure-anthropos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mesureAnthropo)))
            .andExpect(status().isBadRequest());

        List<MesureAnthropo> mesureAnthropoList = mesureAnthropoRepository.findAll();
        assertThat(mesureAnthropoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMesureAnthropos() throws Exception {
        // Initialize the database
        mesureAnthropoRepository.saveAndFlush(mesureAnthropo);

        // Get all the mesureAnthropoList
        restMesureAnthropoMockMvc.perform(get("/api/mesure-anthropos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mesureAnthropo.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.intValue())))
            .andExpect(jsonPath("$.[*].stature").value(hasItem(DEFAULT_STATURE.intValue())))
            .andExpect(jsonPath("$.[*].tailleAssis").value(hasItem(DEFAULT_TAILLE_ASSIS.intValue())))
            .andExpect(jsonPath("$.[*].longTronc").value(hasItem(DEFAULT_LONG_TRONC.intValue())))
            .andExpect(jsonPath("$.[*].longMembreInferieurs").value(hasItem(DEFAULT_LONG_MEMBRE_INFERIEURS.intValue())))
            .andExpect(jsonPath("$.[*].longMembreSuperieur").value(hasItem(DEFAULT_LONG_MEMBRE_SUPERIEUR.intValue())))
            .andExpect(jsonPath("$.[*].anvergureBras").value(hasItem(DEFAULT_ANVERGURE_BRAS.intValue())))
            .andExpect(jsonPath("$.[*].longBras").value(hasItem(DEFAULT_LONG_BRAS.intValue())))
            .andExpect(jsonPath("$.[*].longJambes").value(hasItem(DEFAULT_LONG_JAMBES.intValue())))
            .andExpect(jsonPath("$.[*].longPieds").value(hasItem(DEFAULT_LONG_PIEDS.intValue())))
            .andExpect(jsonPath("$.[*].hauteurPied").value(hasItem(DEFAULT_HAUTEUR_PIED.intValue())))
            .andExpect(jsonPath("$.[*].longMain").value(hasItem(DEFAULT_LONG_MAIN.intValue())))
            .andExpect(jsonPath("$.[*].diamMain").value(hasItem(DEFAULT_DIAM_MAIN.intValue())))
            .andExpect(jsonPath("$.[*].diamBiacromial").value(hasItem(DEFAULT_DIAM_BIACROMIAL.intValue())))
            .andExpect(jsonPath("$.[*].diamBicretal").value(hasItem(DEFAULT_DIAM_BICRETAL.intValue())))
            .andExpect(jsonPath("$.[*].diamThorax").value(hasItem(DEFAULT_DIAM_THORAX.intValue())))
            .andExpect(jsonPath("$.[*].circThorax").value(hasItem(DEFAULT_CIRC_THORAX.intValue())))
            .andExpect(jsonPath("$.[*].circThoraxInspirant").value(hasItem(DEFAULT_CIRC_THORAX_INSPIRANT.intValue())))
            .andExpect(jsonPath("$.[*].circThoraxExpirant").value(hasItem(DEFAULT_CIRC_THORAX_EXPIRANT.intValue())))
            .andExpect(jsonPath("$.[*].circBrasContracte").value(hasItem(DEFAULT_CIRC_BRAS_CONTRACTE.intValue())))
            .andExpect(jsonPath("$.[*].circBrasDecontract").value(hasItem(DEFAULT_CIRC_BRAS_DECONTRACT.intValue())))
            .andExpect(jsonPath("$.[*].circCuisse").value(hasItem(DEFAULT_CIRC_CUISSE.intValue())));
    }
    
    @Test
    @Transactional
    public void getMesureAnthropo() throws Exception {
        // Initialize the database
        mesureAnthropoRepository.saveAndFlush(mesureAnthropo);

        // Get the mesureAnthropo
        restMesureAnthropoMockMvc.perform(get("/api/mesure-anthropos/{id}", mesureAnthropo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mesureAnthropo.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.poids").value(DEFAULT_POIDS.intValue()))
            .andExpect(jsonPath("$.stature").value(DEFAULT_STATURE.intValue()))
            .andExpect(jsonPath("$.tailleAssis").value(DEFAULT_TAILLE_ASSIS.intValue()))
            .andExpect(jsonPath("$.longTronc").value(DEFAULT_LONG_TRONC.intValue()))
            .andExpect(jsonPath("$.longMembreInferieurs").value(DEFAULT_LONG_MEMBRE_INFERIEURS.intValue()))
            .andExpect(jsonPath("$.longMembreSuperieur").value(DEFAULT_LONG_MEMBRE_SUPERIEUR.intValue()))
            .andExpect(jsonPath("$.anvergureBras").value(DEFAULT_ANVERGURE_BRAS.intValue()))
            .andExpect(jsonPath("$.longBras").value(DEFAULT_LONG_BRAS.intValue()))
            .andExpect(jsonPath("$.longJambes").value(DEFAULT_LONG_JAMBES.intValue()))
            .andExpect(jsonPath("$.longPieds").value(DEFAULT_LONG_PIEDS.intValue()))
            .andExpect(jsonPath("$.hauteurPied").value(DEFAULT_HAUTEUR_PIED.intValue()))
            .andExpect(jsonPath("$.longMain").value(DEFAULT_LONG_MAIN.intValue()))
            .andExpect(jsonPath("$.diamMain").value(DEFAULT_DIAM_MAIN.intValue()))
            .andExpect(jsonPath("$.diamBiacromial").value(DEFAULT_DIAM_BIACROMIAL.intValue()))
            .andExpect(jsonPath("$.diamBicretal").value(DEFAULT_DIAM_BICRETAL.intValue()))
            .andExpect(jsonPath("$.diamThorax").value(DEFAULT_DIAM_THORAX.intValue()))
            .andExpect(jsonPath("$.circThorax").value(DEFAULT_CIRC_THORAX.intValue()))
            .andExpect(jsonPath("$.circThoraxInspirant").value(DEFAULT_CIRC_THORAX_INSPIRANT.intValue()))
            .andExpect(jsonPath("$.circThoraxExpirant").value(DEFAULT_CIRC_THORAX_EXPIRANT.intValue()))
            .andExpect(jsonPath("$.circBrasContracte").value(DEFAULT_CIRC_BRAS_CONTRACTE.intValue()))
            .andExpect(jsonPath("$.circBrasDecontract").value(DEFAULT_CIRC_BRAS_DECONTRACT.intValue()))
            .andExpect(jsonPath("$.circCuisse").value(DEFAULT_CIRC_CUISSE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMesureAnthropo() throws Exception {
        // Get the mesureAnthropo
        restMesureAnthropoMockMvc.perform(get("/api/mesure-anthropos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMesureAnthropo() throws Exception {
        // Initialize the database
        mesureAnthropoService.save(mesureAnthropo);

        int databaseSizeBeforeUpdate = mesureAnthropoRepository.findAll().size();

        // Update the mesureAnthropo
        MesureAnthropo updatedMesureAnthropo = mesureAnthropoRepository.findById(mesureAnthropo.getId()).get();
        // Disconnect from session so that the updates on updatedMesureAnthropo are not directly saved in db
        em.detach(updatedMesureAnthropo);
        updatedMesureAnthropo
            .date(UPDATED_DATE)
            .poids(UPDATED_POIDS)
            .stature(UPDATED_STATURE)
            .tailleAssis(UPDATED_TAILLE_ASSIS)
            .longTronc(UPDATED_LONG_TRONC)
            .longMembreInferieurs(UPDATED_LONG_MEMBRE_INFERIEURS)
            .longMembreSuperieur(UPDATED_LONG_MEMBRE_SUPERIEUR)
            .anvergureBras(UPDATED_ANVERGURE_BRAS)
            .longBras(UPDATED_LONG_BRAS)
            .longJambes(UPDATED_LONG_JAMBES)
            .longPieds(UPDATED_LONG_PIEDS)
            .hauteurPied(UPDATED_HAUTEUR_PIED)
            .longMain(UPDATED_LONG_MAIN)
            .diamMain(UPDATED_DIAM_MAIN)
            .diamBiacromial(UPDATED_DIAM_BIACROMIAL)
            .diamBicretal(UPDATED_DIAM_BICRETAL)
            .diamThorax(UPDATED_DIAM_THORAX)
            .circThorax(UPDATED_CIRC_THORAX)
            .circThoraxInspirant(UPDATED_CIRC_THORAX_INSPIRANT)
            .circThoraxExpirant(UPDATED_CIRC_THORAX_EXPIRANT)
            .circBrasContracte(UPDATED_CIRC_BRAS_CONTRACTE)
            .circBrasDecontract(UPDATED_CIRC_BRAS_DECONTRACT)
            .circCuisse(UPDATED_CIRC_CUISSE);

        restMesureAnthropoMockMvc.perform(put("/api/mesure-anthropos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMesureAnthropo)))
            .andExpect(status().isOk());

        // Validate the MesureAnthropo in the database
        List<MesureAnthropo> mesureAnthropoList = mesureAnthropoRepository.findAll();
        assertThat(mesureAnthropoList).hasSize(databaseSizeBeforeUpdate);
        MesureAnthropo testMesureAnthropo = mesureAnthropoList.get(mesureAnthropoList.size() - 1);
        assertThat(testMesureAnthropo.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testMesureAnthropo.getPoids()).isEqualTo(UPDATED_POIDS);
        assertThat(testMesureAnthropo.getStature()).isEqualTo(UPDATED_STATURE);
        assertThat(testMesureAnthropo.getTailleAssis()).isEqualTo(UPDATED_TAILLE_ASSIS);
        assertThat(testMesureAnthropo.getLongTronc()).isEqualTo(UPDATED_LONG_TRONC);
        assertThat(testMesureAnthropo.getLongMembreInferieurs()).isEqualTo(UPDATED_LONG_MEMBRE_INFERIEURS);
        assertThat(testMesureAnthropo.getLongMembreSuperieur()).isEqualTo(UPDATED_LONG_MEMBRE_SUPERIEUR);
        assertThat(testMesureAnthropo.getAnvergureBras()).isEqualTo(UPDATED_ANVERGURE_BRAS);
        assertThat(testMesureAnthropo.getLongBras()).isEqualTo(UPDATED_LONG_BRAS);
        assertThat(testMesureAnthropo.getLongJambes()).isEqualTo(UPDATED_LONG_JAMBES);
        assertThat(testMesureAnthropo.getLongPieds()).isEqualTo(UPDATED_LONG_PIEDS);
        assertThat(testMesureAnthropo.getHauteurPied()).isEqualTo(UPDATED_HAUTEUR_PIED);
        assertThat(testMesureAnthropo.getLongMain()).isEqualTo(UPDATED_LONG_MAIN);
        assertThat(testMesureAnthropo.getDiamMain()).isEqualTo(UPDATED_DIAM_MAIN);
        assertThat(testMesureAnthropo.getDiamBiacromial()).isEqualTo(UPDATED_DIAM_BIACROMIAL);
        assertThat(testMesureAnthropo.getDiamBicretal()).isEqualTo(UPDATED_DIAM_BICRETAL);
        assertThat(testMesureAnthropo.getDiamThorax()).isEqualTo(UPDATED_DIAM_THORAX);
        assertThat(testMesureAnthropo.getCircThorax()).isEqualTo(UPDATED_CIRC_THORAX);
        assertThat(testMesureAnthropo.getCircThoraxInspirant()).isEqualTo(UPDATED_CIRC_THORAX_INSPIRANT);
        assertThat(testMesureAnthropo.getCircThoraxExpirant()).isEqualTo(UPDATED_CIRC_THORAX_EXPIRANT);
        assertThat(testMesureAnthropo.getCircBrasContracte()).isEqualTo(UPDATED_CIRC_BRAS_CONTRACTE);
        assertThat(testMesureAnthropo.getCircBrasDecontract()).isEqualTo(UPDATED_CIRC_BRAS_DECONTRACT);
        assertThat(testMesureAnthropo.getCircCuisse()).isEqualTo(UPDATED_CIRC_CUISSE);
    }

    @Test
    @Transactional
    public void updateNonExistingMesureAnthropo() throws Exception {
        int databaseSizeBeforeUpdate = mesureAnthropoRepository.findAll().size();

        // Create the MesureAnthropo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMesureAnthropoMockMvc.perform(put("/api/mesure-anthropos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mesureAnthropo)))
            .andExpect(status().isBadRequest());

        // Validate the MesureAnthropo in the database
        List<MesureAnthropo> mesureAnthropoList = mesureAnthropoRepository.findAll();
        assertThat(mesureAnthropoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMesureAnthropo() throws Exception {
        // Initialize the database
        mesureAnthropoService.save(mesureAnthropo);

        int databaseSizeBeforeDelete = mesureAnthropoRepository.findAll().size();

        // Delete the mesureAnthropo
        restMesureAnthropoMockMvc.perform(delete("/api/mesure-anthropos/{id}", mesureAnthropo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MesureAnthropo> mesureAnthropoList = mesureAnthropoRepository.findAll();
        assertThat(mesureAnthropoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
