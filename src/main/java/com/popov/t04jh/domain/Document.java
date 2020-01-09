package com.popov.t04jh.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.popov.t04jh.domain.enumeration.TypeDocument;

/**
 * A Document.
 */
@Entity
@Table(name = "document")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "doc_title", nullable = false)
    private String docTitle;

    @Column(name = "date_doc")
    private Instant dateDoc;

    @Column(name = "link_doc")
    private String linkDoc;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_doc", nullable = false)
    private TypeDocument typeDoc;

    @ManyToOne
    @JsonIgnoreProperties("documents")
    private Swimer swimer;

    @ManyToOne
    @JsonIgnoreProperties("documents")
    private FicheTest ficheTest;

    @ManyToMany(mappedBy = "documents")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Exercice> exercices = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public Document docTitle(String docTitle) {
        this.docTitle = docTitle;
        return this;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public Instant getDateDoc() {
        return dateDoc;
    }

    public Document dateDoc(Instant dateDoc) {
        this.dateDoc = dateDoc;
        return this;
    }

    public void setDateDoc(Instant dateDoc) {
        this.dateDoc = dateDoc;
    }

    public String getLinkDoc() {
        return linkDoc;
    }

    public Document linkDoc(String linkDoc) {
        this.linkDoc = linkDoc;
        return this;
    }

    public void setLinkDoc(String linkDoc) {
        this.linkDoc = linkDoc;
    }

    public TypeDocument getTypeDoc() {
        return typeDoc;
    }

    public Document typeDoc(TypeDocument typeDoc) {
        this.typeDoc = typeDoc;
        return this;
    }

    public void setTypeDoc(TypeDocument typeDoc) {
        this.typeDoc = typeDoc;
    }

    public Swimer getSwimer() {
        return swimer;
    }

    public Document swimer(Swimer swimer) {
        this.swimer = swimer;
        return this;
    }

    public void setSwimer(Swimer swimer) {
        this.swimer = swimer;
    }

    public FicheTest getFicheTest() {
        return ficheTest;
    }

    public Document ficheTest(FicheTest ficheTest) {
        this.ficheTest = ficheTest;
        return this;
    }

    public void setFicheTest(FicheTest ficheTest) {
        this.ficheTest = ficheTest;
    }

    public Set<Exercice> getExercices() {
        return exercices;
    }

    public Document exercices(Set<Exercice> exercices) {
        this.exercices = exercices;
        return this;
    }

    public Document addExercice(Exercice exercice) {
        this.exercices.add(exercice);
        exercice.getDocuments().add(this);
        return this;
    }

    public Document removeExercice(Exercice exercice) {
        this.exercices.remove(exercice);
        exercice.getDocuments().remove(this);
        return this;
    }

    public void setExercices(Set<Exercice> exercices) {
        this.exercices = exercices;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Document)) {
            return false;
        }
        return id != null && id.equals(((Document) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Document{" +
            "id=" + getId() +
            ", docTitle='" + getDocTitle() + "'" +
            ", dateDoc='" + getDateDoc() + "'" +
            ", linkDoc='" + getLinkDoc() + "'" +
            ", typeDoc='" + getTypeDoc() + "'" +
            "}";
    }
}
