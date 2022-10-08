package com.github.org.todaybread.todaybread.file.application.facade;

import com.github.org.todaybread.todaybread.file.application.service.file.FileServiceImpl;
import com.github.org.todaybread.todaybread.file.application.service.storage.StorageServiceImpl;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.file.domain.FileType;
import com.github.org.todaybread.todaybread.member.application.service.MemberServiceImpl;
import com.github.org.todaybread.todaybread.member.domain.Member;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileFacadeImpl implements FileFacade {

    private final FileServiceImpl fileService;
    private final StorageServiceImpl storageService;
    private final MemberServiceImpl memberService;


    @Override
    @Transactional
    public File upload(String memberId, FileType type, MultipartFile image) {
        Member member = memberService.getMember(memberId);

        File file = fileService.save(member, type, image);
        storageService.upload(image, file.getKey());

        return file;
    }

    @Override
    @Transactional
    public List<File> uploads(String memberId, FileType type, List<MultipartFile> images) {
        Member member = memberService.getMember(memberId);

        return images.stream().map(
            image -> {
                File file = fileService.save(member, type, image);
                storageService.upload(image, file.getKey());
                return file;
            }
        ).collect(Collectors.toList());
    }
}
