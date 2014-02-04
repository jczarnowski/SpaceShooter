package com.jcf.spaceshooter.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.res.AssetManager;
import android.os.Environment;

/*
 * File input/output class. Allows reading raw assets
 * and reading/writing file from SD card
 */
public class FileIO {
	AssetManager assets;
	String externalStoragePath;
	
	public FileIO(AssetManager assets) {
		this.assets = assets;
		this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
	}
	
	public InputStream readAsset(String fileName) throws IOException {
		return assets.open(fileName);
	}
	
	public InputStream readFile(String fileName) throws IOException {
		return new FileInputStream(externalStoragePath + fileName);
	}
	
	public OutputStream writeFile(String fileName) throws IOException {
		return new FileOutputStream(externalStoragePath + fileName);
	}
}
