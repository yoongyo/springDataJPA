package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Team;


public interface TeamRepository extends JpaRepository<Team, Long> {  // T: Entity, ID: Entity의 식별자 타입 pk id

}
