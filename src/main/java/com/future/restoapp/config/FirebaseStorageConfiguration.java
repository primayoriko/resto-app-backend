package com.future.restoapp.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseStorageConfiguration {

    @Autowired
    private Environment env;

    public String getProjectId() {
        return env.getRequiredProperty("firebase.project-id");
    }

    public String getBucketName() {
        return env.getRequiredProperty("firebase.bucket-name");
    }

    public String getImageUrlFormat() {
        return env.getRequiredProperty("firebase.image-url-format");
    }

    @Bean
    public GoogleCredentials getCredentials() throws IOException {
        String credentials = env.getRequiredProperty("firebase.credentials");
        InputStream credentialsStream = new ByteArrayInputStream(credentials.getBytes());
        return GoogleCredentials.fromStream(credentialsStream);
    }

    @Bean
    public StorageOptions getStorageOptions() throws IOException {
        return StorageOptions.newBuilder()
                .setProjectId(getProjectId())
                .setCredentials(getCredentials())
                .build();
    }

}
