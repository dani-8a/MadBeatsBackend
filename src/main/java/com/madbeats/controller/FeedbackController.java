package com.madbeats.controller;

import com.madbeats.entity.Feedback;
import com.madbeats.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    private static final List<String> VALID_SUBJECTS = Arrays.asList("Issue", "Suggestion", "Others");

    @PostMapping("/send_message")
    public ResponseEntity<String> sendMessageFeedback(@RequestBody Feedback feedback) {
        if (feedback.getSubject().stream().anyMatch(subject -> !VALID_SUBJECTS.contains(subject))) {
        	System.out.println("Invalid subject value. Valid values are: Issue, Suggestion, Others");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid subject value. Valid values are: Issue, Suggestion, Others");
        }
        feedbackRepository.save(feedback);
        System.out.println("Feedback submitted successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body("Feedback submitted successfully");
    }

    @GetMapping("/all_messages")
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        System.out.println("Retrieved all feedback messages:");
        feedbackList.forEach(feedback -> System.out.println(feedback.toString()));
        return ResponseEntity.ok(feedbackList);
    }

    @GetMapping("/{id}/message")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable String id) {
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        if (feedback.isPresent()) {
            System.out.println("Retrieved feedback message with ID " + id + ":");
            System.out.println(feedback.get().toString());
            return ResponseEntity.ok(feedback.get());
        } else {
            System.out.println("Feedback message with ID " + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete_all_messages")
    public ResponseEntity<String> deleteAllFeedbacks() {
        feedbackRepository.deleteAll();
        System.out.println("All feedback messages deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body("All feedback messages deleted successfully");
    }

    @DeleteMapping("/delete/{id}/message")
    public ResponseEntity<String> deleteFeedbackById(@PathVariable String id) {
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        if (feedback.isPresent()) {
            feedbackRepository.deleteById(id);
            System.out.println("Feedback message deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body("Feedback message deleted successfully");
        } else {
        	System.out.println("Feedback message not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Feedback message not found");
        }
    }
}
