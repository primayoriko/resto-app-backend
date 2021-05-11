package com.future.restoapp.service;

public interface AssetService {

    byte[] getImage(String path) throws Exception;

    void addImage(String path, String content) throws Exception;

}
