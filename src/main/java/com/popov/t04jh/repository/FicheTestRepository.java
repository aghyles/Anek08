package com.popov.t04jh.repository;
import com.popov.t04jh.domain.FicheTest;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FicheTest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FicheTestRepository extends JpaRepository<FicheTest, Long> {

}
