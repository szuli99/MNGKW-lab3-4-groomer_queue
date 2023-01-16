package pl.edu.wat.groomer_queue.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.groomer_queue.service.ScriptService;


@RestController
@CrossOrigin
@RequestMapping("/api/script")
public class ScriptController {
    private final ScriptService scriptService;

    @Autowired
    public ScriptController(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @PutMapping()
    public ResponseEntity<String> execScript() {
        //@RequestBody String script
        String script="""
                var Dog = Java.type('pl.edu.wat.groomer_queue.entity.Dog');
                var Set = Java.type('java.util.Set');
                function lowerToUpper(){
        
                for(dog of dogRepository.findAll()){            
                var dogBreed = dog.getBreed();
                var breedUpper = dogBreed.toUpperCase();
                dog.setBreed(breedUpper);
                dogRepository.save(dog);
                }
                return dogRepository.findAll();
                }
                lowerToUpper();
                """;

        String script2="""
                var Owner = Java.type('pl.edu.wat.groomer_queue.entity.Owner');
                var Set = Java.type('java.util.Set');
                function setAge(){
        
                for(owner of ownerRepository.findAll()){            
                var age = Math.floor(Math.random() * (40 - 18 + 1)) + 18;
                owner.setAge(age);
                ownerRepository.save(owner);
                }
                return ownerRepository.findAll();
                }
                setAge();
                """;

        return new ResponseEntity<>(scriptService.exec(script2), HttpStatus.OK) ;
    }
}
