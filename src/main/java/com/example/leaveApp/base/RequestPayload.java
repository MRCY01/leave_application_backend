package com.example.leaveApp.base;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.Session;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestPayload {

    private JsonNode echo;
    private RequestContainer requestContainer;
    private String path;
    private Session session;
    private HttpServletRequest httpServletRequest;
    private ArrayList<String> key;

}
