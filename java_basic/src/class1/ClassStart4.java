package class1;

public class ClassStart4 {
    public static void main(String[] args) {
        Student student1 = new Student();
        student1.name = "학생1";
        student1.age = 17;
        student1.grade = 90;

        Student student2 = new Student();
        student2.name = "학생1";
        student2.age = 17;
        student2.grade = 90;

        Student[] students = new Student[2];
        students[0] = student1;
        students[1] = student2;
    }
}
