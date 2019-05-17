package projeto.atvifma.controle;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projeto.atvifma.controle.resposta.Resposta;
import projeto.atvifma.modelo.Consulta;
import projeto.atvifma.servico.ConsultaServico;
import projeto.atvifma.util.PropriedadesUtil;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaControle {
    private final ConsultaServico consultaServico;

    @Autowired
    public ConsultaControle(ConsultaServico consultaServico) {
        this.consultaServico = consultaServico;
    }

    @GetMapping
    public Resposta<List<Consulta>> todos() {
        List<Consulta> consultas = this.consultaServico.todos();

        Resposta<List<Consulta>> resposta = new Resposta<>();
        resposta.setDados(consultas);
        return resposta;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Resposta<Consulta>> salva(@RequestBody Consulta consulta) {
        Consulta salvo = consultaServico.salva(consulta);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                 .path("/{id}").buildAndExpand(salvo.getId()).toUri();
        Resposta<Consulta> resposta = new Resposta<>();
        resposta.setDados(consulta);

        return ResponseEntity.created(uri).body(resposta);
    }

    @GetMapping("/{id}")
    public Resposta<Consulta> buscaPor(@PathVariable Integer id) {
        Consulta consulta = consultaServico.buscaPor(id );
        Resposta<Consulta> resposta = new Resposta<>();
        resposta.setDados(consulta);

        return resposta;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exclui(@PathVariable Integer id) {

        consultaServico.excluiPor(id );
    }

    @PutMapping("/{id}")
    public Resposta<Consulta> altera(@PathVariable  Integer id, @RequestBody Consulta consulta) {
        Consulta consultaSalvo = this.consultaServico.buscaPor(id);
        BeanUtils.copyProperties(consulta,
                consultaSalvo,
                PropriedadesUtil.obterPropriedadesComNullDe(consulta) );
        Consulta consultaAtualizado = this.consultaServico.atualiza(id, consultaSalvo);
        BeanUtils.copyProperties(consultaAtualizado, consulta);

        Resposta<Consulta> resposta = new Resposta<>();
        resposta.setDados(consulta);

        return resposta;
    }
}
