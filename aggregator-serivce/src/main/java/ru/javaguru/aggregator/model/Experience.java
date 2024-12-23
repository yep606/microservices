package ru.javaguru.aggregator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

@Entity
@Table(name = "experiences")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer sequenceNumber;

    @Column(nullable = false)
    private YearMonth periodFrom;

    private YearMonth periodTo;

    private Boolean presentTime;

    @Column(nullable = false)
    private Long industryId;

    @Column(nullable = false)
    private String industryName;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String position;

    @Column(length = 1000)
    private String achievements;

    private String link;
}

