package com.ten.ten.entities;

import com.ten.ten.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="roles")

public class RoleEntity extends BaseEntity {

    @Column()
    private String nameRole;

    @Column()
    private String description;
}
