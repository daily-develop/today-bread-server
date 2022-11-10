package com.github.org.todaybread.todaybread.store.infra.http;

import com.github.org.todaybread.todaybread.store.application.facade.StoreFacadeImpl;
import com.github.org.todaybread.todaybread.store.infra.http.request.CreateStoreRequest;
import com.github.org.todaybread.todaybread.store.infra.http.request.UpdateStoreRequest;
import com.github.org.todaybread.todaybread.store.infra.http.response.StoreResponse;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@PreAuthorize(value = "hasAuthority('GENERAL')")
public class StoreController {

    private final StoreFacadeImpl storeFacade;

    @QueryMapping
    public StoreResponse store(
        @Valid @NotBlank @Argument String storeId
    ) {
        return storeFacade.get(storeId);
    }

    @QueryMapping
    public List<StoreResponse> stores(
        @Valid @Min(1) @Argument int page,
        @Valid @Min(1) @Argument int take,
        @Argument String search
    ) {
        return storeFacade.getList(page, take, search);
    }

    @QueryMapping
    public List<StoreResponse> managedStore(
        Authentication authentication,
        @Valid @Min(1) @Argument int page,
        @Valid @Min(1) @Argument int take
    ) {
        return storeFacade.getByMemberId(authentication.getName(), page, take);
    }

    @MutationMapping
    public StoreResponse createStore(
        Authentication authentication,
        @Valid @Argument CreateStoreRequest request
    ) {
        return storeFacade.create(authentication.getName(), request);
    }

    @MutationMapping
    public StoreResponse updateStore(
        Authentication authentication,
        @Valid @Argument UpdateStoreRequest request
    ) {
        return storeFacade.update(authentication.getName(), request);
    }

    @MutationMapping
    public Boolean deleteStore(
        Authentication authentication,
        @Valid @NotBlank @Argument String storeId
    ) {
        return storeFacade.delete(authentication.getName(), storeId);
    }

    @SchemaMapping(typeName = "Store", field = "isManager")
    public Boolean isManager(
        Authentication authentication,
        StoreResponse store
    ) {
        return store.getManager().getMember().getId().equals(authentication.getName());
    }
}
