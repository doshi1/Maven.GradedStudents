package io.zipcoder;

import java.util.*;

public class Classroom {
    private List<Student> studentList;
    private int maxNumberOfStudents;
    private int numberOfStudents;
    public Classroom() {
        this(30);
    }

    public Classroom(int maxNumberOfStudents) {
        this.maxNumberOfStudents = maxNumberOfStudents;
        studentList = new ArrayList<Student>(Arrays.asList(new Student[maxNumberOfStudents]));
    }

    public Classroom(Student[] studentArray) {
        this();
        if(studentArray != null) {
            this.studentList = new ArrayList<Student>(Arrays.asList(studentArray));
            maxNumberOfStudents = studentList.size();
            numberOfStudents = studentList.size();
        }

    }

    public Student[] getStudents(){
        return studentList.toArray(new Student[studentList.size()]);
    }

    double sum = 0;
    public double getAverageExamScore(){
        studentList.forEach(student -> sum += student!= null ? student.getAverageExamScore() : 0);
        return sum / maxNumberOfStudents;
    }

    public void addStudent(Student student){
        if(student != null && numberOfStudents < maxNumberOfStudents) {
            studentList.set(numberOfStudents, student);
            numberOfStudents++;
        }
    }

    public void removeStudent(String firstName, String lastName){
        Student studentToBeRemoved = new Student(firstName, lastName, null);
        if(studentList.contains(studentToBeRemoved)){
            studentList.remove(studentToBeRemoved);
        }
    }

    public Student[] getStudentsByScore(){
        ArrayList<Student> tempList = new ArrayList<>(studentList);
        Collections.sort(tempList, new ExamScoreComparator());
        return tempList.toArray(new Student[tempList.size()]);
    }

    public Map<Character, Collection<Student>> getGradeBook(){
        ArrayList<Student> tempList = new ArrayList<>(studentList);
        Map gradeMap = new HashMap<Character, Collection<Student>>();

        Collections.sort(tempList, new ExamScoreComparator());
        for(int i = 0; i < tempList.size(); i++){
            Student student = tempList.get(i);
            if(student != null) {
                student.setGrade(i + 1, tempList.size());
                putStudentToMap(gradeMap, student);
            }
        }

        return gradeMap;
    }
    private void putStudentToMap(Map<Character, Collection<Student>> map, Student student){
        if(map.containsKey(student.getGrade())){
            map.get(student.getGrade()).add(student);
        }else{
            Set<Student> set = new HashSet<Student>();
            set.add(student);
            map.put(student.getGrade(), set);
        }
    }

}
