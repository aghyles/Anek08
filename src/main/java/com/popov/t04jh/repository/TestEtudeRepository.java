package com.popov.t04jh.repository;
import com.popov.t04jh.domain.TestEtude;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TestEtude entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TestEtudeRepository extends JpaRepository<TestEtude, Long> {

}
