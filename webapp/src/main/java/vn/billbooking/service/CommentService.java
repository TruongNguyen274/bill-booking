package vn.billbooking.service;

import vn.billbooking.model.entity.Comment;
import vn.billbooking.model.entity.Karaoke;

import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    Comment findById(long id);

    Comment save(Comment comment);

    List<Comment> findByKaraoke(Karaoke karaoke);

    List<Comment> findByOwnerAndProgress(Karaoke karaoke, String progress);

    List<Comment> findByProgress(String progress);

}
