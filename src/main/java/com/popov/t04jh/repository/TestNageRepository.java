package com.popov.t04jh.repository;
import com.popov.t04jh.domain.TestNage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TestNage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TestNageRepository extends JpaRepository<TestNage, Long> {

}
