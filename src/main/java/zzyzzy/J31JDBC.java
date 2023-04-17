package zzyzzy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class J31JDBC {

    private static String updateBookSQL =
        "update newbooks set title = ?, writer = ?, price = ? where bookno = ?";

    public static void main(String[] args) {
        // newbooks 테이블에서 입력받은
        // 도서정보(도서번호,도서명,저자,가격)를 이용해서 레코드 수정

        // 수정할 도서번호를 입력받음
        Scanner sc = new Scanner(System.in);
        System.out.println("수정할 도서번호는? ");
        int bookno = sc.nextInt();
        System.out.print("수정할 도서명은? ");
        String bkname = sc.next();
        System.out.print("수정할 저자는? ");
        String author = sc.next();
        System.out.print("수정할 가격은? ");
        int price = sc.nextInt();

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = J32JDBCUtil.makeConn();

            pstmt = conn.prepareStatement(updateBookSQL);
            pstmt.setString(1, bkname);
            pstmt.setString(2, author);
            pstmt.setInt(3, price);
            pstmt.setInt(4, bookno);

            // SQL문 실행 후 결과 확인
            int cnt = pstmt.executeUpdate();
            System.out.println("데이터 수정확인 : " + cnt);

        } catch (SQLException e) {
            System.out.println("디비 접속주소나 아이디/비번, SQL문을 확인하세요!!");
        } finally {
            J32JDBCUtil.closeConn(null, pstmt, conn);
        }

    }
}

