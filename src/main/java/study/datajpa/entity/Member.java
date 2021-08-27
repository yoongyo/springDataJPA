package study.datajpa.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})  // 객체를 찍을 때 출력됨. 가급적이면 연관관계는 넣지 않는다.(무한루프 => team)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)  // FetchType.LAZY로 꼭 바꿔줘야한다. -> JPA의 연관관계는 모두 다 FetchType.LAZY로 바꿔줘야한다. 성능최적화 이점.
    @JoinColumn(name = "team_id")  // Foreign key = team_id
    private Team team;

//    @NoArgsConstructor(access = AccessLevel.PROTECTED) 이것으로 대체 할 수 있다.
//    protected Member() {
//    }

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
