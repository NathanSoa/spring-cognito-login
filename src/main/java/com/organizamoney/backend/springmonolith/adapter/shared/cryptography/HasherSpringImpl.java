package com.organizamoney.backend.springmonolith.adapter.shared.cryptography;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class HasherSpringImpl implements Hasher {

    @Override
    public String hash(String value) {
        return Hashing.sha256().hashString(value, StandardCharsets.UTF_8).toString();
    }
}
