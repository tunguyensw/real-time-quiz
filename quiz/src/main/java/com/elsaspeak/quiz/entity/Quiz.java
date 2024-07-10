package com.elsaspeak.quiz.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "title")
    String title;
    @Column(name = "quiz_no")
    String quizNo;
    @Column(name = "quiz_type")
    String type;
    @Column(name = "status")
    String status;
    @Column(name = "meta")
    String meta;
    @Column(name = "created_date")
    LocalDateTime createdDate;
    @Column(name = "updated_date")
    LocalDateTime updatedDate;
    @Column(name = "created_by")
    String createdBy;
}
