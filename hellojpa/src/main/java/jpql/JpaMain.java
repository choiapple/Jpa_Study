package jpql;

import hellojpa.src.main.java.jpql.Member;
import hellojpa.src.main.java.jpql.Team;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        //code
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("teamA");
            member.setAge(10);
            member.setType(MemberType.ADMIN);
            member.setTeam(team);
            em.persist(member);


            em.flush();
            em.clear();

            String query  =
                    "select " +
                            "case when m.age <= 10 then '학생요금'" +
                            "case when " +
                            "from Member m";
            em.createQuery(query);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
