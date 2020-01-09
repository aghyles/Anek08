package com.popov.t04jh.repository;
import com.popov.t04jh.domain.Exercice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Exercice entity.
 */
@Repository
public interface ExerciceRepository extends JpaRepository<Exercice, Long> {

    @Query(value = "select distinct exercice from Exercice exercice left join fetch exercice.documents",
        countQuery = "select count(distinct exercice) from Exercice exercice")
    Page<Exercice> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct exercice from Exercice exercice left join fetch exercice.documents")
    List<Exercice> findAllWithEagerRelationships();

    @Query("select exercice from Exercice exercice left join fetch exercice.documents where exercice.id =:id")
    Optional<Exercice> findOneWithEagerRelationships(@Param("id") Long id);

}
