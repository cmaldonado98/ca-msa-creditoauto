package com.bancopichincha.credito.automotriz.component;

import com.bancopichincha.credito.automotriz.service.InitDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UploadDataComponent {

    @Qualifier("client_service")
    @Autowired
    InitDataService clientService;

    @Qualifier("executive_service")
    @Autowired
    InitDataService executiveService;

    @Qualifier("brand_service")
    @Autowired
    InitDataService brandService;


}
