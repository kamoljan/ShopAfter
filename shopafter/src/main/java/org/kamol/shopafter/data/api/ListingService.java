package org.kamol.shopafter.data.api;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

import org.kamol.shopafter.data.Sort;
import org.kamol.shopafter.data.api.model.Listing;

public interface ListingService {
  @GET("/listing/{category}/{limit}/{sort}") //
  Observable<Listing> listGallery(
      @Path("category") int category,
      @Path("limit") int limit,
      @Path("sort") Sort sort
  );
}
