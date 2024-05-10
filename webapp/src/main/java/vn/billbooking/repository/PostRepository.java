package vn.billbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByName(String name);
    List<Post> findByOwner(Karaoke owner);
}
