package com.github.org.todaybread.todaybread.manager.infra;

import com.github.org.todaybread.todaybread.manager.application.ManagerServiceImpl;
import com.github.org.todaybread.todaybread.manager.infra.http.request.UpdateManagerRequest;
import com.github.org.todaybread.todaybread.manager.infra.http.response.ManagerResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize(value = "hasAuthority('GENERAL')")
public class ManagerController {

    private final ManagerServiceImpl managerService;

    @MutationMapping
    public ManagerResponse updateManager(
        Authentication authentication,
        @Valid @Argument UpdateManagerRequest request
    ) {
        return managerService.update(authentication.getName(), request).toResponse();
    }
}
