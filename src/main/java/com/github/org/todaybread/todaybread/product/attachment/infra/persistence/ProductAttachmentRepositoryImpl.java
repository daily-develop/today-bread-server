package com.github.org.todaybread.todaybread.product.attachment.infra.persistence;

import com.github.org.todaybread.todaybread.product.attachment.domain.ProductAttachment;
import com.github.org.todaybread.todaybread.product.attachment.domain.ProductAttachmentRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductAttachmentRepositoryImpl implements ProductAttachmentRepository {

    private final ProductAttachmentJpaRepository productAttachmentJpaRepository;

    @Override
    public Optional<ProductAttachment> getByFileId(String fileId) {
        return productAttachmentJpaRepository.findByFileId(UUID.fromString(fileId));
    }

    @Override
    public ProductAttachment save(ProductAttachment productAttachment) {
        return productAttachmentJpaRepository.save(productAttachment);
    }
}
