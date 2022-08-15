package com.bancopichincha.credito.automotriz.component;

import com.bancopichincha.credito.automotriz.service.BrandService;
import com.bancopichincha.credito.automotriz.service.ClientService;
import com.bancopichincha.credito.automotriz.service.ExecutiveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class UploadDataComponent {

    private final ClientService clientService;

    private final BrandService brandService;

    private final ExecutiveService executiveService;

    @PostConstruct
    public void initData(){
        clientService.initData();
        brandService.initData();
        executiveService.initData();
    }

}
