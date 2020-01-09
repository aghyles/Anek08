package com.popov.t04jh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.popov.t04jh.web.rest.TestUtil;

public class TestEtudeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TestEtude.class);
        TestEtude testEtude1 = new TestEtude();
        testEtude1.setId(1L);
        TestEtude testEtude2 = new TestEtude();
        testEtude2.setId(testEtude1.getId());
        assertThat(testEtude1).isEqualTo(testEtude2);
        testEtude2.setId(2L);
        assertThat(testEtude1).isNotEqualTo(testEtude2);
        testEtude1.setId(null);
        assertThat(testEtude1).isNotEqualTo(testEtude2);
    }
}
