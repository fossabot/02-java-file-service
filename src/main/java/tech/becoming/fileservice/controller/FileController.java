package tech.becoming.fileservice.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.becoming.fileservice.entity.File;
import tech.becoming.fileservice.repository.FileRepository;
import tech.becoming.fileservice.service.FileService;

@RestController
@RequestMapping("file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String createFile(@RequestBody File newFile) throws Exception {

        return fileService.save(newFile).getId();
    }

    @PostMapping(
            value = "{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String uploadFile(@PathVariable String id,
                             @RequestParam("content") MultipartFile fileContent) throws Exception {

        return fileService.setData(id, fileContent.getBytes()).getId();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public File getFile(@PathVariable String id) throws Exception {
        return fileService.findById(id);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> downloadFile(@PathVariable String id) throws Exception {
        final File file = fileService.findById(id);

        return createDownloadResponse(file);
    }

    private ResponseEntity<Resource> createDownloadResponse(File file) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }

}
