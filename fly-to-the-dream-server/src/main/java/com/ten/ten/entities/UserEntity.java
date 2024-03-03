package com.ten.ten.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ten.ten.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column()
    private String username;

    @Column()
    private String password;

    @Column()
    private String email;

    @Column()
    private Boolean gender;

    @Column()
    private String phoneNumber;

    @Column()
    private Instant dateOfBirth;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<RoleEntity> roleEntities = new HashSet<>();

    public void setAllUserEntity(String username, String password, String email, Boolean gender, String phoneNumber, Instant dateOfBirth) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }
}
