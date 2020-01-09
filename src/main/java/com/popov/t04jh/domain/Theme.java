package com.popov.t04jh.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Theme.
 */
@Entity
@Table(name = "theme")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Theme implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "theme_name", nullable = false)
    private String themeName;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "theme_objectif",
               joinColumns = @JoinColumn(name = "theme_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "objectif_id", referencedColumnName = "id"))
    private Set<Objectif> objectifs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThemeName() {
        return themeName;
    }

    public Theme themeName(String themeName) {
        this.themeName = themeName;
        return this;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getDescription() {
        return description;
    }

    public Theme description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Objectif> getObjectifs() {
        return objectifs;
    }

    public Theme objectifs(Set<Objectif> objectifs) {
        this.objectifs = objectifs;
        return this;
    }

    public Theme addObjectif(Objectif objectif) {
        this.objectifs.add(objectif);
        objectif.getThemes().add(this);
        return this;
    }

    public Theme removeObjectif(Objectif objectif) {
        this.objectifs.remove(objectif);
        objectif.getThemes().remove(this);
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
        if (!(o instanceof Theme)) {
            return false;
        }
        return id != null && id.equals(((Theme) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Theme{" +
            "id=" + getId() +
            ", themeName='" + getThemeName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
