package com.popov.t04jh.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TestEtude.
 */
@Entity
@Table(name = "test_etude")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TestEtude implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "test_title")
    private String testTitle;

    @Column(name = "level_study")
    private String levelStudy;

    @Column(name = "average")
    private Long average;

    @ManyToOne
    @JsonIgnoreProperties("testEtudes")
    private FicheTest ficheTest;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public TestEtude testTitle(String testTitle) {
        this.testTitle = testTitle;
        return this;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public String getLevelStudy() {
        return levelStudy;
    }

    public TestEtude levelStudy(String levelStudy) {
        this.levelStudy = levelStudy;
        return this;
    }

    public void setLevelStudy(String levelStudy) {
        this.levelStudy = levelStudy;
    }

    public Long getAverage() {
        return average;
    }

    public TestEtude average(Long average) {
        this.average = average;
        return this;
    }

    public void setAverage(Long average) {
        this.average = average;
    }

    public FicheTest getFicheTest() {
        return ficheTest;
    }

    public TestEtude ficheTest(FicheTest ficheTest) {
        this.ficheTest = ficheTest;
        return this;
    }

    public void setFicheTest(FicheTest ficheTest) {
        this.ficheTest = ficheTest;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TestEtude)) {
            return false;
        }
        return id != null && id.equals(((TestEtude) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TestEtude{" +
            "id=" + getId() +
            ", testTitle='" + getTestTitle() + "'" +
            ", levelStudy='" + getLevelStudy() + "'" +
            ", average=" + getAverage() +
            "}";
    }
}
