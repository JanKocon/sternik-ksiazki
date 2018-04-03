package pl.sternik.jk.weekend.web.controlers.th;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.sternik.jk.weekend.entities.Ksiazka;
import pl.sternik.jk.weekend.entities.Stan;
import pl.sternik.jk.weekend.repositories.KsiazkaAlreadyExistsException;
import pl.sternik.jk.weekend.repositories.KsiazkaRepository;
import pl.sternik.jk.weekend.repositories.NoSuchKsiazkaException;



@Controller
public class WprawkiControllerTh {

    @Autowired
    @Qualifier("lista")
    KsiazkaRepository baza;
    
    @RequestMapping(path = "/wprawki-th", method = RequestMethod.GET)
    public String wprawki(ModelMap model) {
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        return "th/wprawki";
    }

    @GetMapping("/wprawki-th/{cos}")
    public String wprawki(@PathVariable String cos, ModelMap model) {
        model.addAttribute("cos", cos);
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        return "th/wprawki";
    }

    @GetMapping("/wprawki-th2")
    @ResponseBody
    public String wprawkiParam(@RequestParam("cos") String cosParam, ModelMap model) {
        return "Wprawki z param cos=" + cosParam;
    }
    
    @GetMapping("/wprawki-th3")
    @ResponseBody
    public String wprawkiHeader(@RequestHeader("User-Agent") String cosParam, ModelMap model) {
        return "Uzywasz przegladarki:=" + cosParam;
    }
    
    @GetMapping(value = "/wprawki-th/ksiazki/{id}/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Ksiazka> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Ksiazka m;
        try {
            m = baza.readById(id);
            return new ResponseEntity<Ksiazka>(m, HttpStatus.OK);
            
        } catch (NoSuchKsiazkaException e) {
            System.out.println(e.getClass().getName());
            m = new Ksiazka();
            m.setNumerKatalogowy(id);
            m.setGatunek("Horror");
            m.setStatus(Stan.NOWA);
            m.setTytul("Sternik");
            try {
                baza.create(m);
            } catch (KsiazkaAlreadyExistsException e1) {
                System.out.println(e1.getClass().getName());
            }
            return new ResponseEntity<Ksiazka>(m, HttpStatus.CREATED);
        }
    }

}
