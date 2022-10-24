package com.arturces.springcourseapi.resource;

import com.arturces.springcourseapi.domain.Request;
import com.arturces.springcourseapi.domain.RequestStage;
import com.arturces.springcourseapi.domain.User;
import com.arturces.springcourseapi.model.PageModel;
import com.arturces.springcourseapi.model.PageRequestModel;
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
    public ResponseEntity<PageModel<Request>> listAll(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        PageRequestModel pr = new PageRequestModel(page, size);
        PageModel<Request> pm = requestService.listAllOnLazyMode(pr);

        return ResponseEntity.ok(pm);
    }

    //http://localhost:8080/request/1/request-stages  - buscar estagios de pedidos de um pedido especifico
    @GetMapping("/{id}/request-stages")
    public ResponseEntity<PageModel<RequestStage>> listAllRequestStagesById(
            @PathVariable(name = "id") Long id,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size) {
        PageRequestModel pr = new PageRequestModel(page, size);

        PageModel<RequestStage> pm = stageService.listAllByRequestIdOnLazyModel(id, pr);

        return ResponseEntity.ok(pm);
    }

}
