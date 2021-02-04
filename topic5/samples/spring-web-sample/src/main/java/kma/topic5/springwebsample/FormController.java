package kma.topic5.springwebsample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FormController {

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String formControllerGet() {
        return "form-controller-get";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String formControllerPost(@ModelAttribute FormModel formModel, Model model) {
        model.addAttribute("formModel", formModel);
        return "form-controller-post";
    }

}
