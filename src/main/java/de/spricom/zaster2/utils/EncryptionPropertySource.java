package de.spricom.zaster2.utils;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.util.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class EncryptionPropertySource extends EnumerablePropertySource<Object> {
    private static final String ENCRYPTED_SUFFIX = ".encrypted";
    static final String ENCRYPTION_KEY_PROPERTY = "bot.encryption.key";
    public static final String PROPERTY_SOURCE_NAME = "encryptedProperties";

    private final ConfigurableEnvironment env;
    private final String[] propertyNames;
    private String encryptionKey;

    public EncryptionPropertySource(ConfigurableEnvironment env) {
        super(PROPERTY_SOURCE_NAME);
        this.env = env;
        propertyNames = determinePropertyNames(env);
    }

    @Override
    public boolean containsProperty(String name) {
        return (env.getProperty(name + ENCRYPTED_SUFFIX) != null);
    }

    @Override
    public Object getProperty(String name) {
        if (name.endsWith(ENCRYPTED_SUFFIX)) {
            return null;
        }
        String encryptedValue = env.getProperty(name + ENCRYPTED_SUFFIX);
        if (encryptedValue != null) {
            return decrypt(encryptedValue);
        }
        return null;
    }

    @Override
    public String[] getPropertyNames() {
        return propertyNames;
    }

    private String decrypt(String encryptedValue) {
        try {
            return EncryptionUtils.decrypt(encryptedValue, getEncryptionKey());
        } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException |
                 BadPaddingException ex) {
            throw new IllegalStateException("Cannot decrypt '" + encryptedValue + "': " + ex.getMessage(), ex);
        }
    }

    private String getEncryptionKey() {
        if (encryptionKey == null) {
            encryptionKey = env.getProperty(ENCRYPTION_KEY_PROPERTY);
            if (encryptionKey == null) {
                throw new IllegalStateException("Encryption key not found in environment properties.");
            }
        }
        return encryptionKey;
    }

    private static String[] determinePropertyNames(ConfigurableEnvironment env) {
        return StringUtils.toStringArray(
                env.getPropertySources().stream()
                        .filter(EnumerablePropertySource.class::isInstance)
                        .map(EnumerablePropertySource.class::cast)
                        .flatMap(source1 -> Arrays.stream(source1.getPropertyNames()))
                        .filter(name -> name.endsWith(ENCRYPTED_SUFFIX))
                        .map(name -> name.substring(0, name.length() - ENCRYPTED_SUFFIX.length()))
                        .distinct()
                        .toList());
    }

}
