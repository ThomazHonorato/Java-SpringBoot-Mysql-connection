package medi.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medi.voll.api.paciente.DadosCadastroPacientes;
import medi.voll.api.paciente.Paciente;
import medi.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;
@PostMapping
@Transactional
public void cadastrar(@RequestBody @Valid DadosCadastroPacientes dados){
repository.save(new Paciente(dados));
}
}
