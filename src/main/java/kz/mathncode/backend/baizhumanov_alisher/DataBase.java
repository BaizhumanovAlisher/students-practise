package kz.mathncode.backend.baizhumanov_alisher;

import kz.mathncode.backend.baizhumanov_alisher.messages.Message;
import kz.mathncode.backend.baizhumanov_alisher.messages.Post;
import kz.mathncode.backend.baizhumanov_alisher.messages.Resume;
import kz.mathncode.backend.baizhumanov_alisher.users.Employer;
import kz.mathncode.backend.baizhumanov_alisher.users.Student;
import kz.mathncode.backend.baizhumanov_alisher.users.User;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static final ArrayList<User> users;

    private DataBase() {}

    static {
        users = new ArrayList<>();

        fillTestArrayListUsers(users);
    }

    private static void fillTestArrayListUsers(ArrayList<User> users) {
        Employer employer1 = new Employer("Michael Gibson", "1234");
        User employer2 = new Employer("Bob Soto", "1234");
        User employer3 = new Employer("Victoria Phillips", "1234");
        Student student1 = new Student("Paul Smith", "1234");
        Student student2 = new Student("Jennifer Williams", "1234");
        User student3 = new Student("Clarence Walker", "1234");
        User student4 = new Student("Raymond Perez", "1234");
        User student5 = new Student("Peggy Reed", "1234");
        User student6 = new Student("Megan Bennett", "1234");

        users.add(employer1);
        users.add(employer2);
        users.add(employer3);
        users.add(student1);
        users.add(student2);
        users.add(student3);
        users.add(student4);
        users.add(student5);
        users.add(student6);

        Post post = new Post("new job in bakery", 120, employer1);
        employer1.setPost(post);

        Resume resume1 = new Resume("It's student1", student1);
        student1.setResume(resume1);

        Resume resume2 = new Resume("It's student2", student2);
        student2.setResume(resume2);
    }

    public static User isRealUser(String username, String password) {
        for (User user : users) {
            if (user.getNickname().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    public static List<Message> getMessages(User user) {
        List<Message> list = new ArrayList<>();

        if (user instanceof Employer) {
            for (User u : users) {
                if (u instanceof Student s) {
                    if (s.getResume() != null) {
                        list.add(s.getResume());
                    }
                }
            }
        } else if (user instanceof Student) {
            for (User u : users) {
                if (u instanceof Employer s) {
                    if (s.getPost() != null) {
                        list.add(s.getPost());
                    }
                }
            }
        }

        return list;
    }

    public static boolean isValidNickname(String name) {
        for (User u : users) {
            if (u.getNickname().equals(name)) {
                return false;
            }
        }

        return true;
    }

    public static void addUser(User u) {
        users.add(u);
    }
}
