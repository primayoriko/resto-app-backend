package com.future.restoapp.service;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.Base64;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    ServletContext context;

    @Override
    public byte[] getImageBytes(String filename) throws Exception {
        String path = new File(".").getCanonicalPath() + IMAGE_MENU_DIRECTORY + "/" + filename;
        byte[] fileContent = FileUtils.readFileToByteArray(new File(path));
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
        return fileContent;
    }

    @Override
    public String getImageBase64(String filename) throws Exception {
        byte[] fileContent = getImageBytes(filename);
        return Base64.getEncoder().encodeToString(fileContent);
    }

    @Override
    public void addImage(String path, String encodedContent) throws Exception {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedContent);
        FileUtils.writeByteArrayToFile(new File(path), decodedBytes);
    }

}
