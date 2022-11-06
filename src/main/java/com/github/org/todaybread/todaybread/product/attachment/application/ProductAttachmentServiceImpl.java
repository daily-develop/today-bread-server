package com.github.org.todaybread.todaybread.product.attachment.application;

import com.github.org.todaybread.todaybread.file.application.facade.FileFacade;
import com.github.org.todaybread.todaybread.file.application.service.file.FileService;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.file.domain.FileType;
import com.github.org.todaybread.todaybread.product.attachment.domain.ProductAttachment;
import com.github.org.todaybread.todaybread.product.attachment.domain.ProductAttachmentRepository;
import com.github.org.todaybread.todaybread.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductAttachmentServiceImpl implements ProductAttachmentService {

    private final ProductAttachmentRepository productAttachmentRepository;
    private final FileFacade fileFacade;
    private final FileService fileService;

    @Override
    public ProductAttachment getByFileId(String fileId) {
        return productAttachmentRepository.getByFileId(fileId).orElse(null);
    }

    @Override
    @Transactional
    public ProductAttachment save(String memberId, Product product, MultipartFile upload) {
        File file = fileFacade.upload(memberId, FileType.PRODUCT, upload);

        return productAttachmentRepository.save(
            ProductAttachment.builder()
                .product(product)
                .file(file)
                .build()
        );
    }

    @Override
    public ProductAttachment saveByFileId(Product product, String fileId) {
        File file = fileService.getById(fileId);

        return productAttachmentRepository.save(
            ProductAttachment.builder()
                .product(product)
                .file(file)
                .build()
        );
    }
}
