package com.example.crud_test;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
// http://localhost:8080/student/create?name=Abel%20Michael&matricNumber=2018377&department=Stat
// http://localhost:8080/students
// http://localhost:8080/student/get/ID/1
// http://localhost:8080/student/get/NAME/Abel%20Michael
// http://localhost:8080/student/delete/ID/1
// http://localhost:8080/student/delete/NAME/Abel%20Michael
// http://localhost:8080/student/update/ID/3?name=Agba%20Man&matricNumber=04020108&department=👀
// http://localhost:8080/student/update/NAME/Agba%20Man?name=Agba%20Mano&matricNumber=24020108
@RestController
public class StudentController {
    List<Student> students = new ArrayList<>();
    private final AtomicLong constId = new AtomicLong();
    @RequestMapping(method = RequestMethod.GET, path = "/student/get/{valueType}/{value}")
    public Object getStudent(@PathVariable("valueType") QueryDataType valueType, @PathVariable("value") String value) {
        if (valueType == QueryDataType.ID){
            // get user by Id
            if (getUserIdFromId(value) == -1)
                return "User Does Not exist";
            return students.get(getUserIdFromId(value));
        }  else if (valueType == QueryDataType.NAME){
            // get user by name
            if (getUserIdFromName(value) == -1)
                return "User Does Not exist";
            return students.get(getUserIdFromName(value));
        }
        return null;
    }
    @RequestMapping(method = RequestMethod.GET, path = "/students")
    public Object students() {
        if (students.isEmpty())
            return "No Registered User";
        return students;
    }
    @RequestMapping(method = RequestMethod.GET, path = "/student/create")
    public String student(@RequestParam(value = "name") String name, @RequestParam(value = "matricNumber") long matricNumber, @RequestParam(value = "department") String department) {
        Student student = new Student(constId.incrementAndGet(), name, matricNumber, department);
        students.add(student);
        return "Student Created Successfully";
    }
    @RequestMapping(method = RequestMethod.GET, path = "/student/delete/{valueType}/{value}")
    public String deleteStudent(@PathVariable("valueType") QueryDataType valueType, @PathVariable("value") String value) {
        if (valueType == QueryDataType.ID){
            // delete user by Id
            if (getUserIdFromId(value) == -1)
                return "User Does Not exist";
            deleteStudentMain(getUserIdFromId(value));
            return "Deleted Successfully";
        }  else if (valueType == QueryDataType.NAME){
            // delete user by name
            if (getUserIdFromName(value) == -1)
                return "User Does Not exist";
            deleteStudentMain(getUserIdFromName(value));
            return "Deleted Successfully";
        }
        return "Error Deleting";
    }
    private void deleteStudentMain(int id) {
        students.remove(id);
    }
    @RequestMapping(method = RequestMethod.GET, path = "/student/update/{valueType}/{value}")
    public String updateStudent(@PathVariable("valueType")QueryDataType valueType, @PathVariable("value") String value, @RequestParam(value = "name") @Nullable String name, @RequestParam(value = "matricNumber") @Nullable String matricNumber, @RequestParam(value = "department") @Nullable String department){
        if (valueType == QueryDataType.ID){
            // update user by Id
            if (getUserIdFromId(value) == -1)
                return "User Does Not exist";
            updateStudentMain(getUserIdFromId(value), name, matricNumber, department);
            return "Student updated Successfully";
        }  else if (valueType == QueryDataType.NAME){
            // update user by name
            if (getUserIdFromName(value) == -1)
                return "User Does Not exist";
            updateStudentMain(getUserIdFromName(value), name, matricNumber, department);
            return "Student updated Successfully";
        }
        return "User Does Not exist";
    }

    private void updateStudentMain(int i, @Nullable String name, @Nullable String matricNumber, @Nullable String department) {
        if (name != null)
            students.get(i).setName(name);
        if (matricNumber != null)
            students.get(i).setMatricNumber(Integer.parseInt(matricNumber));
        if (department != null)
            students.get(i).setDepartment(department);
    }

    private int getUserIdFromId(String idM){
        long id = Long.parseLong(idM);
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }
    private int getUserIdFromName(String name){
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }
        @RequestMapping(method = RequestMethod.GET, path = "/error")
    public String Error(){
             return "ERROR";
    }
}