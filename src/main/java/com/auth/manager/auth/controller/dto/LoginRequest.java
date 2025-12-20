package com.auth.manager.auth.controller.dto;

public record LoginRequest (
    String email,
    String password
) {}
