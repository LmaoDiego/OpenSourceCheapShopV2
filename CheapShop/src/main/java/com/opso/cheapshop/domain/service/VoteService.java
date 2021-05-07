package com.opso.cheapshop.domain.service;

import com.opso.cheapshop.domain.model.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface VoteService {
    Page<Vote> getAllVotes(Pageable pageable);

    Vote getVoteById(Long voteId);

    Vote createVote(Vote vote);

    Vote updateVote(Long voteId, Vote voteDetails);

    ResponseEntity<?> deleteVote(Long voteId);
}
