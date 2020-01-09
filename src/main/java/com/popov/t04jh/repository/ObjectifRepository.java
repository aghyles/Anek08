package com.popov.t04jh.repository;
import com.popov.t04jh.domain.Objectif;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Objectif entity.
 */
@Repository
public interface ObjectifRepository extends JpaRepository<Objectif, Long> {

    @Query(value = "select distinct objectif from Objectif objectif left join fetch objectif.exercices",
        countQuery = "select count(distinct objectif) from Objectif objectif")
    Page<Objectif> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct objectif from Objectif objectif left join fetch objectif.exercices")
    List<Objectif> findAllWithEagerRelationships();

    @Query("select objectif from Objectif objectif left join fetch objectif.exercices where objectif.id =:id")
    Optional<Objectif> findOneWithEagerRelationships(@Param("id") Long id);

}
