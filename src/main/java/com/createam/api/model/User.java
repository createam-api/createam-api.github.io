package com.createam.api.model;

import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lukasz@create.am on 07/08/2017.
 */
@Entity
@NoArgsConstructor
@Table(name = "users")
@Getter @Setter
@ToString
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Email
    private String email;

    private Date registered;
}
