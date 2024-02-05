package com.example.msapiuser.Entity;

import com.example.msapiuser.Core.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ms_user")
public class UserEntity extends BaseEntity {
    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "gender")
    private String gender;

    @Column(name = "state")
    private boolean isEnable;

    @Column(name = "address")
    private String address;

    @Column(name = "gsm_no")
    private String gsm_no;

    @Column(name = "date_of_birth")
    private String dob;

}
