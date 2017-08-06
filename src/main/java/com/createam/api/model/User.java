package com.createam.api.model;

import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private Long id;

    @Email
    private String email;

    private Date registered;
}
