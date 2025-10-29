package com.softserve.academy.HW_18_OKHTTP;

import okhttp3.*;

public abstract class BaseApiTest {
    protected static final OkHttpClient client = new OkHttpClient();
    protected static final String BASE_URL = "https://greencity.greencity.cx.ua";

    // ================= GET =================
    protected Response sendGetRequest(String endpoint) throws Exception {
        Request request = new Request.Builder()
                .url(BASE_URL + endpoint)
                .get()
                .build();

        return client.newCall(request).execute();
    }
    // ================= POST =================
    protected Response sendPostRequest(String endpoint, String jsonBody) throws Exception {
        RequestBody body = RequestBody.create(
                jsonBody,                          // саме тут — тіло запиту
                MediaType.get("application/json")  // тип контенту, який ми відправляємо
        );

        Request request = new Request.Builder()
                .url(BASE_URL + endpoint)
                .post(body)
                .build();

        return client.newCall(request).execute();
    }

    // ================= PUT =================
    protected Response sendPutRequest(String endpoint, String jsonBody) throws Exception {
        RequestBody body = RequestBody.create(
                jsonBody,
                MediaType.get("application/json")
        );

        Request request = new Request.Builder()
                .url(BASE_URL + endpoint)
                .put(body)
                .build();

        return client.newCall(request).execute();
    }

    // ================= PATCH =================
    protected Response sendPatchRequest(String endpoint, String jsonBody) throws Exception {
        RequestBody body = RequestBody.create(
                jsonBody,
                MediaType.get("application/json")
        );

        Request request = new Request.Builder()
                .url(BASE_URL + endpoint)
                .patch(body)
                .build();

        return client.newCall(request).execute();
    }

    // ================= DELETE =================
    protected Response sendDeleteRequest(String endpoint) throws Exception {
        Request request = new Request.Builder()
                .url(BASE_URL + endpoint)
                .delete()
                .build();

        return client.newCall(request).execute();
    }

}
