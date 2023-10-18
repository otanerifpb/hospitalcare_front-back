package com.example.hospital_care.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "medico")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @NotBlank(message = "Campo Obrigatorio!")
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @NotBlank(message = "Campo Obrigatorio!")
    private String crm;

    @Getter
    private String sexo;

    @Getter
    private String especialidade;

    @Getter
    private String telefone;

    public void setName(String name) {
        this.name = name;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Medico(String name, String crm, String sexo, String especialidade, String telefone) {
        this.name = name;
        this.crm = crm;
        this.sexo = sexo;
        this.especialidade = especialidade;
        this.telefone = telefone;
    }
}
