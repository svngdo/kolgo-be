package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.FeedbackRequest;
import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.FeedbackDto;
import com.dtu.kolgo.model.Feedback;
import com.dtu.kolgo.model.User;

import java.util.List;

public interface FeedbackService {

    ApiResponse save(Feedback feedback);

    List<FeedbackDto> getDtosBySender(User sender);

    List<FeedbackDto> getDtosByReceiver(User receiver);

    Feedback get(int id);

    ApiResponse update(int id, FeedbackRequest request);

    ApiResponse delete(int id);

}