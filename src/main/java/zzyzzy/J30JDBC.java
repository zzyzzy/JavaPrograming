package zzyzzy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class J30JDBC {

    private static String deleteBookSQL = "delete from newbooks where bookno = ?";

    public static void main(String[] args) {
        // newbooks 테이블에서 입력받은 도서번호를 이용해서 레코드 삭제

        // 삭제할 도서번호를 입력받음
        Scanner sc = new Scanner(System.in);
        System.out.println("삭제할 도서번호는? ");
        int bookno = sc.nextInt();

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = J32JDBCUtil.makeConn();

            pstmt = conn.prepareStatement(deleteBookSQL);
            pstmt.setInt(1, bookno);

            // SQL문 실행 후 결과 확인
            int cnt = pstmt.executeUpdate();
            System.out.println("데이터 삭제확인 : " + cnt);

        } catch (SQLException e) {
            System.out.println("디비 접속주소나 아이디/비번, SQL문을 확인하세요!!");
        } finally {
            J32JDBCUtil.closeConn(null, pstmt, conn);
        }

    }
}

