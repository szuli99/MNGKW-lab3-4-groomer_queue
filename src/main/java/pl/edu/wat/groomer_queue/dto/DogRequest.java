package pl.edu.wat.groomer_queue.dto;

import lombok.Data;

@Data
public class DogRequest {
    private String name;
    private String breed;
    private String ownerId;
}
