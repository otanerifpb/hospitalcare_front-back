package com.example.hospital_care.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "pacientes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @NotBlank(message = "Campo Obrigatorio!")
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    private String telefone;

    @Getter
    private String sexo;

    @Getter
    private String cpf;

    @Getter
    private Date data_de_nascimento;

    @Getter
    private String email;

    @Getter
    private String prontuario;

    @Getter
    private String anamnese;

    public void setName(String name) {
        this.name = name;
    }

    public void setAnamnese(String anamnese) {
        this.anamnese = anamnese;
    }
}
