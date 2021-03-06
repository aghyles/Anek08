package com.popov.t04jh.repository;
import com.popov.t04jh.domain.Groupe;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Groupe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupeRepository extends JpaRepository<Groupe, Long> {

}
