package com.dtu.kolgo.repository;

import com.dtu.kolgo.model.Payment;
import com.dtu.kolgo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findAllByUser(User user);

    Optional<Payment> findByTxnNo(String txnNo);

    Optional<Payment> findByTxnRef(String txnRef);

}
