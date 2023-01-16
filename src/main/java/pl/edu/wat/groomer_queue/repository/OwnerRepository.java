package pl.edu.wat.groomer_queue.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.groomer_queue.entity.Owner;

@Repository
public interface OwnerRepository extends MongoRepository<Owner, String> {
}
