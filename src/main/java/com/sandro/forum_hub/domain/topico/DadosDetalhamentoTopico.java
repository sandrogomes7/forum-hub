package com.sandro.forum_hub.domain.topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(       
                                        String titulo,
                                        String mensagem,
                                        LocalDateTime dataCriacao,
                                        Boolean status,
                                        String autor,
                                        String curso) { 
}
