package io.pax.forum.domain;

/**
 * Created by AELION on 16/02/2018.
 */
public class Comment {


        int id;
        String content;
        User user;


        public Comment (){}

        public Comment(int id, String content) {
            this.id = id;
            this.content = content;

        }

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return this.content;
        }
}

