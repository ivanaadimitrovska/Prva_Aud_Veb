package mk.ukim.finki.prva_aud_veb.web.restControllers;

import mk.ukim.finki.prva_aud_veb.model.Manufacturer;
import mk.ukim.finki.prva_aud_veb.service.implementation.ManufacturerImplementation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturer")
public class ManufacturerRestController {

    private final ManufacturerImplementation manufacturerImplementation;

    public ManufacturerRestController(ManufacturerImplementation manufacturerImplementation) {
        this.manufacturerImplementation = manufacturerImplementation;
    }

    @GetMapping
    public List<Manufacturer> findAll(){
        return manufacturerImplementation.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> vratipoId(@PathVariable Long id){
        return manufacturerImplementation.findById(id).map(r -> ResponseEntity.ok().body(r)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Manufacturer> save(@RequestParam String adresaNaProizvoditel, @RequestParam String imeNaproizvoditel){
        return manufacturerImplementation.save(adresaNaProizvoditel,imeNaproizvoditel).map(r->ResponseEntity.ok().body(r)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        this.manufacturerImplementation.deleteById(id);
        if(manufacturerImplementation.findById(id).isEmpty()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
