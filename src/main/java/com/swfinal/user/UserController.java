package com.swfinal.user;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class UserController {

    private final UserService userService;

    // 생성자 방식으로 주입
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/detail/{seq}")
    public String detail(
            @PathVariable("seq") int seq,
            Model model
    ) {
        Map<String, Object> result = userService.detail(seq);
        model.addAttribute("result", result);
        return "product/detail";
    }
}
