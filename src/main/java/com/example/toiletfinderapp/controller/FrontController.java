package com.example.toiletfinderapp.controller;

import com.example.toiletfinderapp.entity.Comment;
import com.example.toiletfinderapp.entity.Toilet;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class FrontController extends BaseController{
    /**
     * Add comment to a toilet
     */
    @ApiOperation("Add comment to a toilet")
    @PostMapping("/comments/{id}")
    public String addPostComment(@RequestHeader("Authorization") String token, @PathVariable Integer id, Comment comment) {
        Integer uid = jwtHelp.getUserIDFromToken(token);
        if (uid == null) {
            // Couldn't authenticate users
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        comment.setSubmitTime(new Date());
        comment.setToiletId(id);
        commentService.addComment(comment);
        return null;
    }

    /**
     * get all comments by toilet ID
     *
     * @param id Toilet ID
     * @return comment List
     */
    @ApiOperation("get all comments by toilet id")
    @GetMapping("/comments/{id}")
    public List<Comment> listAllComment(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        Integer pid = jwtHelp.getUserIDFromToken(token); // Actually User ID
        // // Couldn't authenticate users
        if (pid == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        return commentService.getAllCommentByTid(id);
    }


    /**
     * Get the specific toilet by Toilet ID
     *
     * @param id Toilet Identifier
     * @return specific toilet
     */
    @ApiOperation("Get the specific post by Post ID")
    @GetMapping("/toilets/{id}")
    public Toilet getToiletById(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        Integer uid = jwtHelp.getUserIDFromToken(token);
        // catch can't get users id
        if (uid == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        return toiletService.getToiletByTid(id);
    }

    /**
     * get nearest toilets by location
     * @return nearest toilets list
     */
    @ApiOperation("Get Nearest toilets by location")
    @GetMapping(value = "/toilets", produces = "application/json")
    public List<Toilet> listAllToilets(@RequestHeader("Authorization") String token, @RequestParam Double longitude, @RequestParam Double latitude) {
        Integer uid = jwtHelp.getUserIDFromToken(token);
        if (uid == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        return toiletService.getBestToilet(longitude, latitude);
    }

    /**
     * Report a toilet
     */
    @ApiOperation("Report a toilet")
    @PostMapping("/toilets")
    public String addMessage(@RequestHeader("Authorization") String token, Toilet toilet) {
        Integer uid = jwtHelp.getUserIDFromToken(token);
        if (uid == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        toiletService.reportToilet(toilet.getToiletId());
        return null;
    }
}
