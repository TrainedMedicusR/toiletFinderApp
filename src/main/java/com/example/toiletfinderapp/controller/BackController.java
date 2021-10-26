package com.example.toiletfinderapp.controller;

import com.example.toiletfinderapp.entity.Comment;
import com.example.toiletfinderapp.entity.Toilet;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/")
public class BackController extends BaseController{

    /**
     * get all comments
     *
     * @return comments List
     */
    @ApiOperation("get all comments")
    @GetMapping("/comments")
    public List<Comment> listAllComments(@RequestHeader("Authorization") String token) {
        Integer id = jwtHelp.getUserIDFromToken(token);
        if (id == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        return commentService.getAllComment();
    }

    /**
     * get all damaged toilets
     *
     * @return toilets List
     */
    @ApiOperation("get all damaged toilets")
    @GetMapping("/damagedtoilet")
    public List<Toilet> listAllDamagedToilet(@RequestHeader("Authorization") String token) {
        Integer id = jwtHelp.getUserIDFromToken(token);
        if (id == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        return toiletService.getAllDamagedToilets();
    }

    /**
     * delete a comment
     *
     * @param id
     **/
    @ApiOperation("Delete a comment")
    @ApiImplicitParam(name = "comment_ID", value = "Comment ID", required = true, dataType = "Integer")
    @DeleteMapping("comments")
    public void deleteComment(@RequestParam(value = "comment_ID") Integer id, @RequestHeader("Authorization") String token) {
        Integer uid = jwtHelp.getUserIDFromToken(token);
        if (uid == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        commentService.deleteCommentById(id);
    }

    /**
     * handle a damaged toilet
     */
    @ApiOperation("handle a damaged toilet")
    @ApiImplicitParam(name = "toilet_ID", value = "Toilet ID", required = true, dataType = "Integer")
    @PostMapping("/damagedtoilet")
    public void handleToilet(@RequestParam(value = "toilet_ID") Integer tid,@RequestParam(value = "is_damage") boolean is_damage,@RequestHeader("Authorization") String token) {
        Integer id = jwtHelp.getUserIDFromToken(token);
        if (id == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        toiletService.handleDamagedToilet(tid,is_damage);
    }


}
