package tech.becoming.fileservice.service;

import org.springframework.stereotype.Service;
import tech.becoming.fileservice.entity.File;
import tech.becoming.fileservice.repository.FileRepository;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {this.fileRepository = fileRepository;}



    public File setData(String id, byte[] bytes) throws Exception {
        final File file = fileRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Not found"));

        file.setData(bytes);

        return fileRepository.save(file);
    }

    public File findById(String id) throws Exception {
        final File file = fileRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Not found"));

        file.setData(null);

        return file;
    }

    public File save(File newFile) throws Exception {
        if(isNewFileValid(newFile)) {
            return fileRepository.save(newFile);
        }

        throw new Exception("The file information is not valid");
    }

    private boolean isNewFileValid(File newFile) {
        final String fileName = newFile.getFileName();

        return fileName != null && fileName.length() > 0;

    }
}
