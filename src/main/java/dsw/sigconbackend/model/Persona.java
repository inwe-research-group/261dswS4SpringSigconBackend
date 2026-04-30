package dsw.sigconbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_persona", nullable=false, unique=true)
    private Long idPersona;

    @Column(name="apellido_paterno", nullable=false, length = 80)
    private String apellidoPaterno;

    @Column(name="apellido_materno", nullable=false, length = 80)
    private String apellidoMaterno;

    @Column(nullable=false, length = 80)
    private String nombres;

    @Column(name="fecha_nacimiento", nullable=false)
    private LocalDate fechaNacimiento;

    @Column(name="ndocumento", nullable=false, unique = true)
    private String numDocumento;

    @Column(nullable=false, length = 120)
    private String direccion;

    @Column(nullable=false, length = 12)
    private String telefono;

    @Column(name="created_at", nullable=false, updatable = false)
    private LocalDateTime createdAt=LocalDateTime.now();

    @Column(name="updated_at", nullable=false)
    private LocalDateTime updatedAt=LocalDateTime.now();

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_sexo", referencedColumnName ="id_sexo", nullable = false )
    private Sexo sexo;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_tipo_documento", referencedColumnName ="id_tipo_documento", nullable = false )
    private TipoDocumento tipoDocumento;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="idubigeo", referencedColumnName ="idubigeo", nullable = false )
    private Ubigeo ubigeo;
}
