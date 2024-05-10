package vn.billbooking.model.mapper;

import vn.billbooking.model.dto.CommentDTO;
import vn.billbooking.model.entity.Comment;

import java.util.List;

public interface CommentMapper {

    // Map Entity to DTO
    CommentDTO toDTO(Comment comment);

    List<CommentDTO> toListDTO(List<Comment> comments);

    // Map DTO to Entity
    Comment toEntity(CommentDTO commentDTO);
}
