package com.softserve.academy.OKHTTP01;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class EcoNewsCommentsTest {
    private static final OkHttpClient client = new OkHttpClient();


    @Test
    @DisplayName("Status code verifying")
    public void getActiveCommentsStatus200() throws Exception {
        String baseUrl = "https://greencity.greencity.cx.ua";
        int ecoNewsId = 124;
        int page = 0;
        int size = 2;

        String url = String.format("%s/eco-news/%d/comments/active?page=%d&size=%d", baseUrl, ecoNewsId, page, size);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            assertThat(response.code()).isEqualTo(200);

            if (response.body() != null) {
                System.out.println(response.body().string());
            }
        }
    }
}
