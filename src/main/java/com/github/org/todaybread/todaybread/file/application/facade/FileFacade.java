package com.github.org.todaybread.todaybread.file.application.facade;

import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.file.domain.FileType;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface FileFacade {

    File upload(String memberId, FileType type, MultipartFile image);

    List<File> uploads(String memberId, FileType type, List<MultipartFile> images);
}