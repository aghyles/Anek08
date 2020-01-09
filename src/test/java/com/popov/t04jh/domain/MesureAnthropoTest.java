package com.popov.t04jh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.popov.t04jh.web.rest.TestUtil;

public class MesureAnthropoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MesureAnthropo.class);
        MesureAnthropo mesureAnthropo1 = new MesureAnthropo();
        mesureAnthropo1.setId(1L);
        MesureAnthropo mesureAnthropo2 = new MesureAnthropo();
        mesureAnthropo2.setId(mesureAnthropo1.getId());
        assertThat(mesureAnthropo1).isEqualTo(mesureAnthropo2);
        mesureAnthropo2.setId(2L);
        assertThat(mesureAnthropo1).isNotEqualTo(mesureAnthropo2);
        mesureAnthropo1.setId(null);
        assertThat(mesureAnthropo1).isNotEqualTo(mesureAnthropo2);
    }
}
