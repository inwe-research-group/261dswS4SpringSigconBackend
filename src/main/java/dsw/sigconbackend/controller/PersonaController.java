package dsw.sigconbackend.controller;

import dsw.sigconbackend.dto.PersonaRequestDTO;
import dsw.sigconbackend.dto.PersonaResponseDTO;
import dsw.sigconbackend.service.PersonaService;
import dsw.sigconbackend.utils.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/persona")
public class PersonaController {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    PersonaService personaService;

    @GetMapping
    public ResponseEntity<?> getPersonas(){
        List<PersonaResponseDTO> listaPersonaResponse= Collections.emptyList();
        try{
            listaPersonaResponse=personaService.listPersona();
        }catch(Exception e){
            logger.error("error inesperado", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaPersonaResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("Persona not found").build());
        return ResponseEntity.ok(listaPersonaResponse);
    }

    @PostMapping
    public ResponseEntity<?> insertPersona(@RequestBody PersonaRequestDTO personaRequest){
        PersonaResponseDTO personaResponse;
        try{
            personaResponse=personaService.insertPersona(personaRequest);
        }catch(Exception e){
            logger.error("error inesperado", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(personaResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("Persona not insert").build());
        return ResponseEntity.ok(personaResponse);
    }

    @PutMapping
    public ResponseEntity<?> updatePersona(@RequestBody PersonaRequestDTO personaRequest){
        PersonaResponseDTO personaResponse;
        try{
            personaResponse=personaService.findPersona(personaRequest.getIdPersona());
            if(personaResponse==null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ErrorResponse.builder().message("Persona not found").build());

            personaResponse=personaService.updatePersona(personaRequest);

        }catch(Exception e){
            logger.error("error inesperado", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(personaResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("Persona not update").build());
        return ResponseEntity.ok(personaResponse);
    }

    @DeleteMapping
    public ResponseEntity<?> deletePersona(@RequestBody PersonaRequestDTO personaRequest){
        PersonaResponseDTO personaResponse;
        try{
            personaResponse=personaService.findPersona(personaRequest.getIdPersona());
            if(personaResponse==null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ErrorResponse.builder().message("Persona not found for delete").build());

            personaService.deletePersona(personaRequest.getIdPersona());

        }catch(Exception e){
            logger.error("error inesperado", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(personaResponse);
    }

    @PostMapping("/find")
    public ResponseEntity<?> findPersonaById(@RequestBody PersonaRequestDTO personaRequest){
        PersonaResponseDTO personaResponse;
        try{
            personaResponse=personaService.findPersona(personaRequest.getIdPersona());
        }catch(Exception e){
            logger.error("error inesperado", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(personaResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("Persona not found").build());
        return ResponseEntity.ok(personaResponse);
    }

    @PostMapping("/findNumdocumento")
    public ResponseEntity<?> findByNumdocumento(@RequestBody PersonaRequestDTO personaRequest){
        PersonaResponseDTO personaResponse;
        try{
            personaResponse=personaService.findByNumDocumento(personaRequest.getNumDocumento());
        }catch(Exception e){
            logger.error("error inesperado", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(personaResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("NumDocumento of Persona not found").build());
        return ResponseEntity.ok(personaResponse);
    }
}
