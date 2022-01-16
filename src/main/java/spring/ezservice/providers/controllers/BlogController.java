package spring.ezservice.providers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import spring.ezservice.providers.models.Blog;
import spring.ezservice.providers.repositories.BlogRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class BlogController {


    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/blogs")
    public List<Blog> findAll() {
        return (List<Blog>) blogRepository.findAll();
    }

    @PostMapping("/blog")
    @ResponseStatus(HttpStatus.CREATED)
    public Blog saveBlog(@Valid @RequestBody Blog savedBlog) {
        return blogRepository.save(savedBlog);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }
}
