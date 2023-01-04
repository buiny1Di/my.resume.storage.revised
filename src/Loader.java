import model.Resume;
import storage.ResumeStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Loader {
    private final static ResumeStorage ARRAY_STORAGE = new ResumeStorage();
    private static  long TIMEOUT;

    

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume r;
        while (true) {
            System.out.print("Введите одну из команд - (list | save uuid | delete uuid | get uuid | update uuid | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 2) {
                System.out.println("Неверная команда.");
                continue;
            }
            String uuid = null;
            if (params.length == 2) {
                uuid = params[1].intern();
            }
            switch (params[0]) {
                case "list":
                    TIMEOUT = System.nanoTime();
                    printAll();
                    break;
                case "size":
                    TIMEOUT = System.nanoTime();
                    System.out.println(ARRAY_STORAGE.size());
                    System.out.println("----command done at " + (System.nanoTime() - TIMEOUT) + " Nanos----");
                    break;
                case "save":
                    TIMEOUT = System.nanoTime();
                    r = new Resume(uuid);
                    ARRAY_STORAGE.save(r);
                    printAll();
                    break;
                case "update":
                    TIMEOUT = System.nanoTime();
                    r = new Resume(uuid);
                    ARRAY_STORAGE.update(r);
                    printAll();
                    break;
                case "delete":
                    TIMEOUT = System.nanoTime();
                    ARRAY_STORAGE.delete(uuid);
                    printAll();
                    break;
                case "get":
                    TIMEOUT = System.nanoTime();
                    System.out.println(ARRAY_STORAGE.get(uuid));
                    break;
                case "clear":
                    TIMEOUT = System.nanoTime();
                    ARRAY_STORAGE.clear();
                    printAll();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }

    static void printAll() {
        Resume[] all = ARRAY_STORAGE.getAll();
        System.out.println("-----------------------------------------");
        if (all.length == 0) {
            System.out.println("Empty");
        } else {
            for (Resume r : all) {
                System.out.println(r);
            }
        }
        System.out.println("----command done at " + (System.nanoTime() - TIMEOUT) + " Nanos----");
    }
}
