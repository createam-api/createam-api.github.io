package com.createam.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created by lukasz@create.am on 07/08/2017.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(of = "id")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Email
    private String email;

    @JsonIgnore
    private String password;

    private boolean enabled;

    private boolean tokenExpired;


    private Date registered;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
}
