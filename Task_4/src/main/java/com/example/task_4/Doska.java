package com.example.task_4;

public class Doska {
    int id;
    int length;
    int width;


    public Doska (int id, int length, int width) {
        this.id = id;
        this.length = length;
        this.width = width;
    }

    @Override
    public String toString() {
        return "Doska id=" + id + ", length=" + length + ", width=" + width +"\n";
    }

    public int getId() {
        return id;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
}
