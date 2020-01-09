package com.popov.t04jh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.popov.t04jh.web.rest.TestUtil;

public class SwimerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Swimer.class);
        Swimer swimer1 = new Swimer();
        swimer1.setId(1L);
        Swimer swimer2 = new Swimer();
        swimer2.setId(swimer1.getId());
        assertThat(swimer1).isEqualTo(swimer2);
        swimer2.setId(2L);
        assertThat(swimer1).isNotEqualTo(swimer2);
        swimer1.setId(null);
        assertThat(swimer1).isNotEqualTo(swimer2);
    }
}
