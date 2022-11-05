package com.github.org.todaybread.todaybread.file.infra.http;

import com.github.org.todaybread.todaybread.file.application.facade.FileFacade;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.file.domain.FileType;
import com.github.org.todaybread.todaybread.file.infra.http.response.FileResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@PreAuthorize(value = "hasAuthority('GENERAL')")
public class FileController {

    private final FileFacade fileFacade;

    @MutationMapping
    public FileResponse upload(
        Authentication authentication,
        @Argument MultipartFile file,
        @Argument FileType type
    ) {
        return fileFacade.upload(authentication.getName(), type, file).toResponse();
    }

    @MutationMapping
    public List<FileResponse> uploads(
        Authentication authentication,
        @Argument List<MultipartFile> files,
        @Argument FileType type
    ) {
        return fileFacade.uploads(authentication.getName(), type, files)
            .stream()
            .map(File::toResponse)
            .toList();
    }
}
