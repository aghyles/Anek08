package com.popov.t04jh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.popov.t04jh.web.rest.TestUtil;

public class TestAutreTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TestAutre.class);
        TestAutre testAutre1 = new TestAutre();
        testAutre1.setId(1L);
        TestAutre testAutre2 = new TestAutre();
        testAutre2.setId(testAutre1.getId());
        assertThat(testAutre1).isEqualTo(testAutre2);
        testAutre2.setId(2L);
        assertThat(testAutre1).isNotEqualTo(testAutre2);
        testAutre1.setId(null);
        assertThat(testAutre1).isNotEqualTo(testAutre2);
    }
}
