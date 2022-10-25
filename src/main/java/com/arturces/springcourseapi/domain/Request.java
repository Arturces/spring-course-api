package com.arturces.springcourseapi.domain;

import com.arturces.springcourseapi.domain.enums.RequestState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "request")
public class Request implements Serializable {

    private static final long serialVersionUID = 3734470482855474717L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75, nullable = false)
    private String subject;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "creation_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(length = 12, nullable = false )
    @Enumerated(EnumType.STRING)
    private RequestState state;

    @ManyToOne
    @JoinColumn(name ="owner_id", nullable = false)
    private User owner;

    @JsonIgnore
    //@Getter(onMethod = @__({@JsonIgnore}))
    @OneToMany(mappedBy = "request")
    private List<RequestStage> stages = new ArrayList<RequestStage>();

}
