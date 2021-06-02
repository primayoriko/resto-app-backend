package com.future.restoapp.service;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.util.Base64;

@Service
public class AssetServiceImpl implements AssetService {

    @Override
    public byte[] getImageBytes(@NotBlank String filename) throws Exception {
        String path = new File(".").getCanonicalPath() + IMAGE_MENU_DIRECTORY + "/" + filename;
        return FileUtils.readFileToByteArray(new File(path));
//        System.out.println(path);
//        InputStream in = context.getResourceAsStream(path);
//        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
//        System.out.println(context.getContextPath());
//        System.out.println(context.getResource(".").getFile());
//        System.out.println(context.getResource(".").getPath());
//        System.out.println(context.getRealPath("."));
//        System.out.println("sadasd");
//        System.out.println(context.getClass().getResource(".").getFile());
//        System.out.println(context.getClass().getResource(".").getPath());
//        System.out.println("1234sadasd");
//        System.out.println(context.getClassLoader().getResource(".").getFile());
//        System.out.println(context.getClassLoader().getResource(".").getPath());
//        ApplicationContext ctx = new FileSystemXmlApplicationContext
    }

    @Override
    public String getImageBase64(@NotBlank String filename) throws Exception {
        byte[] fileContent = getImageBytes(filename);
        return Base64.getEncoder().encodeToString(fileContent);
    }

    @Override
    public String addImage(@NotBlank String filename, @NotBlank String base64Content) throws Exception {
        String path = new File(".").getCanonicalPath() + IMAGE_MENU_DIRECTORY + "/" + filename;
        byte[] decodedBytes = Base64.getDecoder().decode(base64Content);

        FileUtils.writeByteArrayToFile(new File(path), decodedBytes);

        return path;
    }

    @Override
    public boolean deleteImage(@NotBlank String filename) throws Exception {
        String path = new File(".").getCanonicalPath() + IMAGE_MENU_DIRECTORY + "/" + filename;
        File imageFile = new File(path);

        return imageFile.delete();
    }

}
