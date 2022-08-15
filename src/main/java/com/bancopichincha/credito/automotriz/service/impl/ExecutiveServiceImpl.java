package com.bancopichincha.credito.automotriz.service.impl;

import com.bancopichincha.credito.automotriz.exception.ApplicationException;
import com.bancopichincha.credito.automotriz.model.entities.CarYardEntity;
import com.bancopichincha.credito.automotriz.model.entities.ExecutiveEntity;
import com.bancopichincha.credito.automotriz.model.enums.ResponseStatusCode;
import com.bancopichincha.credito.automotriz.repository.CarYardRepository;
import com.bancopichincha.credito.automotriz.repository.ExecutiveRepository;
import com.bancopichincha.credito.automotriz.service.ExecutiveService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
@Log4j2
public class ExecutiveServiceImpl implements ExecutiveService {

    private final ExecutiveRepository executiveRepository;
    private final CarYardRepository carYardRepository;
    @Override
    @Transactional
    public void initData() throws IOException {
        log.info("LOAD EXECUTIVE DATA FROM CSV");
        if (executiveRepository.findAll().isEmpty()) {

            List<ExecutiveEntity> executiveEntities = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new ClassPathResource("files/executives.csv").getFile()));
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                ExecutiveEntity executiveEntity = new ExecutiveEntity();
                CarYardEntity carYardEntity = carYardRepository.findById(Long.valueOf(values[7]))
                        .orElseThrow(() -> {
                            log.error(String.format("Car yard not found id: %s", values[7]));
                            throw new ApplicationException(ResponseStatusCode.CAR_YARD_DOES_NOT_EXISTS);
                        });
                executiveEntity.setIdentification(values[0]);
                executiveEntity.setNames(values[1]);
                executiveEntity.setSurnames(values[2]);
                executiveEntity.setAddress(values[3]);
                executiveEntity.setPhone(values[4]);
                executiveEntity.setCellphone(values[5]);
                executiveEntity.setAge(Long.valueOf(values[6]));
                executiveEntity.setCarYardEntity(carYardEntity);
                executiveEntities.add(executiveEntity);
            }
            executiveRepository.saveAll(executiveEntities);
        }
    }
}
