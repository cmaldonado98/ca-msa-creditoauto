package com.bancopichincha.credito.automotriz.service.impl;

import com.bancopichincha.credito.automotriz.model.entities.BrandEntity;
import com.bancopichincha.credito.automotriz.repository.BrandRepository;
import com.bancopichincha.credito.automotriz.service.BrandService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    @Override
    public void initData() throws IOException {
        log.info("LOAD BRAND DATA FROM CSV");
        if (brandRepository.findAll().isEmpty()) {

            List<BrandEntity> brandEntities = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new ClassPathResource("files/brands.csv").getFile()));
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                BrandEntity brandEntity = new BrandEntity();
                brandEntity.setName(line);
                brandEntities.add(brandEntity);
            }
            brandRepository.saveAll(brandEntities);
        }

    }
}
