package com.example;

import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Writer {
    public static void writeToFile(Object data, String fileName) {
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
            objectWriter.writeValue(new FileWriter(fileName), data);
            System.out.println("Successfully wrote data to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
