package class1;

public class ClassStart2 {
    public static void main(String[] args) {
        String[] stuNames = {"학생1", "학생2"};
        int[] stuAges = {18, 18};
        int[] stuGrades = {90, 100};

        for (int i = 0; i < stuNames.length; i++) {
            System.out.println("이름 : "+ stuNames[i]+" 나이 : "+stuAges[i]+" 성적 : "+stuGrades[i]);
        }
    }
}
