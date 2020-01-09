package com.popov.t04jh.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A MesureAnthropo.
 */
@Entity
@Table(name = "mesure_anthropo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MesureAnthropo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "poids")
    private Long poids;

    @Column(name = "stature")
    private Long stature;

    @Column(name = "taille_assis")
    private Long tailleAssis;

    @Column(name = "long_tronc")
    private Long longTronc;

    @Column(name = "long_membre_inferieurs")
    private Long longMembreInferieurs;

    @Column(name = "long_membre_superieur")
    private Long longMembreSuperieur;

    @Column(name = "anvergure_bras")
    private Long anvergureBras;

    @Column(name = "long_bras")
    private Long longBras;

    @Column(name = "long_jambes")
    private Long longJambes;

    @Column(name = "long_pieds")
    private Long longPieds;

    @Column(name = "hauteur_pied")
    private Long hauteurPied;

    @Column(name = "long_main")
    private Long longMain;

    @Column(name = "diam_main")
    private Long diamMain;

    @Column(name = "diam_biacromial")
    private Long diamBiacromial;

    @Column(name = "diam_bicretal")
    private Long diamBicretal;

    @Column(name = "diam_thorax")
    private Long diamThorax;

    @Column(name = "circ_thorax")
    private Long circThorax;

    @Column(name = "circ_thorax_inspirant")
    private Long circThoraxInspirant;

    @Column(name = "circ_thorax_expirant")
    private Long circThoraxExpirant;

    @Column(name = "circ_bras_contracte")
    private Long circBrasContracte;

    @Column(name = "circ_bras_decontract")
    private Long circBrasDecontract;

    @Column(name = "circ_cuisse")
    private Long circCuisse;

    @ManyToOne
    @JsonIgnoreProperties("mesureAnthropos")
    private Swimer swimer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public MesureAnthropo date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Long getPoids() {
        return poids;
    }

    public MesureAnthropo poids(Long poids) {
        this.poids = poids;
        return this;
    }

    public void setPoids(Long poids) {
        this.poids = poids;
    }

    public Long getStature() {
        return stature;
    }

    public MesureAnthropo stature(Long stature) {
        this.stature = stature;
        return this;
    }

    public void setStature(Long stature) {
        this.stature = stature;
    }

    public Long getTailleAssis() {
        return tailleAssis;
    }

    public MesureAnthropo tailleAssis(Long tailleAssis) {
        this.tailleAssis = tailleAssis;
        return this;
    }

    public void setTailleAssis(Long tailleAssis) {
        this.tailleAssis = tailleAssis;
    }

    public Long getLongTronc() {
        return longTronc;
    }

    public MesureAnthropo longTronc(Long longTronc) {
        this.longTronc = longTronc;
        return this;
    }

    public void setLongTronc(Long longTronc) {
        this.longTronc = longTronc;
    }

    public Long getLongMembreInferieurs() {
        return longMembreInferieurs;
    }

    public MesureAnthropo longMembreInferieurs(Long longMembreInferieurs) {
        this.longMembreInferieurs = longMembreInferieurs;
        return this;
    }

    public void setLongMembreInferieurs(Long longMembreInferieurs) {
        this.longMembreInferieurs = longMembreInferieurs;
    }

    public Long getLongMembreSuperieur() {
        return longMembreSuperieur;
    }

    public MesureAnthropo longMembreSuperieur(Long longMembreSuperieur) {
        this.longMembreSuperieur = longMembreSuperieur;
        return this;
    }

    public void setLongMembreSuperieur(Long longMembreSuperieur) {
        this.longMembreSuperieur = longMembreSuperieur;
    }

    public Long getAnvergureBras() {
        return anvergureBras;
    }

    public MesureAnthropo anvergureBras(Long anvergureBras) {
        this.anvergureBras = anvergureBras;
        return this;
    }

    public void setAnvergureBras(Long anvergureBras) {
        this.anvergureBras = anvergureBras;
    }

    public Long getLongBras() {
        return longBras;
    }

    public MesureAnthropo longBras(Long longBras) {
        this.longBras = longBras;
        return this;
    }

    public void setLongBras(Long longBras) {
        this.longBras = longBras;
    }

    public Long getLongJambes() {
        return longJambes;
    }

    public MesureAnthropo longJambes(Long longJambes) {
        this.longJambes = longJambes;
        return this;
    }

    public void setLongJambes(Long longJambes) {
        this.longJambes = longJambes;
    }

    public Long getLongPieds() {
        return longPieds;
    }

    public MesureAnthropo longPieds(Long longPieds) {
        this.longPieds = longPieds;
        return this;
    }

    public void setLongPieds(Long longPieds) {
        this.longPieds = longPieds;
    }

    public Long getHauteurPied() {
        return hauteurPied;
    }

    public MesureAnthropo hauteurPied(Long hauteurPied) {
        this.hauteurPied = hauteurPied;
        return this;
    }

    public void setHauteurPied(Long hauteurPied) {
        this.hauteurPied = hauteurPied;
    }

    public Long getLongMain() {
        return longMain;
    }

    public MesureAnthropo longMain(Long longMain) {
        this.longMain = longMain;
        return this;
    }

    public void setLongMain(Long longMain) {
        this.longMain = longMain;
    }

    public Long getDiamMain() {
        return diamMain;
    }

    public MesureAnthropo diamMain(Long diamMain) {
        this.diamMain = diamMain;
        return this;
    }

    public void setDiamMain(Long diamMain) {
        this.diamMain = diamMain;
    }

    public Long getDiamBiacromial() {
        return diamBiacromial;
    }

    public MesureAnthropo diamBiacromial(Long diamBiacromial) {
        this.diamBiacromial = diamBiacromial;
        return this;
    }

    public void setDiamBiacromial(Long diamBiacromial) {
        this.diamBiacromial = diamBiacromial;
    }

    public Long getDiamBicretal() {
        return diamBicretal;
    }

    public MesureAnthropo diamBicretal(Long diamBicretal) {
        this.diamBicretal = diamBicretal;
        return this;
    }

    public void setDiamBicretal(Long diamBicretal) {
        this.diamBicretal = diamBicretal;
    }

    public Long getDiamThorax() {
        return diamThorax;
    }

    public MesureAnthropo diamThorax(Long diamThorax) {
        this.diamThorax = diamThorax;
        return this;
    }

    public void setDiamThorax(Long diamThorax) {
        this.diamThorax = diamThorax;
    }

    public Long getCircThorax() {
        return circThorax;
    }

    public MesureAnthropo circThorax(Long circThorax) {
        this.circThorax = circThorax;
        return this;
    }

    public void setCircThorax(Long circThorax) {
        this.circThorax = circThorax;
    }

    public Long getCircThoraxInspirant() {
        return circThoraxInspirant;
    }

    public MesureAnthropo circThoraxInspirant(Long circThoraxInspirant) {
        this.circThoraxInspirant = circThoraxInspirant;
        return this;
    }

    public void setCircThoraxInspirant(Long circThoraxInspirant) {
        this.circThoraxInspirant = circThoraxInspirant;
    }

    public Long getCircThoraxExpirant() {
        return circThoraxExpirant;
    }

    public MesureAnthropo circThoraxExpirant(Long circThoraxExpirant) {
        this.circThoraxExpirant = circThoraxExpirant;
        return this;
    }

    public void setCircThoraxExpirant(Long circThoraxExpirant) {
        this.circThoraxExpirant = circThoraxExpirant;
    }

    public Long getCircBrasContracte() {
        return circBrasContracte;
    }

    public MesureAnthropo circBrasContracte(Long circBrasContracte) {
        this.circBrasContracte = circBrasContracte;
        return this;
    }

    public void setCircBrasContracte(Long circBrasContracte) {
        this.circBrasContracte = circBrasContracte;
    }

    public Long getCircBrasDecontract() {
        return circBrasDecontract;
    }

    public MesureAnthropo circBrasDecontract(Long circBrasDecontract) {
        this.circBrasDecontract = circBrasDecontract;
        return this;
    }

    public void setCircBrasDecontract(Long circBrasDecontract) {
        this.circBrasDecontract = circBrasDecontract;
    }

    public Long getCircCuisse() {
        return circCuisse;
    }

    public MesureAnthropo circCuisse(Long circCuisse) {
        this.circCuisse = circCuisse;
        return this;
    }

    public void setCircCuisse(Long circCuisse) {
        this.circCuisse = circCuisse;
    }

    public Swimer getSwimer() {
        return swimer;
    }

    public MesureAnthropo swimer(Swimer swimer) {
        this.swimer = swimer;
        return this;
    }

    public void setSwimer(Swimer swimer) {
        this.swimer = swimer;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MesureAnthropo)) {
            return false;
        }
        return id != null && id.equals(((MesureAnthropo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MesureAnthropo{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", poids=" + getPoids() +
            ", stature=" + getStature() +
            ", tailleAssis=" + getTailleAssis() +
            ", longTronc=" + getLongTronc() +
            ", longMembreInferieurs=" + getLongMembreInferieurs() +
            ", longMembreSuperieur=" + getLongMembreSuperieur() +
            ", anvergureBras=" + getAnvergureBras() +
            ", longBras=" + getLongBras() +
            ", longJambes=" + getLongJambes() +
            ", longPieds=" + getLongPieds() +
            ", hauteurPied=" + getHauteurPied() +
            ", longMain=" + getLongMain() +
            ", diamMain=" + getDiamMain() +
            ", diamBiacromial=" + getDiamBiacromial() +
            ", diamBicretal=" + getDiamBicretal() +
            ", diamThorax=" + getDiamThorax() +
            ", circThorax=" + getCircThorax() +
            ", circThoraxInspirant=" + getCircThoraxInspirant() +
            ", circThoraxExpirant=" + getCircThoraxExpirant() +
            ", circBrasContracte=" + getCircBrasContracte() +
            ", circBrasDecontract=" + getCircBrasDecontract() +
            ", circCuisse=" + getCircCuisse() +
            "}";
    }
}
