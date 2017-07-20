package com.example.user.elpaso;

import java.util.List;

/**
 * Created by Faruk Ljuca <faruk.ljuca@atlantbh.com>
 * for Atlantbh
 * on 20. July 2017.
 */

public class Photos {
    public List<PhotoData> data;

    static class PhotoData {
        public String source;
    }

    static class Photo {
        public String picture;
    }
}
