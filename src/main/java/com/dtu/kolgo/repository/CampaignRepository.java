package com.dtu.kolgo.repository;

import com.dtu.kolgo.model.Campaign;
import com.dtu.kolgo.model.Enterprise;
import com.dtu.kolgo.model.Kol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

    List<Campaign> findAllByEnterprise(Enterprise ent);

    List<Campaign> findAllByKolsContains(Kol kol);

}
