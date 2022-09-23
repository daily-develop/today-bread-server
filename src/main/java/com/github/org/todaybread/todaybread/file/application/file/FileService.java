package com.github.org.todaybread.todaybread.file.application.file;

import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.file.domain.FileType;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    File upload(String memberId, FileType fileType, MultipartFile file);

    void delete(String fileId);

    void deleteByUploaderId(String uploaderId);
}
