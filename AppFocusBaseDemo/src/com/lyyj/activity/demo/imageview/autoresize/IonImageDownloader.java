/*******************************************************************************
 * Copyright 2011-2015 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.lyyj.activity.demo.imageview.autoresize;

import android.content.Context;

import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

/**
 * Implementation of ImageDownloader which uses {@link com.squareup.okhttp.OkHttpClient} for image stream retrieving.
 *
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class IonImageDownloader extends BaseImageDownloader {

  private Context mContext ;

	public IonImageDownloader(Context context ) {
		super(context);
		mContext = context;
 
	}

	@Override
	protected InputStream getStreamFromNetwork(String imageUri, Object extra) throws IOException {
		try {
			return Ion.with(mContext)
			.load(imageUri)
			.asInputStream().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
	}
}
