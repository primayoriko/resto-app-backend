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

//    public byte[] getImageBytes(@NotBlank String filename) throws Exception {
//        String path = new File(".").getCanonicalPath() + IMAGE_MENU_DIRECTORY + "/" + filename;
//        return FileUtils.readFileToByteArray(new File(path));
////        System.out.println(path);
////        InputStream in = context.getResourceAsStream(path);
////        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
////        System.out.println(context.getContextPath());
////        System.out.println(context.getResource(".").getFile());
////        System.out.println(context.getResource(".").getPath());
////        System.out.println(context.getRealPath("."));
////        System.out.println("sadasd");
////        System.out.println(context.getClass().getResource(".").getFile());
////        System.out.println(context.getClass().getResource(".").getPath());
////        System.out.println("1234sadasd");
////        System.out.println(context.getClassLoader().getResource(".").getFile());
////        System.out.println(context.getClassLoader().getResource(".").getPath());
////        ApplicationContext ctx = new FileSystemXmlApplicationContext
//    }

//    public String getImageBase64(@NotBlank String filename) throws Exception {
//        byte[] fileContent = getImageBytes(filename);
//        return Base64.getEncoder().encodeToString(fileContent);
//    }

//    public String addImage(@NotBlank String filename, @NotBlank String base64Content) throws Exception {
//        String path = new File(".").getCanonicalPath() + IMAGE_MENU_DIRECTORY + "/" + filename;
//        byte[] decodedBytes = Base64.getDecoder().decode(base64Content);
//
//        FileUtils.writeByteArrayToFile(new File(path), decodedBytes);
//
//        return path;
//    }

//    public boolean deleteImage(@NotBlank String filename) throws Exception {
//        String path = new File(".").getCanonicalPath() + IMAGE_MENU_DIRECTORY + "/" + filename;
//        File imageFile = new File(path);
//
//        return imageFile.delete();
//    }

}
