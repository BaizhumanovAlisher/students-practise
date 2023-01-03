package kz.mathncode.backend.baizhumanov_alisher.messages;

import kz.mathncode.backend.baizhumanov_alisher.users.User;

public abstract class Message {
    private String text;
    private final User user;

    public Message(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public User getUser() {
        return user;
    }
}
