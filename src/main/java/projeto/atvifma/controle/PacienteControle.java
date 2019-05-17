package projeto.atvifma.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projeto.atvifma.modelo.Paciente;
import projeto.atvifma.servico.PacienteServico;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteControle {

    private final PacienteServico pacienteServico;

    @Autowired
    public PacienteControle(PacienteServico pacienteServico) {
        this.pacienteServico = pacienteServico;
    }

    @GetMapping
    public List<Paciente> todos() {
        return pacienteServico.todos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paciente salva(@RequestBody Paciente paciente) {
        return pacienteServico.salva(paciente);
    }

    @GetMapping("/{id}")
    public Paciente buscaPor(@PathVariable Integer id) {
        return pacienteServico.buscaPor(id );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exclui(@PathVariable Integer id) {
        pacienteServico.excluiPor(id );
    }

    @PutMapping("/{id}")
    public Paciente altera(@PathVariable  Integer id, @RequestBody Paciente paciente) {
        return  pacienteServico.atualiza(id, paciente );
    }
}
