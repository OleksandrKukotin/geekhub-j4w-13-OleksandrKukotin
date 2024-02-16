package org.geekhub.hw11.entity;

import org.springframework.lang.NonNull;

public record User(Long userId, @NonNull String userName, @NonNull String email) {

}
