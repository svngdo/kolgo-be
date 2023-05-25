package com.dtu.kolgo.repository;

import com.dtu.kolgo.model.Booking;
import com.dtu.kolgo.model.Kol;
import com.dtu.kolgo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findAllByKol(Kol kol);

    List<Booking> findByKol_User(User user);

    Booking findByTxnRef(String txnRef);
}
