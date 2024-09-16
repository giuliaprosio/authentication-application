package com.springapplication.userapp.model;

/**
 * Sealed interface with two specific types: NoUsername and DuplicatedUsername
 * This approach ensures only two errors occur
 */
public sealed interface UserError permits UserError.NoUsername, UserError.DuplicatedUsername {

    record NoUsername() implements UserError {
        @Override
        public String toString() {
            return "Username is required.";
        }
    }

    record  DuplicatedUsername() implements UserError {
        @Override
        public String toString() {
            return "Username already taken";
        }
    }
}
