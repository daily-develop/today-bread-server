package com.github.org.todaybread.todaybread.store.application.facade;

import com.github.org.todaybread.todaybread.file.application.facade.FileFacadeImpl;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.file.domain.FileType;
import com.github.org.todaybread.todaybread.manager.application.ManagerServiceImpl;
import com.github.org.todaybread.todaybread.manager.domain.Manager;
import com.github.org.todaybread.todaybread.manager.exception.NotManagerException;
import com.github.org.todaybread.todaybread.member.application.service.MemberServiceImpl;
import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.store.application.service.StoreServiceImpl;
import com.github.org.todaybread.todaybread.store.domain.Store;
import com.github.org.todaybread.todaybread.store.infra.http.request.CreateStoreRequest;
import com.github.org.todaybread.todaybread.store.infra.http.request.UpdateStoreRequest;
import com.github.org.todaybread.todaybread.store.infra.http.response.StoreResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreFacadeImpl implements StoreFacade {

    private final StoreServiceImpl storeService;
    private final MemberServiceImpl memberService;
    private final ManagerServiceImpl managerService;
    private final FileFacadeImpl fileFacade;

    @Override
    @Transactional
    public StoreResponse create(String memberId, CreateStoreRequest request) {
        Member member = memberService.getMember(memberId);
        Manager manager = managerService.save(
            Manager.builder()
                .nickname(request.getManager().getNickname())
                .member(member)
                .build()
        );

        Store store = storeService.save(
            Store.builder()
                .name(request.getName())
                .description(request.getDescription())
                .location(request.getLocation())
                .phone(request.getPhone())
                .manager(manager)
                .build()
        );

        if (request.getImage() != null) {
            File file = fileFacade.upload(
                member.getId().toString(),
                FileType.PROFILE,
                request.getImage());
            store.updateFile(file);
        }

        manager.updateStore(store);

        return store.toResponse();
    }

    @Override
    @Transactional
    public StoreResponse update(String memberId, UpdateStoreRequest request) {
        Member member = memberService.getMember(memberId);
        Manager manager = managerService.getByStoreId(request.getStoreId());
        if (manager.getMember() != member) {
            throw new NotManagerException();
        }

        return manager.getStore().update(
            request.getName(),
            request.getDescription(),
            request.getLocation(),
            request.getPhone()
        ).toResponse();
    }

    @Override
    @Transactional
    public Boolean delete(String memberId, String storeId) {
        Member member = memberService.getMember(memberId);
        managerService.checkManagerAuthentication(member, storeId);

        storeService.deleteById(storeId);
        return true;
    }

    @Override
    public StoreResponse get(String storeId) {
        return storeService.getById(storeId).toResponse();
    }

    @Override
    public List<StoreResponse> getList(int page, int take, String search) {
        return storeService.getList(page, take, search)
            .stream().map(Store::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<StoreResponse> getByMemberId(String memberId) {
        List<Manager> managerList = managerService.getByMemberId(memberId);

        return managerList.stream().map(manager ->
            storeService.getByManagerId(manager.getId().toString()).toResponse()
        ).collect(Collectors.toList());
    }
}
