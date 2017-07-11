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
    @Headers("Authorization: OAuth EAAbTsoQiiocBABWr5GKQ8HVsgKTUHlGd3tDXDnCs73uK18tVCw5kHchJyNuZCCniQSvZAAcakGhqx7mdumZADF61DqMcdOipt0nwEPKa9owolI6nNZAop1DtnZBWcDWUKQnp1FCCInE9xQ94CJnXypfAIGu6995AYcoJaS6ZCwREVznY02OYNM1RQ9MCJYpw0ZD")
    @GET("/647034035350714/events")
    Call<Events> getEvents();
}
