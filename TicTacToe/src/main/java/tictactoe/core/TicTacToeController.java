package tictactoe.core;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TicTacToeController {

    @GetMapping("/tictactoe")
    public String tictactoe(Model model) {
        model.addAttribute("message", "틱택토 테스트 메시지");
        return "hello";
    }
}
