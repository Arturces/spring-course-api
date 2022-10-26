package com.arturces.springcourseapi.service.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.arturces.springcourseapi.model.UploadFileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class S3Service {

    private AmazonS3 s3;
    private String bucketName;
    private String region;

    @Autowired
    public S3Service(AmazonS3 amazonS3, String awsRegion, String awsBucket) {
        this.s3 = amazonS3;
        this.bucketName = awsBucket;
        this.region = awsRegion;
    }

    public List<UploadFileModel> upload(MultipartFile[] files) {

        List<UploadFileModel> uploadFileModelList = new ArrayList<UploadFileModel>();

        for (MultipartFile file : files) {
            String originalName = file.getOriginalFilename();
            String s3FileName = getUniqueFileName(originalName);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());

            try {
                PutObjectRequest request = new PutObjectRequest(bucketName, s3FileName, file.getInputStream(), metadata).withCannedAcl(CannedAccessControlList.PublicRead);
                s3.putObject(request);

                String location = getFileLocation(s3FileName);

                UploadFileModel uploadFileModel = new UploadFileModel(originalName, location);
                uploadFileModelList.add(uploadFileModel);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        return uploadFileModelList;
    }

    private String getFileLocation(String fileName) {
        return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + fileName;
    }

    private String getUniqueFileName(String fileName){
        return UUID.randomUUID().toString() + "_" + fileName;
    }

}
