package com.fileupload.file.upload;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
@Service
public class Utils {

    public static byte[] compressImage(byte[] data){
        Deflater deflater=new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream(data.length);
        byte[] temp=new byte[4*1024];
        while (!deflater.finished()){
            int size=deflater.deflate(temp);
            outputStream.write(temp,0,size);
        }
        try{
            outputStream.close();
        }
        catch (Exception ignored){
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) throws DataFormatException {
        Inflater inflater=new Inflater();
        inflater.setInput(data);

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream(data.length);
        byte[] temp=new byte[4*1024];
        while (!inflater.finished()){
            int size=inflater.inflate(temp);
            outputStream.write(temp,0,size);
        }
        try{
            outputStream.close();
        }
        catch (Exception ignored){
        }
        return outputStream.toByteArray();
    }
}
