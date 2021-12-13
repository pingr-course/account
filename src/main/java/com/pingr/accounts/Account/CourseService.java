package com.pingr.accounts.Account;

import com.pingr.accounts.Account.exceptions.InvalidArgumentsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course create(Course course) {
        if (course == null) throw new InvalidArgumentsException("curso não pode ser nulo");

        try {
            return this.courseRepository.save(course);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Course> find(Long id) {
        return this.courseRepository.findById(id);
    }
}
