package kyoongdev.kyoongdevspring.modules.file;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "파일", description = "파일 업로드")
@RestController
@RequestMapping("/files")
public class FileController {

  @Operation(summary = "파일 업로드", description = "파일 업로드입니다.")
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  void uploadFile(@RequestPart("file") MultipartFile file) {
    System.out.println(file.getOriginalFilename());
  }
}
