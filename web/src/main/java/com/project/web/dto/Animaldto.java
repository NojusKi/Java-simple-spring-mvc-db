package com.project.web.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@Builder
public class Animaldto {

    private Long id;
    @NotEmpty (message= "Animal name should not be empty")
    private String name;
    @NotEmpty (message= "Animal gender should not be empty")
    private String gender;
    @NotEmpty (message= "Animal species should not be empty")
    private String species;
    @NotNull(message = "Animal age should not be null")
    @Min(value = 1, message = "Animal age should be at least 1")
    private Integer age; // Use Integer to allow null validation

}
