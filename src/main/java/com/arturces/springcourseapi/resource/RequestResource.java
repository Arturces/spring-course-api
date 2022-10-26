package com.arturces.springcourseapi.resource;

import com.arturces.springcourseapi.domain.Request;
import com.arturces.springcourseapi.domain.RequestFile;
import com.arturces.springcourseapi.domain.RequestStage;
import com.arturces.springcourseapi.dto.RequestSaveDto;
import com.arturces.springcourseapi.dto.RequestUpdateDto;
import com.arturces.springcourseapi.model.PageModel;
import com.arturces.springcourseapi.model.PageRequestModel;
import com.arturces.springcourseapi.service.RequestFileService;
import com.arturces.springcourseapi.service.RequestService;
import com.arturces.springcourseapi.service.RequestStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "requests")
public class RequestResource {

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestStageService stageService;

    @Autowired
    private RequestFileService fileService;


    @PostMapping
    public ResponseEntity<Request> save(@RequestBody @Valid RequestSaveDto requestDto) {
        Request request = requestDto.transformToRequest();

        Request createdRequest = requestService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }

    @PreAuthorize("@accessManager.isRequestOwner(#id)")
    @PutMapping("/{id}")
    public ResponseEntity<Request> update(@PathVariable(name = "id") Long id, @RequestBody @Valid RequestUpdateDto requestDto) {
       Request request = requestDto.transformToRequest();
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
    public ResponseEntity<PageModel<Request>> listAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        PageRequestModel pr = new PageRequestModel(page, size);
        PageModel<Request> pm = requestService.listAllOnLazyMode(pr);

        return ResponseEntity.ok(pm);
    }

    //http://localhost:8080/request/1/request-stages  - buscar estagios de pedidos de um pedido especifico
    @GetMapping("/{id}/request-stages")
    public ResponseEntity<PageModel<RequestStage>> listAllRequestStagesById(
            @PathVariable(name = "id") Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "10") int size) {

        PageRequestModel pr = new PageRequestModel(page, size);
        PageModel<RequestStage> pm = stageService.listAllByRequestIdOnLazyModel(id, pr);

        return ResponseEntity.ok(pm);
    }

    @GetMapping("/{id}/files")
    public ResponseEntity<PageModel<RequestFile>> listAllFilesById(
            @PathVariable(name = "id") Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "10") int size) {

        PageRequestModel pr = new PageRequestModel(page, size);
        PageModel<RequestFile> pm = fileService.listAllByRequestId(id,pr);

        return ResponseEntity.ok(pm);
    }
        //https://valor.com/?par1=2&par2=3
    @PostMapping("/{id}/files")
    public ResponseEntity<List<RequestFile>> upload(@RequestParam("files")MultipartFile[] files, @PathVariable(name = "id") Long id ) {
           List<RequestFile> requestFiles = fileService.upload(id,files);

           return ResponseEntity.status(HttpStatus.CREATED).body(requestFiles);
    }



}
