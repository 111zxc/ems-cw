package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
}

