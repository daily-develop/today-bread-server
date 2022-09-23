package com.github.org.todaybread.todaybread.file.application.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void upload(MultipartFile file, String key);

    void delete(String key);
}
