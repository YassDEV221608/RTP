/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TransmitRTP;


/**
 *
 * @author slixo
 */


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.CannotRealizeException;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Controller;
import javax.media.DataSink;
import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoDataSinkException;
import javax.media.NoDataSourceException;
import javax.media.NoPlayerException;
import javax.media.NoProcessorException;
import javax.media.NotRealizedError;
import javax.media.Player;
import javax.media.Processor;
import javax.media.ProcessorModel;
import javax.media.control.FormatControl;
import javax.media.control.TrackControl;
import javax.media.format.AudioFormat;
import javax.media.format.VideoFormat;
import javax.media.protocol.ContentDescriptor;
import javax.media.protocol.DataSource;

public class TransmitRTP {
    
     //media formats needed to build RTP stream
   static final Format[] FORMATS = new Format[] {new AudioFormat(AudioFormat.ULAW_RTP)}; //Ulaw_RTP
   static final ContentDescriptor CONTENT_DESCRIPTOR =new ContentDescriptor(ContentDescriptor.RAW_RTP);

    
    public static void main(String[] args) throws MalformedURLException, IOException, NoDataSourceException, NoProcessorException, CannotRealizeException, NoDataSinkException {

        // media source = microphone
        MediaLocator locator = new MediaLocator("javasound://8000");
        
        // creating a source that will be used in creating the processor
        DataSource source = Manager.createDataSource(locator);
        
        //creating the processor form the source and formats that we want (RTP) 
        Processor mediaProcessor = Manager.createRealizedProcessor( new ProcessorModel(source, FORMATS, CONTENT_DESCRIPTOR));
      
        
        // start the processor
        mediaProcessor.start();
        
        // this is the output medialocator : ip, port and data type  //to 
        MediaLocator outputMediaLocator = new MediaLocator("rtp://127.0.0.1:10000/audio/1");

        // now , we are creating a datasink from the processor's output datasource and send it to output locator
        DataSink dataSink = Manager.createDataSink(mediaProcessor.getDataOutput(),outputMediaLocator);
        
         
        
        //open connection
        dataSink.open();
        
        //start streaming the RTP data
        dataSink.start();
        
        System.out.println("Transmiting...");

    }
    
    
}
