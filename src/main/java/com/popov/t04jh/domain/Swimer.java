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

import com.popov.t04jh.domain.enumeration.Sexe;

/**
 * A Swimer.
 */
@Entity
@Table(name = "swimer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Swimer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "licence_num")
    private String licenceNum;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexe")
    private Sexe sexe;

    @Column(name = "bearthday")
    private Instant bearthday;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Pattern(regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")
    @Column(name = "e_mail")
    private String eMail;

    @Column(name = "address")
    private String address;

    @Column(name = "study_time")
    private String studyTime;

    @Column(name = "first_swim")
    private Instant firstSwim;

    @Column(name = "groupe_name")
    private String groupeName;

    @Column(name = "document")
    private Long document;

    @OneToMany(mappedBy = "swimer")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MesureAnthropo> mesureAnthropos = new HashSet<>();

    @OneToMany(mappedBy = "swimer")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FicheTest> ficheTests = new HashSet<>();

    @OneToMany(mappedBy = "swimer")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("swimers")
    private Groupe groupe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicenceNum() {
        return licenceNum;
    }

    public Swimer licenceNum(String licenceNum) {
        this.licenceNum = licenceNum;
        return this;
    }

    public void setLicenceNum(String licenceNum) {
        this.licenceNum = licenceNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public Swimer firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Swimer lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public Swimer sexe(Sexe sexe) {
        this.sexe = sexe;
        return this;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public Instant getBearthday() {
        return bearthday;
    }

    public Swimer bearthday(Instant bearthday) {
        this.bearthday = bearthday;
        return this;
    }

    public void setBearthday(Instant bearthday) {
        this.bearthday = bearthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Swimer phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public Swimer eMail(String eMail) {
        this.eMail = eMail;
        return this;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getAddress() {
        return address;
    }

    public Swimer address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStudyTime() {
        return studyTime;
    }

    public Swimer studyTime(String studyTime) {
        this.studyTime = studyTime;
        return this;
    }

    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }

    public Instant getFirstSwim() {
        return firstSwim;
    }

    public Swimer firstSwim(Instant firstSwim) {
        this.firstSwim = firstSwim;
        return this;
    }

    public void setFirstSwim(Instant firstSwim) {
        this.firstSwim = firstSwim;
    }

    public String getGroupeName() {
        return groupeName;
    }

    public Swimer groupeName(String groupeName) {
        this.groupeName = groupeName;
        return this;
    }

    public void setGroupeName(String groupeName) {
        this.groupeName = groupeName;
    }

    public Long getDocument() {
        return document;
    }

    public Swimer document(Long document) {
        this.document = document;
        return this;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public Set<MesureAnthropo> getMesureAnthropos() {
        return mesureAnthropos;
    }

    public Swimer mesureAnthropos(Set<MesureAnthropo> mesureAnthropos) {
        this.mesureAnthropos = mesureAnthropos;
        return this;
    }

    public Swimer addMesureAnthropo(MesureAnthropo mesureAnthropo) {
        this.mesureAnthropos.add(mesureAnthropo);
        mesureAnthropo.setSwimer(this);
        return this;
    }

    public Swimer removeMesureAnthropo(MesureAnthropo mesureAnthropo) {
        this.mesureAnthropos.remove(mesureAnthropo);
        mesureAnthropo.setSwimer(null);
        return this;
    }

    public void setMesureAnthropos(Set<MesureAnthropo> mesureAnthropos) {
        this.mesureAnthropos = mesureAnthropos;
    }

    public Set<FicheTest> getFicheTests() {
        return ficheTests;
    }

    public Swimer ficheTests(Set<FicheTest> ficheTests) {
        this.ficheTests = ficheTests;
        return this;
    }

    public Swimer addFicheTest(FicheTest ficheTest) {
        this.ficheTests.add(ficheTest);
        ficheTest.setSwimer(this);
        return this;
    }

    public Swimer removeFicheTest(FicheTest ficheTest) {
        this.ficheTests.remove(ficheTest);
        ficheTest.setSwimer(null);
        return this;
    }

    public void setFicheTests(Set<FicheTest> ficheTests) {
        this.ficheTests = ficheTests;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public Swimer documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public Swimer addDocument(Document document) {
        this.documents.add(document);
        document.setSwimer(this);
        return this;
    }

    public Swimer removeDocument(Document document) {
        this.documents.remove(document);
        document.setSwimer(null);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public Swimer groupe(Groupe groupe) {
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
        if (!(o instanceof Swimer)) {
            return false;
        }
        return id != null && id.equals(((Swimer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Swimer{" +
            "id=" + getId() +
            ", licenceNum='" + getLicenceNum() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", bearthday='" + getBearthday() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", eMail='" + geteMail() + "'" +
            ", address='" + getAddress() + "'" +
            ", studyTime='" + getStudyTime() + "'" +
            ", firstSwim='" + getFirstSwim() + "'" +
            ", groupeName='" + getGroupeName() + "'" +
            ", document=" + getDocument() +
            "}";
    }
}
