package pl.edu.wat.groomer_queue.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.groomer_queue.dto.DogRequest;
import pl.edu.wat.groomer_queue.dto.DogResponse;
import pl.edu.wat.groomer_queue.dto.OwnerResponse;
import pl.edu.wat.groomer_queue.entity.Dog;
import pl.edu.wat.groomer_queue.entity.Owner;
import pl.edu.wat.groomer_queue.exception.EntityNotFound;
import pl.edu.wat.groomer_queue.repository.DogRepository;
import pl.edu.wat.groomer_queue.repository.OwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DogService {
    private final DogRepository dogRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public DogService(DogRepository dogRepository , OwnerRepository ownerRepository) {
        this.dogRepository = dogRepository;
        this.ownerRepository = ownerRepository;
    }

    public DogResponse getDogById(String id) throws EntityNotFound {
        Dog dog = dogRepository.findById(id).orElseThrow(EntityNotFound::new);

        Owner owner = ownerRepository.findById(dog.getOwnerId()).orElseThrow(EntityNotFound::new);

        return new DogResponse(
                dog.getId(),
                dog.getName(),
                dog.getBreed(),
                new OwnerResponse(owner.getId(), owner.getName(), owner.getSurname()));
    }

    public DogResponse save(DogRequest dogRequest) throws EntityNotFound {
        Dog dog = new Dog();
        dog.setName(dogRequest.getName());
        dog.setBreed(dogRequest.getBreed());
        Owner owner = ownerRepository.findById(dogRequest.getOwnerId())
                .orElseThrow(EntityNotFound::new);

        dog.setOwnerId(owner.getId());
        dog = dogRepository.save(
                dog
        );
        System.out.println(dog);
        return new DogResponse(
                dog.getId(),
                dog.getName(),
                dog.getBreed(),
                new OwnerResponse(owner.getId(), owner.getName(), owner.getSurname()));
    }

    public List<DogResponse> getAll() {

        return dogRepository.findAll()
                .stream()
                .map(this::toDogResponse)
                .flatMap(Optional::stream)
                .toList();
    }

    private Optional<DogResponse> toDogResponse(Dog dog) {
        try {
            Owner owner = ownerRepository.findById(dog.getOwnerId()).orElseThrow(EntityNotFound::new);
            return Optional.of(
                    new DogResponse(dog.getId(), dog.getName(), dog.getBreed(), new OwnerResponse(owner.getId(), owner.getName(), owner.getSurname()))
            );
        } catch (EntityNotFound e) {
            return Optional.empty();
        }
    }

    public void delete(String id) throws EntityNotFound {
        if(!dogRepository.existsById(id)) {
            throw new EntityNotFound();
        }
        dogRepository.deleteById(id);
    }


    public DogResponse update(String id, DogRequest dogRequest) throws EntityNotFound {
        Optional<Dog> dogOptional = dogRepository.findById(id);
        if (dogOptional.isEmpty()) {
            throw new EntityNotFound();
        }
        Dog dog = dogOptional.get();
        dog.setName(dogRequest.getName());
        dog.setBreed(dogRequest.getBreed());
        Owner owner = ownerRepository.findById(dogRequest.getOwnerId())
                .orElseThrow(EntityNotFound::new);

        dog.setOwnerId(owner.getId());
        dog = dogRepository.save(dog);
        return new DogResponse(dog.getId(),dog.getName(), dog.getBreed(), new OwnerResponse(owner.getId(),owner.getName(),owner.getSurname()));
    }
}
