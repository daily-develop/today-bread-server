package com.github.org.todaybread.todaybread.product.attachment.application;

import com.github.org.todaybread.todaybread.product.attachment.domain.ProductAttachment;
import com.github.org.todaybread.todaybread.product.domain.Product;
import org.springframework.web.multipart.MultipartFile;

public interface ProductAttachmentService {

    ProductAttachment getByFileId(String fileId);

    ProductAttachment save(String memberId, Product product, MultipartFile upload);

    ProductAttachment saveByFileId(Product product, String fileId);
}
