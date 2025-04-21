package com.alan.Pustok.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alan.Pustok.dto.ProviderDto;
import com.alan.Pustok.entity.Provider;
import com.alan.Pustok.repository.ProviderRepository;
import com.alan.Pustok.service.ProviderService;
import com.alan.Pustok.utils.UploadUtil;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Provider> findAll() {
        return providerRepository.findAll();
    }

    @Override
    public void delete(int id) {
        UploadUtil.deleteImageLink(providerRepository.findById(id).get().getImage(), Provider.class);
        providerRepository.deleteById(id);
    }

    @Override
    public Provider create(ProviderDto providerDto, MultipartFile file) {
        providerDto.setImage(UploadUtil.getImageLink(file, Provider.class));
        Provider provider = modelMapper.map(providerDto, Provider.class);
        return providerRepository.save(provider);
    }

    @Override
    public Provider update(int id, ProviderDto providerDto, MultipartFile file) {
        Provider provider = providerRepository.findById(id).get();
        providerDto.setImage(UploadUtil.getImageLink(file, Provider.class));
        provider = modelMapper.map(providerDto, Provider.class);
        return providerRepository.save(provider);
    }

    @Override
    public Provider findProviderById(int id) {
        return providerRepository.findById(id).get();
    }

}