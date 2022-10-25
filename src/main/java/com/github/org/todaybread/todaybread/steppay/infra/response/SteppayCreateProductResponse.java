package com.github.org.todaybread.todaybread.steppay.infra.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class SteppayCreateProductResponse {
    int id;
    String code;
    String type;
    String status;
    String name;
    String featuredImageUrl;
    List<String> imageUrls;
    String description;
    List<String> combinedProducts;
    List<String> optionGroups;
    Boolean useCombination;
    List<String> optionCombinations;
    List<String> prices;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    Boolean enabledDemo;
    int demoPeriod;
    String demoPeriodUnit;
    List<String> categories;
    String vendorUuid;
    int productOrder;
}
