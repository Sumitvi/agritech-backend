package com.example.bakend.scheme.repository;
import com.example.bakend.scheme.entity.Scheme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchemeRepository extends JpaRepository<Scheme, Long> {

    List<Scheme> findByStateOrState(String state, String all);

    List<Scheme> findByCropOrCrop(String crop, String all);
}
