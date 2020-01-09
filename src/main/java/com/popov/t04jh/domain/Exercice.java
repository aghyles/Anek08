package com.popov.t04jh.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Exercice.
 */
@Entity
@Table(name = "exercice")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Exercice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "exercice_name", nullable = false)
    private String exerciceName;

    @Column(name = "recomendation")
    private String recomendation;

    @Column(name = "doc_schema")
    private Long docSchema;

    @ManyToOne
    @JsonIgnoreProperties("exercices")
    private Dosage dosage;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "exercice_document",
               joinColumns = @JoinColumn(name = "exercice_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "document_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToMany(mappedBy = "exercices")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<FicheSeance> ficheSeances = new HashSet<>();

    @ManyToMany(mappedBy = "exercices")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Objectif> objectifs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExerciceName() {
        return exerciceName;
    }

    public Exercice exerciceName(String exerciceName) {
        this.exerciceName = exerciceName;
        return this;
    }

    public void setExerciceName(String exerciceName) {
        this.exerciceName = exerciceName;
    }

    public String getRecomendation() {
        return recomendation;
    }

    public Exercice recomendation(String recomendation) {
        this.recomendation = recomendation;
        return this;
    }

    public void setRecomendation(String recomendation) {
        this.recomendation = recomendation;
    }

    public Long getDocSchema() {
        return docSchema;
    }

    public Exercice docSchema(Long docSchema) {
        this.docSchema = docSchema;
        return this;
    }

    public void setDocSchema(Long docSchema) {
        this.docSchema = docSchema;
    }

    public Dosage getDosage() {
        return dosage;
    }

    public Exercice dosage(Dosage dosage) {
        this.dosage = dosage;
        return this;
    }

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public Exercice documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public Exercice addDocument(Document document) {
        this.documents.add(document);
        document.getExercices().add(this);
        return this;
    }

    public Exercice removeDocument(Document document) {
        this.documents.remove(document);
        document.getExercices().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<FicheSeance> getFicheSeances() {
        return ficheSeances;
    }

    public Exercice ficheSeances(Set<FicheSeance> ficheSeances) {
        this.ficheSeances = ficheSeances;
        return this;
    }

    public Exercice addFicheSeance(FicheSeance ficheSeance) {
        this.ficheSeances.add(ficheSeance);
        ficheSeance.getExercices().add(this);
        return this;
    }

    public Exercice removeFicheSeance(FicheSeance ficheSeance) {
        this.ficheSeances.remove(ficheSeance);
        ficheSeance.getExercices().remove(this);
        return this;
    }

    public void setFicheSeances(Set<FicheSeance> ficheSeances) {
        this.ficheSeances = ficheSeances;
    }

    public Set<Objectif> getObjectifs() {
        return objectifs;
    }

    public Exercice objectifs(Set<Objectif> objectifs) {
        this.objectifs = objectifs;
        return this;
    }

    public Exercice addObjectif(Objectif objectif) {
        this.objectifs.add(objectif);
        objectif.getExercices().add(this);
        return this;
    }

    public Exercice removeObjectif(Objectif objectif) {
        this.objectifs.remove(objectif);
        objectif.getExercices().remove(this);
        return this;
    }

    public void setObjectifs(Set<Objectif> objectifs) {
        this.objectifs = objectifs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Exercice)) {
            return false;
        }
        return id != null && id.equals(((Exercice) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Exercice{" +
            "id=" + getId() +
            ", exerciceName='" + getExerciceName() + "'" +
            ", recomendation='" + getRecomendation() + "'" +
            ", docSchema=" + getDocSchema() +
            "}";
    }
}
