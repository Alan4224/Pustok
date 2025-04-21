package com.alan.Pustok.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alan.Pustok.dto.ProviderDto;
import com.alan.Pustok.entity.Provider;

@Service
public interface ProviderService {
    List<Provider> findAll();

    Provider create(ProviderDto providerDto, MultipartFile file);

    Provider update(int id, ProviderDto providerDto, MultipartFile file);

    Provider findProviderById(int id);

    void delete(int id);
}
