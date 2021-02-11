package com.example.relearn_iteration_one;

import com.google.firebase.firestore.Exclude;

public class Register {


        private String fullname;
        private String email;
        private String password;

        public Register() {
            // Public no arg constructor for FireStore
        }

        @Exclude
        // prevents document Id from being requested as it is redundant data. idea from Coding in Flow  https://www.youtube.com/playlist?list=PLrnPJCHvNZuDrSqu-dKdDi3Q6nM-VUyxD

        public String getName() {
            return fullname;
        }

        public void setName(String name) {
            this.fullname = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    public Register(String name, String email, String password) {
            this.fullname = name;
            this.email = email;
            this.password = password;

        }
    }
