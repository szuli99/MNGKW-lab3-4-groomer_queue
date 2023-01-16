package pl.edu.wat.groomer_queue.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
public class Dog {
    @MongoId
    private String id;
    private String name;
    private String breed;
    private String ownerId;

}
