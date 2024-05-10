package vn.billbooking.service;

import org.springframework.web.multipart.MultipartFile;
import vn.billbooking.model.dto.FileDTO;

import java.io.InputStream;
import java.util.List;

public interface FileUploadService {

    FileDTO uploadFile(MultipartFile multipartFile);

    List<FileDTO> uploadMutilFile(MultipartFile[] multipartFiles);

    FileDTO downloadFile(String filePath);

}
