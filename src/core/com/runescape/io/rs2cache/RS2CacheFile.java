package com.runescape.io.rs2cache;

public interface RS2CacheFile {
    public int getIndexId();

    public int getId();

    public byte[] getData();

    public byte[] getData(int offset);

    public byte[][] getArchivedFiles();
}
