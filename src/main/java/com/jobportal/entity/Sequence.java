package com.jobportal.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection="sequence")
@AllArgsConstructor
@NoArgsConstructor
public class Sequence {
    @Id
    private String id;
    private long seq;
}
