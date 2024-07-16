package com.sandro.forum_hub.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sandro.forum_hub.domain.topico.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findAll(Pageable paginacao);

    boolean existsByTituloAndMensagem(String titulo, String mensagem);
}
