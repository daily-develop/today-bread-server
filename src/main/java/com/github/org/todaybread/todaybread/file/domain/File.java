package com.github.org.todaybread.todaybread.file.domain;

import com.github.org.todaybread.todaybread.common.domain.Core;
import com.github.org.todaybread.todaybread.file.infra.http.response.FileResponse;
import com.github.org.todaybread.todaybread.member.domain.Member;
import io.jsonwebtoken.lang.Assert;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends Core {

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "uploader_id")
    private Member uploader;

    @Column(name = "file_key")
    private String key;

    @Enumerated(EnumType.STRING)
    private FileType type;

    @Builder
    public File(Member uploader, FileType type, String mime) {
        Assert.isInstanceOf(Member.class, uploader, "member must be instance of Member");
        Assert.isInstanceOf(FileType.class, type, "type must be instance of FileType");

        this.uploader = uploader;
        this.key = type + "/" + UUID.randomUUID() + "." + mime;
        this.type = type;
    }

    public FileResponse toResponse() {
        return FileResponse.builder()
            .file(this)
            .build();
    }
}