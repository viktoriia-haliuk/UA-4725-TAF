package com.softserve.academy.HW_18_OKHTTP;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class EcoNewsCommentsTest extends BaseApiTest{
    private final Gson gson = new Gson();
    private String buildActiveCommentsEndpoint(int ecoNewsId, int page, int size){
return String.format("/eco-news/%d/comments/active?page=%d&size=%d", ecoNewsId, page, size);
    }

    // ============================================================
    // 1. Status code verifying
    // ============================================================

    @Test
    @DisplayName("Verify 200 status code for active comments")
    public void getActiveCommentsStatus200() throws Exception {
       String endpoint = buildActiveCommentsEndpoint(124,0,20);

        try (Response response = sendGetRequest(endpoint)) {
            assertThat(response.code()).isEqualTo(200);

            if (response.body() != null) {
                System.out.println(response.body().string());
            }
        }
    }

    // ============================================================
    // 2. Response has page array
    // ============================================================
    @Test
    @DisplayName("Response contains 'page' array (using Gson)")
    void shouldContainPageArray() throws Exception {
        String endpoint = buildActiveCommentsEndpoint(124, 0, 2);

        try (Response response = sendGetRequest(endpoint)) {
            assertThat(response.code()).isEqualTo(200);

            String responseBody = response.body().string();
            JsonObject json = gson.fromJson(responseBody, JsonObject.class);
            JsonArray comments = json.getAsJsonArray("page");

            assertThat(comments).isNotNull();
            assertThat(comments.size()).isGreaterThan(0);

            System.out.println("Comments count: " + comments.size());
        }
    }
    // ============================================================
    // 3. Each comment contains required fields
    // ============================================================
    @Test
    @DisplayName("Each comment contains required fields")
    void eachCommentHasRequiredFields() throws Exception {
        String endpoint = buildActiveCommentsEndpoint(124, 0, 2);

        try (Response response = sendGetRequest(endpoint)) {
            String responseBody = response.body().string();
            JsonObject json = gson.fromJson(responseBody, JsonObject.class);
            JsonArray comments = json.getAsJsonArray("page");

            for (JsonElement element : comments) {
                JsonObject comment = element.getAsJsonObject();

                assertThat(comment.has("id")).isTrue();
                assertThat(comment.has("createdDate")).isTrue();
                assertThat(comment.has("modifiedDate")).isTrue();
                assertThat(comment.has("author")).isTrue();
                assertThat(comment.has("parentCommentId")).isTrue();
                assertThat(comment.has("text")).isTrue();
                assertThat(comment.has("replies")).isTrue();
                assertThat(comment.has("likes")).isTrue();
                assertThat(comment.has("dislikes")).isTrue();
                assertThat(comment.has("currentUserLiked")).isTrue();
                assertThat(comment.has("currentUserDisliked")).isTrue();
                assertThat(comment.has("status")).isTrue();
                assertThat(comment.has("additionalImages")).isTrue();

                JsonObject author = comment.getAsJsonObject("author");
                assertThat(author.has("id")).isTrue();
                assertThat(author.has("name")).isTrue();
                assertThat(author.has("profilePicturePath")).isTrue();
            }
        }
    }

    // ============================================================
    // 4. Verifying that comment text is not empty
    // ============================================================
    @Test
    @DisplayName("Comment text is not empty")
    void commentTextNotEmpty() throws Exception {
        String endpoint = buildActiveCommentsEndpoint(124, 0, 2);

        try (Response response = sendGetRequest(endpoint)) {
            String responseBody = response.body().string();
            JsonObject json = gson.fromJson(responseBody, JsonObject.class);
            JsonArray comments = json.getAsJsonArray("page");

            for (JsonElement element : comments) {
                String text = element.getAsJsonObject().get("text").getAsString();
                assertThat(text).isNotNull();
                assertThat(text).isNotEmpty();
            }
        }
    }

    // ============================================================
    // 5. Replies, likes, dislikes are numbers
    // ============================================================
    @Test
    @DisplayName("Replies, likes, dislikes are numeric")
    void repliesLikesDislikesAreNumbers() throws Exception {
        String endpoint = buildActiveCommentsEndpoint(124, 0, 2);

        try (Response response = sendGetRequest(endpoint)) {
            String responseBody = response.body().string();
            JsonObject json = gson.fromJson(responseBody, JsonObject.class);
            JsonArray comments = json.getAsJsonArray("page");

            for (JsonElement element : comments) {
                JsonObject comment = element.getAsJsonObject();

                assertThat(comment.get("replies").isJsonPrimitive()).isTrue();
                assertThat(comment.get("replies").getAsJsonPrimitive().isNumber()).isTrue();

                assertThat(comment.get("likes").isJsonPrimitive()).isTrue();
                assertThat(comment.get("likes").getAsJsonPrimitive().isNumber()).isTrue();

                assertThat(comment.get("dislikes").isJsonPrimitive()).isTrue();
                assertThat(comment.get("dislikes").getAsJsonPrimitive().isNumber()).isTrue();
            }
        }
    }

    // ============================================================
    // 6. Status has valid value
    // ============================================================
    @Test
    @DisplayName("Status has valid value")
    void statusHasValidValue() throws Exception {
        String endpoint = buildActiveCommentsEndpoint(124, 0, 2);

        try (Response response = sendGetRequest(endpoint)) {
            String responseBody = response.body().string();
            JsonObject json = gson.fromJson(responseBody, JsonObject.class);
            JsonArray comments = json.getAsJsonArray("page");

            for (JsonElement element : comments) {
                String status = element.getAsJsonObject().get("status").getAsString();
                assertThat(status).isIn("ORIGINAL", "REPLY", "DELETED", "EDITED");
            }
        }
    }

    // ============================================================
    // 7. Pagination fields exist
    // ============================================================
    @Test
    @DisplayName("Pagination fields are present")
    void paginationFieldsPresent() throws Exception {
        String endpoint = buildActiveCommentsEndpoint(124, 0, 2);

        try (Response response = sendGetRequest(endpoint)) {
            String responseBody = response.body().string();
            JsonObject json = gson.fromJson(responseBody, JsonObject.class);

            assertThat(json.has("totalElements")).isTrue();
            assertThat(json.has("currentPage")).isTrue();
            assertThat(json.has("totalPages")).isTrue();
        }
    }


}
