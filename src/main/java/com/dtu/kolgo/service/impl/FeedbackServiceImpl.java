package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.FeedbackRequest;
import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.FeedbackDto;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Feedback;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.FeedbackRepository;
import com.dtu.kolgo.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository repo;
    private final ModelMapper mapper;

    @Override
    public ApiResponse save(Feedback feedback) {
        return null;
    }

    @Override
    public List<FeedbackDto> getDtosBySender(User sender) {
        return repo.findAllBySender(sender)
                .stream()
                .map(feedback -> mapper.map(feedback, FeedbackDto.class))
                .toList();
    }

    @Override
    public List<FeedbackDto> getDtosByReceiver(User receiver) {
        return repo.findAllByReceiver(receiver)
                .stream()
                .map(feedback -> mapper.map(feedback, FeedbackDto.class))
                .toList();
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

}
