package com.arturces.springcourseapi.domain;

import com.arturces.springcourseapi.domain.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = -5419288627551889644L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75, nullable = false)
    private String name;

    @Column(length = 75, nullable = false, unique = true)
    private String email;

    @Getter(onMethod = @__({@JsonIgnore}))//ignorar password na serialização
    @Setter(onMethod = @__({@JsonProperty})) //durante a deserialização consideramos o password
    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 20, nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Getter(onMethod = @__({@JsonIgnore}))
    @Setter(onMethod = @__({@JsonProperty}))
    @OneToMany(mappedBy = "owner")
    private List<Request> requests = new ArrayList<Request>();

    @Getter(onMethod = @__({@JsonIgnore}))
    @OneToMany(mappedBy = "owner")
    private List<RequestStage> stages = new ArrayList<RequestStage>();
}
