package com.popov.t04jh.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TestAutre.
 */
@Entity
@Table(name = "test_autre")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TestAutre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "test_title")
    private String testTitle;

    @Column(name = "obs_1")
    private String obs1;

    @Column(name = "obs_2")
    private String obs2;

    @Column(name = "obs_3")
    private String obs3;

    @Column(name = "obs_4")
    private String obs4;

    @Column(name = "obs_5")
    private String obs5;

    @Column(name = "obs_6")
    private String obs6;

    @Column(name = "obs_7")
    private String obs7;

    @ManyToOne
    @JsonIgnoreProperties("testAutres")
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

    public TestAutre testTitle(String testTitle) {
        this.testTitle = testTitle;
        return this;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public String getObs1() {
        return obs1;
    }

    public TestAutre obs1(String obs1) {
        this.obs1 = obs1;
        return this;
    }

    public void setObs1(String obs1) {
        this.obs1 = obs1;
    }

    public String getObs2() {
        return obs2;
    }

    public TestAutre obs2(String obs2) {
        this.obs2 = obs2;
        return this;
    }

    public void setObs2(String obs2) {
        this.obs2 = obs2;
    }

    public String getObs3() {
        return obs3;
    }

    public TestAutre obs3(String obs3) {
        this.obs3 = obs3;
        return this;
    }

    public void setObs3(String obs3) {
        this.obs3 = obs3;
    }

    public String getObs4() {
        return obs4;
    }

    public TestAutre obs4(String obs4) {
        this.obs4 = obs4;
        return this;
    }

    public void setObs4(String obs4) {
        this.obs4 = obs4;
    }

    public String getObs5() {
        return obs5;
    }

    public TestAutre obs5(String obs5) {
        this.obs5 = obs5;
        return this;
    }

    public void setObs5(String obs5) {
        this.obs5 = obs5;
    }

    public String getObs6() {
        return obs6;
    }

    public TestAutre obs6(String obs6) {
        this.obs6 = obs6;
        return this;
    }

    public void setObs6(String obs6) {
        this.obs6 = obs6;
    }

    public String getObs7() {
        return obs7;
    }

    public TestAutre obs7(String obs7) {
        this.obs7 = obs7;
        return this;
    }

    public void setObs7(String obs7) {
        this.obs7 = obs7;
    }

    public FicheTest getFicheTest() {
        return ficheTest;
    }

    public TestAutre ficheTest(FicheTest ficheTest) {
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
        if (!(o instanceof TestAutre)) {
            return false;
        }
        return id != null && id.equals(((TestAutre) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TestAutre{" +
            "id=" + getId() +
            ", testTitle='" + getTestTitle() + "'" +
            ", obs1='" + getObs1() + "'" +
            ", obs2='" + getObs2() + "'" +
            ", obs3='" + getObs3() + "'" +
            ", obs4='" + getObs4() + "'" +
            ", obs5='" + getObs5() + "'" +
            ", obs6='" + getObs6() + "'" +
            ", obs7='" + getObs7() + "'" +
            "}";
    }
}
