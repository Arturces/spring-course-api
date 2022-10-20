package com.arturces.springcourseapi.repository;

import com.arturces.springcourseapi.domain.Request;
import com.arturces.springcourseapi.domain.RequestStage;
import com.arturces.springcourseapi.domain.enums.RequestState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Long> {

    public List<RequestStage> findAllByRequestId(Long id);

    @Query("UPDATE request SET state = ?2 WHERE id = ?1")
    public Request updateStatus(Long id, RequestState state);


}
