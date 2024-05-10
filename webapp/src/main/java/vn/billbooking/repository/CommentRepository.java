package vn.billbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.billbooking.model.entity.Comment;
import vn.billbooking.model.entity.Karaoke;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByOwner(Karaoke karaoke);

    List<Comment> findByOwnerAndProgress(Karaoke karaoke, String progress);

    List<Comment> findByProgress(String progress);

}
