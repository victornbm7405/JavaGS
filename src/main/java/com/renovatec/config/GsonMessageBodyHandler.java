package com.renovatec.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.MessageBodyWriter;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces("application/json")
@Consumes("application/json")
public class GsonMessageBodyHandler implements MessageBodyWriter<Object>, MessageBodyReader<Object> {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, jakarta.ws.rs.core.MediaType mediaType) {
        return true;
    }

    @Override
    public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, jakarta.ws.rs.core.MediaType mediaType,
                           jakarta.ws.rs.core.MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException {
        try (Reader reader = new InputStreamReader(entityStream)) {
            return gson.fromJson(reader, genericType);
        }
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, jakarta.ws.rs.core.MediaType mediaType) {
        return true;
    }

    @Override
    public void writeTo(Object object, Class<?> type, Type genericType, Annotation[] annotations, jakarta.ws.rs.core.MediaType mediaType,
                        jakarta.ws.rs.core.MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException {
        try (Writer writer = new OutputStreamWriter(entityStream)) {
            gson.toJson(object, genericType, writer);
        }
    }

    @Override
    public long getSize(Object object, Class<?> type, Type genericType, Annotation[] annotations, jakarta.ws.rs.core.MediaType mediaType) {
        return -1;
    }
}
