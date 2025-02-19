package com.arun.ytclone.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service implements FileService{

    @Autowired
    private AmazonS3 amazonS3;

    @Value("project.ytclone")
    private static String s3BucketName;

    @Override
    public String uploadFile(MultipartFile file) {

        String fileNameExt = StringUtils.getFilenameExtension(file.getOriginalFilename());

        String key = UUID.randomUUID() + "." + fileNameExt;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest("project.ytclone", key, file.getInputStream(), metadata);
            amazonS3.putObject(putObjectRequest);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error occured while uploading the file to s3");
        }

        amazonS3.setObjectAcl("project.ytclone", key, CannedAccessControlList.PublicRead);

        return amazonS3.getUrl("project.ytclone", key).toString();
    }
}
