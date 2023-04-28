package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.FeedbackRequest;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.dto.response.FeedbackResponse;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Feedback;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.FeedbackRepository;
import com.dtu.kolgo.service.FeedbackService;
import com.dtu.kolgo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository repo;
    private final UserService userService;

    @Override
    public ApiResponse save(Feedback feedback) {
        return null;
    }

    @Override
    public List<FeedbackResponse> getAllResponses(User user) {
        return mapEntitiesToDtos(repo.findAllByUser(user));
    }

    @Override
    public List<FeedbackResponse> getAllResponses(Principal principal) {
        User user = userService.get(principal);
        return getAllResponses(user);
    }

    @Override
    public Feedback get(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found Feedback with ID: " + id));
    }

    @Override
    public ApiResponse update(int id, FeedbackRequest request) {
        Feedback feedback = get(id);
        feedback.setRating(request.getRating());
        feedback.setComment(request.getComment());
        repo.save(feedback);

        return new ApiResponse("Updated Feedback successfully");
    }

    @Override
    public ApiResponse delete(int id) {
        repo.deleteById(id);
        return new ApiResponse("Deleted Feedback successfully");
    }

    @Override
    public FeedbackResponse mapEntityToDto(Feedback feedback) {
        return FeedbackResponse.builder()
                .id(feedback.getId())
                .userId(feedback.getUser().getId())
                .lastName(feedback.getUser().getFirstName())
                .lastName(feedback.getUser().getLastName())
                .rating(feedback.getRating())
                .comment(feedback.getComment())
                .build();
    }

    @Override
    public List<FeedbackResponse> mapEntitiesToDtos(List<Feedback> feedbacks) {
        return feedbacks.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
