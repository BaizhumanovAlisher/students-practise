package kz.mathncode.backend.baizhumanov_alisher.users;

import kz.mathncode.backend.baizhumanov_alisher.messages.Resume;

public class Student extends User {
    private Resume resume;
    public Student(String nickname, String password) {
        super(nickname, password);
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }
}
