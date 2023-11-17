package watch.store.smart_web.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test")
public class testController {

    public String test() {

        return "123";
    }
}
