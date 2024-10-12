package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = "         "; // Başlangıçta tüm hücreler boş (boşluk)
        char currentPlayer = 'X'; // Sırasıyla 'X' ve 'O' kullanacağız

        // Tahtayı yazdır
        printBoard(s);

        // Kullanıcıdan hamle yapmasını iste
        while (true) {
            String input = scanner.nextLine();

            if (!isValidInput(input)) {
                System.out.println("You should enter numbers!");
                continue;
            }

            String[] parts = input.split(" ");
            int r = Integer.parseInt(parts[0]);
            int c = Integer.parseInt(parts[1]);

            if (!isValidCoordinate(r, c)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            int position = (r - 1) * 3 + (c - 1);

            // Kontrolü ' ' karakterine göre yap
            if (s.charAt(position) != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                // Hamle yap
                StringBuilder sb = new StringBuilder(s);
                sb.setCharAt(position, currentPlayer); // 'X' veya 'O' koy
                s = sb.toString();
                printBoard(s);

                // Oyun durumunu kontrol et ve bitiş durumunu kontrol et
                if (checkGameStatus(s)) {
                    break; // Oyun bitti, döngüyü sonlandır
                }

                // Sıradaki oyuncuyu değiştir
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    // Tahtayı yazdıran metot
    public static void printBoard(String s) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                char cell = s.charAt(i * 3 + j);
                System.out.print((cell == ' ' ? ' ' : cell) + " "); // Boş hücreyi boş bırak
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    // Geçerli koordinat kontrolü
    public static boolean isValidCoordinate(int row, int col) {
        return (row >= 1 && row <= 3) && (col >= 1 && col <= 3);
    }

    // Kullanıcı girişinin geçerli olup olmadığını kontrol et
    public static boolean isValidInput(String input) {
        return input.matches("\\d \\d") || input.matches("\\d\\s\\d");
    }

    // Oyun durumunu kontrol et ve oyunun bitip bitmediğini döndür
    public static boolean checkGameStatus(String s) {
        int xs = 0, os = 0;

        // X'leri ve O'ları say
        for (char c : s.toCharArray()) {
            if (c == 'X') {
                xs++;
            } else if (c == 'O') {
                os++;
            }
        }

        // Geçersiz durum kontrolü
        if (Math.abs(xs - os) > 1) {
            System.out.println("Impossible");
            return true; // Oyun bitiyor
        }

        // Kazanan kontrolü
        boolean xWins = checkWin(s, 'X');
        boolean oWins = checkWin(s, 'O');

        if (xWins && oWins) {
            System.out.println("Impossible");
            return true; // Oyun bitiyor
        } else if (xWins) {
            System.out.println("X Wins");
            return true; // Oyun bitiyor
        } else if (oWins) {
            System.out.println("O Wins");
            return true; // Oyun bitiyor
        } else if (xs + os == 9) {
            System.out.println("Draw");
            return true; // Oyun bitiyor
        }
        return false; // Oyun devam ediyor
    }

    public static boolean checkWin(String s, char player) {
        // Kazanma koşullarını kontrol et
        for (int i = 0; i < 3; i++) {
            // Satır kontrolü
            if (s.charAt(i * 3) == player && s.charAt(i * 3 + 1) == player && s.charAt(i * 3 + 2) == player) {
                return true;
            }
            // Sütun kontrolü
            if (s.charAt(i) == player && s.charAt(i + 3) == player && s.charAt(i + 6) == player) {
                return true;
            }
        }
        // Çapraz kontrolü
        if (s.charAt(0) == player && s.charAt(4) == player && s.charAt(8) == player) {
            return true;
        }
        if (s.charAt(2) == player && s.charAt(4) == player && s.charAt(6) == player) {
            return true;
        }

        return false; // Hiçbiri kazanmadı
    }
}
