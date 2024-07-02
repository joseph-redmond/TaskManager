package tech.conceptualarts.taskmanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Project {
    @Id
    private String id = UUID.randomUUID().toString();

    @NotBlank
    @Length(min = 1, max = 150)
    private String title;

    @Length(max = 500)
    private String description;
}
