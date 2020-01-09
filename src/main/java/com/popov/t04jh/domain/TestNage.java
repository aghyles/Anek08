package com.popov.t04jh.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TestNage.
 */
@Entity
@Table(name = "test_nage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TestNage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "test_title")
    private String testTitle;

    @Column(name = "nl_50")
    private Long nl50;

    @Column(name = "nl_100")
    private Long nl100;

    @Column(name = "nl_200")
    private Long nl200;

    @Column(name = "nl_400")
    private Long nl400;

    @Column(name = "nl_800")
    private Long nl800;

    @Column(name = "nl_1500")
    private Long nl1500;

    @Column(name = "pap_50")
    private Long pap50;

    @Column(name = "pap_100")
    private Long pap100;

    @Column(name = "pap_200")
    private Long pap200;

    @Column(name = "dos_50")
    private Long dos50;

    @Column(name = "dos_100")
    private Long dos100;

    @Column(name = "dos_200")
    private Long dos200;

    @Column(name = "brasse_50")
    private Long brasse50;

    @Column(name = "brasse_100")
    private Long brasse100;

    @Column(name = "brasse_200")
    private Long brasse200;

    @Column(name = "na_4_ge_100")
    private Long na4ge100;

    @Column(name = "na_4_ge_200")
    private Long na4ge200;

    @Column(name = "na_4_ge_400")
    private Long na4ge400;

    @Column(name = "h_1_nl")
    private Long h1nl;

    @Column(name = "autre")
    private String autre;

    @ManyToOne
    @JsonIgnoreProperties("testNages")
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

    public TestNage testTitle(String testTitle) {
        this.testTitle = testTitle;
        return this;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public Long getNl50() {
        return nl50;
    }

    public TestNage nl50(Long nl50) {
        this.nl50 = nl50;
        return this;
    }

    public void setNl50(Long nl50) {
        this.nl50 = nl50;
    }

    public Long getNl100() {
        return nl100;
    }

    public TestNage nl100(Long nl100) {
        this.nl100 = nl100;
        return this;
    }

    public void setNl100(Long nl100) {
        this.nl100 = nl100;
    }

    public Long getNl200() {
        return nl200;
    }

    public TestNage nl200(Long nl200) {
        this.nl200 = nl200;
        return this;
    }

    public void setNl200(Long nl200) {
        this.nl200 = nl200;
    }

    public Long getNl400() {
        return nl400;
    }

    public TestNage nl400(Long nl400) {
        this.nl400 = nl400;
        return this;
    }

    public void setNl400(Long nl400) {
        this.nl400 = nl400;
    }

    public Long getNl800() {
        return nl800;
    }

    public TestNage nl800(Long nl800) {
        this.nl800 = nl800;
        return this;
    }

    public void setNl800(Long nl800) {
        this.nl800 = nl800;
    }

    public Long getNl1500() {
        return nl1500;
    }

    public TestNage nl1500(Long nl1500) {
        this.nl1500 = nl1500;
        return this;
    }

    public void setNl1500(Long nl1500) {
        this.nl1500 = nl1500;
    }

    public Long getPap50() {
        return pap50;
    }

    public TestNage pap50(Long pap50) {
        this.pap50 = pap50;
        return this;
    }

    public void setPap50(Long pap50) {
        this.pap50 = pap50;
    }

    public Long getPap100() {
        return pap100;
    }

    public TestNage pap100(Long pap100) {
        this.pap100 = pap100;
        return this;
    }

    public void setPap100(Long pap100) {
        this.pap100 = pap100;
    }

    public Long getPap200() {
        return pap200;
    }

    public TestNage pap200(Long pap200) {
        this.pap200 = pap200;
        return this;
    }

    public void setPap200(Long pap200) {
        this.pap200 = pap200;
    }

    public Long getDos50() {
        return dos50;
    }

    public TestNage dos50(Long dos50) {
        this.dos50 = dos50;
        return this;
    }

    public void setDos50(Long dos50) {
        this.dos50 = dos50;
    }

    public Long getDos100() {
        return dos100;
    }

    public TestNage dos100(Long dos100) {
        this.dos100 = dos100;
        return this;
    }

    public void setDos100(Long dos100) {
        this.dos100 = dos100;
    }

    public Long getDos200() {
        return dos200;
    }

    public TestNage dos200(Long dos200) {
        this.dos200 = dos200;
        return this;
    }

    public void setDos200(Long dos200) {
        this.dos200 = dos200;
    }

    public Long getBrasse50() {
        return brasse50;
    }

    public TestNage brasse50(Long brasse50) {
        this.brasse50 = brasse50;
        return this;
    }

    public void setBrasse50(Long brasse50) {
        this.brasse50 = brasse50;
    }

    public Long getBrasse100() {
        return brasse100;
    }

    public TestNage brasse100(Long brasse100) {
        this.brasse100 = brasse100;
        return this;
    }

    public void setBrasse100(Long brasse100) {
        this.brasse100 = brasse100;
    }

    public Long getBrasse200() {
        return brasse200;
    }

    public TestNage brasse200(Long brasse200) {
        this.brasse200 = brasse200;
        return this;
    }

    public void setBrasse200(Long brasse200) {
        this.brasse200 = brasse200;
    }

    public Long getNa4ge100() {
        return na4ge100;
    }

    public TestNage na4ge100(Long na4ge100) {
        this.na4ge100 = na4ge100;
        return this;
    }

    public void setNa4ge100(Long na4ge100) {
        this.na4ge100 = na4ge100;
    }

    public Long getNa4ge200() {
        return na4ge200;
    }

    public TestNage na4ge200(Long na4ge200) {
        this.na4ge200 = na4ge200;
        return this;
    }

    public void setNa4ge200(Long na4ge200) {
        this.na4ge200 = na4ge200;
    }

    public Long getNa4ge400() {
        return na4ge400;
    }

    public TestNage na4ge400(Long na4ge400) {
        this.na4ge400 = na4ge400;
        return this;
    }

    public void setNa4ge400(Long na4ge400) {
        this.na4ge400 = na4ge400;
    }

    public Long geth1nl() {
        return h1nl;
    }

    public TestNage h1nl(Long h1nl) {
        this.h1nl = h1nl;
        return this;
    }

    public void seth1nl(Long h1nl) {
        this.h1nl = h1nl;
    }

    public String getAutre() {
        return autre;
    }

    public TestNage autre(String autre) {
        this.autre = autre;
        return this;
    }

    public void setAutre(String autre) {
        this.autre = autre;
    }

    public FicheTest getFicheTest() {
        return ficheTest;
    }

    public TestNage ficheTest(FicheTest ficheTest) {
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
        if (!(o instanceof TestNage)) {
            return false;
        }
        return id != null && id.equals(((TestNage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TestNage{" +
            "id=" + getId() +
            ", testTitle='" + getTestTitle() + "'" +
            ", nl50=" + getNl50() +
            ", nl100=" + getNl100() +
            ", nl200=" + getNl200() +
            ", nl400=" + getNl400() +
            ", nl800=" + getNl800() +
            ", nl1500=" + getNl1500() +
            ", pap50=" + getPap50() +
            ", pap100=" + getPap100() +
            ", pap200=" + getPap200() +
            ", dos50=" + getDos50() +
            ", dos100=" + getDos100() +
            ", dos200=" + getDos200() +
            ", brasse50=" + getBrasse50() +
            ", brasse100=" + getBrasse100() +
            ", brasse200=" + getBrasse200() +
            ", na4ge100=" + getNa4ge100() +
            ", na4ge200=" + getNa4ge200() +
            ", na4ge400=" + getNa4ge400() +
            ", h1nl=" + geth1nl() +
            ", autre='" + getAutre() + "'" +
            "}";
    }
}
