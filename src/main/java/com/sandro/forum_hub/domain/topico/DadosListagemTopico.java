package com.sandro.forum_hub.domain.topico;

import java.time.LocalDateTime;

public record DadosListagemTopico(
                                  String titulo,
                                  String mensagem,
                                  LocalDateTime dataCriacao,
                                  Boolean status,
                                  String autor,
                                  String curso) {
    public DadosListagemTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(),
                topico.getAutor(), topico.getCurso());
    }
}
