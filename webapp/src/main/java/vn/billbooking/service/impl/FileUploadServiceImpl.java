package vn.billbooking.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.billbooking.model.dto.FileDTO;
import vn.billbooking.service.FileUploadService;
import vn.billbooking.utils.ContantUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public FileDTO uploadFile(MultipartFile multipartFile) {
        try {
            UUID uuid = UUID.randomUUID();
            String[] fileFrags = multipartFile.getOriginalFilename().split("\\.");
            String extension = fileFrags[fileFrags.length-1];
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

            String uploadDir = ContantUtil.PATH_FILE_UPLOAD;
            fileName = uuid.toString() + "." + extension;

            boolean success = saveFile(uploadDir, fileName, multipartFile);

            FileDTO fileDTO = null;
            if (success) {
                fileDTO = new FileDTO();
                fileDTO.setId(uuid.toString());
                fileDTO.setName(multipartFile.getOriginalFilename());
                fileDTO.setPath(ContantUtil.PATH_URL_UPLOAD + fileName);
                fileDTO.setSize(multipartFile.getSize());
            }

            return fileDTO;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<FileDTO> uploadMutilFile(MultipartFile[] multipartFiles) {
        if (multipartFiles == null) {
            return null;
        }

        List<FileDTO> fileDTOList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty() && multipartFile.getOriginalFilename() != null
                    && !multipartFile.getOriginalFilename().equalsIgnoreCase("")) {
                FileDTO fileDTO = uploadFile(multipartFile);
                if (fileDTO != null) {
                    fileDTOList.add(fileDTO);
                }
            }
        }
        return fileDTOList;
    }

    @Override
    public FileDTO downloadFile(String filePath) {
        return null;
    }

    private boolean saveFile(String uploadDir, String fileName, MultipartFile multipartFile) {
        try {
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                return true;
            } catch (IOException ioe) {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
