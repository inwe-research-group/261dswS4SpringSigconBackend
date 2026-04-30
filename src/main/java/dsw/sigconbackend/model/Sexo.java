package dsw.sigconbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="sexo")
public class Sexo {
    @Id
    @Column(name="id_sexo")
    private String idSexo;

    @Column(nullable = false,unique=true)
    private String descripcion;

    @Column(name="desc_corto",nullable = false,unique=true)
    private String descCorto;
}
