package tech.becoming.fileservice;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("file")
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        // Load file from database
        File file = fileService.get(fileId).orElseThrow(() -> new Exception("The file was not found"));

        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(file.getFileType()))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }

    @PostMapping(value = "upload", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile[] file) throws Exception {

        File stored = fileService.store(file[0]);

        return stored.id;

//        System.out.println(body.keySet());
//        System.out.println(body.values());
    }

}
