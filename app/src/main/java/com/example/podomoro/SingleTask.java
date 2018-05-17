package com.example.podomoro;

import java.util.UUID;

/**
 * Created by cym on 1/24/18.
 */

public class SingleTask {
    private UUID mId;
    private String mTitle;
    private String mContent;
    private int mRemain;
    private boolean mSolved;

    public SingleTask() {
        this(UUID.randomUUID());
    }

    public SingleTask(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }


    public int getRemain() {
        return mRemain;
    }

    public void setRemain(int remain) {
        mRemain = remain;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
