package client;

import dao.AlbumDao;

import java.rmi.Naming;
import java.util.Scanner;

public class Client {

    private static final String URL = "rmi://HOANGPHUC:6541/";

    public static void main(String[] args) {
        try {
            AlbumDao albumDao = (AlbumDao) Naming.lookup(URL + "albumDao");

            Scanner scanner = new Scanner(System.in);
            int option;

            while (true) {
                System.out.println("Vui lòng chọn một trong các lựa chọn sau:");
                System.out.println("1. Cập nhật giá của album.");
                System.out.println("2. Hiển thị tất cả album.");
                System.out.println("3. Hiển thị album theo thể loại và năm.");
                System.out.println("4. Hiển thị số lượng album theo thể loại.");
                System.out.println("5. Thoát.");
                System.out.println("Nhập lựa chọn của bạn: ");

                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Nhập id của album: ");
                        String id = scanner.next();
                        System.out.println("Nhập giá mới: ");
                        double newPrice = scanner.nextDouble();
                        albumDao.updatePriceOfAlbum(id, newPrice);
                        break;
                    case 2:
                        albumDao.getAllAlbum().forEach(x -> System.out.println(x));
                        break;
                    case 3:
                        System.out.println("Nhập thể loại: ");
                        String genre = scanner.next();
                        System.out.println("Nhập năm: ");
                        int year = scanner.nextInt();
                        albumDao.listAlbumByGenreAndYear(genre, year).forEach(x -> System.out.println(x));
                        break;
                    case 4:
                        albumDao.getNumberOfAlbumByGenre().forEach((k, v) -> System.out.println(k + ": " + v));
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
