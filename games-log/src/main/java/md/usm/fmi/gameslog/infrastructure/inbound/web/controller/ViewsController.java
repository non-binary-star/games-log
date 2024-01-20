package md.usm.fmi.gameslog.infrastructure.inbound.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewsController {

    @GetMapping(value = {
            "/user/{user_id}/gameslist",
            "/user/{user_id}/gameslist/{filter}"})
    public String index() {
        return "forward:/";
    }
}
