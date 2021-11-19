package io.github.valfadeev.rundeck.plugin.vault;

import com.dtolabs.rundeck.core.plugins.configuration.Description;
import com.dtolabs.rundeck.plugins.util.DescriptionBuilder;
import com.dtolabs.rundeck.plugins.util.PropertyBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.github.valfadeev.rundeck.plugin.vault.SupportedAuthBackends.*;
import static io.github.valfadeev.rundeck.plugin.vault.ConfigOptions.*;


class DescriptionProvider {



    static Description getDescription() {
        return DescriptionBuilder.builder()
                .name("vault-storage")
                .description("Use Hashicorp Vault secrets as backend for Rundeck Key Storage")
                .title("Hashicorp Vault - Key Storage Plugin")
                .property(PropertyBuilder.builder()
                        .string(VAULT_PREFIX)
                        .title("Vault prefix")
                        .description("Prefix in Vault secret backend")
                        .defaultValue("rundeck")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_ADDRESS)
                        .title("Vault address")
                        .description("Address of the Vault server")
                        .defaultValue("https://localhost:8200")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_TOKEN)
                        .title("Vault token")
                        .description("Vault authentication token. "
                                + "Required, if authentication backend is 'token'")
                )
                .property(PropertyBuilder.builder()
                        .select(VAULT_AUTH_BACKEND)
                        .title("Vault auth backend")
                        .description("Authentication backend")
                        .defaultValue(TOKEN)
                        .values(APPROLE,
                                CERT,
                                GITHUB,
                                TOKEN,
                                USERPASS)
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_KEY_STORE_FILE)
                        .title("Key store file")
                        .description("A Java keystore, containing a client certificate "
                                + "that's registered with Vault's TLS Certificate auth backend.")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_KEY_STORE_FILE_PASSWORD)
                        .title("Key store password")
                        .description("The password needed to access the keystore")
                        .defaultValue("")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_TRUST_STORE_FILE)
                        .title("Truststore file")
                        .description("A JKS truststore file, containing the Vault "
                                + "server's X509 certificate")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_PEM_FILE)
                        .title("PEM file")
                        .description("The path of a file containing an X.509 certificate, "
                                + "in unencrypted PEM format with UTF-8 encoding.")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_CLIENT_PEM_FILE)
                        .title("Client PEM file")
                        .description("The path of a file containing an X.509 certificate, "
                                + "in unencrypted PEM format with UTF-8 encoding.")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_CLIENT_KEY_PEM_FILE)
                        .title("Client key PEM file")
                        .description("The path of a file containing an RSA private key, "
                                + "in unencrypted PEM format with UTF-8 encoding.")
                )
                .property(PropertyBuilder.builder()
                        .booleanType(VAULT_VERIFY_SSL)
                        .title("Disable SSL validation")
                        .description("Specifies whether SSL validation is to be performed")
                        .required(true)
                        .defaultValue("true")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_USERNAME)
                        .title("User name")
                        .description("Required for user/password and LDAP authentication backend")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_PASSWORD)
                        .title("Password")
                        .description("Required for user/password and LDAP authentication backend")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_APPROLE_ID)
                        .title("AppRole role ID")
                        .description("The role-id used for authentication")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_APPROLE_SECRET_ID)
                        .title("AppRole secret ID")
                        .description("The secret-id used for authentication")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_APPROLE_AUTH_MOUNT)
                        .title("AppRole mount name")
                        .description("The mount name of the AppRole authentication back end")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_GITHUB_TOKEN)
                        .title("GitHub token")
                        .description("The app-id used for authentication")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_MAX_RETRIES)
                        .title("Max retries")
                        .description("Maximum number of connection "
                                + "retries to Vault server")
                        .defaultValue("5")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_RETRY_INTERVAL_MILLISECONDS)
                        .title("Retry interval")
                        .description("Connection retry interval, in ms")
                        .defaultValue("1000")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_OPEN_TIMEOUT)
                        .title("Open timeout")
                        .description("Connection opening timeout, in seconds")
                        .defaultValue("5")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_READ_TIMEOUT)
                        .title("Read timeout")
                        .description("Response read timeout, in seconds")
                        .defaultValue("20")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_SECRET_BACKEND)
                        .title("Secret Backend")
                        .description("The secret backend to use in vault")
                        .defaultValue("secret")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_NAMESPACE)
                        .title("Namespace")
                        .description("The namespace to access and save the secrets")
                )
                .property(PropertyBuilder.builder()
                        .string(VAULT_STORAGE_BEHAVIOUR)
                        .title("Storage Behaviour")
                        .description("Use the default Rundeck Behaviour for key storage (with rundeck headers) or use just the key/value behaviour from vault. Options are: rundeck, vault")
                        .defaultValue("rundeck")
                 )
                .property(PropertyBuilder.builder()
                        .select(VAULT_ENGINE_VERSION)
                        .title("Vault Engine Version")
                        .description("Key/Value Secret Engine Config")
                        .values(Arrays.asList("1","2"))
                        .defaultValue("1")
                )
                .build();
    }
}
