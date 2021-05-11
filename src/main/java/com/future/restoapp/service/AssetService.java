package com.future.restoapp.service;

public interface AssetService {

    String getImage(String path) throws Exception;

    void addImage(String path, String content) throws Exception;

}
