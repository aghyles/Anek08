package com.popov.t04jh.repository;
import com.popov.t04jh.domain.MesureAnthropo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MesureAnthropo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MesureAnthropoRepository extends JpaRepository<MesureAnthropo, Long> {

}
