//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.opso.cheapshop;

import com.opso.cheapshop.domain.model.Vote;
import com.opso.cheapshop.domain.repository.VoteRepository;
import com.opso.cheapshop.domain.service.VoteService;
import com.opso.cheapshop.service.VoteServiceImpl;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
public class VoteServiceImplTest {
    @MockBean
    private VoteRepository voteRepository;
    @Autowired
    private VoteService voteService;

    public VoteServiceImplTest() {
    }

    @Test
    @DisplayName("When getVoteByTitle With Valid Title Then Returns Post")
    public void whenGetVoteByTitleWithValidTitleThenReturnsPost() {
        String title = "Great Vote";
        Long id = 1L;
        Vote vote = (new Vote()).setId(id).setName(title);
        Mockito.when(this.voteRepository.findById(id)).thenReturn(Optional.of(vote));
        Vote foundVote = this.voteService.getVoteById(id);
        Assertions.assertThat(foundVote.getId()).isEqualTo(id);
    }

    @TestConfiguration
    static class VoteServiceImplTestConfiguration {
        VoteServiceImplTestConfiguration() {
        }

        @Bean
        public VoteService voteService() {
            return new VoteServiceImpl();
        }
    }
}
