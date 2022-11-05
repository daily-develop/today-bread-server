package com.github.org.todaybread.todaybread.product.attachment.application;

import com.github.org.todaybread.todaybread.file.application.service.file.FileService;
import com.github.org.todaybread.todaybread.product.attachment.domain.ProductAttachment;
import com.github.org.todaybread.todaybread.product.attachment.domain.ProductAttachmentRepository;
import com.github.org.todaybread.todaybread.product.domain.Product;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductAttachmentServiceImpl implements ProductAttachmentService {

    private final ProductAttachmentRepository productAttachmentRepository;
    private final FileService fileService;

    @Override
    public ProductAttachment getByFileId(String fileId) {
        return productAttachmentRepository.getByFileId(fileId).orElse(null);
    }

    @Override
    @Transactional
    public List<ProductAttachment> save(Product product, List<String> fileIds) {
        return fileIds.stream()
            .map(fileService::getById)
            .filter(Objects::nonNull)
            .map(
                file -> productAttachmentRepository.save(
                    ProductAttachment.builder()
                        .product(product)
                        .file(file)
                        .build()
                )
            ).toList();
    }
}
