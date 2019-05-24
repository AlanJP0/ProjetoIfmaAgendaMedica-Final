package projeto.atvifma.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.atvifma.controle.eventos.HeaderLocationEvento;
import projeto.atvifma.controle.validacao.Validacao;
import projeto.atvifma.modelo.Paciente;
import projeto.atvifma.servico.PacienteServico;

import projeto.atvifma.servico.PacienteServico;
import projeto.atvifma.controle.dto.PacienteDTO;
import projeto.atvifma.controle.resposta.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pacientes")
public class PacienteControle {

    private final PacienteServico pacienteServico;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    public PacienteControle(PacienteServico pacienteServico) {
        this.pacienteServico = pacienteServico;
    }

    @GetMapping
    public List<Paciente> todos() {
        return pacienteServico.todos();
    }

    @GetMapping
    public Resposta<List<PacienteDTO>> todas() {
        List<PacienteDTO> pacienteDTO = pacienteServico.todas()
                .stream()
                .map(paciente -> new PacienteDTO(paciente))
                .collect(Collectors.toList());

        return Resposta.comDadosDe(pacienteDTO);
    }

    @PostMapping
    public ResponseEntity<Resposta<PacienteDTO>> salva(@Valid @RequestBody PacienteDTO pacienteDTO,
                                                       HttpServletResponse response) {
        Paciente pacienteSalva = pacienteServico.salva(pacienteDTO.getPaciente());

        publisher.publishEvent(new HeaderLocationEvento(this, response, pacienteSalva.getId()) );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Resposta.comDadosDe(new PacienteDTO(pacienteSalva)));
    }

    @GetMapping("/{id}")
    public Resposta<PacienteDTO> buscaPor(@PathVariable Integer id) {
        Paciente paciente = pacienteServico.buscaPor(id);
        return Resposta.comDadosDe(new PacienteDTO(paciente ));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exclui(@PathVariable Integer id) {
        pacienteServico.excluiPor(id );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resposta<PacienteDTO>> atualizar(@PathVariable Integer id,
                                                            @RequestBody PacienteDTO categoriaDTO) {

        Paciente paciente = categoriaDTO.atualizaIgnorandoNuloA(pacienteServico.buscaPor(id));

        List<Erro> erros = this.getErros(new PacienteDTO(paciente) );
        if (existe(erros)) {
            return ResponseEntity.badRequest().body(Resposta.com(erros) );
        }

        Paciente pacienteAtualizada = pacienteServico.atualiza(id, paciente);
        return ResponseEntity.ok(Resposta.comDadosDe(new PacienteDTO(pacienteAtualizada )));
    }

    private boolean existe(List<Erro> erros) {
        return Objects.nonNull( erros ) &&  !erros.isEmpty();
    }

    private List<Erro> getErros(PacienteDTO dto) {
        Validacao<PacienteDTO> validacao = new Validacao<>();
        return validacao.valida(dto);
    }
}
