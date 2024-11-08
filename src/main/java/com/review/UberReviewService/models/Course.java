package com.review.UberReviewService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//for Practice
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course extends BaseModel{
    //id is coming from BaseModel
    private String name;
    private String roolNo;

    //mention type of assocaition

    @ManyToMany(mappedBy = "courses")
    private List<Student> students=new ArrayList<>();
}
