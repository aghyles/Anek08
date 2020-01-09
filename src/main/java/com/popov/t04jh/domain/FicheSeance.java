package com.popov.t04jh.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A FicheSeance.
 */
@Entity
@Table(name = "fiche_seance")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FicheSeance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "fiche_s_num", nullable = false)
    private Long ficheSNum;

    @NotNull
    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "observation")
    private String observation;

    @Column(name = "volume")
    private Long volume;

    @Column(name = "bilan")
    private String bilan;

    @OneToMany(mappedBy = "ficheSeance")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Presence> presences = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "fiche_seance_exercice",
               joinColumns = @JoinColumn(name = "fiche_seance_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "exercice_id", referencedColumnName = "id"))
    private Set<Exercice> exercices = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("ficheSeances")
    private Groupe groupe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFicheSNum() {
        return ficheSNum;
    }

    public FicheSeance ficheSNum(Long ficheSNum) {
        this.ficheSNum = ficheSNum;
        return this;
    }

    public void setFicheSNum(Long ficheSNum) {
        this.ficheSNum = ficheSNum;
    }

    public Instant getDate() {
        return date;
    }

    public FicheSeance date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getObservation() {
        return observation;
    }

    public FicheSeance observation(String observation) {
        this.observation = observation;
        return this;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Long getVolume() {
        return volume;
    }

    public FicheSeance volume(Long volume) {
        this.volume = volume;
        return this;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public String getBilan() {
        return bilan;
    }

    public FicheSeance bilan(String bilan) {
        this.bilan = bilan;
        return this;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    public Set<Presence> getPresences() {
        return presences;
    }

    public FicheSeance presences(Set<Presence> presences) {
        this.presences = presences;
        return this;
    }

    public FicheSeance addPresence(Presence presence) {
        this.presences.add(presence);
        presence.setFicheSeance(this);
        return this;
    }

    public FicheSeance removePresence(Presence presence) {
        this.presences.remove(presence);
        presence.setFicheSeance(null);
        return this;
    }

    public void setPresences(Set<Presence> presences) {
        this.presences = presences;
    }

    public Set<Exercice> getExercices() {
        return exercices;
    }

    public FicheSeance exercices(Set<Exercice> exercices) {
        this.exercices = exercices;
        return this;
    }

    public FicheSeance addExercice(Exercice exercice) {
        this.exercices.add(exercice);
        exercice.getFicheSeances().add(this);
        return this;
    }

    public FicheSeance removeExercice(Exercice exercice) {
        this.exercices.remove(exercice);
        exercice.getFicheSeances().remove(this);
        return this;
    }

    public void setExercices(Set<Exercice> exercices) {
        this.exercices = exercices;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public FicheSeance groupe(Groupe groupe) {
        this.groupe = groupe;
        return this;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FicheSeance)) {
            return false;
        }
        return id != null && id.equals(((FicheSeance) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FicheSeance{" +
            "id=" + getId() +
            ", ficheSNum=" + getFicheSNum() +
            ", date='" + getDate() + "'" +
            ", observation='" + getObservation() + "'" +
            ", volume=" + getVolume() +
            ", bilan='" + getBilan() + "'" +
            "}";
    }
}
