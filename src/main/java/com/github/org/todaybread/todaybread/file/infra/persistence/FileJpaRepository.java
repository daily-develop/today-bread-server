package com.github.org.todaybread.todaybread.file.infra.persistence;

import com.github.org.todaybread.todaybread.file.domain.File;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileJpaRepository extends JpaRepository<File, UUID> {

    List<File> findByUploaderId(UUID uploaderId);
}
