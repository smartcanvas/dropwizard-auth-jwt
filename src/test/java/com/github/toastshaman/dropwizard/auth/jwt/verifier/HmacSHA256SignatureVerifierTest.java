package com.github.toastshaman.dropwizard.auth.jwt.verifier;

import com.github.toastshaman.dropwizard.auth.jwt.model.JWTToken;
import com.github.toastshaman.dropwizard.auth.jwt.parser.DefaultJWTTokenParser;
import com.google.common.io.BaseEncoding;
import org.junit.Test;

import java.nio.charset.Charset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HmacSHA256SignatureVerifierTest {

    @Test
    public void
    verifies_a_valid_signature() {
        final String encodedToken = ""
                + "eyJ0eXAiOiJKV1QiLA0KICJhbGciOiJIUzI1NiJ9"
                + ".eyJpc3MiOiJqb2UiLA0KICJleHAiOjEzMDA4MTkzODAsDQogImh0dHA6Ly9leGFtcGxlLmNvbS9pc19yb290Ijp0cnVlfQ"
                + ".dBjftJeZ4CVP-mB92K27uhbUJU1p1r_wW1gFWFOEjXk";

        final byte[] key = decode("AyM1SysPpbyDfgZld3umj1qzKObwVMkoqQ-EstJQLr_T-1qS0gZH75aKtMN3Yj0iPS4hcgUuTwjAzZr1Z9CAow");

        JWTToken token = new DefaultJWTTokenParser().parse(encodedToken);

        HmacSHA256SignatureVerifier verifier = new HmacSHA256SignatureVerifier(key);

        assertThat(verifier.verifySignature(token), equalTo(true));
    }

    private byte[] decode(String input) { return BaseEncoding.base64Url().omitPadding().decode(input); }
}
