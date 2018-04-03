package pl.sternik.jk.weekend.web.controlers.th;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.sternik.jk.weekend.entities.Ksiazka;
import pl.sternik.jk.weekend.services.KlaserService;
import pl.sternik.jk.weekend.services.NotificationService;


@Controller
public class KlaserController {

    @Autowired
//    @Qualifier("spring-data")
    @Qualifier("lista")
//    @Qualifier("lista")
    private KlaserService klaserService;

    @Autowired
    private NotificationService notificationService;

//    @ModelAttribute("statusyAll")
//    public List<Status> populateStatusy() {
//        return Arrays.asList(Status.ALL);
//    }

    @ModelAttribute("booksAll")
    public List<Ksiazka> populateBooks() {
        return this.klaserService.findAll();
    }

    @ModelAttribute("booksToSell")
    public List<Ksiazka> populateBooksToSell() {
        return this.klaserService.findAllToSell();
    }
    @ModelAttribute("booksDuplicate")
    public List<Ksiazka> populateBooksDuplicate() {
        return this.klaserService.findAllduplicate();
    }


//    @ModelAttribute("coinsLast3")
//    public List<Ksiazka> populateLast3Coins() {
//        return this.klaserService.findLatest3();
//    }

    @RequestMapping({ "/", "/index" })
    public String index(Model model) {
        return "th/index";
    }

    @RequestMapping(value = "/ksiazki", method = RequestMethod.GET)
    public String showMainPage(Model model) {
        model.addAttribute("MyMessages",  notificationService.getNotificationMessages());
        return "th/klaser";
    }

    @RequestMapping("/tosell")
    public String showToSellPage() {
        return "th/tosell";
    }

    @RequestMapping("/duplikat")
    public String showDuplikatPage() {
        return "th/duplikat";
    }

}
