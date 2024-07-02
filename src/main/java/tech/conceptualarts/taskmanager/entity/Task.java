package tech.conceptualarts.taskmanager.entity;

import jakarta.validation.constraints.NotNull;
import tech.conceptualarts.taskmanager.enums.Urgency;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task extends TrackableObject{
    @Id
    private String id = UUID.randomUUID().toString();

    private LocalDateTime deadline;

    @NotNull
    private Urgency urgency = Urgency.NO_PRIORITY;

    @NotBlank
    @Length(max = 150)
    private String title;

    @Length(max = 500)
    private String body;

    @ManyToOne
    @Column(columnDefinition = "varchar(36)", name = "project_id")
    private Project project;
}
