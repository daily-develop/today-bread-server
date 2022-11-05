package com.github.org.todaybread.todaybread.product.attachment.application;

import com.github.org.todaybread.todaybread.product.attachment.domain.ProductAttachment;
import com.github.org.todaybread.todaybread.product.domain.Product;
import java.util.List;

public interface ProductAttachmentService {

    ProductAttachment getByFileId(String fileId);

    List<ProductAttachment> save(Product product, List<String> fileIds);
}
