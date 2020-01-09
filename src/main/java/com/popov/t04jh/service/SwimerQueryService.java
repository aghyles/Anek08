package com.popov.t04jh.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.popov.t04jh.domain.Swimer;
import com.popov.t04jh.domain.*; // for static metamodels
import com.popov.t04jh.repository.SwimerRepository;
import com.popov.t04jh.service.dto.SwimerCriteria;

/**
 * Service for executing complex queries for {@link Swimer} entities in the database.
 * The main input is a {@link SwimerCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Swimer} or a {@link Page} of {@link Swimer} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SwimerQueryService extends QueryService<Swimer> {

    private final Logger log = LoggerFactory.getLogger(SwimerQueryService.class);

    private final SwimerRepository swimerRepository;

    public SwimerQueryService(SwimerRepository swimerRepository) {
        this.swimerRepository = swimerRepository;
    }

    /**
     * Return a {@link List} of {@link Swimer} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Swimer> findByCriteria(SwimerCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Swimer> specification = createSpecification(criteria);
        return swimerRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Swimer} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Swimer> findByCriteria(SwimerCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Swimer> specification = createSpecification(criteria);
        return swimerRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SwimerCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Swimer> specification = createSpecification(criteria);
        return swimerRepository.count(specification);
    }

    /**
     * Function to convert {@link SwimerCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Swimer> createSpecification(SwimerCriteria criteria) {
        Specification<Swimer> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Swimer_.id));
            }
            if (criteria.getLicenceNum() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLicenceNum(), Swimer_.licenceNum));
            }
            if (criteria.getFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstName(), Swimer_.firstName));
            }
            if (criteria.getLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastName(), Swimer_.lastName));
            }
            if (criteria.getSexe() != null) {
                specification = specification.and(buildSpecification(criteria.getSexe(), Swimer_.sexe));
            }
            if (criteria.getBearthday() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBearthday(), Swimer_.bearthday));
            }
            if (criteria.getPhoneNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhoneNumber(), Swimer_.phoneNumber));
            }
            if (criteria.geteMail() != null) {
                specification = specification.and(buildStringSpecification(criteria.geteMail(), Swimer_.eMail));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), Swimer_.address));
            }
            if (criteria.getStudyTime() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStudyTime(), Swimer_.studyTime));
            }
            if (criteria.getFirstSwim() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstSwim(), Swimer_.firstSwim));
            }
            if (criteria.getGroupeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGroupeName(), Swimer_.groupeName));
            }
            if (criteria.getDocument() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDocument(), Swimer_.document));
            }
            if (criteria.getMesureAnthropoId() != null) {
                specification = specification.and(buildSpecification(criteria.getMesureAnthropoId(),
                    root -> root.join(Swimer_.mesureAnthropos, JoinType.LEFT).get(MesureAnthropo_.id)));
            }
            if (criteria.getFicheTestId() != null) {
                specification = specification.and(buildSpecification(criteria.getFicheTestId(),
                    root -> root.join(Swimer_.ficheTests, JoinType.LEFT).get(FicheTest_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(Swimer_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getGroupeId() != null) {
                specification = specification.and(buildSpecification(criteria.getGroupeId(),
                    root -> root.join(Swimer_.groupe, JoinType.LEFT).get(Groupe_.id)));
            }
        }
        return specification;
    }
}
