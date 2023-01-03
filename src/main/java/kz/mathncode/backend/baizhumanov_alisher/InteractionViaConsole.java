package kz.mathncode.backend.baizhumanov_alisher;

import kz.mathncode.backend.baizhumanov_alisher.messages.Message;
import kz.mathncode.backend.baizhumanov_alisher.messages.Post;
import kz.mathncode.backend.baizhumanov_alisher.messages.Resume;
import kz.mathncode.backend.baizhumanov_alisher.users.Employer;
import kz.mathncode.backend.baizhumanov_alisher.users.Student;
import kz.mathncode.backend.baizhumanov_alisher.users.User;

import java.util.List;
import java.util.Scanner;

public class InteractionViaConsole {
    public static Scanner sc = new Scanner(System.in);
    public static String exit = "e";
    public static String revert = "r";
    public static void mainThread() {
        System.out.println("Important commands:");
        System.out.println("e - exit");
        System.out.println("r - revert");

        while (true) {
            try {
                onePassCycle();
            } catch (Exception e) {
                if (e.getMessage().equals(exit)) {
                    break;
                }
            }
        }
    }

    private static void onePassCycle() {
        int eventNum = chooseEvent();

        if (eventNum == 1) {
            User user = login();
            userOpportunity(user);
        } else if (eventNum == 2) {
            createUser();
        } else {
            System.out.println("Invalid answer. Try again.");
        }
    }

    private static void userOpportunity(User user) {
        System.out.println("Welcome " + user.getNickname());
        while (true) {
            System.out.println("You can:");
            System.out.println("1 - set new nickname");
            System.out.println("2 - set new password");
            System.out.println("3 - set new own message");
            System.out.println("4 - get all available messages");

            String answer = nextLine();

            oneCycleInteration(user, answer);
        }
    }

    private static void oneCycleInteration(User user, String answer) {
        switch (answer) {
            case "1":
                String newNickname = nextLine();
                if (DataBase.isValidNickname(newNickname)) {
                    user.setNickname(newNickname);
                    System.out.println("The new nickname has been successfully installed.");
                }
                break;
            case "2":
                String newPassword = nextLine();
                user.setPassword(newPassword);
                System.out.println("The new password has been successfully installed.");
                break;
            case "3":
                if (user instanceof Student student) {
                    System.out.println("Write your resume.");
                    String resumeString = nextLine();
                    Resume resume = new Resume(resumeString, student);
                    student.setResume(resume);
                } else if (user instanceof Employer employer) {
                    System.out.println("Write your post.");
                    String postString = nextLine();
                    System.out.println("Write your salary.");
                    int salary = Integer.parseInt(nextLine());

                    Post post = new Post(postString, salary, employer);
                    employer.setPost(post);
                }
                break;
            case "4":
                List<Message> messages = DataBase.getMessages(user);
                System.out.println(messages);
                break;
            default:
                System.out.println("Invalid answer. Try again.");
        }
    }

    private static void createUser() {
        System.out.println("Creating new User.");

        while (true) {
            System.out.println("Enter your type: Student / Employer? (st/em)");
            String type = nextLine();
            boolean isStudent;

            if (type.equals("st")) {
                isStudent = true;
            } else if (type.equals("em")) {
                isStudent = false;
            } else {
                System.out.println("Invalid type. Try again.");
                continue;
            }

            System.out.println("Enter new nickname.");
            String nickname = nextLine();
            if (!DataBase.isValidNickname(nickname)) {
                System.out.println("Invalid nickname. Try again.");
                continue;
            }
            System.out.println("Enter new password.");
            String password = nextLine();

            if (isStudent) {
                DataBase.addUser(new Student(nickname, password));
            } else {
                DataBase.addUser(new Employer(nickname, password));
            }

            System.out.println("The user has been successfully created.");
            break;
        }
    }

    private static int chooseEvent() {
        while (true) {
            System.out.println("Choose event:");
            System.out.println("1 - log in");
            System.out.println("2 - sign up");

            String answer = nextLine();


            if (answer.equals("1")) {
                return 1;
            } else if (answer.equals("2")) {
                return 2;
            }

            System.out.println("Incorrect answer. Try again.");
        }
    }

    private static User login() {
        while (true) {
            System.out.println("Enter a nickname:");
            String nickname = nextLine();

            System.out.println("Enter a password:");

            String password = nextLine();

            User result = DataBase.isRealUser(nickname, password);

            if (result != null) {
                return result;
            } else {
                System.out.println("Invalid nickname or password.Try again.");
            }
        }
    }

    private static String nextLine() {
        String line = sc.nextLine();

        if (line.equals(exit)) {
            throw new RuntimeException(exit);
        } else if (line.equals(revert)) {
            throw new RuntimeException(revert);
        }

        return line;
    }
}
