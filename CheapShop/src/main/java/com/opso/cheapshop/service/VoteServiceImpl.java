//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.opso.cheapshop.service;

import com.opso.cheapshop.domain.model.Vote;
import com.opso.cheapshop.domain.repository.VoteRepository;
import com.opso.cheapshop.domain.service.VoteService;
import com.opso.cheapshop.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    private VoteRepository voteRepository;

    public VoteServiceImpl() {
    }

    public Page<Vote> getAllVotes(Pageable pageable) {
        return this.voteRepository.findAll(pageable);
    }

    public Vote getVoteById(Long voteId) {
        return (Vote)this.voteRepository.findById(voteId).orElseThrow(() -> {
            return new ResourceNotFoundException("Vote", "Id", voteId);
        });
    }

    public Vote createVote(Vote vote) {
        return (Vote)this.voteRepository.save(vote);
    }

    public Vote updateVote(Long voteId, Vote voteDetails) {
        return (Vote)this.voteRepository.findById(voteId).map((vote) -> {
            vote.setName(voteDetails.getName());
            return (Vote)this.voteRepository.save(vote);
        }).orElseThrow(() -> {
            return new ResourceNotFoundException("Vote", "Id", voteId);
        });
    }

    public ResponseEntity<?> deleteVote(Long voteId) {
        return (ResponseEntity)this.voteRepository.findById(voteId).map((vote) -> {
            this.voteRepository.delete(vote);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> {
            return new ResourceNotFoundException("Vote", "Id", voteId);
        });
    }
}
