package zzyzzy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class J29JDBC {
    private static String DRV = "org.mariadb.jdbc.Driver";
    private static String URL = "jdbc:mariadb://fullstacks.chfcmpaskysu.ap-northeast-2.rds.amazonaws.com:3306/fullstacks";
    private static String USR = "admin";
    private static String PWD = "fullstack_2023";

    private static String selectBookSQL =
        "select * from newbooks where title like ? order by bookno desc";

    public static void main(String[] args) {
        // newbooks 테이블에서 도서명에 'IT CookBook'이 포함된 레코드 조회
        List<Book> bookdata = new ArrayList<>();

        // 검색할 도서명을 입력받음
        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 도서명은? ");
        String findbook = sc.nextLine();

        try {
            Class.forName(DRV);
        } catch (ClassNotFoundException e) {
            System.out.println("mariadb 용 JDBC 드라이버가 없어요!!");
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(URL, USR, PWD);
            pstmt = conn.prepareStatement(selectBookSQL);
            pstmt.setString(1, "%"+ findbook +"%");

            // SQL문 실행 후 결과집합 받음
            rs = pstmt.executeQuery();  // DML 실행시 사용

            while(rs.next()) {
                Book book = new Book(rs.getInt(1),
                    rs.getString(2), rs.getString(3),
                    rs.getInt(4), rs.getString(5));
                bookdata.add(book);
            }

        } catch (SQLException e) {
            System.out.println("디비 접속주소나 아이디/비번, SQL문을 확인하세요!!");
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception ex) {}
            if (pstmt != null) try { pstmt.close(); } catch (Exception ex) {}
            if (conn != null) try { conn.close(); } catch (Exception ex) {}
        }

        // 도서정보 출력
        for (Book b : bookdata) {
            System.out.println(b);
        }

    }
}

