package com.github.org.todaybread.todaybread.file.domain;

import java.util.List;
import java.util.Optional;

public interface FileRepository {

    Optional<File> getById(String id);

    List<File> getByUploaderId(String uploaderId);

    File save(File file);

    void delete(String fileId);

    void delete(File file);
}
