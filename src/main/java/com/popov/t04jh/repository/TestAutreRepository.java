package com.popov.t04jh.repository;
import com.popov.t04jh.domain.TestAutre;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TestAutre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TestAutreRepository extends JpaRepository<TestAutre, Long> {

}
