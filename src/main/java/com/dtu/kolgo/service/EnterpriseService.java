package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.UpdateEnterpriseRequest;
import com.dtu.kolgo.dto.response.EnterpriseResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.model.Enterprise;
import com.dtu.kolgo.model.User;

import java.util.List;

public interface EnterpriseService {

    void save(Enterprise ent);

    List<EnterpriseResponse> getAll();

    Enterprise getById(int entId);

    Enterprise getByUser(User user);

    EnterpriseResponse getProfileById(int entId);

    WebResponse update(int entId, UpdateEnterpriseRequest request);

    WebResponse delete(int entId);

}
