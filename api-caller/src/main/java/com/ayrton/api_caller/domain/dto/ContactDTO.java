package com.ayrton.api_caller.domain.dto;
import java.util.Date;

public record ContactDTO (Long id, String name, String email, String source, Date createdAt, Date updatedAt){}

