package vn.billbooking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Post;
import vn.billbooking.repository.PostRepository;
import vn.billbooking.service.PostService;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Post findByName(String name) {
        return postRepository.findByName(name).orElse(null);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> findByOwner(Karaoke owner) {
        return postRepository.findByOwner(owner);
    }

}
