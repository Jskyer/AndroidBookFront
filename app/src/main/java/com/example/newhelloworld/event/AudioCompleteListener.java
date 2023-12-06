package com.example.newhelloworld.event;

import com.example.newhelloworld.model.Episode;

public interface AudioCompleteListener {
    void onCompleteForNext(Episode episode);
}
