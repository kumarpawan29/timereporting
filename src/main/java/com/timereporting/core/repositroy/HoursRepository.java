package com.timereporting.core.repositroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.timereporting.core.model.Hours;

@Repository("hoursRepository")
public interface HoursRepository extends JpaRepository<Hours, Long> {
	
	List<Hours> findByReferencePk(int referencePk);
	
}
