package com.jcode_development.magalums.swagger;

import com.jcode_development.magalums.model.notification.NotificationRequest;
import com.jcode_development.magalums.model.notification.NotificationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.Set;

@Tag(name = "Notification Management", description = "Controller for managing notifications")
public interface NotificationDocumentation {

    @Operation(
            summary = "Create notification",
            description = "Save a new notification with JSON or XML payload",
            responses = {
                    @ApiResponse(description = "Accepted", responseCode = "202", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "422", content = @Content()),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content())
            }
    )
    ResponseEntity<String> save(NotificationRequest data);

    @Operation(
            summary = "Get all notifications",
            description = "Retrieve a list of all notifications",
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = NotificationResponse.class))
                                    ),
                                    @Content(
                                            mediaType = "application/xml",
                                            array = @ArraySchema(schema = @Schema(implementation = NotificationResponse.class))
                                    )
                            }
                    ),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content())
            }
    )
    ResponseEntity<Set<NotificationResponse>> getNotifications();

    @Operation(
            summary = "Find notification by ID",
            description = "Retrieve a single notification by its ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = NotificationResponse.class)
                                    ),
                                    @Content(
                                            mediaType = "application/xml",
                                            schema = @Schema(implementation = NotificationResponse.class)
                                    )
                            }
                    ),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content())
            }
    )
    ResponseEntity<NotificationResponse> getNotification(String id);

    @Operation(
            summary = "cancel notification",
            description = "cancel notification based on the ID provided by the user",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content()),
            }
    )
    ResponseEntity<String> cancel(String id);
}
