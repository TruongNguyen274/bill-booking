package vn.billbooking.model.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import vn.billbooking.model.dto.AccountDTO;
import vn.billbooking.model.dto.CommentDTO;
import vn.billbooking.model.dto.KaraokeDTO;
import vn.billbooking.model.entity.Account;
import vn.billbooking.model.entity.Booking;
import vn.billbooking.model.entity.Comment;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.mapper.CommentMapper;
import vn.billbooking.service.AccountService;
import vn.billbooking.service.CommentService;
import vn.billbooking.service.KaraokeService;
import vn.billbooking.utils.ContantUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapperImpl implements CommentMapper {

    @Autowired
    private AccountService accountService;

    @Autowired
    private KaraokeService karaokeService;

    @Autowired
    private CommentService commentService;

    @Override
    public CommentDTO toDTO(Comment comment) {

        if (comment == null) return null;

        CommentDTO commentDTO = new CommentDTO();

        // Karaoke
        if (comment.getOwner() != null) {
            KaraokeDTO karaokeDTO = new KaraokeDTO();
            karaokeDTO.setId(comment.getOwner().getId());
            karaokeDTO.setAddress(comment.getOwner().getAddress());
            karaokeDTO.setName(comment.getOwner().getName());
            karaokeDTO.setPhone(comment.getOwner().getPhone());

            commentDTO.setOwner(karaokeDTO);
            commentDTO.setOwnerId(karaokeDTO.getId());
        }
        // Customer / User
        if (comment.getAccount() != null) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setId(comment.getAccount().getId());
            accountDTO.setFullName(comment.getAccount().getFullName());
            accountDTO.setEmail(comment.getAccount().getEmail());
            accountDTO.setPhone(comment.getAccount().getPhone());

            commentDTO.setAccount(accountDTO);
            commentDTO.setAccountId(accountDTO.getId());
        }

        commentDTO.setId(comment.getId());
        commentDTO.setRate(comment.getRate());
        commentDTO.setReply(comment.getReply());
        commentDTO.setStatus(comment.isStatus());
        commentDTO.setProgress(comment.getProgress());

        return commentDTO;
    }

    @Override
    public List<CommentDTO> toListDTO(List<Comment> comments) {

        if (comments == null) return null;

        List<CommentDTO> list = new ArrayList<>(comments.size());
        comments.forEach(comment -> {
            list.add(toDTO(comment));
        });

        return list;
    }

    @Override
    public Comment toEntity(CommentDTO commentDTO) {

        if (commentDTO == null) return null;

        Comment comment = commentService.findById(commentDTO.getId());

        if (commentDTO.getOwnerId() != 0) {
            Karaoke owner = karaokeService.findById(commentDTO.getOwnerId());
            comment.setOwner(owner);
        }

        if (commentDTO.getAccountId() != 0) {
            Account account = accountService.findById(commentDTO.getAccountId());
            comment.setAccount(account);
        }

        comment.setRate(commentDTO.getRate());
        comment.setReply(commentDTO.getReply());
        comment.setStatus(commentDTO.isStatus());
        comment.setProgress(commentDTO.getProgress());
        commentService.save(comment);

        //calculator totalComment totalRating
        String progress = commentDTO.getProgress();
        if (!progress.isEmpty()) {
            Karaoke karaoke = comment.getOwner();
            List<Comment> comments = commentService.findByOwnerAndProgress(karaoke, ContantUtil.PROGRESS_APPROVED);
            if (!CollectionUtils.isEmpty(comments)) {
                int size = comments.size();
                karaoke.setTotalComment(size);
                int totalRate = comments.stream().mapToInt(i -> i.getRate()).sum();
                double rating = totalRate / size;
                karaoke.setTotalRating(rating);
                int totalRating1 = 0;
                int totalRating2 = 0;
                int totalRating3 = 0;
                int totalRating4 = 0;
                int totalRating5 = 0;
                for (Comment cm : comments) {
                    if (cm.getRate() == 1)
                        totalRating1 += 1;
                    if (cm.getRate() == 2)
                        totalRating2 += 1;
                    if (cm.getRate() == 3)
                        totalRating3 += 1;
                    if (cm.getRate() == 4)
                        totalRating4 += 1;
                    if (cm.getRate() == 5)
                        totalRating5 += 1;
                }
                karaoke.setTotalRating1(totalRating1);
                karaoke.setTotalRating2(totalRating2);
                karaoke.setTotalRating3(totalRating3);
                karaoke.setTotalRating4(totalRating4);
                karaoke.setTotalRating5(totalRating5);
            } else {
                karaoke.setTotalComment(0);
                karaoke.setTotalRating(0.0);
                karaoke.setTotalRating1(0);
                karaoke.setTotalRating2(0);
                karaoke.setTotalRating3(0);
                karaoke.setTotalRating4(0);
                karaoke.setTotalRating5(0);
            }
            karaokeService.save(karaoke);
        }

        return comment;
    }
}
