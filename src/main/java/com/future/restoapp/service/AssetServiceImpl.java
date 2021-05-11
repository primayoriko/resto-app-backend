package com.future.restoapp.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.InputStream;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    ServletContext context;

    @Override
    public byte[] getImage(String path) throws Exception {
//        InputStream in = context.getResourceAsStream(path);
        InputStream in = getClass().getResourceAsStream(path);

//        System.out.println(context.getContextPath());
//        System.out.println(context.getRealPath(path));
//        System.out.println(context.getResource(path));
//        System.out.println(context.getResourcePaths(path));

        return IOUtils.toByteArray(in);
    }

    @Override
    public void addImage(String path, String content) throws Exception {

    }

}
