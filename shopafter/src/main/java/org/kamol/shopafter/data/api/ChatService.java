package org.kamol.shopafter.data.api;

import retrofit.Callback;
import retrofit.http.PUT;
import retrofit.http.Path;

import org.kamol.shopafter.data.api.model.Chat;

public interface ChatService {
  @PUT("/chat/{ad}/{profile}") void putChat(
      @Path("ad") String ad,
      @Path("profile") String profile,
      Callback<Chat> cb
  );
}
