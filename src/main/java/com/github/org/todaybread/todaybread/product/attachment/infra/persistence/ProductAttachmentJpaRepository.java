package com.github.org.todaybread.todaybread.product.attachment.infra.persistence;

import com.github.org.todaybread.todaybread.product.attachment.domain.ProductAttachment;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttachmentJpaRepository extends JpaRepository<ProductAttachment, UUID> {

    Optional<ProductAttachment> findByFileId(UUID fileId);

}
