package com.popov.t04jh.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TestPhysique.
 */
@Entity
@Table(name = "test_physique")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TestPhysique implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "testphy_title")
    private String testphyTitle;

    @Column(name = "test_phy_1")
    private String testPhy1;

    @Column(name = "test_phy_2")
    private String testPhy2;

    @Column(name = "test_phy_3")
    private String testPhy3;

    @Column(name = "test_phy_4")
    private String testPhy4;

    @Column(name = "test_phy_5")
    private String testPhy5;

    @ManyToOne
    @JsonIgnoreProperties("testPhysiques")
    private FicheTest ficheTest;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestphyTitle() {
        return testphyTitle;
    }

    public TestPhysique testphyTitle(String testphyTitle) {
        this.testphyTitle = testphyTitle;
        return this;
    }

    public void setTestphyTitle(String testphyTitle) {
        this.testphyTitle = testphyTitle;
    }

    public String getTestPhy1() {
        return testPhy1;
    }

    public TestPhysique testPhy1(String testPhy1) {
        this.testPhy1 = testPhy1;
        return this;
    }

    public void setTestPhy1(String testPhy1) {
        this.testPhy1 = testPhy1;
    }

    public String getTestPhy2() {
        return testPhy2;
    }

    public TestPhysique testPhy2(String testPhy2) {
        this.testPhy2 = testPhy2;
        return this;
    }

    public void setTestPhy2(String testPhy2) {
        this.testPhy2 = testPhy2;
    }

    public String getTestPhy3() {
        return testPhy3;
    }

    public TestPhysique testPhy3(String testPhy3) {
        this.testPhy3 = testPhy3;
        return this;
    }

    public void setTestPhy3(String testPhy3) {
        this.testPhy3 = testPhy3;
    }

    public String getTestPhy4() {
        return testPhy4;
    }

    public TestPhysique testPhy4(String testPhy4) {
        this.testPhy4 = testPhy4;
        return this;
    }

    public void setTestPhy4(String testPhy4) {
        this.testPhy4 = testPhy4;
    }

    public String getTestPhy5() {
        return testPhy5;
    }

    public TestPhysique testPhy5(String testPhy5) {
        this.testPhy5 = testPhy5;
        return this;
    }

    public void setTestPhy5(String testPhy5) {
        this.testPhy5 = testPhy5;
    }

    public FicheTest getFicheTest() {
        return ficheTest;
    }

    public TestPhysique ficheTest(FicheTest ficheTest) {
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
        if (!(o instanceof TestPhysique)) {
            return false;
        }
        return id != null && id.equals(((TestPhysique) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TestPhysique{" +
            "id=" + getId() +
            ", testphyTitle='" + getTestphyTitle() + "'" +
            ", testPhy1='" + getTestPhy1() + "'" +
            ", testPhy2='" + getTestPhy2() + "'" +
            ", testPhy3='" + getTestPhy3() + "'" +
            ", testPhy4='" + getTestPhy4() + "'" +
            ", testPhy5='" + getTestPhy5() + "'" +
            "}";
    }
}
