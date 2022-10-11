package africa.semicolon.lumexpress.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LumExpressUtilsTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void generateTokenTest() {
        String token = LumExpressUtils.generateToken();
        assertThat(token).isNotNull();
        assertThat(token.length()).isEqualTo(5);
    }
}