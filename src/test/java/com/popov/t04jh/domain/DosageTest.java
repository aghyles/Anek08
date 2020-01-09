package com.popov.t04jh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.popov.t04jh.web.rest.TestUtil;

public class DosageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dosage.class);
        Dosage dosage1 = new Dosage();
        dosage1.setId(1L);
        Dosage dosage2 = new Dosage();
        dosage2.setId(dosage1.getId());
        assertThat(dosage1).isEqualTo(dosage2);
        dosage2.setId(2L);
        assertThat(dosage1).isNotEqualTo(dosage2);
        dosage1.setId(null);
        assertThat(dosage1).isNotEqualTo(dosage2);
    }
}
