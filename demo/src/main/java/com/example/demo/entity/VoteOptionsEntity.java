package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vote_options")
public class VoteOptionsEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "activity_id", nullable = false)
    private Long activityId;

    @Column(name = "option_symbol", nullable = false)
    private String optionSymbol;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "vote_count", nullable = false)
    private Integer voteCount = 0;

}
