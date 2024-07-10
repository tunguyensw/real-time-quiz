package com.elsaspeak.quiz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "title")
    String title;
    @Column(name = "question_type")
    String type;
    @Column(name = "status")
    String status;
    @Column(name = "meta")
    String meta;
}
