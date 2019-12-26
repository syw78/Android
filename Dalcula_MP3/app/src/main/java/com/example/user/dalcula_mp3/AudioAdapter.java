package com.example.user.dalcula_mp3;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class AudioAdapter extends CursorRecyclerViewAdapter<RecyclerView.ViewHolder> {

    public AudioAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        AudioData audioData = AudioData.bindCursor(cursor);
        ((AudioViewHolder) viewHolder).setAudioItem(audioData, cursor.getPosition());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new AudioViewHolder(v);
    }

    public static class AudioData {
        public long mId; // 오디오 고유 ID
        public long mAlbumId; // 오디오 앨범아트 ID
        public String mTitle; // 타이틀 정보
        public String mArtist; // 아티스트 정보
        public String mAlbum; // 앨범 정보
        public long mDuration; // 재생시간
        public String mDataPath; // 실제 데이터위치

        public static AudioData bindCursor(Cursor cursor) {
            AudioData audioData = new AudioData();
            audioData.mId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.AudioColumns._ID));
            audioData.mAlbumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM_ID));
            audioData.mTitle = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE));
            audioData.mArtist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST));
            audioData.mAlbum = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM));
            audioData.mDuration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION));
            audioData.mDataPath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA));
            return audioData;
        }
    }

    private class AudioViewHolder extends RecyclerView.ViewHolder {
        private final Uri artworkUri = Uri.parse("content://media/external/audio/albumart");
        private ImageView mImgAlbumArt;
        private TextView mTxtTitle;
        private TextView mTxtSubTitle;
        private TextView mTxtDuration;
        private AudioData mItem;
        private int mPosition;

        private AudioViewHolder(View view) {
            super(view);
            mImgAlbumArt = (ImageView) view.findViewById(R.id.img_albumart);
            mTxtTitle = (TextView) view.findViewById(R.id.txt_title);
            mTxtSubTitle = (TextView) view.findViewById(R.id.txt_sub_title);
            mTxtDuration = (TextView) view.findViewById(R.id.txt_duration);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // ..
                }
            });
        }

        public void setAudioItem(AudioData item, int position) {
            mItem = item;
            mPosition = position;
            mTxtTitle.setText(item.mTitle);
            mTxtSubTitle.setText(item.mArtist + "(" + item.mAlbum + ")");
            mTxtDuration.setText(DateFormat.format("mm:ss", item.mDuration));
            Uri albumArtUri = ContentUris.withAppendedId(artworkUri, item.mAlbumId);
            Picasso.with(itemView.getContext()).load(albumArtUri).error(R.drawable.music).into(mImgAlbumArt);
        }
    }
}