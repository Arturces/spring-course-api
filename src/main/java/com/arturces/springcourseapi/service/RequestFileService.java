package com.arturces.springcourseapi.service;

import com.arturces.springcourseapi.domain.Request;
import com.arturces.springcourseapi.domain.RequestFile;
import com.arturces.springcourseapi.model.PageModel;
import com.arturces.springcourseapi.model.PageRequestModel;
import com.arturces.springcourseapi.model.UploadFileModel;
import com.arturces.springcourseapi.repository.RequestFileRepository;
import com.arturces.springcourseapi.service.s3.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestFileService {

    @Autowired
    private RequestFileRepository fileRepository;

    @Autowired
    private S3Service s3Service;

    public List<RequestFile> upload(Long requestId, MultipartFile[] files) {
        List<UploadFileModel> uploadFiles = s3Service.upload(files);
        List<RequestFile> requestFiles = new ArrayList<RequestFile>();

        uploadFiles.forEach(uploadFile -> {
            RequestFile file = new RequestFile();
            file.setName(uploadFile.getName());
            file.setLocation(uploadFile.getLocation());

            Request request = new Request();
            request.setId(requestId);

            file.setRequest(request);
            requestFiles.add(file);
        });

        return fileRepository.saveAll(requestFiles);

    }

    public PageModel<RequestFile> listAllByRequestId(Long requestId, PageRequestModel prm) {
        Pageable pageable = prm.toSpringPageRequest();
        Page<RequestFile> page = fileRepository.findAllByRequestId(requestId, pageable);
        PageModel<RequestFile> pm = new PageModel<>((int) page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());

        return pm;

    }
}
