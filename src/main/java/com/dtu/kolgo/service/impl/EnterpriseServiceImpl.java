package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.EnterpriseRegisterRequest;
import com.dtu.kolgo.dto.response.EnterpriseResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.model.Enterprise;
import com.dtu.kolgo.service.EnterpriseService;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Override
    public WebResponse register(EnterpriseRegisterRequest request) {
        return null;
    }

    @Override
    public WebResponse verify(String registerToken) {
        return null;
    }

    @Override
    public EnterpriseResponse save(Enterprise enterprise) {
        return null;
    }

}
