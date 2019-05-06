package projeto.atvifma.controle;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projeto.atvifma.modelo.Medico;
import projeto.atvifma.servico.MedicoServico;
import projeto.atvifma.controle.resposta.Resposta;
import projeto.atvifma.util.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoControle {

    private final MedicoServico medicoServico;

    @Autowired
    public MedicoControle(MedicoServico medicoservico) {
        this.medicoServico = medicoservico;
    }

    @GetMapping
    public Resposta<List<Medico>> todos() {
        List<Medico> medicos = this.medicoServico.todos();

        Resposta<List<Medico>> resposta = new Resposta<>();
        resposta.setDados(medicos);
        return resposta;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Resposta<Medico>> salva(@RequestBody Medico medico) {
        Medico salvo = medicoServico.salva(medico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                  .path("/{id}").buildAndExpand(salvo.getId()).toUri();
        Resposta<Medico> resposta = new Resposta<>();
        resposta.setDados(medico);

        return ResponseEntity.created(uri).body(resposta);
    }

    @GetMapping("/{id}")
    public Resposta<Medico> buscaPor(@PathVariable Integer id) {
        Medico medico = medicoServico.buscaPor(id );
        Resposta<Medico> resposta = new Resposta<>();
        resposta.setDados(medico);

        return resposta;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exclui(@PathVariable Integer id) {

        medicoServico.excluiPor(id );
    }

    @PutMapping("/{id}")
    public Resposta<Medico> altera(@PathVariable  Integer id, @RequestBody Medico medico) {
        Medico medicoSalvo = this.medicoServico.buscaPor(id);
        BeanUtils.copyProperties(medico,
                medicoSalvo,
                PropriedadesUtil.obterPropriedadesComNullDe(medico) );
        Medico medicoAtualizado = this.medicoServico.atualiza(id, medicoSalvo);
        BeanUtils.copyProperties(medicoSalvo, medico);

        Resposta<Medico> resposta = new Resposta<>();
        resposta.setDados(medico);

        return resposta;
    }

}
