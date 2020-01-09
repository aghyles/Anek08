package com.popov.t04jh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.popov.t04jh.web.rest.TestUtil;

public class TestNageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TestNage.class);
        TestNage testNage1 = new TestNage();
        testNage1.setId(1L);
        TestNage testNage2 = new TestNage();
        testNage2.setId(testNage1.getId());
        assertThat(testNage1).isEqualTo(testNage2);
        testNage2.setId(2L);
        assertThat(testNage1).isNotEqualTo(testNage2);
        testNage1.setId(null);
        assertThat(testNage1).isNotEqualTo(testNage2);
    }
}
