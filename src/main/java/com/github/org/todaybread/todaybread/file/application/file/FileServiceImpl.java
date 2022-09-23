package com.github.org.todaybread.todaybread.file.application.file;

import com.github.org.todaybread.todaybread.file.application.storage.StorageServiceImpl;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.file.domain.FileType;
import com.github.org.todaybread.todaybread.file.exceptions.FailedToFileUploadException;
import com.github.org.todaybread.todaybread.file.exceptions.NotSupportedFileFormatException;
import com.github.org.todaybread.todaybread.file.infra.persistence.FileRepositoryImpl;
import com.github.org.todaybread.todaybread.member.application.service.MemberServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileServiceImpl implements FileService {

    private final FileRepositoryImpl fileRepository;
    private final StorageServiceImpl storageService;
    private final MemberServiceImpl memberService;

    @Override
    @Transactional
    public File upload(String memberId, FileType fileType, MultipartFile multipartFile) {
        String mime = multipartFile.getOriginalFilename() == null
            ? "jpeg"
            : multipartFile.getOriginalFilename().substring(
                multipartFile.getOriginalFilename().lastIndexOf(".") + 1
            ).toLowerCase();
        if (
            !"gif".equals(mime)
                && !"jpg".equals(mime)
                && !"png".equals(mime)
                && !"jpeg".equals(mime)
        ) {
            throw new NotSupportedFileFormatException(mime);
        }
        File file = fileRepository.save(
            File.builder()
                .uploader(memberService.getMember(memberId))
                .type(fileType)
                .mime(mime)
                .build()
        );
        try {
            storageService.upload(multipartFile, file.getKey());
            return file;
        } catch (FailedToFileUploadException e) {
            fileRepository.delete(file.getId().toString());
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(String fileId) {
        fileRepository.getById(fileId).ifPresent(file -> storageService.delete(file.getKey()));
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
