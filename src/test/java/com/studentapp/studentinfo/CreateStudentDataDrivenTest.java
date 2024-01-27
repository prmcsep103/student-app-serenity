package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBase;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay
 */
/*@Concurrent(threads = "4x")
@UseTestDataFrom("src/test/resources/testdata/studentinfo.csv")
@RunWith(SerenityParameterizedRunner.class)*/
public class CreateStudentDataDrivenTest extends TestBase {

    private String firstName;
    private String lastName;
    private String email;
    private String programme;
    private String course1;
    private String course2;

    @Steps
    StudentSteps steps;

    @Title("Data driven test for adding multiple students to the application")
    @Test
    public void createMultipleStudents(){
        List<String> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        steps.createStudent(firstName, lastName, email, programme, courses).statusCode(201);
    }

}
