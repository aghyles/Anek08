package com.popov.t04jh.repository;
import com.popov.t04jh.domain.Dosage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Dosage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DosageRepository extends JpaRepository<Dosage, Long> {

}
