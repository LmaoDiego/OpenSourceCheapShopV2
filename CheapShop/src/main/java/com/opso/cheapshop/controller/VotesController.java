package com.opso.cheapshop.controller;

import com.opso.cheapshop.domain.model.Vote;
import com.opso.cheapshop.domain.service.VoteService;
import com.opso.cheapshop.resource.SaveVoteResource;
import com.opso.cheapshop.resource.VoteResource;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api"})
public class VotesController {
    @Autowired
    private VoteService voteService;
    @Autowired
    private ModelMapper mapper;

    public VotesController() {
    }

    @GetMapping({"/votes"})
    public Page<VoteResource> getAllVotes(Pageable pageable) {
        List<VoteResource> votes = (List)this.voteService.getAllVotes(pageable).getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int voteCount = votes.size();
        return new PageImpl(votes, pageable, (long)voteCount);
    }

    @GetMapping({"/votes/{id}"})
    public VoteResource getVoteById(@PathVariable(name = "id") Long voteId) {
        return this.convertToResource(this.voteService.getVoteById(voteId));
    }

    @PostMapping({"/votes"})
    public VoteResource createVote(@Valid @RequestBody SaveVoteResource resource) {
        return this.convertToResource(this.voteService.createVote(this.convertToEntity(resource)));
    }

    @PutMapping({"/votes/{id}"})
    public VoteResource updateVote(@PathVariable(name = "id") Long voteId, @Valid @RequestBody SaveVoteResource resource) {
        return this.convertToResource(this.voteService.updateVote(voteId, this.convertToEntity(resource)));
    }

    @DeleteMapping({"/votes/{voteId}"})
    public ResponseEntity<?> deleteVote(@PathVariable Long voteId) {
        return this.voteService.deleteVote(voteId);
    }

    private Vote convertToEntity(SaveVoteResource resource) {
        return (Vote)this.mapper.map(resource, Vote.class);
    }

    private VoteResource convertToResource(Vote entity) {
        return (VoteResource)this.mapper.map(entity, VoteResource.class);
    }
}