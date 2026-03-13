package com.cts.eduLink.application.serviceTest;

import com.cts.eduLink.application.classexception.CourseException;
import com.cts.eduLink.application.projection.CourseDetailProjection;
import com.cts.eduLink.application.repository.CourseRepository;
import com.cts.eduLink.application.repository.FacultyRepository;
import com.cts.eduLink.application.service.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceImplTest {

    @Mock
    private  CourseRepository courseRepository;

    private  CourseServiceImpl courseService;
    private  List<CourseDetailProjection> courseDetailProjectionList;
    private FacultyRepository facultyRepository;

    @BeforeEach
    void setUp() {
        courseService = new CourseServiceImpl(courseRepository,facultyRepository);
        courseDetailProjectionList = new ArrayList<>();
        courseDetailProjectionList.add(new CourseDetailProjection("java springBoot" , "K",  4.8));
    }
    @Test
    public void courseProjectTest_200(){
        when(courseRepository.findAllAvailableCourse()).thenReturn(courseDetailProjectionList);
        List<CourseDetailProjection> courseDetailProjections = courseService.findAllAvailableCourse();
        assertEquals(!courseDetailProjections.isEmpty(), courseDetailProjections.size()==1,"Tested Successfully");
    }

    @Test
    public void courseProjectTest_404(){
        courseDetailProjectionList = new ArrayList<>();
        CourseException exception = assertThrows(CourseException.class,()->{
            courseService.findAllAvailableCourse();
        });
        verify(courseRepository).findAllAvailableCourse();
        assertEquals("No course Available!",exception.getMessage());
    }
}
