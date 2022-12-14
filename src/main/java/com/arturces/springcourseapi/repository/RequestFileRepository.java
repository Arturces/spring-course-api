package com.arturces.springcourseapi.repository;


import com.arturces.springcourseapi.domain.RequestFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestFileRepository extends JpaRepository<RequestFile, Long> {


    public Page<RequestFile> findAllByRequestId(Long id, Pageable pageable);

}
