package com.example.warelineassignment.glide;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;


@GlideModule
public final class MyAppGlideModule extends AppGlideModule {

    @Override
    public void applyOptions( Context context,  GlideBuilder builder) {
        // Caching here
        int memoryCachingSizeBytes = 1024*1024*20;
        builder.setMemoryCache(new LruResourceCache(memoryCachingSizeBytes));

        int diskCachingSizeBytes = 1024 * 1024* 100;
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,diskCachingSizeBytes));
    }
}
