package tech.becoming.fileservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.becoming.fileservice.entity.File;
import tech.becoming.fileservice.repository.FileRepository;

@RestController
@RequestMapping("file")
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final FileRepository fileRepository;

    public FileController(FileRepository fileRepository) {this.fileRepository = fileRepository;}

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String createFile(@RequestBody File newFile) {

        final File savedFile = fileRepository.save(newFile);

        return savedFile.getId();
    }

    @PostMapping(
            value = "{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String uploadFile(@PathVariable String id,
                             @RequestParam("content") MultipartFile fileContent) throws Exception {

        final File file = fileRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Not found"));

        file.setData(fileContent.getBytes());

        fileRepository.save(file);

        return file.getId();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public File getFile(@PathVariable String id) throws Exception {
        final File file = fileRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Not found"));

        file.setData(null);

        return file;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> downloadFile(@PathVariable String id) throws Exception {
        final File file = fileRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Not found"));

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }

}
