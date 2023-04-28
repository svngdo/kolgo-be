package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.FeedbackRequest;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.dto.response.FeedbackResponse;
import com.dtu.kolgo.model.Feedback;
import com.dtu.kolgo.model.User;

import java.security.Principal;
import java.util.List;

public interface FeedbackService {

    ApiResponse save(Feedback feedback);

    List<FeedbackResponse> getAllResponses(User user);

    List<FeedbackResponse> getAllResponses(Principal principal);

    Feedback get(int id);

    ApiResponse update(int id, FeedbackRequest request);

    ApiResponse delete(int id);

    FeedbackResponse mapEntityToDto(Feedback feedback);

    List<FeedbackResponse> mapEntitiesToDtos(List<Feedback> feedbacks);

}