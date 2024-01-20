package com.studentapp.studentinfo;

import com.studentapp.constants.EndPoints;
import com.studentapp.model.StudentPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jay
 */
public class StudentSteps {

    @Step("Creating student with firstName : {0}, lastName : {1}, email : {2}, programme : {3} and courses : {4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme,
                                             List<String> courseList) {
        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body(studentPojo)
                .post()
                .then().log().all();
    }

    @Step("Getting the Student information with firstName : {0}")
    public HashMap<String, Object> getStudentInfoByFirstName(String firstName) {
        String s1 = "findAll{it.firstName == '";
        String s2 = "'}.get(0)";

        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then().statusCode(200)
                .extract()
                .path(s1 + firstName + s2);
    }

    @Step("Updating student information with studentId : {0}, firstName : {1}, lastName : {2}, email : {3}, programme : {4} and courses : {5}")
    public ValidatableResponse updateStudent(int studentId, String firstName, String lastName,
                                             String email, String programme, List<String> courseList) {
        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("studentID", studentId)
                .body(studentPojo)
                .when()
                .put(EndPoints.UPDATE_STUDENT_BY_ID)
                .then().log().all();
    }

    @Step("Deleting student information with studentId : {0}")
    public ValidatableResponse deleteStudent(int studentId){
        return SerenityRest.given().log().all()
                .pathParam("studentID", studentId)
                .when()
                .delete(EndPoints.DELETE_STUDENT_BY_ID)
                .then();
    }

    @Step("Getting student information with studentId : {0}")
    public ValidatableResponse getStudentInfoById(int studentId){
        return SerenityRest.given().log().all()
                .pathParam("studentID", studentId)
                .when()
                .get(EndPoints.GET_SINGLE_STUDENT_BY_ID)
                .then();
    }
}
