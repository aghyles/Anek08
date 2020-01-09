package com.popov.t04jh.repository;
import com.popov.t04jh.domain.TestPhysique;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TestPhysique entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TestPhysiqueRepository extends JpaRepository<TestPhysique, Long> {

}
