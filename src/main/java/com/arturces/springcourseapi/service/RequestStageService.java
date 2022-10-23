package com.arturces.springcourseapi.service;

import com.arturces.springcourseapi.domain.RequestStage;
import com.arturces.springcourseapi.domain.enums.RequestState;
import com.arturces.springcourseapi.exception.NotFoundException;
import com.arturces.springcourseapi.repository.RequestRepository;
import com.arturces.springcourseapi.repository.RequestStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestStageService {

    @Autowired
    private RequestStageRepository requestStageRepository;

    @Autowired
    private RequestRepository requestRepository;

    public RequestStage save(RequestStage stage){
        stage.setRealizationDate(new Date());

        RequestStage createdStage = requestStageRepository.save(stage);

        Long requestId = stage.getRequest().getId();
        RequestState state = stage.getState();

        requestRepository.updateStatus(requestId, state);

        return createdStage;
    }

    public RequestStage getById(Long id){
        Optional<RequestStage> result = requestStageRepository.findById(id);
        return result.orElseThrow(()-> new NotFoundException("There are note user with id = " + id));

    }

    public List<RequestStage> listAllByRequestId(Long requestId){
        List<RequestStage> stages = requestStageRepository.findAllByRequestId(requestId);
        return stages;
    }
}
