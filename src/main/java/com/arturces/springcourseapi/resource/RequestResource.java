package com.arturces.springcourseapi.resource;

import com.arturces.springcourseapi.domain.Request;
import com.arturces.springcourseapi.domain.RequestStage;
import com.arturces.springcourseapi.service.RequestService;
import com.arturces.springcourseapi.service.RequestStageService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "requests")
public class RequestResource {

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestStageService stageService;

    @PostMapping
    public ResponseEntity<Request> save(@RequestBody Request request) {
        Request createdRequest = requestService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Request> update(@PathVariable(name = "id") Long id, @RequestBody Request request) {
        request.setId(id);

        Request updateRequest = requestService.udpdate(request);
        return ResponseEntity.ok(updateRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getById(@PathVariable(name = "id") Long id) {
        Request request = requestService.getById(id);
        return ResponseEntity.ok(request);
    }

    @GetMapping
    public ResponseEntity<List<Request>> listAll() {
        List<Request> requests = requestService.listAll();
        return ResponseEntity.ok(requests);
    }

    //http://localhost:8080/request/1/request-stages  - buscar estagios de pedidos de um pedido especifico
    @GetMapping("/{id}/request-stages")
    public ResponseEntity<List<RequestStage>> listAllRequestStagesById(@PathVariable(name = "id") Long id) {
        List<RequestStage> stages = stageService.listAllByRequestId(id);
        return ResponseEntity.ok(stages);
    }

}
