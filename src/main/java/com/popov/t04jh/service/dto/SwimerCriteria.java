package com.popov.t04jh.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import com.popov.t04jh.domain.enumeration.Sexe;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the {@link com.popov.t04jh.domain.Swimer} entity. This class is used
 * in {@link com.popov.t04jh.web.rest.SwimerResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /swimers?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SwimerCriteria implements Serializable, Criteria {
    /**
     * Class for filtering Sexe
     */
    public static class SexeFilter extends Filter<Sexe> {

        public SexeFilter() {
        }

        public SexeFilter(SexeFilter filter) {
            super(filter);
        }

        @Override
        public SexeFilter copy() {
            return new SexeFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter licenceNum;

    private StringFilter firstName;

    private StringFilter lastName;

    private SexeFilter sexe;

    private InstantFilter bearthday;

    private StringFilter phoneNumber;

    private StringFilter eMail;

    private StringFilter address;

    private StringFilter studyTime;

    private InstantFilter firstSwim;

    private StringFilter groupeName;

    private LongFilter document;

    private LongFilter mesureAnthropoId;

    private LongFilter ficheTestId;

    private LongFilter documentId;

    private LongFilter groupeId;

    public SwimerCriteria(){
    }

    public SwimerCriteria(SwimerCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.licenceNum = other.licenceNum == null ? null : other.licenceNum.copy();
        this.firstName = other.firstName == null ? null : other.firstName.copy();
        this.lastName = other.lastName == null ? null : other.lastName.copy();
        this.sexe = other.sexe == null ? null : other.sexe.copy();
        this.bearthday = other.bearthday == null ? null : other.bearthday.copy();
        this.phoneNumber = other.phoneNumber == null ? null : other.phoneNumber.copy();
        this.eMail = other.eMail == null ? null : other.eMail.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.studyTime = other.studyTime == null ? null : other.studyTime.copy();
        this.firstSwim = other.firstSwim == null ? null : other.firstSwim.copy();
        this.groupeName = other.groupeName == null ? null : other.groupeName.copy();
        this.document = other.document == null ? null : other.document.copy();
        this.mesureAnthropoId = other.mesureAnthropoId == null ? null : other.mesureAnthropoId.copy();
        this.ficheTestId = other.ficheTestId == null ? null : other.ficheTestId.copy();
        this.documentId = other.documentId == null ? null : other.documentId.copy();
        this.groupeId = other.groupeId == null ? null : other.groupeId.copy();
    }

    @Override
    public SwimerCriteria copy() {
        return new SwimerCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getLicenceNum() {
        return licenceNum;
    }

    public void setLicenceNum(StringFilter licenceNum) {
        this.licenceNum = licenceNum;
    }

    public StringFilter getFirstName() {
        return firstName;
    }

    public void setFirstName(StringFilter firstName) {
        this.firstName = firstName;
    }

    public StringFilter getLastName() {
        return lastName;
    }

    public void setLastName(StringFilter lastName) {
        this.lastName = lastName;
    }

    public SexeFilter getSexe() {
        return sexe;
    }

    public void setSexe(SexeFilter sexe) {
        this.sexe = sexe;
    }

    public InstantFilter getBearthday() {
        return bearthday;
    }

    public void setBearthday(InstantFilter bearthday) {
        this.bearthday = bearthday;
    }

    public StringFilter getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(StringFilter phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public StringFilter geteMail() {
        return eMail;
    }

    public void seteMail(StringFilter eMail) {
        this.eMail = eMail;
    }

    public StringFilter getAddress() {
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public StringFilter getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(StringFilter studyTime) {
        this.studyTime = studyTime;
    }

    public InstantFilter getFirstSwim() {
        return firstSwim;
    }

    public void setFirstSwim(InstantFilter firstSwim) {
        this.firstSwim = firstSwim;
    }

    public StringFilter getGroupeName() {
        return groupeName;
    }

    public void setGroupeName(StringFilter groupeName) {
        this.groupeName = groupeName;
    }

    public LongFilter getDocument() {
        return document;
    }

    public void setDocument(LongFilter document) {
        this.document = document;
    }

    public LongFilter getMesureAnthropoId() {
        return mesureAnthropoId;
    }

    public void setMesureAnthropoId(LongFilter mesureAnthropoId) {
        this.mesureAnthropoId = mesureAnthropoId;
    }

    public LongFilter getFicheTestId() {
        return ficheTestId;
    }

    public void setFicheTestId(LongFilter ficheTestId) {
        this.ficheTestId = ficheTestId;
    }

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getGroupeId() {
        return groupeId;
    }

    public void setGroupeId(LongFilter groupeId) {
        this.groupeId = groupeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SwimerCriteria that = (SwimerCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(licenceNum, that.licenceNum) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(sexe, that.sexe) &&
            Objects.equals(bearthday, that.bearthday) &&
            Objects.equals(phoneNumber, that.phoneNumber) &&
            Objects.equals(eMail, that.eMail) &&
            Objects.equals(address, that.address) &&
            Objects.equals(studyTime, that.studyTime) &&
            Objects.equals(firstSwim, that.firstSwim) &&
            Objects.equals(groupeName, that.groupeName) &&
            Objects.equals(document, that.document) &&
            Objects.equals(mesureAnthropoId, that.mesureAnthropoId) &&
            Objects.equals(ficheTestId, that.ficheTestId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(groupeId, that.groupeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        licenceNum,
        firstName,
        lastName,
        sexe,
        bearthday,
        phoneNumber,
        eMail,
        address,
        studyTime,
        firstSwim,
        groupeName,
        document,
        mesureAnthropoId,
        ficheTestId,
        documentId,
        groupeId
        );
    }

    @Override
    public String toString() {
        return "SwimerCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (licenceNum != null ? "licenceNum=" + licenceNum + ", " : "") +
                (firstName != null ? "firstName=" + firstName + ", " : "") +
                (lastName != null ? "lastName=" + lastName + ", " : "") +
                (sexe != null ? "sexe=" + sexe + ", " : "") +
                (bearthday != null ? "bearthday=" + bearthday + ", " : "") +
                (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", " : "") +
                (eMail != null ? "eMail=" + eMail + ", " : "") +
                (address != null ? "address=" + address + ", " : "") +
                (studyTime != null ? "studyTime=" + studyTime + ", " : "") +
                (firstSwim != null ? "firstSwim=" + firstSwim + ", " : "") +
                (groupeName != null ? "groupeName=" + groupeName + ", " : "") +
                (document != null ? "document=" + document + ", " : "") +
                (mesureAnthropoId != null ? "mesureAnthropoId=" + mesureAnthropoId + ", " : "") +
                (ficheTestId != null ? "ficheTestId=" + ficheTestId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (groupeId != null ? "groupeId=" + groupeId + ", " : "") +
            "}";
    }

}
