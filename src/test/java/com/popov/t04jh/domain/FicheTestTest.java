package com.popov.t04jh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.popov.t04jh.web.rest.TestUtil;

public class FicheTestTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheTest.class);
        FicheTest ficheTest1 = new FicheTest();
        ficheTest1.setId(1L);
        FicheTest ficheTest2 = new FicheTest();
        ficheTest2.setId(ficheTest1.getId());
        assertThat(ficheTest1).isEqualTo(ficheTest2);
        ficheTest2.setId(2L);
        assertThat(ficheTest1).isNotEqualTo(ficheTest2);
        ficheTest1.setId(null);
        assertThat(ficheTest1).isNotEqualTo(ficheTest2);
    }
}
