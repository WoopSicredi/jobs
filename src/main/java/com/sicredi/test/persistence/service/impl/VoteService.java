package com.sicredi.test.persistence.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicredi.test.persistence.dao.IUserVoteDao;
import com.sicredi.test.persistence.dao.IVoteCountDao;
import com.sicredi.test.persistence.model.UserVote;
import com.sicredi.test.persistence.model.VoteCount;
import com.sicredi.test.persistence.model.VoteOption;
import com.sicredi.test.persistence.service.IVoteService;

/**
 * Implementa a persistência de dados relacionados a votos.
 */
@Service
@Transactional
public class VoteService implements IVoteService {

    private final IUserVoteDao userVoteDao;
    private final IVoteCountDao voteCountDao;

    @Autowired
    public VoteService(IUserVoteDao userVoteDao, IVoteCountDao voteCountDao) {
        this.userVoteDao = userVoteDao;
        this.voteCountDao = voteCountDao;
    }

    @Override
    public UserVote createVote(long topicId, String username, VoteOption voteOption) {
        UserVote userVote = new UserVote(topicId, username);
        VoteCount findById = voteCountDao.findByTopicIdAnVoteOption(topicId, voteOption.toString());

        VoteCount voteCount = Optional.ofNullable(findById).orElse(new VoteCount(topicId, voteOption.toString()));
        voteCount.increment();
        voteCountDao.save(voteCount);
        return userVoteDao.save(userVote);
    }

    @Override
    public List<VoteCount> findByTopicId(long topicId) {
        return voteCountDao.findByTopicId(topicId);
    }

    @Override
    public boolean userAlreadyVote(String username, long topicId) {
        UserVote userVote = userVoteDao.findByTopicIdAndUsername(topicId, username);
        return userVote != null;
    }

}
