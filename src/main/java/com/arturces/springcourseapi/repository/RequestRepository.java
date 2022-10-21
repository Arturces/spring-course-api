package com.arturces.springcourseapi.repository;

import com.arturces.springcourseapi.domain.Request;
import com.arturces.springcourseapi.domain.enums.RequestState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    public List<Request> findAllByOwnerId(Long id);

    @Transactional(readOnly = false) //disponivel para leitura e disponivel para a escrita
    @Modifying //modificador da entidade request, modificar o estado
    @Query("UPDATE request SET state = ?2 WHERE id = ?1")
    public int updateStatus(Long id, RequestState state);


}
