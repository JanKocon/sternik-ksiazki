package pl.sternik.jk.weekend.web.controlers.th;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.sternik.jk.weekend.entities.Ksiazka;
import pl.sternik.jk.weekend.entities.Stan;
import pl.sternik.jk.weekend.repositories.KsiazkaUtils;
import pl.sternik.jk.weekend.services.KlaserService;
import pl.sternik.jk.weekend.services.NotificationService;
import pl.sternik.jk.weekend.services.NotificationService.NotificationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class KsiazkaController {
    @Autowired
    private Logger logger;

    @Autowired
    // @Qualifier("spring-data")
    @Qualifier("lista")
    // @Qualifier("lista")
    private KlaserService klaserService;

    @Autowired
    private NotificationService notifyService;

    @ModelAttribute("statusyAll")
    public List<Stan> populateStatusy() {
        return Arrays.asList(Stan.ALL);
    }
    
    @ModelAttribute("MyMessages")
    public List<NotificationMessage> populateMessages() {
        logger.info("Messagesy dej");
        return notifyService.getNotificationMessages();
    }
    

    @GetMapping(value = "/ksiazki/{id}")
    public String view(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Ksiazka> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
            Ksiazka ksiazka = result.get();
            model.addAttribute("ksiazka", ksiazka);
            return "th/ksiazka";
        } else {
            notifyService.addErrorMessage("Cannot find ksiazka #" + id);
            model.clear();
            return "redirect:/ksiazki";
        }
    }

    @RequestMapping(value = "/ksiazki/{id}/json", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Ksiazka> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Ksiazka> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
            Ksiazka ksiazka = result.get();
            return new ResponseEntity<Ksiazka>(ksiazka, HttpStatus.OK);
        } else {
            notifyService.addErrorMessage("Cannot find ksiazka #" + id);
            model.clear();
            return new ResponseEntity<Ksiazka>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/ksiazki", params = { "save" }, method = RequestMethod.POST)
    public String saveKsiazka(Ksiazka ksiazka, BindingResult bindingResult, ModelMap model) {
        // @Valid
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Wypełnij pola poprawnie");
            model.addAttribute("MyMessages",  notifyService.getNotificationMessages());
            return "th/ksiazka";
        }
        
        if (ksiazka.getStatus() == Stan.NOWA) {
            ksiazka.setStatus(Stan.UZYWANA);
        }
        
        Optional<Ksiazka> result = klaserService.edit(ksiazka);
        if (result.isPresent())
            notifyService.addInfoMessage("Zapis udany");
        else
            notifyService.addErrorMessage("Zapis NIE udany");
        model.clear();
        return "redirect:/ksiazki";
    }

    @RequestMapping(value = "/ksiazki", params = { "create" }, method = RequestMethod.POST)
    public String createKsiazka(Ksiazka ksiazka, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            logger.info("Create książka errors");
            notifyService.addErrorMessage("Wypełnij pola poprawnie");
            model.addAttribute("MyMessages",  notifyService.getNotificationMessages());
            return "th/ksiazka";
        }
        klaserService.create(ksiazka);
        model.clear();
        notifyService.addInfoMessage("Zapis książki udany");
        return "redirect:/ksiazki";
    }

    @RequestMapping(value = "/ksiazki", params = { "remove" }, method = RequestMethod.POST)
    public String removeRow(final Ksiazka ksiazka, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("remove"));
        Optional<Boolean> result = klaserService.deleteById(rowId.longValue());
        return "redirect:/ksiazki";
    }

    @RequestMapping(value = "/ksiazki/create", method = RequestMethod.GET)
    public String showMainPages(final Ksiazka ksiazka) {
        // Ustawiamy date nowej ksiazki, na dole strony do dodania
        ksiazka.setDataWydania(Calendar.getInstance().getTime());
        return "th/ksiazka";
    }
}