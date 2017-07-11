package com.example.user.elpaso;

import java.util.Date;
import java.util.List;

/**
 * Created by Faruk Ljuca <faruk.ljuca@atlantbh.com>
 * for Atlantbh
 * on 11. July 2017.
 */

public class Events {
    public List<Data> data;

    static class Data {
        public long id;
        public String name;
        public String description;
        public Date start_time;
        public Place place;
    }

    static class Place {
        public String name;
    }
}
