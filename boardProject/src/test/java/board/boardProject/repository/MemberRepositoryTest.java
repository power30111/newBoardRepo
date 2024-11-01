package board.boardProject.repository;

import board.boardProject.domain.Member;
import board.boardProject.domain.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    @Commit
    void register(){
        Member testMember = Member.builder()
                .username("테스트용아이디")
                .password(bCryptPasswordEncoder.encode("123456"))
                .nickname("테스트용닉네임")
                .email("테스트용 이메일")
                .role(Role.ROLE_USER)
                .build();

        memberRepository.save(testMember);
        Member referenceById = memberRepository.getReferenceById(1L);
        Assertions.assertThat(testMember).isEqualTo(referenceById);

    }

}