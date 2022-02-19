package clone.colley.controller;

import clone.colley.dto.Response.MainResponseDto;
import clone.colley.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainPageController {

    private final MainService mainService;

    @Autowired
    public MainPageController(MainService mainService) {
        this.mainService = mainService;
    }

    //시간순 정렬 <디폴트>
    @GetMapping("/")
    public List<MainResponseDto> getAllPageTimed() {
        return mainService.getAllPageTimed();
    }

    //좋아요순 정렬
    @GetMapping("/likes")
    public List<MainResponseDto> getAllPageLiked() {
        return mainService.getAllPageLiked();
    }

}
