package pl.edu.wat.groomer_queue.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.groomer_queue.dto.DogRequest;
import pl.edu.wat.groomer_queue.dto.DogResponse;
import pl.edu.wat.groomer_queue.dto.OwnerResponse;
import pl.edu.wat.groomer_queue.exception.EntityNotFound;
import pl.edu.wat.groomer_queue.service.DogService;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/api/dog")
public class DogController {
    private final DogService dogService;

    @Autowired
    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping()
    public ResponseEntity<List<DogResponse>> getAllDog() {
        List<DogResponse> ownerOptional = dogService.getAll();
        return new ResponseEntity<>(ownerOptional, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createDog(@RequestBody DogRequest ownerRequest) {
        try {
            return new ResponseEntity<>(dogService.save(ownerRequest).getId(), HttpStatus.CREATED);
        } catch (EntityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<DogResponse> getDogByIdVar(@PathVariable String id) throws EntityNotFound {
        Optional<DogResponse> ownerOptional = Optional.ofNullable(dogService.getDogById(id));
        if (ownerOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ownerOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable String id) {
        try {
            dogService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<DogResponse> updateDog(@PathVariable String id, @RequestBody DogRequest dogRequest) throws EntityNotFound {
        Optional<DogResponse> updatedDog = Optional.ofNullable(dogService.update(id, dogRequest));
        if (updatedDog.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedDog.get(), HttpStatus.OK);
    }
}
