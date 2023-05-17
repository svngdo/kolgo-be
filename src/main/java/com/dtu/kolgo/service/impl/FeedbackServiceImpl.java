package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Feedback;
import com.dtu.kolgo.repository.FeedbackRepository;
import com.dtu.kolgo.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository repo;

    @Override
    public Feedback get(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found Feedback with ID: " + id));
    }

}
