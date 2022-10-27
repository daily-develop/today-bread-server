package com.github.org.todaybread.todaybread.auth.infra.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oauth")
public class OAuthController {

    @GetMapping("/redirect")
    String oauthRedirect() {
        return """
            <!doctype html>
            <html lang="kr">
            	<head>
            		<meta charset="UTF-8">
            		<meta
            			content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
            			name="viewport">
            		<meta content="ie=edge" http-equiv="X-UA-Compatible">
            		<title>오늘의 빵</title>

            		<style>
                  body {
                    display: flex;
                    width: 100vw;
                    height: 100vh;
                    align-items: center;
                    justify-content: center;
                  }

                  .logo {
                    font-weight: 600;
                    font-size: 32px;
                  }
            		</style>
            	</head>
            	<body>
            		<span class="logo">오늘의 빵</span>
            	</body>
            </html>
                        """;
    }
}
