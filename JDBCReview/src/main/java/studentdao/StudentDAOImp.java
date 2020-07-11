package studentdao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-25 0:07
 */
public class StudentDAOImp extends BaseDAO implements StudentDAO {
    @Override
    public int addStudent(Connection connection) {
        Scanner scanner = null;
        int update = 0;
        try {
            scanner = new Scanner(System.in);
            System.out.println("请输入考生的详细信息\n");
            System.out.print("Type：");
            int type = scanner.nextInt();
            System.out.print("IDCard：");
            String IDCard = scanner.next();
            System.out.print("ExamCard：");
            String examCard = scanner.next();
            System.out.print("StudentName：");
            String studentName = scanner.next();
            System.out.print("Location：");
            String location = scanner.next();
            System.out.print("Grade：");
            int grade = scanner.nextInt();

            String sql = "insert into examstudent(Type,IDCard,ExamCard,StudentName,Location,Grade) values (?,?,?,?,?,?)";

            update = update(connection, sql, type, IDCard, examCard, studentName, location, grade);

            System.out.println();
            System.out.println("信息录入成功！");
        } catch (SQLException e) {
            System.out.println("信息录入失败！");
            e.printStackTrace();
        } finally {
            scanner.close();
        }

        return update;

    }

    @Override
    public int deleteByExamCard(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        int result = -1;

        while (true){

            System.out.println("请输入学生的考号：");
            String exam = scanner.next();

            String sql = "delete from examstudent where ExamCard = ?";
            try {
                result = update(connection, sql, exam);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (result == 0){
                System.out.println("查无此人，请重新输入！");
            }else if (result == 1){
                System.out.println("删除成功！");
                break;
            }
        }

        return result;

    }

    @Override
    public void getStudent(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        String result;

        while (true){
            System.out.println("请选择输入类型：");
            System.out.println("a:准考证号");
            System.out.println("b:身份证号");
            result = scanner.next();

            if (!"a".equalsIgnoreCase(result) && !"b".equalsIgnoreCase(result)) {
                System.out.println("您的输入有误，请重新输入");
            }else {
                break;
            }
        }

        StudentBean instance = null;
        switch (result){

            case "a":
                while (true) {
                    System.out.println("请输入准考证号：");
                    String exam = scanner.next();

                    String sql = "select * from examstudent where ExamCard = ?";

                    try {
                        instance = getInstance(connection, sql, StudentBean.class, exam);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    if (instance == null){
                        System.out.println("查无此人，请重新输入");
                    }else {
                        break;
                    }
                }
            break;
            case "b":
                while (true) {
                    System.out.println("请输入身份证号：");
                    String ID = scanner.next();

                    String sql = "select * from examstudent where IDCard = ?";

                    try {
                        instance = getInstance(connection, sql, StudentBean.class, ID);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    if (instance == null){
                        System.out.println("查无此人，请重新输入");
                    }else {
                        break;
                    }
                }
            break;

        }

        System.out.println("-----------------查询结果-----------------");
        System.out.println("流水号：" + instance.getFlowID());
        System.out.println("四级/六级：" + instance.getType());
        System.out.println("身份证号：" + instance.getIDCard());
        System.out.println("准考证号：" + instance.getExamCard());
        System.out.println("学生姓名：" + instance.getStudentName());
        System.out.println("区域：" + instance.getLocation());
        System.out.println("成绩：" + instance.getGrade());


    }


}
