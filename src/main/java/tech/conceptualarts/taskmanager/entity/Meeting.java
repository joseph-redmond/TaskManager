package tech.conceptualarts.taskmanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Meeting extends TrackableObject {

    @Id
    private String id = UUID.randomUUID().toString();

    private LocalDateTime deadline;

    @NotBlank
    @Length(max = 30)
    private String urgency;

    @NotBlank
    @Length(max = 150)
    private String title;

    @Length(max = 500)
    private String description;
}
