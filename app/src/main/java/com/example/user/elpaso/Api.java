package com.example.user.elpaso;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Faruk Ljuca <faruk.ljuca@atlantbh.com>
 * for Atlantbh
 * on 11. July 2017.
 */

public interface Api {
    String AUTH = "Authorization: OAuth EAAbTsoQiiocBAGy7W33dsLktijW8DHyM6LZBIdk0xdLkzdsUKq9y5JSzY0j6BXxfGZAoMh77OC2HAQO5F2F6k5AiiJj52XhUNf1AnsGlK3WsAK5EYZADcJExy2uOPWYFFYRw6jceIHU1oks9U5LWZAZCQHsZAw6hoZD";


    @Headers(AUTH)
    @GET("/647034035350714/events")
    Call<Events> getEvents();

    @Headers(AUTH)
    @GET("/647034035350714/albums?fields=cover_photo,name")
    Call<Album> getAlbums();

    @Headers(AUTH)
    @GET("/{photoId}?fields=picture")
    Call<Photos.Photo> getPhoto(@Path("photoId") String photoId);

    @Headers(AUTH)
    @GET("/{albumId}/photos?fields=source")
    Call<Photos> getPhotos(@Path("albumId") String albumId);

    @Headers(AUTH)
    @GET("/youtube/v3/playlistItems?playlistId=PL8j4JX89AHoBq882qg-NlnM21x5PDgAlX&maxResults=25&part=snippet,contentDetails&key=AIzaSyCegm8JrysQjsMRsIWn1_WNdjqHkrmxcII")
    Call<Videos> getVideos();
}
