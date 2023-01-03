package kz.mathncode.backend.baizhumanov_alisher.users;

import kz.mathncode.backend.baizhumanov_alisher.messages.Post;

public class Employer extends User {
    private Post post;

    public Employer(String nickname, String password) {
        super(nickname, password);
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
