package com.arturces.springcourseapi.domain;

import com.arturces.springcourseapi.domain.enums.RequestState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity(name = "request_stage")
public class RequestStage implements Serializable {
    @Serial
    private static final long serialVersionUID = 4307480004224458879L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "realization_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date realizationDate;

    @Column(length = 12, nullable = false )
    @Enumerated(EnumType.STRING)
    private RequestState state;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;


    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

}

