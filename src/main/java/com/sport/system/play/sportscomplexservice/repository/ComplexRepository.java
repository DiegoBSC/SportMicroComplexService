package com.sport.system.play.sportscomplexservice.repository;

import com.sport.system.play.sportscomplexservice.entity.Complex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplexRepository extends MongoRepository<Complex, String> {

    Page<Complex> findAllByIdCompany(String idCompany, Pageable pageable);
}
