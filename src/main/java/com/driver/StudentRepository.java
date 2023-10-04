package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    private  final HashMap<String,Student> sdb = new HashMap<>();
    private  final  HashMap<String ,Teacher> tdb = new HashMap<>();
    private  final HashMap<String,String> stdb = new HashMap<>();

    public void addStudent(Student student) {
        sdb.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        tdb.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if(sdb.containsKey(student) && tdb.containsKey(teacher)){
            stdb.put(student,teacher);
        }
    }

    public Student getStudentByName(String name) {
        return sdb.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return tdb.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        List<String> stname = new ArrayList<>();
        for(String key : sdb.keySet()){
            if(key.equals(teacher)){
                stname.add(key);
            }
        }
        return stname;
    }

    public List<String> getAllStudents() {
        List<String> allstudents = new ArrayList<>();
        for(String s : sdb.keySet()){
            allstudents.add(s);
        }
        return allstudents;
    }

    public void deleteTeacherByName(String teacher) {
        String teacTo_student = null;
        teacTo_student = stdb.get(teacher);
        stdb.remove(teacher);
        sdb.remove(teacTo_student);
        tdb.remove(teacher);
    }

    public void deleteAllTeachers() {
        for(String key : tdb.keySet()){
            tdb.remove(key);
            String stud = stdb.get(key);
            stdb.remove(key);
            sdb.remove(stud);

        }
    }
}