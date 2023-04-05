package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.EnterpriseRegisterRequest;
import com.dtu.kolgo.dto.response.EnterpriseResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.model.Enterprise;

public interface EnterpriseService {

    WebResponse register(EnterpriseRegisterRequest request);

    WebResponse verify(String registerToken);

    EnterpriseResponse save(Enterprise enterprise);

}
