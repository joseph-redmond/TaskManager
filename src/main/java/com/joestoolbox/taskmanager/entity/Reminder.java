package com.joestoolbox.taskmanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reminder extends TrackableObject {
    @Id
    private String id = UUID.randomUUID().toString();

    @NotBlank
    @Length(max = 30)
    private String type;

    @NotNull
    private LocalDateTime deadline;

    @NotNull
    private Boolean complete;

    @NotBlank
    @Length(max = 250)
    private String body;
}
