package com.prashanth.email.model;

import lombok.Data;

@Data
public class InputFields {
    String recipient;
    String subject;
    String name;
    String body;
    String bcc;
    String cc;

}
