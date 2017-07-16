package com.example.user.elpaso;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Faruk Ljuca <faruk.ljuca@atlantbh.com>
 * for Atlantbh
 * on 11. July 2017.
 */

public interface Api {
    @Headers("Authorization: OAuth EAAbTsoQiiocBAGy7W33dsLktijW8DHyM6LZBIdk0xdLkzdsUKq9y5JSzY0j6BXxfGZAoMh77OC2HAQO5F2F6k5AiiJj52XhUNf1AnsGlK3WsAK5EYZADcJExy2uOPWYFFYRw6jceIHU1oks9U5LWZAZCQHsZAw6hoZD")
    @GET("/647034035350714/events")
    Call<Events> getEvents();
}
