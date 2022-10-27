package com.github.org.todaybread.todaybread.file.application.service.file;

import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.file.domain.FileType;
import com.github.org.todaybread.todaybread.member.domain.Member;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    File getById(String fileId);

    File save(Member member, FileType type, MultipartFile image);

    void delete(String fileId);

    void deleteByUploaderId(String uploaderId);
}
