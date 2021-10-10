package com.future.restoapp.service.impl;

import com.future.restoapp.config.FirebaseStorageConfiguration;
import com.future.restoapp.service.AssetService;
import com.google.cloud.storage.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private StorageOptions storageOptions;

    @Autowired
    private FirebaseStorageConfiguration storageConfig;

    private Storage storage;

    private String projectId;

    private String bucketName;

    private String imageUrlFormat;

    @PostConstruct
    private void initializeService() throws Exception {
        storage = storageOptions.getService();

        projectId = storageConfig.getProjectId();

        bucketName = storageConfig.getBucketName();

        imageUrlFormat = storageConfig.getImageUrlFormat();
    }

    @Override
    public String saveImage(@NotBlank String filename, @NotBlank String mimeType, @NotBlank String base64Content) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64Content);
        BlobId blobId = BlobId.of(bucketName, filename);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(mimeType).build();
        Blob blob = storage.create(blobInfo, decodedBytes);
        return String.format(imageUrlFormat, filename);
    }

}
