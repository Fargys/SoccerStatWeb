package ConsoleHelper;

import java.io.*;

public class ConsoleHelper implements Closeable {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String readString() {
        while (true) {
            try {
                return reader.readLine();
            } catch (IOException e) {
                System.out.println("Введите корректные данные");
            }
        }
    }

    public static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                System.out.println("Введите корректные данные");
            }
        }
    }

    /**
     * The method specifies the range of allowed values
     */
    public static int readInt(int from, int to) {
        while (true) {
            try {
                int result = Integer.parseInt(reader.readLine());
                if (result < from && result > to) throw new IOException();
                return result;
            } catch (IOException e) {
                System.out.println("Введите корректные данные");
            }
        }
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}

