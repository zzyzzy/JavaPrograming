package zzyzzy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class J27JDBC {

    private static String insertBookSQL =
            "insert into newbooks (title, writer, price) values (?,?,?)";

    public static void main(String[] args) {
        // newbooks 테이블에 도서정보 저장
        Scanner sc = new Scanner(System.in);
        System.out.print("도서명은? ");
        String bkname = sc.next();
        System.out.print("저자는? ");
        String author = sc.next();
        System.out.print("가격은? ");
        int price = sc.nextInt();

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 데이터베이스 접속
            conn = J32JDBCUtil.makeConn();

            // 실행할 SQL문 생성
            pstmt = conn.prepareStatement(insertBookSQL);
            // 실행할 SQL문의 placeholder에 값 전달
            pstmt.setString(1, bkname);
            pstmt.setString(2, author);
            pstmt.setInt(3, price);

            // SQL문 실행 후 결과 확인
            int cnt = pstmt.executeUpdate();  // DML 실행시 사용
            System.out.println("데이터 입력확인 : " + cnt);

        } catch (SQLException e) {
            System.out.println("디비 접속주소나 아이디/비번, SQL문을 확인하세요!!");
            System.out.println(e.getMessage());
        } finally {
            J32JDBCUtil.closeConn(null, pstmt, conn);
        }

    }
}
