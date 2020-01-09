package com.popov.t04jh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.popov.t04jh.web.rest.TestUtil;

public class TestPhysiqueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TestPhysique.class);
        TestPhysique testPhysique1 = new TestPhysique();
        testPhysique1.setId(1L);
        TestPhysique testPhysique2 = new TestPhysique();
        testPhysique2.setId(testPhysique1.getId());
        assertThat(testPhysique1).isEqualTo(testPhysique2);
        testPhysique2.setId(2L);
        assertThat(testPhysique1).isNotEqualTo(testPhysique2);
        testPhysique1.setId(null);
        assertThat(testPhysique1).isNotEqualTo(testPhysique2);
    }
}
