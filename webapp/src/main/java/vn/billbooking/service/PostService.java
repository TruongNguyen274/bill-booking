package vn.billbooking.service;

import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> findAll();

    Post findById(long id);

    Post findByName(String name);

    Post save(Post post);

    List<Post> findByOwner(Karaoke owner);

}
