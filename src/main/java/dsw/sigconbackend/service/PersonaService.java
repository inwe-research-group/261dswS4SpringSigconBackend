package dsw.sigconbackend.service;

import dsw.sigconbackend.dto.PersonaRequestDTO;
import dsw.sigconbackend.dto.PersonaResponseDTO;
import dsw.sigconbackend.model.Persona;
import dsw.sigconbackend.repository.PersonaRepository;
import dsw.sigconbackend.repository.SexoRepository;
import dsw.sigconbackend.repository.TipoDocumentoRepository;
import dsw.sigconbackend.repository.UbigeoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    SexoRepository sexoRepository;
    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;
    @Autowired
    UbigeoRepository ubigeoRepository;

    public List<PersonaResponseDTO> listPersona(){
        return PersonaResponseDTO.fromEntities(personaRepository.findAll());
    }

    public PersonaResponseDTO insertPersona(PersonaRequestDTO personaRequest){
        Persona persona= PersonaRequestDTO.toEntity(personaRequest);
        persona.setCreatedAt(java.time.LocalDateTime.now());
        persona.setUpdatedAt(java.time.LocalDateTime.now());
        persona=personaRepository.save(persona);
        return PersonaResponseDTO.fromEntity(persona);
    }

    public PersonaResponseDTO updatePersona(PersonaRequestDTO personaRequest){
        Persona persona= PersonaRequestDTO.toEntity(personaRequest);
        persona.setUpdatedAt(java.time.LocalDateTime.now());
        persona=personaRepository.save(persona);
        return PersonaResponseDTO.fromEntity(persona);
    }

    public void deletePersona(Long id){
        personaRepository.deleteById(id);
    }

    public PersonaResponseDTO findPersona(Long id){
        return PersonaResponseDTO.fromEntity(personaRepository.findById(id).get());
    }

    public PersonaResponseDTO findByNumDocumento(String nDocumento){
        return PersonaResponseDTO.fromEntity((Persona)personaRepository.findByNumDocumento(nDocumento));
    }

}
