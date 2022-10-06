package com.github.org.todaybread.todaybread.file.infra.http.response;

import com.github.org.todaybread.todaybread.file.domain.File;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Getter
@NoArgsConstructor
public class FileResponse {

    @Value("${storage.end-point}")
    private String endPoint;

    @Value("${storage.bucket}")
    private String bucket;

    String id;

    String url;

    @Builder
    public FileResponse(
        File file
    ) {
        this.id = file.getId().toString();
        this.url = "$endPoint/$bucket/" + file.getKey();
    }
}
