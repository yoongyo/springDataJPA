package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {
    // 정적 Query 사용 가능(동적 쿼리는 Querydsl로)

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findTop3HelloBy();

    List<Member> findByUsername(@Param("username") String username);

    // 실무에서 자주 쓰임
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    // 실무에서 많이 사용함.
    @Query("select m.username from Member m")
    List<String> findUsernameList();

    // 주로 많이사용 => 단, JPQL 문법은 나중에 Querydsl로 바꾸자
    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    // 실무에서 많이 쓰는 in
    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);


    // 반환타입 종류   -> 이외의 타입 https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-return-types
    List<Member> findListByUsername(String username);   // 컬렉션   -> 없으면 결과가 빈 컬렉션
    Member findMemberByUsername(String username);  // 단건
    Optional<Member> findOptionalByUsername(String username);  // 단건 Optional  -> 없으면 결과가 null ,  결과가 둘 이상이면 예외발생
}
