package com.popov.t04jh.repository;
import com.popov.t04jh.domain.FicheSeance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the FicheSeance entity.
 */
@Repository
public interface FicheSeanceRepository extends JpaRepository<FicheSeance, Long> {

    @Query(value = "select distinct ficheSeance from FicheSeance ficheSeance left join fetch ficheSeance.exercices",
        countQuery = "select count(distinct ficheSeance) from FicheSeance ficheSeance")
    Page<FicheSeance> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct ficheSeance from FicheSeance ficheSeance left join fetch ficheSeance.exercices")
    List<FicheSeance> findAllWithEagerRelationships();

    @Query("select ficheSeance from FicheSeance ficheSeance left join fetch ficheSeance.exercices where ficheSeance.id =:id")
    Optional<FicheSeance> findOneWithEagerRelationships(@Param("id") Long id);

}
