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
 * A FicheTest.
 */
@Entity
@Table(name = "fiche_test")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FicheTest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "type_test", nullable = false)
    private String typeTest;

    @NotNull
    @Column(name = "datetest", nullable = false)
    private Instant datetest;

    @OneToMany(mappedBy = "ficheTest")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TestAutre> testAutres = new HashSet<>();

    @OneToMany(mappedBy = "ficheTest")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TestEtude> testEtudes = new HashSet<>();

    @OneToMany(mappedBy = "ficheTest")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TestNage> testNages = new HashSet<>();

    @OneToMany(mappedBy = "ficheTest")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Document> documents = new HashSet<>();

    @OneToMany(mappedBy = "ficheTest")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TestPhysique> testPhysiques = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("ficheTest")
    private Swimer swimer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeTest() {
        return typeTest;
    }

    public FicheTest typeTest(String typeTest) {
        this.typeTest = typeTest;
        return this;
    }

    public void setTypeTest(String typeTest) {
        this.typeTest = typeTest;
    }

    public Instant getDatetest() {
        return datetest;
    }

    public FicheTest datetest(Instant datetest) {
        this.datetest = datetest;
        return this;
    }

    public void setDatetest(Instant datetest) {
        this.datetest = datetest;
    }

    public Set<TestAutre> getTestAutres() {
        return testAutres;
    }

    public FicheTest testAutres(Set<TestAutre> testAutres) {
        this.testAutres = testAutres;
        return this;
    }

    public FicheTest addTestAutre(TestAutre testAutre) {
        this.testAutres.add(testAutre);
        testAutre.setFicheTest(this);
        return this;
    }

    public FicheTest removeTestAutre(TestAutre testAutre) {
        this.testAutres.remove(testAutre);
        testAutre.setFicheTest(null);
        return this;
    }

    public void setTestAutres(Set<TestAutre> testAutres) {
        this.testAutres = testAutres;
    }

    public Set<TestEtude> getTestEtudes() {
        return testEtudes;
    }

    public FicheTest testEtudes(Set<TestEtude> testEtudes) {
        this.testEtudes = testEtudes;
        return this;
    }

    public FicheTest addTestEtude(TestEtude testEtude) {
        this.testEtudes.add(testEtude);
        testEtude.setFicheTest(this);
        return this;
    }

    public FicheTest removeTestEtude(TestEtude testEtude) {
        this.testEtudes.remove(testEtude);
        testEtude.setFicheTest(null);
        return this;
    }

    public void setTestEtudes(Set<TestEtude> testEtudes) {
        this.testEtudes = testEtudes;
    }

    public Set<TestNage> getTestNages() {
        return testNages;
    }

    public FicheTest testNages(Set<TestNage> testNages) {
        this.testNages = testNages;
        return this;
    }

    public FicheTest addTestNage(TestNage testNage) {
        this.testNages.add(testNage);
        testNage.setFicheTest(this);
        return this;
    }

    public FicheTest removeTestNage(TestNage testNage) {
        this.testNages.remove(testNage);
        testNage.setFicheTest(null);
        return this;
    }

    public void setTestNages(Set<TestNage> testNages) {
        this.testNages = testNages;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public FicheTest documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public FicheTest addDocument(Document document) {
        this.documents.add(document);
        document.setFicheTest(this);
        return this;
    }

    public FicheTest removeDocument(Document document) {
        this.documents.remove(document);
        document.setFicheTest(null);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<TestPhysique> getTestPhysiques() {
        return testPhysiques;
    }

    public FicheTest testPhysiques(Set<TestPhysique> testPhysiques) {
        this.testPhysiques = testPhysiques;
        return this;
    }

    public FicheTest addTestPhysique(TestPhysique testPhysique) {
        this.testPhysiques.add(testPhysique);
        testPhysique.setFicheTest(this);
        return this;
    }

    public FicheTest removeTestPhysique(TestPhysique testPhysique) {
        this.testPhysiques.remove(testPhysique);
        testPhysique.setFicheTest(null);
        return this;
    }

    public void setTestPhysiques(Set<TestPhysique> testPhysiques) {
        this.testPhysiques = testPhysiques;
    }

    public Swimer getSwimer() {
        return swimer;
    }

    public FicheTest swimer(Swimer swimer) {
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
        if (!(o instanceof FicheTest)) {
            return false;
        }
        return id != null && id.equals(((FicheTest) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FicheTest{" +
            "id=" + getId() +
            ", typeTest='" + getTypeTest() + "'" +
            ", datetest='" + getDatetest() + "'" +
            "}";
    }
}
