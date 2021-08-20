package com.woa.tutorial;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.woa.R;
import com.woa.databinding.ActivityTutorialFullVideoPlayerBinding;

import java.util.Objects;

public class TutorialFullVideoPlayerActivity extends AppCompatActivity {

     ActivityTutorialFullVideoPlayerBinding binding;
     SimpleExoPlayer simpleExoPlayer;
     String url;
     KProgressHUD loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityTutorialFullVideoPlayerBinding.inflate(getLayoutInflater());

        setTitle("Tutorial Video");
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_outline_arrow_back);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loading = KProgressHUD.create(TutorialFullVideoPlayerActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setDetailsLabel("Loading")
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);
        loading.show();


        // get from customer Activity
        Bundle bundle= getIntent().getExtras();
        if (bundle !=null)
        {
            url   = bundle.getString("url");
            try {
                BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
                TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));

                simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

                Uri uri = Uri.parse(url);

                DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
                ExtractorsFactory factory = new DefaultExtractorsFactory();

//            MediaSource mediaSource = new HlsMediaSource(Uri.parse("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8"),
//                    mediaDataSourceFactory, mainHandler, null);*/

                MediaSource mediaSource = new ExtractorMediaSource(uri, defaultHttpDataSourceFactory, factory, null, null);
                binding.exoPlayer.setPlayer(simpleExoPlayer);
                simpleExoPlayer.prepare(mediaSource);
                simpleExoPlayer.setPlayWhenReady(true);
                binding.exoPlayer.setKeepScreenOn(true);
                simpleExoPlayer.addListener(new ExoPlayer.EventListener() {
                    @Override
                    public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {

                    }

                    @Override
                    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

                    }

                    @Override
                    public void onLoadingChanged(boolean isLoading) {

                    }

                    @Override
                    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                        if (playbackState == ExoPlayer.STATE_BUFFERING) {
                            loading.show();
                        } else {
                            loading.dismiss();
                        }

                    }

                    @Override
                    public void onRepeatModeChanged(int repeatMode) {

                    }

                    @Override
                    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

                    }

                    @Override
                    public void onPlayerError(ExoPlaybackException error) {

                    }

                    @Override
                    public void onPositionDiscontinuity(int reason) {

                    }

                    @Override
                    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

                    }

                    @Override
                    public void onSeekProcessed() {

                    }
                });



            }catch (Exception ignored){

            }

        }


    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        simpleExoPlayer.setPlayWhenReady(false);
        simpleExoPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        simpleExoPlayer.setPlayWhenReady(false);
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        // Handle item selection
        if (itemId == android.R.id.home) {
            this.finish();
            return true;
        } else return super.onOptionsItemSelected(item);
    }
}