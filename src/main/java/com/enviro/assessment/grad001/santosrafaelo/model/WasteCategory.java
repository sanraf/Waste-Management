package com.enviro.assessment.grad001.santosrafaelo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class WasteCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DisposalGuideline> guidelines;

}
