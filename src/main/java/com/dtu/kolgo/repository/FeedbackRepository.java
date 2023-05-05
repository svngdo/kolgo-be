package com.dtu.kolgo.repository;

import com.dtu.kolgo.model.Feedback;
import com.dtu.kolgo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    List<Feedback> findAllBySender(User sender);

    List<Feedback> findAllByReceiver(User receiver);

}
