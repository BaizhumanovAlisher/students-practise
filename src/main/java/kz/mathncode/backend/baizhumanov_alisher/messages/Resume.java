package kz.mathncode.backend.baizhumanov_alisher.messages;

import kz.mathncode.backend.baizhumanov_alisher.users.Student;

public class Resume extends Message {
    public Resume(String text, Student student) {
        super(text, student);
    }
}
