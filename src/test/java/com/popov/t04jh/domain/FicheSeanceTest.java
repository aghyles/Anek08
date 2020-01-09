package com.popov.t04jh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.popov.t04jh.web.rest.TestUtil;

public class FicheSeanceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheSeance.class);
        FicheSeance ficheSeance1 = new FicheSeance();
        ficheSeance1.setId(1L);
        FicheSeance ficheSeance2 = new FicheSeance();
        ficheSeance2.setId(ficheSeance1.getId());
        assertThat(ficheSeance1).isEqualTo(ficheSeance2);
        ficheSeance2.setId(2L);
        assertThat(ficheSeance1).isNotEqualTo(ficheSeance2);
        ficheSeance1.setId(null);
        assertThat(ficheSeance1).isNotEqualTo(ficheSeance2);
    }
}
