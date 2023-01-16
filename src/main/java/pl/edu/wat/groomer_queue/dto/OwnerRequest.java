package pl.edu.wat.groomer_queue.dto;

import lombok.Data;

@Data
public class OwnerRequest {
    private String surname;
    private String name;
    private String telephone;
}
