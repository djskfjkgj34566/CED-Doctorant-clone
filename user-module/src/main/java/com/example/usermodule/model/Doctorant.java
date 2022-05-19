package com.example.usermodule.model;

import com.example.usermodule.dto.ProductionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Doctorant extends User{

    //private int niveau;
    private String address;
    private String specialite;
    private String date_preinscription;
    private String vkey;


}
