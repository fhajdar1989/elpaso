package com.example.user.elpaso;

import java.util.List;

/**
 * Created by Faruk Ljuca <faruk.ljuca@atlantbh.com>
 * for Atlantbh
 * on 20. July 2017.
 */

class Album {
    public List<SingleAlbum> data;

    static class SingleAlbum {
        public String id;
        public CoverPhoto cover_photo;
        public String name;
    }

    static class CoverPhoto {
        public String id;
    }
}
