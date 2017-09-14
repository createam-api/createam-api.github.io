package com.createam.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by lukasz@create.am on 13/08/2017.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Table(name = "privileges")
public class Privilege {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}
