package com.sandro.forum_hub.controller;

import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.sandro.forum_hub.domain.repository.TopicoRepository;
import com.sandro.forum_hub.domain.topico.DadosAtualizacaoTopico;
import com.sandro.forum_hub.domain.topico.DadosListagemTopico;
import com.sandro.forum_hub.domain.topico.DadosCadastroTopico;
import com.sandro.forum_hub.domain.topico.DadosDetalhamentoTopico;
import com.sandro.forum_hub.domain.topico.Topico;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<?> registrarTopico(
            @RequestBody @Valid DadosCadastroTopico dadosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {

        boolean topicoExiste = topicoRepository.existsByTituloAndMensagem(dadosRegistroTopico.titulo(),
                dadosRegistroTopico.mensagem());

        if (topicoExiste) {
            String errorMessage = "Topico já existe";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
        }

        var topico = topicoRepository.save(new Topico(dadosRegistroTopico));
        var dadosRespostaTopico = new DadosDetalhamentoTopico(topico.getTitulo(),
                topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor(),
                topico.getCurso());
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(dadosRespostaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listarTopicos(
            @PageableDefault(size = 10, sort = { "dataCriacao" }, direction = Sort.Direction.ASC) Pageable paginacao) {
        var page = topicoRepository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizacaoTopico dadosAtualizarTopico) {

        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (!optionalTopico.isPresent()) {
            String errorMessage = "ID não encontrado";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage); // Retorna 404 Not Found
        }

        Topico topico = optionalTopico.get();
        topico.atualizarDados(dadosAtualizarTopico);
        var dadosRespostaTopico = new DadosDetalhamentoTopico(topico.getTitulo(),
                topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor(),
                topico.getCurso());
        return ResponseEntity.ok(dadosRespostaTopico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterTopico(@PathVariable Long id) {
        Optional<Topico> topicoid = topicoRepository.findById(id);

        if (topicoid.isPresent()) {
            var topico = topicoid.get();
            var dadosRespostaTopico = new DadosDetalhamentoTopico(topico.getTitulo(),
                    topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor(),
                    topico.getCurso());
            return ResponseEntity.ok(dadosRespostaTopico);
        } else {
            String errorMessage = "ID não encontrado";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topicoid = topicoRepository.findById(id);

        if (topicoid.isPresent()) {
            var topico = topicoid.get();
            if (topico.getStatus() == true) {
                topicoRepository.deleteById(id);
                return ResponseEntity.ok("Topico eliminado com sucesso");
            } else {
                String errorMessage = "Topico já eliminado";
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
            }
        } else {
            String errorMessage = "ID não encontrado";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

}
