package kz.mathncode.backend.baizhumanov_alisher.messages;

import kz.mathncode.backend.baizhumanov_alisher.users.Employer;

public class Post extends Message {
    private final Employer employer;
    private int salary;

    public Post(String text, int salary, Employer employer) {
        super(text, employer);
        this.salary = salary;
        this.employer = employer;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Employer getEmployer() {
        return employer;
    }
}
