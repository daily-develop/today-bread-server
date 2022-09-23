package com.github.org.todaybread.todaybread.auth.domain.token;

public interface TokenRepository {

	Token save(String key, Token token, Long timeToLive);

	void delete(String key);

	Token getByKey(String key);
}
