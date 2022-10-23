package com.arturces.springcourseapi.service;

import com.arturces.springcourseapi.domain.Request;
import com.arturces.springcourseapi.domain.enums.RequestState;
import com.arturces.springcourseapi.exception.NotFoundException;
import com.arturces.springcourseapi.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public Request save(Request request) {
        request.setState(RequestState.OPEN);
        request.setCreationDate(new Date());

        Request createdRequest = requestRepository.save(request);
        return createdRequest;
    }

    public Request udpdate(Request request) {
        Request updateRequest = requestRepository.save(request);
        return updateRequest;
    }

    public Request getById(Long id) {
        Optional<Request> result = requestRepository.findById(id);
        return result.orElseThrow(() -> new NotFoundException("There are note user with id = " + id));

    }

    public List<Request> listAll() {
        List<Request> requests = requestRepository.findAll();
        return requests;
    }

    public List<Request> listAllByOwnerId(Long ownerId) {
        List<Request> requests = requestRepository.findAllByOwnerId(ownerId);
        return requests;
    }


}
