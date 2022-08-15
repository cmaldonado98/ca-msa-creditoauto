package com.bancopichincha.credito.automotriz.component;

import com.bancopichincha.credito.automotriz.service.BrandService;
import com.bancopichincha.credito.automotriz.service.CarYardService;
import com.bancopichincha.credito.automotriz.service.ClientService;
import com.bancopichincha.credito.automotriz.service.ExecutiveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
@AllArgsConstructor
public class UploadDataComponent {

    private final ClientService clientService;

    private final BrandService brandService;

    private final ExecutiveService executiveService;
    private final CarYardService carYardService;

    @PostConstruct
    public void initData() throws IOException {
        clientService.initData();
        brandService.initData();
        carYardService.initData();
        executiveService.initData();
    }

}
