package com.example.apirest.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Data
@Entity(name = "usuario")
@Table(name="usuarios")
public class User implements Serializable{
    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;

}
