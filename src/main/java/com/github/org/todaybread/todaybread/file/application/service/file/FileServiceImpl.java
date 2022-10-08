package com.github.org.todaybread.todaybread.file.application.service.file;

import com.github.org.todaybread.todaybread.file.application.service.storage.StorageServiceImpl;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.file.domain.FileType;
import com.github.org.todaybread.todaybread.file.exceptions.NotSupportedFileFormatException;
import com.github.org.todaybread.todaybread.file.infra.persistence.FileRepositoryImpl;
import com.github.org.todaybread.todaybread.member.domain.Member;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileServiceImpl implements FileService {

    private static final List<String> ALLOWED_FILE_TYPE = Arrays.asList(
        "jpeg",
        "png",
        "jpg"
    );
    private final FileRepositoryImpl fileRepository;
    private final StorageServiceImpl storageService;

    @Override
    @Transactional
    public File save(Member member, FileType type, MultipartFile image) {
        String mime = image.getOriginalFilename() == null ? "jpeg" :
            image.getOriginalFilename().substring(
                image.getOriginalFilename().lastIndexOf(".") + 1
            ).toLowerCase();

        if (!ALLOWED_FILE_TYPE.contains(mime)) {
            throw new NotSupportedFileFormatException(mime);
        }

        return fileRepository.save(
            File.builder()
                .uploader(member)
                .mime(mime)
                .type(type)
                .build()
        );
    }

    @Override
    @Transactional
    public void delete(String fileId) {
        fileRepository.delete(fileId);
    }

    @Override
    @Transactional
    public void deleteByUploaderId(String uploaderId) {
        List<File> list = fileRepository.getByUploaderId(uploaderId);
        for (File file : list) {
            storageService.delete(file.getKey());
            fileRepository.delete(file);
        }
    }
}
