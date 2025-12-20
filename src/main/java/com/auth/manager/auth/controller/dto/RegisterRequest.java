package com.auth.manager.auth.controller.dto;

public record RegisterRequest(
        String email,
        String password,
        String name
) {}