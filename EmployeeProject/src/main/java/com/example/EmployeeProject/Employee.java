package com.example.EmployeeProject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "ETable")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer EmpID;

    @Column(name = "emp_name")
    @JsonProperty("empName") // Maps JSON "empName" to Java "EmpName"
    private String EmpName;

    @Column(name = "band")
    @JsonProperty("band")
    private String Band;

    @Column(name = "salary")
    @JsonProperty("salary")
    private Integer salary;

    @Column(name = "experience")
    @JsonProperty("experience")
    private Integer Experience;
}
