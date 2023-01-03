package storage;

import model.Resume;

public class ResumeStorage {
    public static final int MAX_LENTH = 10000;
    private Resume[] storage = new Resume[MAX_LENTH];
    private int size = 0;


    boolean isOverflow(){
        if (size == MAX_LENTH) {
            System.out.println("Storage overflow!");
            return true;
        }
        return false;
    }
    void showExist(String uuid){
        System.out.println("Sorry! but resume whith uuid " + uuid + " already exist!");
    }
    void showNotExist(String uuid){
        System.out.println("Sorry! but resume whith uuid " + uuid + " is absent!");
    }
    public void save(Resume r) {
        if(isOverflow()){
            return;
        }
        if (getIndex(r.getUuid()) < 0) {
            storage[size] = r;
            size++;
            return;
        }
        showExist(r.getUuid());
    }


    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            return;
        }
        showNotExist(uuid);
    }


    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
            return;
        }
        showNotExist(r.getUuid());
    }


    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        showNotExist(uuid);
        return null;
    }


    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }


    public int size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;

    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
