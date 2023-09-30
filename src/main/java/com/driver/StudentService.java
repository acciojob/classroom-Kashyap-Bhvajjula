package com.driver;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    private final Map<String, Student> students = new HashMap<>();
    private final Map<String, Teacher> teachers = new HashMap<>();
    private final Map<String, List<String>> teacherToStudents = new HashMap<>();

    public void addStudent(Student student) {
        students.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String studentName, String teacherName) {
        teacherToStudents.computeIfAbsent(teacherName, k -> new ArrayList<>()).add(studentName);
    }

    public Student getStudentByName(String name) {
        return students.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teachers.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return teacherToStudents.getOrDefault(teacher, new ArrayList<>());
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(students.keySet());
    }

    public void deleteTeacherByName(String teacherName) {
        teachers.remove(teacherName);
        teacherToStudents.remove(teacherName);
    }

    public void deleteAllTeachers() {
        teachers.clear();
        teacherToStudents.clear();
    }
}
