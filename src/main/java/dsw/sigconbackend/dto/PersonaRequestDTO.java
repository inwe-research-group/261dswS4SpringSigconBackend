package dsw.sigconbackend.dto;

import dsw.sigconbackend.model.Persona;
import dsw.sigconbackend.model.Sexo;
import dsw.sigconbackend.model.TipoDocumento;
import dsw.sigconbackend.model.Ubigeo;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonaRequestDTO {
    private Long idPersona;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombres;
    private LocalDate fechaNacimiento;
    private String numDocumento;
    private String direccion;
    private String telefono;
    private String idSexo;
    private Integer idTipoDocumento;
    private String idubigeo;

    public static Persona toEntity(PersonaRequestDTO personaRequestDTO){
        Persona persona=new Persona();
        if(personaRequestDTO.getIdPersona()!=null && personaRequestDTO.getIdPersona()>0)
            persona.setIdPersona(personaRequestDTO.getIdPersona());
        else
            persona.setIdPersona(null);//permite que el sistema autogenere la clave

        persona.setApellidoPaterno(personaRequestDTO.getApellidoPaterno());
        persona.setApellidoMaterno(personaRequestDTO.getApellidoMaterno());
        persona.setNombres(personaRequestDTO.getNombres());
        persona.setFechaNacimiento(personaRequestDTO.getFechaNacimiento());
        persona.setDireccion(personaRequestDTO.getDireccion());
        persona.setNumDocumento(personaRequestDTO.getNumDocumento());
        persona.setTelefono(personaRequestDTO.getTelefono());
        if(personaRequestDTO.getIdSexo()!=null)
            persona.setSexo(Sexo.builder().idSexo(personaRequestDTO.getIdSexo()).build());
        if(personaRequestDTO.getIdTipoDocumento()!=null)
            persona.setTipoDocumento(TipoDocumento.builder().idTipoDocumento(personaRequestDTO.getIdTipoDocumento()).build());
        if(personaRequestDTO.getIdubigeo()!=null)
            persona.setUbigeo(Ubigeo.builder().idUbigeo(personaRequestDTO.getIdubigeo()).build());

        return persona;
    }
}
