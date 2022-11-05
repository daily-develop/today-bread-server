package com.github.org.todaybread.todaybread.product.attachment.domain;

import java.util.Optional;

public interface ProductAttachmentRepository {

    Optional<ProductAttachment> getByFileId(String fileId);

    ProductAttachment save(ProductAttachment productAttachment);

}
