package com.github.org.todaybread.todaybread.steppay;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.github.org.todaybread.todaybread.config.RestTemplateConfig;
import com.github.org.todaybread.todaybread.steppay.application.SteppayProductServiceImpl;
import com.github.org.todaybread.todaybread.steppay.infra.request.SteppayCreateProductRequest;
import com.github.org.todaybread.todaybread.steppay.infra.response.SteppayProductResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

@Import(value = RestTemplateConfig.class)
@SpringBootTest
@Transactional
class SteppayProductServiceImplTest {

    @Autowired
    private SteppayProductServiceImpl steppayCreateProductService;

    @Test
    @DisplayName("스텝페이 상품을 생성할 수 있어요.")
    public void create() {
        SteppayProductResponse response = steppayCreateProductService.create(
            SteppayCreateProductRequest.builder()
                .name("test_package")
                .featuredImageUrl("featured_image_url")
                .description("test_package_description")
                .build()
        );

        assertThat(response).isNotNull().isInstanceOf(SteppayProductResponse.class);

        assertThat(response.getId()).isNotNull();
        assertThat(response.getType()).isEqualTo("BOX");
        assertThat(response.getStatus()).isEqualTo("SALE");
        assertThat(response.getName()).isEqualTo("test_package");
        assertThat(response.getFeaturedImageUrl()).isEqualTo("featured_image_url");
        assertThat(response.getDescription()).isEqualTo("test_package_description");
    }
}
