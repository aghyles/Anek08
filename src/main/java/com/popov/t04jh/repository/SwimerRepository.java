package com.popov.t04jh.repository;
import com.popov.t04jh.domain.Swimer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Swimer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SwimerRepository extends JpaRepository<Swimer, Long>, JpaSpecificationExecutor<Swimer> {

}
