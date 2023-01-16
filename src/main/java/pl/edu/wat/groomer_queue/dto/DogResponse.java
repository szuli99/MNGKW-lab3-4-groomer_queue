package pl.edu.wat.groomer_queue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DogResponse {
    private String id;
    private String name;
    private String breed;
    private OwnerResponse owner;
}
