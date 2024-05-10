package vn.billbooking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.billbooking.model.entity.Comment;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.repository.CommentRepository;
import vn.billbooking.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findByKaraoke(Karaoke karaoke) {
        return commentRepository.findByOwner(karaoke);
    }

    @Override
    public List<Comment> findByOwnerAndProgress(Karaoke karaoke, String progress) {
        return commentRepository.findByOwnerAndProgress(karaoke, progress);
    }

    @Override
    public List<Comment> findByProgress(String progress) {
        return commentRepository.findByProgress(progress);
    }

}
